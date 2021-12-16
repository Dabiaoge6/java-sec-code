package org.spring.demo.customFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

public class CustomFilter implements Filter {
  private Logger logger = Logger.getLogger(CustomFilter.class);

  public void init(FilterConfig filterConfig) throws ServletException {
    logger.info("customFilter init");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    logger.info("customFilter doFilter");
  }

  public void destroy() {
    logger.info("customFilter destroy");
  }
}
