package org.spring.demo.controller.vulnercontroller.otherTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestVulStackTraceOfLargeNumber {

  @RequestMapping("/testVulStackTrace001.do")
  public void testVulStackTrace(HttpServletRequest request, HttpServletResponse response) {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("param");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      //csrf001
      out = response.getWriter();
      for(int i=0;i<105;i++){
        fos = new FileOutputStream(csrfFile, true);
        byte[] context = param.getBytes("GB2312");
        fos.write(context);
        fos.flush();
      }
      out.println("CSRF");
      //csrf002
      /*for(int i=0;i<100;i++){
        PrintWriter out1 = new PrintWriter(csrfFile);
        out1.write(param);
      }*/
    } catch (IOException e) {
      e.printStackTrace();
      out.println("ERROR");
    } finally {
      try {
        if (fos != null && out != null) {
          fos.close();
          out.close();
        }
      } catch (IOException e) {
        out.println("ERROR");
      }
    }
  }

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

}
