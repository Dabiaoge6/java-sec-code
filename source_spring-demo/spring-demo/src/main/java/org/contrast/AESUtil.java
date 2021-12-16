package org.contrast;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil
{
  private static final String a = "AES";
  private static final String b = "AES/ECB/NoPadding";
  private static final String c = "AES/ECB/PKCS5Padding";
  private static final int d = 74;
  private static final int e = 79;
  private static final int f = 42;

  public static byte[] encrypt(byte[] paramArrayOfByte)
      throws GeneralSecurityException
  {
    byte[] arrayOfByte = a();
    Cipher localCipher = Cipher.getInstance("AES");
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte, "AES");
    localCipher.init(1, localSecretKeySpec);
    return localCipher.doFinal(paramArrayOfByte);
  }

  public static byte[] decrypt(byte[] paramArrayOfByte) throws GeneralSecurityException {
    byte[] arrayOfByte1 = a();
    byte[] arrayOfByte2 = null;
    SecretKeySpec localSecretKeySpec;
    try {
      Cipher localCipher1 = Cipher.getInstance("AES");
      localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
      localCipher1.init(2, localSecretKeySpec);
      arrayOfByte2 = localCipher1.doFinal(paramArrayOfByte);
    } catch (GeneralSecurityException localGeneralSecurityException1) {
//      M.a("Failed decrypting attempt (no params)");
    }

    if (arrayOfByte2 == null) {
      try {
        Cipher localCipher2 = Cipher.getInstance("AES/ECB/NoPadding");
        localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
        localCipher2.init(2, localSecretKeySpec);
        arrayOfByte2 = localCipher2.doFinal(paramArrayOfByte);
      } catch (GeneralSecurityException localGeneralSecurityException2) {
//        M.a("Failed decrypting attempt (no padding)");
      }
    }

    if (arrayOfByte2 == null) {
      Cipher localCipher3 = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
      localCipher3.init(2, localSecretKeySpec);
      arrayOfByte2 = localCipher3.doFinal(paramArrayOfByte);
    }

    return arrayOfByte2;
  }

  private static byte[] a() {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("!");
    localStringBuilder.append('*');
    localStringBuilder.append('O');
    localStringBuilder.append('J');
    localStringBuilder.append("Base64Util".charAt(3));
    localStringBuilder.append("ClasspathUtil".charAt(0));
    localStringBuilder.append("IOUtil".charAt(0));
    localStringBuilder.append("EncodingUtil".charAt(1));
    localStringBuilder.append("!");
    localStringBuilder.append('*');
    localStringBuilder.append('O');
    localStringBuilder.append('J');
    localStringBuilder.append("Base64Util".charAt(3));
    localStringBuilder.append("ClasspathUtil".charAt(0));
    localStringBuilder.append("IOUtil".charAt(0));
    localStringBuilder.append("EncodingUtil".charAt(1));

    byte[] arrayOfByte = null;
    try {
      arrayOfByte = localStringBuilder.reverse().toString().getBytes("ASCII");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}

    return arrayOfByte;
  }
}
