package org.spring.demo.controller.vulnercontroller.insecuresocket;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.insecuresocket.InsecureSocketCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


@Controller
@RequestMapping("insecureSocket")
public class SSLClient {

  private static String CLIENT_KEY_STORE = "client_ks";

  @RequestMapping(value = "/startClient.do")
  public void startClient1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("").getPath()
        .replace("classes", "keystore");
    if(keyStorePath.contains("/")){
      keyStorePath.replace('/',File.separatorChar);
    }
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath.substring(1,keyStorePath.length()-1) + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert1();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @RequestMapping(value = "/startClient2.do")
  public void startClient2(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore").substring(1);
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert2();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @RequestMapping(value = "/startClient3.do")
  public void startClient3(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore");
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert3();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @RequestMapping(value = "/startClient4.do")
  public void startClient4(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore");
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert4();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @RequestMapping(value = "/startClient5.do")
  public void startClient5(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore");
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert5();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @RequestMapping(value = "/startClient6.do")
  public void startClient6(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore");
    System
        .setProperty("javax.net.ssl.trustStore", keyStorePath + File.separator + CLIENT_KEY_STORE);
    System.setProperty("javax.net.debug", "ssl,handshake");
    String message = request.getParameter("message");
    PrintWriter out = response.getWriter();
    Socket s = null;
    try {
      s = InsecureSocketCommon.clientWithoutCert6();
      PrintWriter writer = new PrintWriter(s.getOutputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      writer.println(message);
      writer.flush();
      out.println(reader.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }
}
