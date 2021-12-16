package com.seczone;

public class Test {

  public static void main(String[] args) {
    String str = "hi,javassist!";
    print(str);
  }

  public static void print(String str) {
    str = str + "in agent-demo";
    System.out.println("method body==>" + str);
  }

}
