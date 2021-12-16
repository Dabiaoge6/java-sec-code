package org.spring.demo.controller.vulnercontroller.otherTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("testCharArray")
public class TestCharArrayXss {

  @RequestMapping(value = "/xss001.do")
  public void register(HttpServletRequest req,HttpServletResponse res) throws IOException {
    String name = req.getParameter("name");
    char[] chars = "{\"id\":\"01234\",\"name\":\"       \"}".toCharArray();
    name.getChars(0,name.length(),chars,22);
    System.out.println(String.valueOf(chars));
//    char[] chars1 = "              \"pwd\":\"5678\"}".toCharArray();
    chars[14] = '\"';
    chars[15] = 'p';
    chars[16] = 'w';
    chars[17] = 'd';
    chars[18] = '\"';
    chars[19] = ':';
    chars[20] = '\"';
    chars[21] = '5';
    chars[22] = '6';
    chars[23] = '7';
    chars[24] = '8';
    chars[25] = '9';
    chars[26] = '\"';
    chars[27] = '}';
    chars[28]=' ';
    chars[29] = ' ';
    chars[30] = ' ';
//    System.arraycopy(chars,0,chars1,0,14);
//    System.out.println(String.valueOf(chars1));
//    String jsonStr = new String(chars1,0,chars1.length);
    String jsonStr1 = new String(chars,0,chars.length);
    JSONObject jsonObject = JSON.parseObject(jsonStr1);
//    System.out.println(jsonObject.getString("pwd"));
    res.getWriter().print(jsonObject.getString("id"));
//    res.getWriter().print(String.valueOf(chars));

}

  public static void main(String[] args) {
    /*char[] chars = "test".toCharArray();
    char[] chars1 = "01235".toCharArray();
    System.arraycopy(chars,0,chars1,1,4);
    System.out.println(String.valueOf(chars1));*/
    String name = "seczone";
    char[] chars = "{\"id\":\"01234\",\"name\":\"       \"}".toCharArray();
    name.getChars(0,name.length(),chars,22);
    System.out.println(String.valueOf(chars));
    char[] chars1 = new char[30];
    chars1 = "              \"pwd\":\"5678\"}".toCharArray();
    System.arraycopy(chars,0,chars1,0,14);
    System.out.println(String.valueOf(chars1));
    String jsonStr = new String(chars1,0,chars1.length);
    JSONObject jsonObject = JSON.parseObject(jsonStr);
    System.out.println(jsonObject.getString("pwd"));
  }

}
