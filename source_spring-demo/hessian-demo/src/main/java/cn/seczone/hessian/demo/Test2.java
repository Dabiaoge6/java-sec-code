package cn.seczone.hessian.demo;

import cn.seczone.hessian.demo.service.BasicService;
import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;

public class Test2 {

  public static void main(String[] args) throws MalformedURLException {
    String url = "http://localhost:8087/hessian-demo/test";

    HessianProxyFactory factory = new HessianProxyFactory();
    BasicService basic = (BasicService) factory.create(BasicService.class, url);
    basic.getUserByName("hujj");
  }

}
