package org.spring.demo.controller.vulnercontroller.logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/logic/home"})
public class LogicHomeController {
  @RequestMapping({"/toLogin"})
  public String userLogin() {
    return "/user/userLogin";
  }
  
  @RequestMapping({"/toRegister"})
  public String userRegister() {
    return "/user/userRegister";
  }
  
  @RequestMapping({"/hello"})
  public String hello() {
    return "/user/hello";
  }
  
  @RequestMapping({"/error"})
  public String error() {
    return "/user/error";
  }

  @RequestMapping({"/index"})
  public String index() {
    return "loginIndex";
  }
}