package com.service;

import com.mapper.UserMapper;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerciveImp implements UserSercive {
    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(String uname, String password) {
        userMapper.addUser(uname,password);
    }
    public List<User> selectUser(){
        return userMapper.selectUser();
    }
    public String getUserByUname(String uname){
        return userMapper.getUserByUname(uname);
    } public String getRoleByUname(String uname){
        return userMapper.getRoleByUname(uname);
    }

}
