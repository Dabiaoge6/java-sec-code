package org.spring.demo.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wlf
 * @Description:
 * @date 2021/9/1 17:39
 */
public class CustomEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        try {
            if(null !=  request.getServletPath() && request.getServletPath().contains("nodata")){
                return;
            }
            if(null !=  request.getServletPath() && request.getServletPath().endsWith(".js")){
                return;
            }
            else {
                response.setHeader("X-XSS-Protection", "1; mode=block");
                response.setHeader("Content-Security-Policy", "referrer no-referrer");//csp响应头
                response.setHeader("X-Frame-Options", "DENY ");
                response.setHeader("X-Content-Type-Options", "nosniff");
                response.setHeader("Referrer-Policy", "no-referrer");//引用策略头
                response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
                response.setDateHeader("Expires", 0); //prevent caching at the proxy server
                response.setHeader("Pragma", "no-cache");  //HTTP 1.0
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}