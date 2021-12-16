package org.spring.demo.controller.detectRuleConfig.customVulRule;

import org.vulhunter.common.sqlinjection.ExecuteSql;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRule1 {

  public static boolean doInputValidator(String ruleId){
    if(ruleId != null && !"".equals(ruleId)){
      return true;
    }
    return false;
  }
  public String doSanitizer(String input){
    return input.concat("");
  }


  public void regMatcher(HttpServletRequest request) {
    String username = request.getParameter("username");
    String valName = doMatcher(username);
    String sql1 = "select * from app1_user where username = '" + valName + "'";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    String sql = stringBuilder.toString();
    ExecuteSql.addBatchAndCleanBatch(sql);
  }

  public String doMatcher(String input){
    Pattern compile = Pattern.compile("^[a-zA-Z0-9]+$");
    Matcher matcher = compile.matcher(input);
    if(matcher.matches()){
      return input;
    }
    return input.concat(" ");
  }
}
