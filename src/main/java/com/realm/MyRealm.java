package com.realm;

import com.service.UserSercive;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserSercive userSercive;

    public UserSercive getUserSercive() {
        return userSercive;
    }

    public void setUserSercive(UserSercive userSercive) {
        this.userSercive = userSercive;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String uname = (String) principalCollection.getPrimaryPrincipal();
        String role = userSercive.getRoleByUname(uname);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set set = new HashSet();
        set.add(role);
        simpleAuthorizationInfo.setRoles(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String uname = (String) authenticationToken.getPrincipal();
        String password = userSercive.getUserByUname(uname);

        return new SimpleAuthenticationInfo(uname,password,"myRealm");
    }
}
