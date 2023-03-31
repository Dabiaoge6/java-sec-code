package com.seczone.security.config;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShiroLogoutFilter extends LogoutFilter {
    @Override
    public boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //清除HTTPSession的用户信息
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")!=null) {
            session.removeAttribute("user");
        }
        System.out.println("=HTTPSession用户数据被清空了=");

        return super.preHandle(httpServletRequest, response);
    }


}
