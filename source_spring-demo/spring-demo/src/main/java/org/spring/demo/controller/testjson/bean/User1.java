package org.spring.demo.controller.testjson.bean;

import org.spring.demo.controller.testjson.TestJsonController;

public class User1 {
  private String name;
  private User2 user2 ;

  public User1(){
    TestJsonController.counter++;
    this.name = "aaa";
    System.err.println(TestJsonController.counter);
    if(TestJsonController.counter >=9000){
      return;
    }
    this.user2 = new User2();
  }
  public String getName() {
    return name;
  }

  public User2 getUser2() {
    return user2;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUser2(User2 user2) {
    this.user2 = user2;
  }
}
