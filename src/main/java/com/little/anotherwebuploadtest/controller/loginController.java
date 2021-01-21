package com.little.anotherwebuploadtest.controller;

import com.little.anotherwebuploadtest.Repositry.userImfoRepositry;

import com.little.anotherwebuploadtest.bean.GithubUser;
import com.little.anotherwebuploadtest.bean.githubAccses;
import com.little.anotherwebuploadtest.bean.usermainimfo;
import com.little.anotherwebuploadtest.mapper.loginMapper;
import com.little.anotherwebuploadtest.mapper.usermainMapper;
import com.little.anotherwebuploadtest.privider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luo x
 * @date 2020-12-13 10:15
 */

@Controller
public class loginController {

    @Autowired
    userImfoRepositry userImfoRepositry;


    @Autowired
    usermainMapper usermainMapper;

    @Autowired
    loginMapper loginMapper;

    //87a303b5737e6c71efb9afbb0d96ebbe6ad72c61

    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String id;

    @Value("${github.client.secret}")
    private String secrect;

    @Value("${github.redirect.url}")
    private String url;

    //未登陆到登陆界面
    @RequestMapping("/tologin")
    public String Tologin(){
        return "login";
    }

    //已登陆到用户信息界面
    @RequestMapping("/user/imfo")
    public String ToUserImfo(){
        return "user/userImfo";
    }

    //以github账户登陆
    //http://localhost:8080/callback?code=d8cd9642b167b709ee41&state=1
    @RequestMapping("/callback")
    public String githubCallback(@RequestParam(name="code") String code,
                                 @RequestParam(name = "state") String state,
                                 HttpSession session, Map<String,Object> map,
                                 Model model){

        githubAccses githubAccses=new githubAccses();
        githubAccses.setCode(code);
        githubAccses.setState(state);

        githubAccses.setClient_id(id);
        githubAccses.setRedirect_uri(url);
        githubAccses.setClient_secret(secrect);

        String token=githubProvider.getAccessTocken(githubAccses);
        GithubUser githubUser=githubProvider.getUser(token);


//       这里需要添加登录的github是否注册账号

        if(githubUser.getName()!=null){
            //登陆成功,防止表单重复提交，可以重定向到主页
            //判断此github账号是否存在
            int userId=usermainMapper.ghUserIsRe(githubUser);
            session.setAttribute("githubUser",githubUser.getId());
            session.setAttribute("githubid",githubUser.getId());
            if (userId==-1){
                map.put("code",-1);
                map.put("message","未注册平台账户，请先注册账户");
                return "login";
            }else{
                String username = usermainMapper.getusername(userId);
                session.setAttribute("loginUser",username);
                session.setAttribute("userid",userId);
                map.put("code",1);
                map.put("message","登陆成功");
                return "redirect:/panel.html";
            }
        }else{
            map.put("code",1);
            map.put("message","登录失败，请重试。");
            return "login";
        }
    }

    //重定向显示到主界面
    @RequestMapping("/panel.html")
    public String redirectToMain(Model model){
        return "main";
    }


    //用户登陆
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> userLogin(@RequestParam("name") String username,
            @RequestParam("pwd") String pwd,
            HttpSession session)  {
        usermainimfo usermainimfo = new usermainimfo();
        Map<String,Object> map=new HashMap<>();
        //登陆判断
        //是否为邮箱
        int i=-1;   //是否登陆成功
        if(username.indexOf("@")>0&&username.indexOf(".")>0&username.indexOf("@")<username.indexOf(".")){
            //邮箱登陆
            i=loginMapper.userLogin("mail",username,pwd);
            if (i==1){
                usermainimfo=loginMapper.getuserImfo("mail",username,pwd);
            }
        }else{
            //判断是否存在字母
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(username);
            if(isNum.matches()){
                //id登陆
                i=loginMapper.userLogin("id",username,pwd);
                if (i==1){
                    usermainimfo=loginMapper.getuserImfo("id",username,pwd);
                }
            }else{
                //用户名登陆
                i=loginMapper.userLogin("user",username,pwd);
                if (i==1){
                    usermainimfo=loginMapper.getuserImfo("user",username,pwd);
                }
            }
        }
        if (i==1){
            map.put("code",i);
            map.put("message","登陆成功！");
            session.setAttribute("userid",usermainimfo.getId());
            session.setAttribute("loginUser",usermainimfo.getUser());
        }else{
            map.put("code",i);
            map.put("message","账户或密码错误！");
        }
        return map;

    }



}
