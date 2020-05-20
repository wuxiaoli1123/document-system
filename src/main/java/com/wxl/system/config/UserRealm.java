package com.wxl.system.config;


import com.wxl.system.entity.User;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

//自定义的UserRealm
//和数据库交换

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //获取权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //从数据库查询用户的权限和角色
        //获取用户名
        User user =(User)principalCollection.getPrimaryPrincipal();//此pricipal就是认证中的principal(认证时所构造的)

        String account =user.getAccount();

        log.info("获取权限");

        Set<String> roles = userService.findRCodeByAccount(account);
        if(roles != null && roles.size()>0){
            simpleAuthorizationInfo.addRoles(roles);
        }

        Set<String> permissions = userService.findPCodeByAccount(account);
        if(permissions != null && permissions.size()>0){
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }

        return simpleAuthorizationInfo;


    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库
        User user =userService.findByAccount(userToken.getUsername());

        log.info("认证！");

        if(user==null){
            //不存在该用户
            return null;
        }else {

            //封装
            ByteSource bsSalt = new SimpleByteSource(user.getPrivate_salt());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),bsSalt,getName());
            return authenticationInfo;

        }

    }
}
