package org.spring.demo.controller.vulnercontroller.logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/logic/admin"})
public class LogicAdminController {
  @RequestMapping({"/index"})
  public String show() {
    return "/admin/show";
  }


  @RequestMapping({"/security"})
  public String security(HttpSession session) {
    if (session.getAttribute("auth").equals("admin")){
      return "/admin/show";
    }else {
      return "/admin/noAuth";
    }
  }
}