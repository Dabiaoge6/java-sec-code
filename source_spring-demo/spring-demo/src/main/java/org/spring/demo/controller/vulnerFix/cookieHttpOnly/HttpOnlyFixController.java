package org.spring.demo.controller.vulnerFix.cookieHttpOnly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("cookiehttpFix")
public class HttpOnlyFixController {

    @RequestMapping("/httpOnlyFix.do")
    public void httpOnly(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Set-Cookie", "test;HTTPOnly");
        PrintWriter out = response.getWriter();
        out.println("the request don't report cookie-httpOnly-missing");
    }
}
