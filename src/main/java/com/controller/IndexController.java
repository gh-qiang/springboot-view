package com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.User;
import com.service.UserSercive;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Autowired
    private UserSercive userSercive;

    public UserSercive getUserSercive() {
        return userSercive;
    }

    public void setUserSercive(UserSercive userSercive) {
        this.userSercive = userSercive;
    }

    @RequestMapping("login")
    public String index(HttpServletRequest request) {
        request.setAttribute("name", "pp");
        return "login";
    }

    @RequestMapping("handle")
    public String login(String uname, String password) {
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(uname, password);
        try {
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {
                return "index";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        return "login";
    }

    @RequestMapping("addUser")
    public String add() {
        return "add";
    }

    @RequestMapping("sub")
    public String sub(String uname, String password) {
        userSercive.addUser(uname, password);
        return "index";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("page")
    public String page(@RequestParam(defaultValue = "1") Integer startPage, Model model) {
        //startPage适用当前线程或重新开启线程，用于处理接下来查询的数据集合们，跟据设定的野马的每页显示的的数量
        //直接将查询的集合设定为page类型
        PageHelper.startPage(startPage, 6);
        List<User> userList = userSercive.selectUser();
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        model.addAttribute("userPageInfo", userPageInfo);

        return "page";
    }


}