package org.contrast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import org.apache.commons.io.IOUtils;

/**
 * Created by hujj on 2020-06-29.
 */
public class TestDecrypt {

  public static void main(String[] args) {
    InputStream localInputStream = ClassLoader.getSystemResourceAsStream("cmdi/patterns.json");
    FileOutputStream outputStream = null;
    try {
      byte[] arrayOfByte1 = IOUtils.toByteArray(localInputStream);
      byte[] arrayOfByte2 = AESUtil.decrypt(arrayOfByte1);
//      ByteArrayInputStream inputStream = new ByteArrayInputStream(arrayOfByte2);
      String str = new String(arrayOfByte2);
      System.out.println(str);
      /*String path = ClassLoader.getSystemClassLoader().getResource("sqli").getPath();
      File jsonFile = new File(
          path+"/test.json");
      outputStream = new FileOutputStream(jsonFile);
      outputStream.write(arrayOfByte2);*/
    } catch (IOException e) {
      e.printStackTrace();
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    } finally {
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
