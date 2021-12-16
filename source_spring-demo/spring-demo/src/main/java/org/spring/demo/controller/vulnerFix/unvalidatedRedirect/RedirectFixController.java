package org.spring.demo.controller.vulnerFix.unvalidatedRedirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("redirectFix")
public class RedirectFixController {

    private boolean isValidUrl(String path){
        if(path.equals("index.jsp")){
            return true;
        }
        return false;
    }

    @RequestMapping("/sendRedirectFix.do")
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
        try {
            String location = request.getParameter("location");
            boolean isValid = isValidUrl(location);
            if(!isValid){
                return;
            }
            response.sendRedirect("../"+location);
            PrintWriter out = response.getWriter();
            out.println("UnValidated Redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
