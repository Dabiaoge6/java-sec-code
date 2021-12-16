package org.spring.demo.controller.vulnercontroller.logic;

import org.dao.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vulhunter.util.RandomValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LogicIndexController {
  @Autowired
  private UserDao userDao;
  
  @ResponseBody
  @RequestMapping({"/getVerify"})
  public void getVerify(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("image/jpeg");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expire", 0L);
    RandomValidateCode randomValidateCode = new RandomValidateCode();
    try {
      randomValidateCode.getRandcode(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  @ResponseBody
  @RequestMapping({"/checkCode"})
  public Map<String, String> checkCode(@RequestParam("checkCode") String imageCode, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    String verifyCode = (String)session.getAttribute("RANDOMVALIDATECODEKEY");
    HashMap<String, String> result = new HashMap<>();
    if (!imageCode.equals(verifyCode)) {
      result.put("errorInfo", "验证码填写错误");
    } else {
      result.put("success", "true");
    } 
    return result;
  }
  
  @ResponseBody
  @RequestMapping({"/checkLogin"})
  public Map<String, String> checkLogin(@RequestParam("username") String name, @RequestParam("password") String pwd, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    HashMap<String, String> result = new HashMap<>();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    List<String> userList = userDao.login(name, pwd);
    if (null != userList && !userList.isEmpty()){
      result.put("success", "true");
      result.put("msg", "登录成功");
      cleanFailNum(session, name);
      session.setAttribute("auth", name.equals("admin") ? "admin" : "user");
    }else {
      List<String> nameList = userDao.selectId(name);
      if (null != nameList && !nameList.isEmpty()){
        addFailNum(session, name);
        result.put("msg", "密码错误！");
      }else {
        addFailNum(session, name);
        result.put("msg", "用户名不存在请检查！");
      }
    }

    return result;
  }
  
  @RequestMapping({"/register"})
  @Transactional
  public String register(User user) {
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
            + uuid.substring(19, 23) + uuid.substring(24);
    userDao.insert(id, user.getUsername(), user.getPassword());
    return "/user/userLogin";
  }
  
  public boolean checkLock(HttpSession session, String username) {
    Object o = session.getServletContext().getAttribute(username);
    if (o == null)
      return true; 
    HashMap<String, Object> map = (HashMap<String, Object>)o;
    int num = ((Integer)map.get("num")).intValue();
    Date date = (Date)map.get("lastDate");
    long timeDifference = ((new Date()).getTime() - date.getTime()) / 60L / 1000L;
    if (num >= 3 && timeDifference < 30L)
      return false; 
    return true;
  }
  
  public void addFailNum(HttpSession session, String username) {
    Object o = session.getServletContext().getAttribute(username);
    HashMap<String, Object> map = null;
    int num = 0;
    if (o == null) {
      map = new HashMap<>();
    } else {
      map = (HashMap<String, Object>)o;
      num = ((Integer)map.get("num")).intValue();
      Date date = (Date)map.get("lastDate");
      long timeDifference = ((new Date()).getTime() - date.getTime()) / 60L / 1000L;
      if (timeDifference >= 30L)
        num = 0; 
    } 
    map.put("num", Integer.valueOf(num + 1));
    map.put("lastDate", new Date());
    session.getServletContext().setAttribute(username, map);
  }
  
  public void cleanFailNum(HttpSession session, String username) {
    session.getServletContext().removeAttribute(username);
  }
}
