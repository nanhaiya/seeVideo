package com.little.anotherwebuploadtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//指定包下均为mapper接口
@MapperScan(value = "com.little.anotherwebuploadtest.mapper")
@SpringBootApplication
public class AnotherWebUploadtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotherWebUploadtestApplication.class, args);
    }

}
