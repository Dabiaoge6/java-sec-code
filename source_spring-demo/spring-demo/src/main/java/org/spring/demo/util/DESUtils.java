package org.spring.demo.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;

public class DESUtils {

  private static final String ALGORITHM_DES = "DES";
  private static final String PUBLIC_KEY = "_o_1op0ones_wvm8wsxvu_o6";
  public static final String ENCODING = "UTF-8";

  /**
   * 加密操作，接收数组
   *
   * @param source 原文
   * @param seckey 秘钥
   */
  public static byte[] encryptDes(byte[] source, byte[] seckey) {

    Cipher cipher = null;
    try {
      SecretKey secretKey = new SecretKeySpec(seckey, ALGORITHM_DES);
      cipher = Cipher.getInstance(ALGORITHM_DES);

      cipher.init(Cipher.ENCRYPT_MODE, secretKey);

      return cipher.doFinal(source);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 解密处理
   *
   * @param source 密文
   * @param seckey 秘钥
   */
  public static byte[] decryptDes(byte[] source, byte[] seckey) {

    Cipher cipher = null;
    try {
      SecretKey secretKey = new SecretKeySpec(seckey, ALGORITHM_DES);
      cipher = Cipher.getInstance(ALGORITHM_DES);

      cipher.init(Cipher.DECRYPT_MODE, secretKey);

      return cipher.doFinal(source);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 加密处理，接收的和返回的都是字符串，便于系统中的操作和转换
   *
   * @param source 原文
   * @param key 秘钥
   */
  public static String encryptDes(String source, String key) {
    try {
      byte[] src = source.getBytes(ENCODING);
      byte[] keys = key.getBytes(ENCODING);
      return new String(Base64.encode(decryptDes(src, keys)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 解密操作，接收的返回的都是字符串
   *
   * @param source 密文
   * @param key 秘钥
   */
  public static String decryptDes(String source, String key) {
    try {
      byte[] src = source.getBytes(ENCODING);
      byte[] keys = key.getBytes(ENCODING);
      return new String(decryptDes(Base64.decode(src), keys), ENCODING);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  @Deprecated
  public static void main(String[] args) throws Exception {
    //秘钥必须是8的倍数
    String key = "12345678";
    String source = "wsx123456";

    byte[] sec = encryptDes(source.getBytes(ENCODING), key.getBytes(ENCODING));

    String newVal = new String(Base64.encode(sec), ENCODING);

    byte[] origin = decryptDes(Base64.decode(newVal.getBytes(ENCODING)), key.getBytes(ENCODING));

    String oriVal = new String(origin, ENCODING);

    System.out.print(oriVal);
  }
}
