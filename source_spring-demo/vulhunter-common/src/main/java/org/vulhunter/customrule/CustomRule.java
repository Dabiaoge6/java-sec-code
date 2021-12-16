package org.vulhunter.customrule;

public class CustomRule {

  public static boolean doInputValidator(String ruleId){
    if(ruleId != null && !"".equals(ruleId)){
      return true;
    }
    return false;
  }

  public String doSanitizer(String input){
    return input.concat("");
  }

}
