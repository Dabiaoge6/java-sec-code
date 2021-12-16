package org.spring.demo.controller.vulnercontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("elinjection")
public class ElInjectionController {
	
	@RequestMapping("/jstl_el_post.do")
    public void Fjstl_el_post(HttpServletRequest request, HttpServletResponse response) {
        String[] messages = request.getParameterValues("message");
        String message = messages[0];
        /*String message = request.getParameter("message");*/
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        try {
            response.sendRedirect("../elinjection/jstl_el_post.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "/elinjection/jstl_el_post";
    }
	

}
