package org.spring.demo.login;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        //filterConfig.getInitParameter()
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        String url = request.getRequestURI();
        System.out.println("========>path="+path+", url="+url+"\n");
        /*Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            String name = (String)headers.nextElement();
            System.out.println(String.format("header: %s=%s", name,request.getHeader(name))+"\n");
        }*/

        // 如果不需要过滤的静态文件，直接放行
        /*if (path.endsWith(".css") || path.endsWith(".js")
                || url.indexOf("resource") > 0 || url.indexOf("note") > 0
                || url.endsWith("login.jsp") || url.endsWith("login.do")
                || path.endsWith(".ico") || path.endsWith(".gif")) {
            filterChain.doFilter(request, response);
            return;
        } else {
            if (checkUser(request, response)) {
                filterChain.doFilter(request, response);
                return;
            } else {
                String contextPath = getContextPath(request);
                response.sendRedirect(contextPath+ "/scan/csrf/login.jsp");
                return;
            }
        }*/
        filterChain.doFilter(request, response);
    }

    protected String getContextPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();

        if ((contextPath == null) || (contextPath.equals("/"))) {
            return "";
        }
        return contextPath;
    }

    private boolean checkUser(HttpServletRequest request, HttpServletResponse response){

        /*Cookie cookie = getCookie(request, response);
        if(cookie != null ){
            return true;
        }*/
        HttpSession session = request.getSession(false);// 如果不存在返回空
        if (session == null) {
            return false;
        }
        /*Object obj = session.getAttribute("csrfToken");
        if (obj == null) {
            return false;
        }*/

        return true;
    }

    private Cookie getCookie(HttpServletRequest request, HttpServletResponse response){
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                // 遍历数组
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sessionid")) {
                        // 设置cookie的值
                        String value = cookie.getValue();
                        if(value == null || value !="fixed"){
                            System.out.println("===========cookie value="+value);
                            cookie.setValue("fixed");
                            cookie.setPath("/");
                            response.addCookie(cookie);
                            return cookie;
                        }
                    }
                }
            }
        return null;
    }

    public void destroy(){
    }
}
