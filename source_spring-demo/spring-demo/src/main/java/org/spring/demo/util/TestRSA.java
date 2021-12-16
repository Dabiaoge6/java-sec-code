package org.spring.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestRSA {

  public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQgEoj3z9JrdPNI23DbMQkl3gkGuDke7iBr5yrYyqolkTyxuBLWFwHNuGv4VKOj9fXg61QxpaJ/fxDBvMvmkBSRowHBloGFceVTx8wV/8u0DcjvTCu0IZ1zp6wjG6xBn5j66Sg/q+9hvaY2p7fkKmsvcW6VoNPgQHU1Cf01DLZmQIDAQAB+oXcINOiE3AsuZ4VJmwNZg9Y/7fY+OFRS2JAh5YMsrv2qyoGP+Z9ksre26NYR+Lt91B2lhdwJHLpQpziaANZm/ONb31fj/lwIDAQAB";
  public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCASiPfP0mt080jbcNsxCSXeCQa4OR7uIGvnKtjKqiWRPLG4EtYXAc24a/hUo6P19eDrVDGlon9/EMG8y+aQFJGjAcGWgYVx5VPHzBX/y7QNyO9MK7QhnXOnrCMbrEGfmPrpKD+r72G9pjant+Qqay9xbpWg0+BAdTUJ/TUMtmZAgMBAAECgYBSozY/Z4FW+31h5fPgK+DFu/8TGFAgXuTvCaJnz2Md9IkZTDejxT6cYWUr53toI5zhvz/XLw6FXNQ54KxMJq/s9PiZYUgq/PMrnyU4gBSTm5BmiWjdaGicVEZ1lofHjpkAchPNW/CzwxD8AeKI7QaObE+EkWbLAi6sa+nRdHKgrQJBAOwYLD2DncU15XCKS0RNzTrNohdBQcisOPHdtQO0CGZlxx3xjuU4WL6/EpdmbjTeYbOSDKCmY5vyVbYZdOWfEs8CQQDiFIwWpvW2WLxLVw3i2P55WmMMXuecwEzg++ae3Ht7nW0zNcWSsyvHh40sM8XqEzmWOzMY6JOePbkuVfWTc4cXAkBRzf5mQhiEoKwjVofF3v9hhKbJT/8vPR1uENgLtHHEqTdZFL3ihqeZUDNs6jz9bKCFy/E8KOsSueEg+6kZdwjZAkEAj2RW4fstd2VasDJb5ViaNqAEmJENOBej60L6KCJR07qqy0M8t+oaR2iLOtDvo6Jj8QxFQXQqRMCDVodAxjANKwJAL3KuaqA6kdy9RxdV3uP8nRXLY7C/1ZIK6U0pyZqKXEwpD+7Ar3hwwhPz9TeuoqjB/cCknZjw70BQFQ0/VUHW2g==";

  private static String algorithm = "RSA"; //$NON-NLS-1$
  private static final int MAX_ENCRYPT_BLOCK = 117;
  private static final int MAX_DECRYPT_BLOCK = 128;
  private static String data = "hello"; //$NON-NLS-1$

  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
    /*String test = testEncrypt(privateKey,data);
    String testDecrypt = testDecrypt(publicKey, test);
    System.out.println(testDecrypt);*/

  }

  /**
   * 加密
   * @param key
   * @param data
   */
  /*public static String testEncrypt(String key,String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException{
    byte[] decode = Base64.getDecoder().decode(key);
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
    KeyFactory kf = KeyFactory.getInstance(algorithm);
    PrivateKey generatePrivate = kf.generatePrivate(pkcs8EncodedKeySpec);
    Cipher ci = Cipher.getInstance(algorithm);
    ci.init(Cipher.ENCRYPT_MODE, generatePrivate);

    byte[] bytes = data.getBytes();
    int inputLen = bytes.length;
    int offLen = 0;//偏移量
    int i = 0;
    ByteArrayOutputStream bops = new ByteArrayOutputStream();
    while(inputLen - offLen > 0){
      byte [] cache;
      if(inputLen - offLen > MAX_ENCRYPT_BLOCK){
        cache = ci.doFinal(bytes, offLen,MAX_ENCRYPT_BLOCK);
      }else{
        cache = ci.doFinal(bytes, offLen,inputLen - offLen);
      }
      bops.write(cache);
      i++;
      offLen = MAX_ENCRYPT_BLOCK * i;
    }
    bops.close();
    byte[] encryptedData = bops.toByteArray();
    String encodeToString = Base64.getEncoder().encodeToString(encryptedData);
    return encodeToString;
  }*/




  /**
   * 解密
   * @param key
   * @param data
   */
  /*public static String testDecrypt(String key,String data) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
    byte[] decode = Base64.getDecoder().decode(key);
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
    KeyFactory kf = KeyFactory.getInstance(algorithm);
    PublicKey generatePublic = kf.generatePublic(x509EncodedKeySpec);
    Cipher ci = Cipher.getInstance(algorithm);
    ci.init(Cipher.DECRYPT_MODE,generatePublic);

    byte[] bytes = Base64.getDecoder().decode(data);
    int inputLen = bytes.length;
    int offLen = 0;
    int i = 0;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while(inputLen - offLen > 0){
      byte[] cache;
      if(inputLen - offLen > MAX_DECRYPT_BLOCK){
        cache = ci.doFinal(bytes,offLen,MAX_DECRYPT_BLOCK);
      }else{
        cache = ci.doFinal(bytes,offLen,inputLen - offLen);
      }
      byteArrayOutputStream.write(cache);
      i++;
      offLen = MAX_DECRYPT_BLOCK * i;

    }
    byteArrayOutputStream.close();
    byte[] byteArray = byteArrayOutputStream.toByteArray();
    return new String(byteArray);
  }*/


}
