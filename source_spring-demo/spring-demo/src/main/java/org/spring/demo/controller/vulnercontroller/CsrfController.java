package org.spring.demo.controller.vulnercontroller;

import org.service.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("csrf")
public class CsrfController {

  @Autowired
  private UserService userService;

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

  @RequestMapping("/csrf001.do")
  public void fileOutputStream(HttpServletRequest request, HttpServletResponse response) {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("param");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      out = response.getWriter();
      fos = new FileOutputStream(csrfFile, true);
      byte[] context = param.getBytes("GB2312");
      fos.write(context);
      fos.flush();
      out.println("CSRF");
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

  @RequestMapping("/csrf002.do")
  public void fileOutputStreamAuto(HttpServletRequest request, HttpServletResponse response) {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("param");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      out = response.getWriter();
      fos = new FileOutputStream(csrfFile, true);
      byte[] context = param.getBytes("GB2312");
      fos.write(context);
      fos.flush();
      out.println("CSRF");
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
  @RequestMapping("/csrf003.do")
  public void fileOutputStreamAutoTwo(HttpServletRequest request, HttpServletResponse response) {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("param");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      out = response.getWriter();
      fos = new FileOutputStream(csrfFile, true);
      byte[] context = param.getBytes("GB2312");
      fos.write(context);
      fos.flush();
      out.println("CSRF");
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

  @RequestMapping("/csrf004.do")
  public void fileOutputStreamAutoThree(HttpServletRequest request, HttpServletResponse response) {
    FileOutputStream fos = null;
    PrintWriter out = null;
    String param = request.getParameter("param");
    String path = request.getSession().getServletContext().getRealPath("") + "\\csrf";
    File csrfFile = this.getFile(path);
    try {
      out = response.getWriter();
      fos = new FileOutputStream(csrfFile, true);
      byte[] context = param.getBytes("GB2312");
      fos.write(context);
      fos.flush();
      out.println("CSRF");
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
