package org.spring.demo.controller.testserialized;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestObject implements Serializable {

  String str;

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  @Override
  public String toString() {
    return "TestObject{" +
        "str='" + str + '\'' +
        '}';
  }

  public static void main(String[] args){
    String username = "hujj";
    String pwd = "test-pwd'";
   /* Pattern pattern = Pattern.compile("([';]+|(--)+).*");

    Matcher matcher = pattern.matcher(pwd);
    boolean isMath = matcher.matches();
    String result = matcher.replaceAll("");
    String[] split = pattern.split(pwd);*/

    pwd = pwd.replaceAll("([';]+|(--)+).*", "");
    String sql1 = "select * from app1_user where username = '" + username + "'";
    String sql2 = " AND pwd = '" + pwd + "';";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(sql);
  }
}
