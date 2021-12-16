package org.spring.demo.customFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.spring.demo.custom.RequestWrapper;

public class RequestFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    //备份HttpServletRequest
    ServletRequest requestWrapper = null;
    if(request instanceof HttpServletRequest) {
      requestWrapper = new RequestWrapper((HttpServletRequest) request);
    }
    //使用流
    InputStream reader = requestWrapper.getInputStream();
    ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(100);
    int i =0;
    byte [] b = new byte[100];
    while((i = reader.read(b))!= -1){
      byteOutput.write(b, 0, i);
    }
    request.setAttribute("post", new String(byteOutput.toByteArray()));
    // pass the request along the filter chain
    if(null == requestWrapper){
      chain.doFilter(request, response);
    } else {
      chain.doFilter(requestWrapper, response);
    }
    reader.close();

    }

  @Override
  public void destroy() {

  }
}
