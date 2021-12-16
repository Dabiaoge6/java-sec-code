package com.seczone;

import cn.webank.rmb.message.Message;
import cn.webank.rmb.message.WeMQMessageUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class AppActivityEvent extends TimerTaskEvent {

  @Override
  public void run() {
    startTest();
  }

  public void startTest() {
    try {
      String body =
          "{\"bid\":\"" + String.valueOf((int) ((Math.random() * 9 + 1) * 1000)) + "\"}";
      org.apache.rocketmq.common.message.Message msg = new org.apache.rocketmq.common.message.Message(
          "topic_example_java",
          "TagA",
          (body).getBytes(RemotingHelper.DEFAULT_CHARSET)
      );
      Message message = WeMQMessageUtil.extractRMBMessageFromWeMQMessage(msg);
      JsonObject jsonObject = JsonParser.parseString(message.getContent()).getAsJsonObject();
      String bid = jsonObject.get("bid").getAsString();
      System.out.println("bid =" + bid);
      RMBMessageService.GetStringValueFromMysql(bid);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    org.apache.rocketmq.common.message.Message msg = null;
    try {
      String body =
          "{\"bid\":\"<?xml version=\\\"1.0\\\" encoding=\\\"utf-8\\\"?><user><name>test-untrustedDeserial</name><mobile>18239439283</mobile></user>\"}";
      msg = new org.apache.rocketmq.common.message.Message(
          "topic_example_java",
          "TagA",
          (body).getBytes(RemotingHelper.DEFAULT_CHARSET)
      );
      Message message = WeMQMessageUtil.extractRMBMessageFromWeMQMessage(msg);
      String consumerSeqNo = getMessageFieldValue(message, "sysHeader", "consumerSeqNo");
      String bizSeqNo = getMessageFieldValue(message, "sysHeader", "bizSeqNo");
      System.out.println(consumerSeqNo + "," + bizSeqNo);
      JsonObject jsonObject = JsonParser.parseString(message.getContent()).getAsJsonObject();
      String bid = jsonObject.get("bid").getAsString();
      System.out.println("bid =" + bid);
//      RMBMessageService.GetStringValueFromMysql(bid);
      RMBMessageService.testFixUntrustedDeserial(bid);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String getMessageFieldValue(Object message, String fieldName,
      String childFieldName) {
    Object messageField = Reflection.getField(message, fieldName);
    String childField = new String();
    if (messageField != null) {
      if ("sysHeader".equals(fieldName)) {
        childField = (String) Reflection.getSuperField(messageField, childFieldName);
      } else {
        childField = (String) Reflection.getField(messageField, childFieldName);
      }
    }
    return childField;
  }


}
