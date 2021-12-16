package org.spring.demo.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

/**
 * Created by hujj on 2020-06-22.
 */
public class JSPView implements View {

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public void render(Map<String, ?> map, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
//==========================乱码问题解决==========================
    //1.让浏览器用UTF-8解析返回数据
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    //2.让servlet用UTF-8转码
    response.setCharacterEncoding("UTF-8");
    response.getWriter().print("自定义视图解析器测试输出：" + map.get("message"));
  }
}
