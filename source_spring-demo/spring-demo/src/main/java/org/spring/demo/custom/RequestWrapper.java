package org.spring.demo.custom;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

  private final byte[] body;

  public RequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    InputStream is = request.getInputStream();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte buff[] = new byte[ 1024 ];
    int read;
    while( ( read = is.read( buff ) ) > 0 ) {
      baos.write( buff, 0, read );
    }
    body = baos.toByteArray();
    baos.close();
    is.close();
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    /*final ByteArrayInputStream bais = new ByteArrayInputStream(body);
    return new ServletInputStream() {

      @Override
      public int read() throws IOException {
        return bais.read();
      }
    };*/
    return new CustomInputStream( this.body );
  }
}
