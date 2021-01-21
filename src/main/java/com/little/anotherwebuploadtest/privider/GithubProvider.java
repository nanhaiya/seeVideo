package com.little.anotherwebuploadtest.privider;

import com.alibaba.fastjson.JSON;
import com.little.anotherwebuploadtest.bean.GithubUser;
import com.little.anotherwebuploadtest.bean.githubAccses;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author luo x
 * @date 2020-12-13 13:35
 */

@Component
public class GithubProvider {
    public void githubLoad(){
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/authorize?client_id=613eb62c9cbcfb5cda5b&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getAccessTocken(githubAccses githubAccses){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(githubAccses));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //access_token=d58a7f838096c7b335dee94305d25d397f41477e&scope=user&token_type=bearer
            String token=string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
