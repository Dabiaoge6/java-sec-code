package org.spring.demo.controller.detectRuleConfig.customWhiteList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("urlWhiteList")
public class UrlWhiteList {

  private File getFile(String path) {
    String fileName = path + "\\" + "csrf.txt";
    File csrfFile = new File(fileName);
    if (!csrfFile.exists()) {
      try {
        csrfFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return csrfFile;
  }

  @RequestMapping("/testUrlWhite.do")
  public void testUrlWhiteList(@RequestParam String text,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("text");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      out = response.getWriter();
      fos = new FileOutputStream(csrfFile, true);
      byte[] context = param.getBytes("GB2312");
      fos.write(context);
      fos.flush();
      out.println("CSRF");
      StringBuffer stringBuffer = request.getRequestURL();
      String uri1 = stringBuffer.substring(0,stringBuffer.lastIndexOf("/urlWhiteList"));
      response.sendRedirect(uri1);
    } catch (IOException e) {
      e.printStackTrace();
      out.println("ERROR");
    } finally {
      try {
        if (fos != null) {
          fos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
        out.println("ERROR");
      }
    }
  }

}
