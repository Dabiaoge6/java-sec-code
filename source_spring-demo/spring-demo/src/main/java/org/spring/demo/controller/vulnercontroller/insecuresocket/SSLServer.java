package org.spring.demo.controller.vulnercontroller.insecuresocket;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

@Controller
@RequestMapping("insecureSocket")
public class SSLServer {

  private static String SERVER_KEY_STORE = "server_ks";
  private static String SERVER_KEY_STORE_PASSWORD = "123123";

  @RequestMapping(value = "/startServer.do")
  public void start(HttpServletRequest request, HttpServletResponse response) {
    String keyStorePath = this.getClass().getClassLoader().getResource("/").getPath()
        .replace("classes", "keystore");
    String path = keyStorePath + File.separator + SERVER_KEY_STORE;
    System
        .setProperty("javax.net.ssl.trustStore", path);
    System.setProperty("javax.net.ssl.keyStorePassword", SERVER_KEY_STORE_PASSWORD);
    SSLContext context;
    ServerSocket _socket = null;
    SSLSocket clientSocket = null;
    try {
      context = SSLContext.getInstance("TLS");
      KeyStore ks = KeyStore.getInstance("jceks");
      ks.load(new FileInputStream(path), null);
      KeyManagerFactory kf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());

      context.init(kf.getKeyManagers(), null, null);

      ServerSocketFactory factory = context.getServerSocketFactory();
      _socket = factory.createServerSocket(53241);
      System.out.println("serversocketInetAddress====" + _socket.getInetAddress());
      System.out.println("serversocketHostName====" + _socket.getInetAddress().getHostName());
      System.out.println("serversocketHostAddress====" + _socket.getInetAddress().getHostAddress());
      ((SSLServerSocket) _socket).setNeedClientAuth(false);
      while (true) {
        clientSocket = (SSLSocket) _socket.accept();
        Connection clientConnection = new Connection(clientSocket);
        Thread clientThread = new Thread(clientConnection);
        System.out.println("Client " + clientThread.getId() + " is connected");
        clientThread.run();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
        if (_socket != null) {
          _socket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }
}

class Connection implements Runnable {

  private Socket clientSocket = null;

  public Connection(SSLSocket sslsocket) {
    clientSocket = sslsocket;
  }

  public void run() {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      while (true) {
        String line = reader.readLine();
        if (line == null) {
          System.out.println("Communication end.");
          break;
        }
        System.out.println("Receive message: " + line);
      }
      reader.close();
      clientSocket.close();
    } catch (IOException ioExp) {
      ioExp.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
