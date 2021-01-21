package com.little.anotherwebuploadtest.config;

import com.little.anotherwebuploadtest.component.loginHandlerInterceter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luo x
 * @date 2020-12-12 23:36
 */

@Configuration
public class mvcConfig {



    @Bean
    public WebMvcConfigurer WebMvcConfigurer(){

        WebMvcConfigurer adapter=new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

                registry.addViewController("/").setViewName("main");
                registry.addViewController("/index.html").setViewName("main");
                registry.addViewController("/panel.html").setViewName("main");
                registry.addViewController("/needLogin").setViewName("login");
                registry.addViewController("/test").setViewName("video/videoImfo");
            }

            //登录拦截器
            public void addInterceptors(InterceptorRegistry registry) {
                //注册拦截器
                //静态资源； *.css,*.js
                //SpringBoot已经做好了
                //拦截  放行
                registry.addInterceptor(new loginHandlerInterceter())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/login","/user/login","/hello");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**");
            }


        };



        return adapter;

    }


}
