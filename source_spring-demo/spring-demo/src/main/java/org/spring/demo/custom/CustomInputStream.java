package org.spring.demo.custom;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;

public class CustomInputStream extends ServletInputStream {
  private ByteArrayInputStream inputStream;

  public CustomInputStream(byte[] buffer) {
    this.inputStream = new ByteArrayInputStream( buffer );
  }

  @Override
  public int read() throws IOException {
    return inputStream.read();
  }
}
