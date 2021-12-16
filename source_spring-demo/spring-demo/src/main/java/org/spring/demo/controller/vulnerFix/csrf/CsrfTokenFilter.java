package org.spring.demo.controller.vulnerFix.csrf;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CsrfTokenFilter implements Filter {
    private Logger logger = Logger.getLogger(CsrfTokenFilter.class);
    private String tokenStr = "";
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Server generate protectedKey start");
        //生成随机token
        tokenStr = new Long(System.currentTimeMillis()).toString();
        logger.info("current protectedKey is "+tokenStr);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();
        ((HttpSession) session).setAttribute("protectedKey",tokenStr);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        logger.info("Server generate protectedKey end");
    }
}
