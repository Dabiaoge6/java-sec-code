package org.spring.demo.controller.vulnerFix.unvalidatedForward;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("forwardFix")
public class ForwardFixController {
    private boolean isContain(String urlInput){
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("white_url", "www.sohu.com,www.baidu.com,sina.com,59.123.11.80:8080");//信任域名列表
        String url=urlInput+"/".toLowerCase();
        int start = urlInput.lastIndexOf("://");
        String input = url.substring(start+3).substring(0, url.substring(start+3).indexOf("/"));
        if (!Arrays.<String> asList(extMap.get("white_url").split(",")).contains(input)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 设置白名单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/forwardFix.do")
    public void forward2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        String name = request.getParameter("name");
        boolean isContain = isContain(name);
        servletContext.getRequestDispatcher(name);
        PrintWriter out = response.getWriter();
        out.println("UnValidated Forward");
    }
}
