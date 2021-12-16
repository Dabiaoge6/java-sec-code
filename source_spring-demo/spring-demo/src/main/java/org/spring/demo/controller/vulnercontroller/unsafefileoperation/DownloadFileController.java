package org.spring.demo.controller.vulnercontroller.unsafefileoperation;

import java.io.*;
import java.net.URLEncoder;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("download")
public class DownloadFileController {

  @RequestMapping("/download001.do")
  public void downloadFile001(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      //1.从请求中获取待下载的文件名
      String fileName = request.getParameter("filename");
      String path = request.getSession().getServletContext().getRealPath("/");
      File file = new File(path + fileName);
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        String filename = URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setContentType("multipart/form-data");
        ServletOutputStream out = response.getOutputStream();
        byte tmp[] = new byte[1024];
        int len = 0;
        //2.读取文件内容
        while ((len = fileInputStream.read(tmp)) != -1) {
          //3.把读取到文件内容写进response中；
          out.write(tmp, 0, len);
        }
        out.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
