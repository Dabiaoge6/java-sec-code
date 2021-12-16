package cn.seczone.hessian.demo.service;

import java.sql.SQLException;
import org.vulhunter.bean.User;

public interface BasicService {

  public User getUser(String name,String pwd);

  public void getUserByName(String name);

}
