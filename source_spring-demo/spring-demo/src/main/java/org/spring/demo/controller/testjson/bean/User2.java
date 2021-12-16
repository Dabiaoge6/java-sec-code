package org.spring.demo.controller.testjson.bean;

import org.spring.demo.controller.testjson.TestJsonController;

public class User2 {
  private String name;
  private User1 user1;
  public User2(){
    this.name = "bbb";
    TestJsonController.counter++;
    System.err.println(TestJsonController.counter);
  if(TestJsonController.counter >=9000){
    return;
  }
    this.user1 = new User1();
  }
  public String getName() {
    return name;
  }

  public User1 getUser1() {
    return user1;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUser1(User1 user1) {
    this.user1 = user1;
  }
}
