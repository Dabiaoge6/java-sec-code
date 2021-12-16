package org.spring.demo.controller.vulnercontroller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hqlInjection")
public class HqlInjectionController {

  private static SessionFactory sessionFactory;
  //获取Session对象
  public static Session getSession() {
    return getSessionFactory().getCurrentSession();
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null || sessionFactory.isClosed()) {
      sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    return sessionFactory;
  }


  @RequestMapping(value = "/hql001.do",produces = "text/html;charset=utf-8")
  @ResponseBody
  public String hql001(@RequestParam String test){
    try {
      org.hibernate.Session session = getSessionFactory().openSession();
      Query filter = session.createFilter("", test);
      return filter.getQueryString();
    }catch (Exception e){
      return "上报漏洞：1.HQL注入 2.不安全的随机数算法";
    }
  }
}
