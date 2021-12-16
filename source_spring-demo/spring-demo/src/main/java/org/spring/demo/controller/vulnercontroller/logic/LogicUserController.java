package org.spring.demo.controller.vulnercontroller.logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/logic/user"})
public class LogicUserController {
  @RequestMapping({"/index"})
  public String show() {
    return "/user/show";
  }
}
