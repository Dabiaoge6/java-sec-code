package org.spring.demo.controller.vulnercontroller.otherTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.service.demo.service.UserService;
import org.spring.demo.RegisterUser;
import org.spring.demo.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("secondInjection")
public class SecondInjectionController {

  private Logger logger = Logger.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/register.do")
  public void register(@ModelAttribute("form") RegisterUser user, HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    String name = user.getName();
    String pwd = user.getPwd();
    int age = 25;
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    //sql-injection会影响xss的上报，先注释
    boolean result = userService.insert(id, name, pwd, age);
    if (!result) {
      res.sendRedirect("../secondinjection/register.jsp");
    }else{
      res.sendRedirect("../secondinjection/login.jsp");
    }
  }

  @RequestMapping(value = "/login.do")
  public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    String name = req.getParameter("name");
    String pwd = req.getParameter("pwd");
    boolean isLogin = userService.login(name,pwd);
    if(isLogin){
      HttpSession session = req.getSession();
      session.setAttribute("username",name);
      out.print("login success");
    }else{
      out.print("login fail");
    }
    out.close();
  }

  @RequestMapping("/update.do")
  public void updatePwd(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String oldpwd = request.getParameter("pwd_update");
    String newpwd = request.getParameter("newpwd");
    String confirmpwd = request.getParameter("confirmpwd");
    HttpSession session = request.getSession();
    Object name = session.getAttribute("username");
    if(null == name){
      out.print("session username is empty");
    }else{
      String username = String.valueOf(name);
      List<String> idList = userService.selectId(username);
      if (idList.size() == 0) {
        out.println("The account does not exist");
        return;
      }
      if (!(newpwd.equals(confirmpwd))) {
        out.println("Incorrect password input");
        return;
      }
      boolean result = userService.updatePwd(username, newpwd, oldpwd);
      if (!result) {
        out.print("Update User Fail");
      } else {
        out.println("Update User Success");
      }
    }
    out.close();
  }

}
