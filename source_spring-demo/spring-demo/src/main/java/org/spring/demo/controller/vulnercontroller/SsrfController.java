package org.spring.demo.controller.vulnercontroller;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

@Controller
@RequestMapping("ssrf")
public class SsrfController {

    @RequestMapping("/ssrf001.do")
    public void openConnection(HttpServletRequest request, HttpServletResponse response) {
        String inputParam = request.getParameter("testURL");
        //http://www.baidu.com/news/index.jsp?query_string=haha
        String urlPath = "https://" + inputParam + "/news/index.jsp?queryString=haha";
        try {
            URL url1 = new URL(urlPath);
            url1.openConnection();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("上报漏洞：服务器端请求域名伪造");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/ssrf002.do")
    public void openConnectionProxy(HttpServletRequest request, HttpServletResponse response) {
        String inputParam = request.getParameter("testURL");
        //http://www.baidu.com/news/index.jsp?query_string=haha
        String urlPath = "https://www.baidu.com/" + inputParam + "?queryString=haha";
        try {
            URL url1 = new URL(urlPath);
            url1.openConnection(Proxy.NO_PROXY);
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("上报漏洞：服务器端请求路径伪造");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/ssrf003.do")
    public void setURI(HttpServletRequest request, HttpServletResponse response) {
        String inputParam = request.getParameter("testURL");
        //String url = "http://"+urlInput+":8086/spring-demo/reflectedXss/writeRaw.do";
        //String requestURI = request.getRequestURI();
        //http://www.baidu.com/news/index.jsp?query_string=haha
        String urlPath = "https://www.baidu.com/news/index.jsp?" + inputParam;
        HttpRequestBase httpRequestBase = new HttpRequestBase() {
            @Override
            public String getMethod() {
                return null;
            }
        };
        try {
            //httpRequestBase.setURI(new URI(requestURI));
            httpRequestBase.setURI(new URI(urlPath));
            URI uri = httpRequestBase.getURI();
            //URL url = new URL(uri.toString());
            //url.openConnection();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("上报漏洞：服务器端请求参数伪造");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
