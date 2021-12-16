package org.spring.demo.controller.detectRuleConfig.customDetecRule;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("sensitiveQueryStr")
public class TestSensitiveQueryStr {

  @RequestMapping("/testKey.do")
  public void isitKey(@RequestParam(value = "seczone") String username, HttpServletRequest req,
      HttpServletResponse res) throws Exception {
    PrintWriter out = res.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = username.substring(1);
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/testId.do")
  public void testId(@RequestParam(value = "xx") String id, HttpServletRequest req,
      HttpServletResponse res) throws Exception {
    //测试身份证号正则表达式:^(.)+([0-9]{14})+(.*)$
    PrintWriter out = res.getWriter();
    String idSub = id.substring(1);
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/testMobile.do")
  public void testMobile(@RequestParam(value = "yy") String mobile, HttpServletRequest req,
      HttpServletResponse res) throws Exception {
    //测试手机号码正则表达式:^(((13|18)[0-9]{9})|(15[012356789][0-9]{8})|((147|170|171|173|175|176|177|178)[0-9]{8}))$
    PrintWriter out = res.getWriter();
    String idSub = mobile.substring(1);
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/testBankCardNumber.do")
  public void testBankCardNumber(@RequestParam(value = "zz") String bankCardNum, HttpServletRequest req,
      HttpServletResponse res) throws Exception {
    //测试手机号码正则表达式:^(((13|18)[0-9]{9})|(15[012356789][0-9]{8})|((147|170|171|173|175|176|177|178)[0-9]{8}))$
    PrintWriter out = res.getWriter();
    String idSub = bankCardNum.substring(1);
    out.println("the request should report isit");
    out.close();
  }

  public static void main(String[] args){
    String mobile = "^(((13|18)[0-9]{9})|(15[012356789][0-9]{8})|((147|170|171|173|175|176|177|178)[0-9]{8}))$";
    Pattern pattern = Pattern.compile(mobile);
    String input = "13430800244";
    Matcher matcher = pattern.matcher(input);
    if(matcher.matches()){
      System.out.println(input);
    }else {
      System.out.println("the input doesn't matcher regex");
    }
  }

}
