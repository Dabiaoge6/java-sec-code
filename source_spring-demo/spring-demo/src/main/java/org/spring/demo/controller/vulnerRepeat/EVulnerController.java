package org.spring.demo.controller.vulnerRepeat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eVulner")
public class EVulnerController {

  @RequestMapping("/eRepeat001.do")
  public void eRepeat001(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    response.addHeader("Set-Cookie", "test-true;HTTPOnly");
    response.addHeader("Content-Security-Policy", "script-src 'self';" + "object-src 'none';"
        + "child-src https;");
    response.setContentType("text/html;charset=utf-8");
    response.setHeader("X-Frame-Options", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
    response.setHeader("Content-Security-Policy",
        "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    out.println("the request report Cache-Control-missing");
  }

  @RequestMapping("/eRepeat002.do")
  public void eRepeat002(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    response.addHeader("Set-Cookie", "test-true;HTTPOnly");
    response.addHeader("Content-Security-Policy", "script-src 'self';" + "object-src 'none';"
        + "child-src https:");
    response.setContentType("text/html;charset=utf-8");
    response.setHeader("X-Frame-Options", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
    response.setHeader("Content-Security-Policy-Security-Policy",
        "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    out.println("the request report Cache-Control-missing");
  }

  @RequestMapping("/eRepeat003.do")
  public void eRepeat003(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    response.addHeader("Content-Security-Policy", "script-src 'self';" + "object-src 'none';"
        + "child-src https:");
    response.setContentType("text/html;charset=utf-8");
    response.setHeader("X-Frame-Options", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
    response.setHeader("Content-Security-Policy-Security-Policy",
        "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    out.println("the request report Cache-Control-missing");
  }

  @RequestMapping("/eRepeat004.do")
  public void eRepeat004(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    response.addHeader("Set-Cookie", "test-true;HTTPOnly");
    response.addHeader("Content-Security-Policy", "script-src 'self';" + "object-src 'none';"
        + "child-src https:");
    response.setContentType("text/html;charset=utf-8");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Referrer-Policy", "no-referrer-when-downgrade");
    response.setHeader("Content-Security-Policy-Security-Policy",
        "default-src 'self';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'");
    out.println("the request report Cache-Control-missing");
  }

}
