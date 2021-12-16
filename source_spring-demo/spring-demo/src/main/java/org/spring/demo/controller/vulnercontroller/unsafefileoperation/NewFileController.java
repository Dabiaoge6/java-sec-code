package org.spring.demo.controller.vulnercontroller.unsafefileoperation;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("newFile")
public class NewFileController {

  @RequestMapping("/newFile001.do")
  public void newFile001(HttpServletRequest request, HttpServletResponse response) {
    String fileName = request.getParameter("fileName");
    String filePath = request.getSession().getServletContext().getRealPath("") + "\\static\\upload";
    File testFile = new File(filePath + File.separator + fileName);
  }

}
