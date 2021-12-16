package org.spring.demo.controller.vulnercontroller.storexss;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/person"})
public class PersonController {

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

    @RequestMapping({"/addperson"})
    public String saveperson() {
        return "storexss/savepage";
    }

    @RequestMapping({"/saveperson"})
    public String saveperson(Person person, String province, String city, String country) {
        Address address = new Address();
        address.setProvince("province");
        address.setCity("city");
        address.setCountry("country");
        person.setIdCard("idCard");
        person.setPhone("phone");
        person.setAddress(address);
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();
        session.save(person);
        tx.commit();
        return "redirect:main.do";
    }

    @RequestMapping({"/main"})
    public String mian(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();
        List<Person> personlist = session.createCriteria(Person.class).list();
        tx.commit();
        request.setAttribute("personlist", personlist);
        return "storexss/main";
    }
    @RequestMapping({"/deletePersonById"})
    public String deletePersonById(@RequestParam("id") String id,HttpServletRequest request, Model model) {
        Session session = this.getSession();
        Transaction tx = session.beginTransaction();
        Person person = (Person) session.createQuery("from Person where id=?").setParameter(0, id).uniqueResult();
        session.delete(person);
        List<Person> personlist = session.createCriteria(Person.class).list();
        tx.commit();
        request.setAttribute("personlist", personlist);
        //model.addAttribute("person", person);
        return "storexss/main";
    }


}