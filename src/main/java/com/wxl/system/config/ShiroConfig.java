package com.wxl.system.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@CrossOrigin
@Slf4j
public class ShiroConfig {

    //ShiroFilterFactoryBean:3 @Qualifier("securityManager")  DefaultWebSecurityManager defaultWebSecurityManager
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(getDefaultWebSecurityManager());

        //添加shiro的内置过滤器
        /** anon: 无需认证即可访问
         * authc: 必须认证以了才能访问
         * user: 必须拥有 记住我 功能才能访问
         * perms: 拥有对某个资源的权限才能访问
         * role: 拥有某个角色权限访问
         */

        //有序序列（优先级）
        Map<String, String> filterMap = new LinkedHashMap<>();

        log.info("页面限制");

        //（权限操作）
        //登录请求（所有人都可以访问）
        filterMap.put("/user/login","authc");

        //权限控制
        filterMap.put("/user/*","perms[user:power]");
        filterMap.put("/teacher/**","perms[teacher:power]");
        filterMap.put("/student/**","perms[student:power]");
        filterMap.put("/manager/**","perms[manager:power]");

        filterMap.put("/user/*","roles[role_student]");






        //退出
        filterMap.put("/user/logout","logout");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/user/login");
        //未授权页面
        bean.setUnauthorizedUrl("/user/noauth");

        return bean;
    }

    //DefaultWebSecurityManager:2
    @Bean(name="securityManager")
    //@Qualifier("userRealm") UserRealm userRealm
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    //创建realm对象，需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    //密码加密
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedMatcher = new HashedCredentialsMatcher();
        hashedMatcher.setHashAlgorithmName("md5");
       return hashedMatcher;
    }

}
