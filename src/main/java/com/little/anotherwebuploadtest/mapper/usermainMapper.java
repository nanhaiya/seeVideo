package com.little.anotherwebuploadtest.mapper;

import com.little.anotherwebuploadtest.bean.GithubUser;
import com.little.anotherwebuploadtest.bean.usermainimfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface usermainMapper {

    //通过id获取用户user
    @Select("select user from usermainimfo where id=#{id}")
    public String getusername(int id);


    @Select("select githubInsert(#{id},#{name})")
    public int ghUserIsRe(GithubUser githubUser);
}
