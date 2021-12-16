package org.spring.demo.controller.vulnerFix.trustboundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("trustBoundaryFix")
public class TrustBoundaryFixController {
    private boolean isMathcher(String input){
        String regex = "^[a-zA-Z]\\\\w{5,17}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    @RequestMapping("/tbvFix.do")
    public void tbv1(HttpServletRequest request, HttpServletResponse response) {
//        inputStr= request.getParameter("id");
        String id = request.getParameter("id");
        boolean isMatcher = isMathcher(id);
        if(!isMatcher){
            return ;
        }
        HttpSession session = request.getSession();
        session.setAttribute("sessionPolicyId", id);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write("session setAttribute");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
