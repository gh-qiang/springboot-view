package com.mapper;

import com.pojo.User;

import java.util.List;

public interface UserMapper {
    public void addUser(String uname,String password);
    public List<User> selectUser();
    public String getUserByUname(String uname);
    public String getRoleByUname(String uname);
}
