package com.little.anotherwebuploadtest.mapper;

import com.little.anotherwebuploadtest.bean.movieImfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface movieMapper {

//    通过传入参数获取信息（最新电影或者类型）
    public List<movieImfo> getMovie(@RequestParam("type") String type);

    public List<movieImfo> getMovieByName(@RequestParam("movieName") String movieName);

    public movieImfo getImfoById(@RequestParam("movieid") String movieid);

    public List<movieImfo> getvipMovie();

    public List<movieImfo> getvipMovieType(@RequestParam("type") String type);

}
