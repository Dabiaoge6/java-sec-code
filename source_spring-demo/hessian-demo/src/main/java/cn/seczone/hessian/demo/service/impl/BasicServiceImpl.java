package cn.seczone.hessian.demo.service.impl;

import cn.seczone.hessian.demo.service.BasicService;
import com.caucho.hessian.server.HessianServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.vulhunter.bean.User;
import org.vulhunter.common.sqlinjection.ExecuteSql;

public class BasicServiceImpl extends HessianServlet implements BasicService {

  public User getUser(String name, String pwd) {
    return new User("hessian", "seczone");
  }

  @Override
  public void getUserByName(String name) {
    String sql = "select * from user where USERNAME = '" + name + "';";
    System.out.println(sql);
    ResultSet set = ExecuteSql.execute(sql);
  }


}
