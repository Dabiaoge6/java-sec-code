package org.spring.demo.controller.vulnerRepeat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.spring.demo.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.hardkey.HardCodeKeyTest;
import org.vulhunter.common.hardkey.SecretHardKeyTest;
import org.vulhunter.common.sqlinjection.ExecuteSql;

@Controller
@RequestMapping("bVulner")
public class BVulnerController {

  //springmvc-autobinding
  //同一个key,同一个class
  @RequestMapping("/bRepeat001.do")
  public void bRepeat001(RegisterUser user,HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String name = user.getName();
    String pwd = user.getPwd();
    if(name ==null || "".equals(name)||pwd == null || "".equals(pwd)){
      return;
    }
    List<RegisterUser> userList = new ArrayList<RegisterUser>();
    userList.add(user);
    PrintWriter writer = response.getWriter();
    writer.println("register success");
  }

  @RequestMapping("/bRepeat002.do")
  public void bRepeat002(RegisterUser user,HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String name = user.getName();
    String pwd = user.getPwd();
    if(name ==null || "".equals(name)||pwd == null || "".equals(pwd)){
      return;
    }
    List<RegisterUser> userList = new ArrayList<RegisterUser>();
    userList.add(user);
    PrintWriter writer = response.getWriter();
    writer.println("register success");
  }

  @RequestMapping("/bRepeat003.do")
  public void bRepeat003(RegisterUser registerUser,HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String name = registerUser.getName();
    String pwd = registerUser.getPwd();
    if(name ==null || "".equals(name)||pwd == null || "".equals(pwd)){
      return;
    }
    List<RegisterUser> userList = new ArrayList<RegisterUser>();
    userList.add(registerUser);
    PrintWriter writer = response.getWriter();
    writer.println("register success");
  }

  //hardcoded
  @RequestMapping("/bRepeat004.do")
  public void bRepeat004(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    SecretHardKeyTest secretkey = new SecretHardKeyTest();
    secretkey.test();
    PrintWriter out = response.getWriter();
    out.println("the request should report hard-key and hardcode-password");
  }

  @RequestMapping("/bRepeat005.do")
  public void bRepeat005(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    SecretHardKeyTest secretkey = new SecretHardKeyTest();
    secretkey.test();
    PrintWriter out = response.getWriter();
    out.println("the request should report hard-key and hardcode-password");
  }

}
