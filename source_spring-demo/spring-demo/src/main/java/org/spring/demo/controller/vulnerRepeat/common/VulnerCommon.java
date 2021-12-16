package org.spring.demo.controller.vulnerRepeat.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

public class VulnerCommon {

  public void md5() throws NoSuchAlgorithmException {
    MessageDigest instance = MessageDigest.getInstance("MD5");
  }

  public void testMD5(HttpServletRequest request) throws NoSuchAlgorithmException {
    if(request == null){
      return;
    }
    md5();
  }
}
