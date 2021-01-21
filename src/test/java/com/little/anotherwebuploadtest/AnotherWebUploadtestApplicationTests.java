package com.little.anotherwebuploadtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class AnotherWebUploadtestApplicationTests {

    @Test
    void contextLoads() {
        String data="[{\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51989/index.m3u8\", \"name\": \"第01集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51990/index.m3u8\", \"name\": \"第02集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51991/index.m3u8\", \"name\": \"第03集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51992/index.m3u8\", \"name\": \"第04集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51993/index.m3u8\", \"name\": \"第05集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51994/index.m3u8\", \"name\": \"第06集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51995/index.m3u8\", \"name\": \"第07集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51996/index.m3u8\", \"name\": \"第08集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51997/index.m3u8\", \"name\": \"第09集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51998/index.m3u8\", \"name\": \"第10集\"}, {\"url\": \"https://cdn-iqiyi-com.diudie.com/series/51999/index.m3u8\", \"name\": \"第11集\"}]\n";
        data=data.replace("[","");
        data=data.replace("]","");
        String[] movieData=data.split("},");
        List<JSON> list=new LinkedList<>();
        for(int i=0;i<movieData.length;i++){
            if(movieData[i].indexOf('}')<0){
                movieData[i]+="}";
            }
            movieData[i]=movieData[i].trim();
            JSONObject json=JSONObject.parseObject(movieData[i]);
            list.add(i,json);
        }
        System.out.println(list);

    }

}
