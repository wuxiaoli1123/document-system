package com.wxl.system.config;


import com.wxl.system.entity.Permission;
import com.wxl.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@CrossOrigin
@Slf4j
public class ShiroConfig {

    @Autowired
    private PermissionService permissionService;

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

//        //（权限操作）
//        //登录请求（所有人都可以访问）
//        filterMap.put("/user/login","anon");
//
//        //动态权限控制
//        List<Permission> permissions = permissionService.findAllPermission();
//        for (Permission permission : permissions){
//             filterMap.put(permission.getUrl(),"perms["+permission.getCode()+"]");
//        }
//
//        log.info("权限控制"+filterMap);
//
//        //退出
//        filterMap.put("/user/logout","logout");
//
//        bean.setFilterChainDefinitionMap(filterMap);
//
//        //设置登录的请求
//        bean.setLoginUrl("/user/login");
//        //未授权页面
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

        //告诉realm密码匹配方式
        userRealm.setAuthorizationCacheName("perms");

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
