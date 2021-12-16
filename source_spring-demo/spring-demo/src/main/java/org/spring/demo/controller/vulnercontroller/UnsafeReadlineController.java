package org.spring.demo.controller.vulnercontroller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unsafeReadline")
public class UnsafeReadlineController {

  @RequestMapping("/testReadline.do")
  public void testUnsafeReadline(HttpServletRequest request, HttpServletResponse response) {
    try {
      BufferedReader br = request.getReader();
      String newline = br.readLine();
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      response.getWriter().println("上报漏洞：不安全的readLine方法");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
