package org.spring.demo.controller.vulnercontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;


@Controller
@RequestMapping("isit")
public class IsitController {

  private static final Logger logger = LoggerFactory.getLogger(IsitController.class);

  @RequestMapping("/isit001.do")
  public void isit1(@RequestParam("password") String password, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.getBytes() 类型：O2R
     */
    byte[] pwdByte = password.getBytes();
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] text = c.update(pwdByte);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit002.do")
  public void isit2(@RequestParam("account") String account, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] result = account.getBytes();
      byte[] text = c.update(result, 1, result.length - 1);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit003.do")
  public void isit3(@RequestParam("mobile") String mobile, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String algorithm = "DES";
    Cipher c;
    /*
     * java.util.StringTokenizer.&lt;init&gt;(java.lang.String) 类型：P2O
     */
    StringTokenizer st = new StringTokenizer(mobile);
    try {
      while (st.hasMoreTokens()) {
        String result = st.nextToken();
        c = Cipher.getInstance(algorithm);
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        keyGen.init(56);
        Key key = keyGen.generateKey();
        c.init(Cipher.ENCRYPT_MODE, key);
        int text = c.update(result.getBytes(), 1, result.length() - 1, new byte[20]);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit004.do")
  public void isit4(@RequestParam("name") String name, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = name.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[10];
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      int text = c.update(nameByte, 0, nameByte.length, resultByte, resultByte.length);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit5.do")
  public void isit5(@RequestParam("xx") String id, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = id.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[10];
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      int text = c.update(nameByte, 0, nameByte.length, resultByte, resultByte.length);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

//  @RequestMapping("/isit6.do")
//  public void isit6(@RequestParam("xx") String id, HttpServletRequest request,
//      HttpServletResponse response) throws IOException {
//    PrintWriter out = response.getWriter();
//    /*
//     * java.lang.String.substring(int,int) 类型：O2R
//     */
//    String subName = id.substring(1);
//    out.println("the request should report isit-querystring");
//    out.close();
//  }
//
//  @RequestMapping("/isit7.do")
//  public void isit7(@RequestParam("xx") String id, HttpServletRequest request,
//      HttpServletResponse response) throws IOException {
//    PrintWriter out = response.getWriter();
//    /*
//     * java.lang.String.substring(int,int) 类型：O2R
//     */
//    String subName = id.substring(1);
//    out.println("the request don't should report isit-querystring");
//    out.close();
//  }

  @RequestMapping("/isit1_header.do")
  public void isit1Header(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getHeader("password");
    /*
     * java.lang.String.getBytes() 类型：O2R
     */
    byte[] pwdByte = password.getBytes();
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] text = c.update(pwdByte);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    out.println("上报漏洞：不安全的认证协议；不安全的加密算法");
    out.close();
  }

  @RequestMapping("/isit2_header.do")
  public void isit2Header(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getHeader("password");
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] result = password.getBytes();
      byte[] text = c.update(result, 1, result.length - 1);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit3_header.do")
  public void isit3Header(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getHeader("password");
    String algorithm = "DES";
    Cipher c;
    /*
     * java.util.StringTokenizer.&lt;init&gt;(java.lang.String) 类型：P2O
     */
    StringTokenizer st = new StringTokenizer(password);
    try {
      while (st.hasMoreTokens()) {
        String result = st.nextToken();
        c = Cipher.getInstance(algorithm);
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        keyGen.init(56);
        Key key = keyGen.generateKey();
        c.init(Cipher.ENCRYPT_MODE, key);
        int text = c.update(result.getBytes(), 1, result.length() - 1, new byte[20]);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit4_header.do")
  public void isit4Header(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getHeader("password");
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = password.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[10];
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      int text = c.update(nameByte, 0, nameByte.length, resultByte, resultByte.length);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

//  @RequestMapping("/isit1_post.do")
//  public void isit1Post(HttpServletRequest request, HttpServletResponse response)
//      throws IOException {
//    PrintWriter out = response.getWriter();
//    String p1 = request.getQueryString();
//    String password = request.getParameter("password");
//    /*
//     * java.lang.String.getBytes() 类型：O2R
//     */
//    byte[] pwdByte = password.getBytes();
//    String algorithm = "DES";
//    Cipher c;
//    try {
//      c = Cipher.getInstance(algorithm);
//      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
//      keyGen.init(56);
//      Key key = keyGen.generateKey();
//      c.init(Cipher.ENCRYPT_MODE, key);
//      byte[] text = c.update(pwdByte);
//    } catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    } catch (NoSuchPaddingException e) {
//      e.printStackTrace();
//    } catch (InvalidKeyException e) {
//      e.printStackTrace();
//    }
//    out.println("the request should report isit");
//    out.close();
//  }

  @RequestMapping("/isit2_post.do")
  public void isit2Post(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getParameter("password");
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] result = password.getBytes();
      byte[] text = c.update(result, 1, result.length - 1);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit3_post.do")
  public void isit3Post(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getParameter("password");
    String algorithm = "DES";
    Cipher c;
    /*
     * java.util.StringTokenizer.&lt;init&gt;(java.lang.String) 类型：P2O
     */
    StringTokenizer st = new StringTokenizer(password);
    try {
      while (st.hasMoreTokens()) {
        String result = st.nextToken();
        c = Cipher.getInstance(algorithm);
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        keyGen.init(56);
        Key key = keyGen.generateKey();
        c.init(Cipher.ENCRYPT_MODE, key);
        int text = c.update(result.getBytes(), 1, result.length() - 1, new byte[20]);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }

  @RequestMapping("/isit4_post.do")
  public void isit4Post(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String password = request.getQueryString();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    String subName = password.substring(1);
    byte[] nameByte = subName.getBytes();
    byte[] resultByte = new byte[20];
    String algorithm = "DES";
    Cipher c;
    try {
      c = Cipher.getInstance(algorithm);
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(56);
      Key key = keyGen.generateKey();
      c.init(Cipher.ENCRYPT_MODE, key);
      c.update(nameByte, 0, 15, resultByte, 8);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (ShortBufferException e) {
      e.printStackTrace();
    }
    out.println("the request should report isit");
    out.close();
  }



  @RequestMapping("/{phoneNumber}/testPhone.do")
  public void testPhone(HttpServletRequest request, HttpServletResponse response, @PathVariable String phoneNumber)
          throws IOException {
    PrintWriter out = response.getWriter();
    /*
     * java.lang.String.substring(int,int) 类型：O2R
     */
    logger.info("phoneNumber is : " + phoneNumber);
    out.println("the request should report isit");
    out.close();
  }
}
