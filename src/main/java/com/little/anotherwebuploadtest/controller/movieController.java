package com.little.anotherwebuploadtest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.anotherwebuploadtest.bean.movieImfo;
import com.little.anotherwebuploadtest.bean.movieUrl;
import com.little.anotherwebuploadtest.mapper.movieMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;
import java.util.*;

/**
 * @author luo x
 * @date 2021-01-04 10:11
 */

@Controller
@RequestMapping("/movie")
public class movieController {

    @Autowired
    movieMapper movieMapper;

    @RequestMapping("/getMovie")
    @ResponseBody
    public Map<String,Object> getMoive(@RequestParam("type") String type,
                         @RequestParam(value = "pn",defaultValue = "1") Integer currpage
                         ){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currpage,20);
        List<movieImfo> list =movieMapper.getMovie(type);
        PageInfo<movieImfo> pageinfo=new PageInfo<>(list);
        map.put("pageinfo",pageinfo);
        return map;
    }

//    通过电影名模糊查询
    @RequestMapping("/getMovieByName")
    @ResponseBody
    public Map<String,Object> getMovieByName(@RequestParam("movieName") String movieName,
                                       @RequestParam(value = "pn",defaultValue = "1") Integer currpage
    ){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currpage,20);
        List<movieImfo> list =movieMapper.getMovieByName(movieName);
        PageInfo<movieImfo> pageinfo=new PageInfo<>(list);
        map.put("pageinfo",pageinfo);
        return map;
    }


    //电影详情页 getImfo
    @RequestMapping("/getImfo/{id}")
    public String getImfo(@PathVariable("id") String movieid,
                          Model model){
        movieImfo movieImfo=movieMapper.getImfoById(movieid);
        model.addAttribute("videoId",movieImfo.getVideoId());
        model.addAttribute("videoName",movieImfo.getVideoname());
        model.addAttribute("pic",movieImfo.getPic());
        model.addAttribute("type",movieImfo.getType());
        String data=movieImfo.getPlayUrl();
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(data, new TypeReference<LinkedHashMap<String, String>>() {
        });
        List<movieUrl> list=new ArrayList<>();
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            movieUrl movieUrl=new movieUrl((String)entry.getKey(),(String)entry.getValue());
            list.add(movieUrl);
        }
        model.addAttribute("movieUrl",list);
        return "video/videoImfo";
    }

    @RequestMapping("/playvideo")
    public String playvideo(@RequestParam("url") String url,
                            @RequestParam("name") String name,
                            Model model){
        model.addAttribute("url",url);
        model.addAttribute("name",name);
        return "video/videoPlay";
    }





}
