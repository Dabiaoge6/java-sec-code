package org.spring.demo;

import java.util.List;

public class TestTracker {

  public static void track(String actualClassName, String[] policies, Object thisObj,
      Object[] args) {
    System.out.println("actualClassName = " + actualClassName);
    System.out.println("policies = " + policies[0]);
    System.out.println(
        "args = " + args.length + ",args[0] instanceof list ?" + (args[0] instanceof List));

  }


}
