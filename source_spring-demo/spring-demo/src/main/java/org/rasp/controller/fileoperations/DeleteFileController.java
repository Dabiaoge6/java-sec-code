package org.rasp.controller.fileoperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hujj on 2020-05-21.
 */
@Controller
@RequestMapping("unsafeFileOperation")
public class DeleteFileController {

  @RequestMapping(value = "/deleteFile.do")
  public void deleteFile(HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    String filename = req.getParameter("filename");
    if(filename != null){
      String path = req.getRealPath("/")+"/"+filename;
      try {
        FileOutputStream os = new FileOutputStream("openrasp.test");
        PrintWriter writer = new PrintWriter(os);
        writer.close();
        File f = new File("openrasp.test");
        f.renameTo(new File(path));
        out.println("文件已创建:" + path + "\n<br/>");
      } catch (Exception e) {
        out.print(e);
      }

      try{
        File file = new File(path);
        if(file.delete()){
          out.println( "文件 " + file.getName() + " 删除成功！" + "\n<br/>");
        }else{
          out.println("文件删除失败！");
        }
      }catch(Exception e){
        out.print(e);
      }
    }
    out.close();
  }

}
