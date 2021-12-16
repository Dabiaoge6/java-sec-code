package org.spring.demo.controller.vulnercontroller.sensitivelog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("sensitivelog")
public class SensitiveLogController {

  private static Logger logger = LoggerFactory.getLogger(SensitiveLogController.class);

  @RequestMapping(value = "/sensitiveLog001.do")
  public void testTrace5(HttpServletRequest request, HttpServletResponse response) {
    String myInfo = request.getParameter("myInfo");
    Mac digest;
    try {
      digest = Mac.getInstance("HmacSHA512");
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      digest.init(keyGenerator.generateKey());
      byte[] result = myInfo.getBytes();
      digest.update(result);
      String resultStr = new String(result, 0, result.length);
      logger.trace(resultStr);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/sensitiveLog002.do")
  public void testTrace6(HttpServletRequest request, HttpServletResponse response) {
    String myInfo = request.getParameter("myInfo");
    Mac digest;
    String resultStr = "";
    try {
      digest = Mac.getInstance("HmacSHA512");
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      digest.init(keyGenerator.generateKey());
      byte[] result = myInfo.getBytes();
      digest.update(result, 1, result.length - 1);
      String result1 = new String(result, 0, result.length);
      resultStr = result1.concat("�����㷨����");
      logger.trace(resultStr, new Exception());
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/sensitiveLog003.do")
  public void testWarn2(HttpServletRequest request, HttpServletResponse response) {
    String myInfo = request.getParameter("myInfo");
    byte[] result = myInfo.getBytes();
    Mac digest;
    try {
      digest = Mac.getInstance("HmacSHA512");
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      digest.init(keyGenerator.generateKey());
      digest.update(result);
      String resultStr = new String(result, 0, result.length);
      logger.warn(resultStr);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/sensitiveLog004.do")
  public void testDebug2(HttpServletRequest request, HttpServletResponse response) {
    String myInfo = request.getParameter("myInfo");
    byte[] result = myInfo.getBytes();
    Mac digest;
    try {
      digest = Mac.getInstance("HmacSHA512");
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      digest.init(keyGenerator.generateKey());
      digest.update(result, 1, result.length - 1);
      String resultStr = new String(result, 0, result.length);
      logger.debug(resultStr);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/sensitiveLog005.do")
  public void testError1(HttpServletRequest request, HttpServletResponse response)
      throws NoSuchAlgorithmException {
    String myInfo = request.getParameter("myInfo");
    MessageDigest digest = MessageDigest.getInstance("SHA-1");
    byte[] result = myInfo.getBytes();
    digest.update(result);
    String resultStr = new String(result, 1, result.length - 1);
    logger.error(resultStr);
  }

  @RequestMapping(value = "/sensitiveLog006.do")
  public void testInfo1(HttpServletRequest request, HttpServletResponse response)
      throws NoSuchAlgorithmException {
    String myInfo = request.getParameter("myInfo");
    MessageDigest digest = MessageDigest.getInstance("MD2");
    byte[] result = myInfo.getBytes();
    digest.update(result, 1, result.length - 1);
    String resultStr = new String(result, 1, result.length - 1);
    logger.info(resultStr);
  }

  @RequestMapping(value = "/sensitiveLog008.do")
  public void testTrace7(HttpServletRequest request, HttpServletResponse response) {
    String mobile = request.getParameter("mobile");
    logger.trace("手机号码为：" + mobile);
  }
}
