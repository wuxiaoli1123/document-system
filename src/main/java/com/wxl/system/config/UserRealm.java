package com.wxl.system.config;


import com.wxl.system.entity.User;
import com.wxl.system.service.UserService;
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

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("teacher:add");

        //拿到当前登录的对象
       Subject subject = SecurityUtils.getSubject();
       User currentUser = (User) subject.getPrincipal();//拿到user对象

        //设置当前用户的权限



        return null;


    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库
        User user =userService.findByAccount(userToken.getUsername());

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
