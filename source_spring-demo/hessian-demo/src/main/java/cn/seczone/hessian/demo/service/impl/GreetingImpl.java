package cn.seczone.hessian.demo.service.impl;

import cn.seczone.hessian.demo.service.GreetingAPI;
import com.caucho.hessian.server.HessianServlet;

public class GreetingImpl extends HessianServlet implements GreetingAPI {

  private String _greeting;

  public GreetingImpl() {
    this._greeting = "Hello, world";
  }

  public void setGreeting(final String greeting) {
    this._greeting = greeting;
  }

  public String greeting() {
    return this._greeting;
  }

  public String hello() {
    return this._greeting;
  }

  public void testSqlInjection(String userName,String password){

  }

}
