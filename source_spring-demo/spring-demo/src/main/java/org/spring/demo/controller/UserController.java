package org.spring.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.dao.demo.bean.User;
import org.service.demo.service.UserService;
import org.spring.demo.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

@Controller
public class UserController {

  private Logger logger = Logger.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/registerFormData.do")
  public void registerFormData(@ModelAttribute("form") RegisterUser user, HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    //判断session是否相等
    String hiddenToken = user.getProtectedKey();
    HttpSession session = req.getSession();
    String csrfToken = session.getAttribute("protectedKey").toString();
    boolean isEqual = isEquals(hiddenToken, csrfToken);
    if (!isEqual) {
      return;
    }
    PrintWriter out = res.getWriter();
    String name = user.getName();
    String pwd = user.getPwd();
    int age = 25;
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    //sql-injection会影响xss的上报，先注释
    boolean result = userService.insert(id, name, pwd, age);
//    boolean result = true;
    if (!result) {
      out.print("sorry,"+ name+" registere fail"  );
    }
    out.print(name+" registere success"  );
    out.close();
  }

  @RequestMapping(value = "/register.do", headers = {"content-type=text/xml"})
  public void register(@RequestBody RegisterUser user, HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    String name = user.getName();
    char[] dst = new char[name.length()];
    int srcEnd = name.length() - 1;
    name.getChars(0, srcEnd, dst, 0);
    String name1 = new String(dst);
    String pwd = user.getPwd();
    int age = 25;
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    boolean result = userService.insert(id, name, pwd, age);
    if (!result) {
      out.print("<res><mes>fail</mes><user>" + name + "</user></res>");
    }
    out.println("<res><mes>success</mes><user>" + name + "</user></res>");
    out.close();
  }

  @RequestMapping("/login_1.do")
  public void test(HttpServletRequest req, HttpServletResponse res) {
    String requestBody = "";
    PrintWriter out = null;
    HttpServletResponse.class.getPackage().getImplementationVersion();

    try {
      out = res.getWriter();
      /*char[] buf = new char[512];
      int len = 0;
      BufferedReader reader = req.getReader();
      StringBuffer contentBuffer = new StringBuffer();
      while ((len = reader.read(buf)) != -1) {
        contentBuffer.append(buf, 0, len);
      }
      requestBody = contentBuffer.toString();*/

      BufferedReader br = req.getReader();
      String str = "";
      while ((str = br.readLine()) != null) {
        requestBody += str;
      }

      if (requestBody == null || "".equals(requestBody)) {
        return;
      }

      ObjectMapper objectMapper = new ObjectMapper();
      org.spring.demo.User user = objectMapper.readValue(requestBody, org.spring.demo.User.class);
      String name = user.getName();
      boolean isLogin = userService.login(user.getName(), user.getPwd());
      if (!isLogin) {
        out.print("<res><mes>fail</mes><user>" + name + "</user></res>");
      }
      out.println("<res><mes>success</mes><user>" + name + "</user></res>");
    } catch (Exception e) {
      out.print("<res><mes>execption</mes>");
    }
    out.flush();
    out.close();
  }

  @RequestMapping("/parseJson.do")
  public void parseJson(HttpServletRequest req, HttpServletResponse res) {
    String requestBody = "";
    PrintWriter out = null;
    HttpServletResponse.class.getPackage().getImplementationVersion();

    try {
      out = res.getWriter();

      BufferedReader br = req.getReader();
      String str = "";
      while ((str = br.readLine()) != null) {
        requestBody += str;
      }

      if (requestBody == null || "".equals(requestBody)) {
        return;
      }

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(requestBody);
//            String name = user.getName();
//            boolean isLogin = userService.login(user.getName(), user.getPwd());
//            if (!isLogin) {
//                out.print("<res><mes>fail</mes><user>" + name + "</user></res>");
//            }
//            out.println("<res><mes>success</mes><user>" + name + "</user></res>");
    } catch (Exception e) {
      out.print("<res><mes>execption</mes>");
    }
    out.flush();
    out.close();
  }

  /**
   * 测试js xss
   * @param name
   * @param req
   * @param res
   * @throws IOException
   */
  @RequestMapping("/jsXss.do")
  public void test2(@RequestParam String name, HttpServletRequest req, HttpServletResponse res)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    User user = new User();
    user.setName(name);
    String userJsonStr = objectMapper.writeValueAsString(user);
    PrintWriter out = res.getWriter();
    out.println(name);

  }

  @RequestMapping(value = "/login.do", headers = {
      "content-type=application/x-java-serialized-object"})
  public void login(HttpServletRequest req, HttpServletResponse res) {
    String requestBody = "";
    PrintWriter out = null;
    byte[] cache = new byte[1024];
    try {
      out = res.getWriter();
      /*char[] buf = new char[512];
      int len = 0;
      BufferedReader reader = req.getReader();
      StringBuffer contentBuffer = new StringBuffer();
      while ((len = reader.read(buf)) != -1) {
        contentBuffer.append(buf, 0, len);
      }
      requestBody = contentBuffer.toString();*/

      InputStream inputStream = req.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      int len = 0;
      while ((len = objectInputStream.read(cache)) > 0) {
        requestBody = new String(cache, 0, len);
      }

      if (requestBody == null || "".equals(requestBody)) {
        return;
      }

      ObjectMapper objectMapper = new ObjectMapper();
      org.spring.demo.User user = objectMapper.readValue(requestBody, org.spring.demo.User.class);
      String name = user.getName();
      boolean isLogin = userService.login(user.getName(), user.getPwd());
      if (!isLogin) {
        out.print("{\"res\":{\"mes\":\"fail\",\"user\":\"" + name + "\"}}");
      }
      out.print("{\"res\":{\"mes\":\"success\",\"user\":\"" + name + "\"}}");
    } catch (Exception e) {
      out.print("{\"res\":{\"mes\":\"fail\"}}");
    }
    out.flush();
    out.close();
  }

  @RequestMapping(value = "/login_restapi.do", headers = {
      "content-type=application/x-java-serialized-object"})
  public void loginSerialized(HttpServletRequest req, HttpServletResponse res) {
    PrintWriter out = null;
    Thread currentThread = Thread.currentThread();
    System.err.println("app threadId = "+currentThread.getId()+",app threadName = "+currentThread.getName());
    try {
      out = res.getWriter();
      InputStream inputStream = req.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      Thread.sleep(1*60*1000);
      org.spring.demo.User user = (org.spring.demo.User) objectInputStream.readObject();
      String name = user.getName();
      boolean isLogin = userService.login(user.getName(), user.getPwd());
      if (!isLogin) {
        out.print("{\"res\":{\"mes\":\"fail\",\"user\":\"" + name + "\"}}");
      }
      out.print("{\"res\":{\"mes\":\"success\",\"user\":\"" + name + "\"}}");
    } catch (Exception e) {
      out.print("{\"res\":{\"mes\":\"fail\"}}");
    }
    out.flush();
    out.close();
  }

  @RequestMapping(value = "/login_2.do", headers = {
      "content-type=application/x-java-serialized-object"})
  public void login1(@RequestBody org.spring.demo.User user, HttpServletRequest req,
      HttpServletResponse res) {
    PrintWriter out = null;
    try {
      out = res.getWriter();
      ObjectMapper objectMapper = new ObjectMapper();
      String name = user.getName();
      boolean isLogin = userService.login(user.getName(), user.getPwd());
      if (!isLogin) {
        out.print("{\"res\":{\"mes\":\"fail\",\"user\":\"" + name + "\"}}");
      }
      out.print("{\"res\":{\"mes\":\"success\",\"user\":\"" + name + "\"}}");
    } catch (Exception e) {
      out.print("{\"res\":{\"mes\":\"fail\"}}");
    }
    out.flush();
    out.close();
  }

  private boolean isEquals(String hiddenToken, String csrfToken) {
    if (hiddenToken == null || hiddenToken.equals("")) {
      logger.info("form-hidden submit null token");
      return false;
    }
    if (csrfToken == null || csrfToken.equals("")) {
      logger.info("session get null csrfToken");
      return false;
    }
    if (csrfToken.equals(hiddenToken)) {
      logger.info("CSRF PROTECTED");
      return true;
    } else {
      logger.info("CSRF");
      return false;
    }
  }
}
