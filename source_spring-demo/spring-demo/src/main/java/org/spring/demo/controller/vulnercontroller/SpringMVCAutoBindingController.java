package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.spring.demo.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unsafeAutoBinding")
public class SpringMVCAutoBindingController {

  @RequestMapping("/binding001.do")
  public void binding001(RegisterUser user,HttpServletRequest request,HttpServletResponse response)
      throws IOException {
    String name = user.getName();
    String pwd = user.getPwd();
    if(name ==null || "".equals(name)||pwd == null || "".equals(pwd)){
      return;
    }
    List<RegisterUser> userList = new ArrayList<RegisterUser>();
    userList.add(user);
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    PrintWriter writer = response.getWriter();
    writer.println("上报漏洞：不安全的SpringMVC自动绑定");
  }
}
