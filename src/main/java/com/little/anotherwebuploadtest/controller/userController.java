package com.little.anotherwebuploadtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.anotherwebuploadtest.bean.movieImfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luo x
 * @date 2020-12-14 15:44
 * 用户的个人操作
 */

@Controller
@RequestMapping("/user")
public class userController {


    //用户退出
    @RequestMapping("/quit")
    public String loadQuit(HttpSession session){
        session.removeAttribute("loginUser");
        session.removeAttribute("userid");
        session.removeAttribute("githubUser");
        return "redirect:/panel.html";

    }


    //用户vip电影
    @Autowired
    com.little.anotherwebuploadtest.mapper.movieMapper movieMapper;



    @RequestMapping("/getvipMovie")
    public String getvipMovie(@RequestParam(value = "pn",defaultValue = "1") Integer currpage,
                                          @RequestParam(value = "type",defaultValue = "") String type,
                                          Model model){
        PageHelper.startPage(currpage,20);
        List<movieImfo> list=new ArrayList<>();
        if(type.equals("")){
            list =movieMapper.getvipMovie();
        }else{
            list =movieMapper.getvipMovieType(type);
        }
        PageInfo<movieImfo> pageinfo=new PageInfo<>(list);
        model.addAttribute("movie",pageinfo);
        return "video/vipmovie";
    }

}
