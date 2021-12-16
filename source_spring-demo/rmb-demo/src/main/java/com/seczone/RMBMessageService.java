package com.seczone;

import cn.webank.rmb.message.Message;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.xstream.XStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.vulhunter.bean.User;
import org.vulhunter.common.untrustdeserialization.XStreamUtil;

/**
 * Created by hujj on 2020-07-06.
 */
public class RMBMessageService {

  private static Connection conn = null;

  static {
    String url = "jdbc:mysql://db.hf.seczone.cn:3306/APPdb";
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(url, "root", "seczone@123");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void addBatchAndCleanBatch(String bid) {
    Statement stmt;
    try {
      stmt = conn.createStatement();
      String sql = "select * from app1_user where id='" + bid + "';";
      System.out.println(
          "-----------------------------------query sql----------------------------------------");
      System.out.println(sql);
      stmt.addBatch(sql);
      stmt.clearBatch();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void addBatchGson(Message message) {
    Statement stmt;
    try {
      stmt = conn.createStatement();
      Gson gson = new Gson();
      User user = gson.fromJson(message.getContent(), User.class);
      String name = user.getName();
      String pwd = user.getPwd();
      String sql1 = "select * from app1_user where username = '" + name + "'";
      String sql2 = " AND pwd = '" + pwd + "';";
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(sql1);
      stringBuilder.append(sql2);
      String sql = stringBuilder.toString();
      System.out.println(
          "-----------------------------------query sql----------------------------------------");
      System.out.println(sql);
      stmt.addBatch(sql);
      stmt.clearBatch();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static String GetStringValueFromMysql(String bid) throws Exception {
    try {

      String statement = "select * from app1_user where id='" + bid + "';";
      PreparedStatement prepareStat = conn.prepareStatement(statement);
      ResultSet resultSet = prepareStat.executeQuery();
      if (resultSet.next()) {
        System.out.println(resultSet.getString("pwd"));
        return resultSet.getString("pwd");
      } else {
        throw new Exception("tdsql bid & key不存在, bid: " + bid);
      }
    } catch (Exception e) {
      throw new Exception("tdsql bid & key不存在, bid:  " + bid);
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        throw new Exception("tdsql bid & key不存在, bid: " + bid);
      }
    }
  }

  public static void addBatchAndCleanBatch1(Message message) {
    Statement stmt;
    try {
      stmt = conn.createStatement();
      String name = message.getContent();
      String sql1 = "select * from app1_user where username = '" + name + "'";
      String sql2 = " AND pwd = '" + "123" + "';";
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(sql1);
      stringBuilder.append(sql2);
      String sql = stringBuilder.toString();
      System.out.println(
          "-----------------------------------query sql----------------------------------------");
      System.out.println(sql);
      stmt.addBatch(sql);
      stmt.clearBatch();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void testFileWrite(String path) {
    FileOutputStream fos = null;
    String mycontent = "This is my Data which needs to be written into the file.";
    byte[] bytesArray = mycontent.getBytes();
    try {
      fos = new FileOutputStream(path);
      fos.write(bytesArray);
      fos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("File Written Successfully");
  }

  public static void testFixUntrustedDeserial(String xmlString) {
    org.vulhunter.common.untrustdeserialization.User user = (org.vulhunter.common.untrustdeserialization.User) XStreamUtil
        .fromXML(xmlString, org.vulhunter.common.untrustdeserialization.User.class);
    System.out.println("fix test =====>" + user.toString());
  }

  public static void testUntrustedDeserial(String xmlString) {
    XStream xstream = new XStream();
    xstream.alias("user", org.vulhunter.common.untrustdeserialization.User.class);
    org.vulhunter.common.untrustdeserialization.User user = (org.vulhunter.common.untrustdeserialization.User) xstream
        .fromXML(xmlString);
    System.out.println("test =====>" + user.toString());
  }

  public static void testUntrustedDeserialFromMessage(Message message) {
    JsonObject jsonObject = JsonParser.parseString(message.getContent()).getAsJsonObject();
    String xmlString = jsonObject.get("bid").getAsString();
    XStream xstream = new XStream();
    xstream.alias("user", org.vulhunter.common.untrustdeserialization.User.class);
    org.vulhunter.common.untrustdeserialization.User user = (org.vulhunter.common.untrustdeserialization.User) xstream
        .fromXML(xmlString);
    System.out.println("test =====>" + user.toString());
  }

}
