package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("weakRandom")
public class WeakRandomController {

  @RequestMapping("/weakRandom001.do")
  public void weakRandom1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    Random weakRandom = new Random();
    weakRandom.nextInt(10);
    out.println("上报漏洞：不安全的随机数算法");
  }
}
