package com.little.anotherwebuploadtest.controller;

import com.little.anotherwebuploadtest.bean.GithubUser;
import com.little.anotherwebuploadtest.bean.usermainimfo;
import com.little.anotherwebuploadtest.mapper.loginMapper;
import com.little.anotherwebuploadtest.tool.tool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

/**
 * @author luo x
 * @date 2020-12-20 10:13
 */

@Controller
@RequestMapping("/regist")
public class registController {

    @Autowired
    loginMapper loginMapper;

    @Autowired
    tool tool;


    //跳转注册
    @RequestMapping("/Regist")
    public String userRegist(){
        return "user/userregist";
    }

    //判断是否存在用户
    @RequestMapping("/iscun")
    @ResponseBody
    public int ifuserCun(@RequestParam("username") String username){
        return loginMapper.ifuserCun(username);
    }


    //判断邮箱是否被绑定
    @RequestMapping("/flogMailBadng")
    @ResponseBody
    public int flogMailBadng(@RequestParam("mail") String mail){
        return loginMapper.flogMailBadng(mail);
    }


    //获取邮箱验证码
    @RequestMapping("/sendmail")
    @ResponseBody
    public int sendmail(@RequestParam("sendto") String sendto,
                        HttpSession session) throws MessagingException {
        int flagsend=1;
       // String tittle,String value,String sendto
        String title="【测试程序】注册验证";
        Random rd=new Random();
        int index=rd.nextInt(899999)+100000;
        String value="【测试程序】：您的注册邮箱验证码为："+index;
        try {
            tool.sengmail(title,value,sendto);
        }catch (MessagingException e){
            System.out.println("发送失败");
            flagsend=0;
        }
        if (flagsend==1){
            session.setAttribute("mialCode",index);
        }
        return flagsend;
    }

    //返回验证码
    @RequestMapping("/getCode")
    @ResponseBody
    public int getCode(HttpSession session){
        return (int)session.getAttribute("mialCode");
    }


    //注册账户
    @RequestMapping("/registUser")
    public String registUser(@RequestParam("username") String username,
                             @RequestParam("pwd") String pwd,
                             @RequestParam("mail") String mail,
                             HttpSession session,
                             Map<String,Object> map){
        usermainimfo user=new usermainimfo();
        user.setUser(username);
        user.setPwd(pwd);
        user.setMail(mail);
        int i=loginMapper.registUser(username,pwd,mail);
        //将github账号绑定到当前账号
        if(session.getAttribute("githubUser")!=null){
            user.setGithubBangId((int)session.getAttribute("githubUser"));
            loginMapper.bangGithub(user);
        }
        //qq、微信


        if(i==1){
            session.setAttribute("loginUser",user.getUser());
            session.setAttribute("userid",user.getId());

            return "redirect:/panel.html";
            //添加成功
        }else{
            //添加失败
            map.put("message","注册失败，请重试。");
            return "login";
        }
    }


}
