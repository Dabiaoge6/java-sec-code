package com.seczone.security.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /**
         * anno: 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user: 必须拥有 记住我 功能才能用
         * perms: 拥有对某个资源的权限才能访问
         * role: 拥有某个权限才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        // 放行login.html页面
        filterMap.put("/login2", "anon"); // 要将登陆的接口放出来，不然没权限访问登陆的接口
        filterMap.put("/static/**", "anon");
        filterMap.put("/data/**", "anon");
        filterMap.put("/data/**", "anon");

        //授权  所有接口访问都要进行登陆
//        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");// 修改调整的登录页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");// 设置未授权提示页面
        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager:                                                  <2>

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建realm 对象，需要自定义类:                                                  <1>

    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect: 用来整合 shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}