package com.little.anotherwebuploadtest.mapper;

import com.little.anotherwebuploadtest.bean.usermainimfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

public interface loginMapper {

    //登陆判断
    //lei:传入类型id、mail、username
    public int userLogin(@Param("lei") String lei,@Param("username") String username,@Param("pwd") String pwd);

    //获取账户信息
    public usermainimfo getuserImfo(@Param("lei") String lei,@Param("username") String username,@Param("pwd") String pwd);

    //判断用户名是否存在
    public int ifuserCun(@Param("username") String username);

    //判定邮箱是否被绑定
    public int flogMailBadng(@Param("mail") String mail);

    //注册账户
    public int registUser(@Param("username") String username,@Param("pwd") String pwd,@Param("mail") String mail);

    //绑定github账户
    public void bangGithub(usermainimfo usermainimfo);



}
