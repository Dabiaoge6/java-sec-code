package org.spring.demo.controller.vulnerRepeat;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cVulner")
public class CVulnerController {

  @RequestMapping("/cRepeat001.do")
  public void cRepeat001(@RequestParam("isitKey") String name, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = name.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[10];
    String algorithm = "DES";
    Cipher c = Cipher.getInstance(algorithm);
    KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
    keyGen.init(56);
    Key key = keyGen.generateKey();
    c.init(Cipher.ENCRYPT_MODE, key);
    int text = c.update(nameByte, 0, nameByte.length, resultByte, resultByte.length);
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/cRepeat002.do")
  public void cRepeat002(@RequestParam("isitKey") String name, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = name.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[10];
    String algorithm = "DES";
    Cipher c = Cipher.getInstance(algorithm);
    KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
    keyGen.init(56);
    Key key = keyGen.generateKey();
    c.init(Cipher.ENCRYPT_MODE, key);
    int text = c.update(nameByte, 0, nameByte.length, resultByte, resultByte.length);
    out.println("the request should report isit");
    out.close();
  }

}
