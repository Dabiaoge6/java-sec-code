package com.seczone;

public class PreTest {

  public static void beforeMethod(String args) {
    args = "beforeMethod handled : " + args ;
    System.out.println(args);
  }

  public static void afterMethod(String args) {
    args = "afterMethod handled : " + args ;
    System.out.println(args);
  }

}
