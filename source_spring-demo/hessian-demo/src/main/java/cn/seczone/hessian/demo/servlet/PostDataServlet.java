package cn.seczone.hessian.demo.servlet;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.vulhunter.bean.User;

public class PostDataServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 处理请求
    ServletInputStream sis = req.getInputStream();
    Hessian2Input h2i = new Hessian2Input(sis);

    h2i.startMessage();
    User person = (User) h2i.readObject();
    System.out.println("receive:\n" + person.toString());
    System.out.println(h2i.readString());
    //sql注入

    h2i.completeMessage();
    h2i.close();
    sis.close();

    // 发送响应
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("x-application/hessian");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    Hessian2Output h2o = new Hessian2Output(bos);

    h2o.startMessage();
    h2o.writeObject(new User("chenlei", "sichuang"));
    h2o.writeString("I am server.");
    h2o.completeMessage();
    h2o.close();

    ServletOutputStream sos = resp.getOutputStream();
    sos.write(bos.toByteArray());
    sos.flush();
    bos.close();
    sos.close();
  }
}
