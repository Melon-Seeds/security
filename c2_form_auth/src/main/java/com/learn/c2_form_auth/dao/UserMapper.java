package com.learn.c2_form_auth.dao;


import com.learn.c2_form_auth.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username= #{userName} ")
    public User findByUserName(String userName);
}
