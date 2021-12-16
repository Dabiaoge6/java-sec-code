package org.spring.demo.controller.vulnercontroller.otherTest;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class TestHardKey {
  private final String keyCopy = "MIIE";

  public void test() {
    KeyPairGenerator generator;
    try {
      generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(1024);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

}
