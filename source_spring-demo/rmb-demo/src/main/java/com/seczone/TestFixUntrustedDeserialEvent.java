package com.seczone;

import cn.webank.rmb.message.Message;
import cn.webank.rmb.message.WeMQMessageUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class TestFixUntrustedDeserialEvent extends TimerTaskEvent {

  @Override
  public void run() {
    startTestFixUntrustedDeserial();
  }

  public void startTestFixUntrustedDeserial() {
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
      JsonObject jsonObject = JsonParser.parseString(message.getContent()).getAsJsonObject();
      String bid = jsonObject.get("bid").getAsString();
      System.out.println("bid =" + bid);
      RMBMessageService.testFixUntrustedDeserial(bid);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}
