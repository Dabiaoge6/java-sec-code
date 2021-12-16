package cn.seczone.hessian.demo;

import cn.seczone.hessian.demo.service.GreetingAPI;
import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;

public class Test {

  public static void main(String[] args) throws MalformedURLException {
    String url = "http://localhost:8087/hessian-demo/hessian";

    HessianProxyFactory factory = new HessianProxyFactory();
    GreetingAPI basic = (GreetingAPI) factory.create(GreetingAPI.class, url);

    System.out.println("hello(): " + basic.hello());
  }

}
