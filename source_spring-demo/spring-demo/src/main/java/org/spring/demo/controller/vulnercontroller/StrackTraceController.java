package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("strackTrace")
public class StrackTraceController {

  @RequestMapping("/exception.do")
  public void exception(HttpServletRequest request, HttpServletResponse response) throws Exception{
    throw new Exception("上报漏洞：系统异常信息泄露");
  }
}
