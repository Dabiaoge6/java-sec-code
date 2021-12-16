package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cspInsecure")
public class CspInsecureController {

  @RequestMapping("/unsafeCsp002.do")
  public void cspInsecure(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
//        response.setHeader("Content-Security-Policy", "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    response.setHeader("Content-Security-Policy",
        "script-src 'unsafe-inline';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    response.getWriter().print("set Content-Security-Policy-Insecure header");
    /*response.setHeader("X-Content-Security-Policy", "*");
        response.setHeader("X-Webkit-CSP", "*");
        response.setHeader("Content-Security-Policy", "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 'block';base-uri 'self';referer 'self'");*/
  }

  @RequestMapping("/safeCsp003.do")
  public void cspSecure(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setHeader("Content-Security-Policy",
        "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    response.getWriter().print("set Content-Security-Policy-Secure header");
  }

}
