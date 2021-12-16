package org.spring.demo.controller.vulnercontroller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dos")
public class DOSController {

  @RequestMapping("/testSleep.do")
  public void testDos(HttpServletRequest request, HttpServletResponse response) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    Mythread mythread = new Mythread();
    Thread thread = new Thread(mythread);
    /*try {
      Thread.sleep(6000,500000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
    for (int i = 0; i < 6; i++) {
      es.execute(thread);
    }
  }

  @RequestMapping("/testSleep1.do")
  public void testDos1(HttpServletRequest request, HttpServletResponse response) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    Mythread1 mythread = new Mythread1();
    Thread thread = new Thread(mythread);
    for (int i = 0; i < 6; i++) {
      es.execute(thread);
    }
  }
}

class Mythread extends Thread {

  @Override
  public void run() {
    try {
      Thread.sleep(6000,500000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    System.out.println("发包成功！");
  }
}

class Mythread1 extends Thread {

  @Override
  public void run() {
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    System.out.println("发包成功！");
  }
}
