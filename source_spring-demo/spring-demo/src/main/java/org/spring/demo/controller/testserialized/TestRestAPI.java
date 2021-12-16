package org.spring.demo.controller.testserialized;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.spring.demo.User;

public class TestRestAPI {

  public static void main(String[] args) {
//    TestObject testObject = new TestObject();
//    testObject.setStr("Hello There!");
//    String serverURL = "http://localhost:9090/infinispan/rest/___defaultcache/hello";
    User user = new User();
    user.setName("hujj");
    user.setPwd("seczone");
    String serverURL = "http://localhost:8086/spring-demo/login_restapi.do";
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(user);

    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      putObject(serverURL, user);
      /*TestObject tObj = (TestObject) getObject();
      System.out.println("Got the object back successfully: "+tObj.getStr());*/
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Object getObject() throws Exception{
    HttpClient client = new HttpClient();
    HttpMethod get = new GetMethod("http://localhost:9090/infinispan/rest/___defaultcache/hello");
    get.setRequestHeader("Accept", "application/x-java-serialized-object");
    client.executeMethod(get);
    System.out.println(get.getStatusCode());
    ObjectInputStream in = new ObjectInputStream(get.getResponseBodyAsStream());
    return in.readObject();
  }

  private static void putObject(String urlServerAddress, Object obj) throws IOException {
    System.out.println("----------------------------------------");
    System.out.println("Executing PUT");
    System.out.println("----------------------------------------");
    URL address = new URL(urlServerAddress);
    System.out.println("executing request " + urlServerAddress);
    HttpURLConnection connection = (HttpURLConnection) address.openConnection();
    System.out.println("Executing put method of value: " + obj);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-java-serialized-object");
    connection.setDoOutput(true);

    ObjectOutputStream outputStreamWriter = new ObjectOutputStream(connection.getOutputStream());
    outputStreamWriter.writeObject(obj);
    connection.connect();
    outputStreamWriter.flush();
    outputStreamWriter.close();
    System.out.println("----------------------------------------");
    System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
    System.out.println("----------------------------------------");
    connection.disconnect();
  }
}
