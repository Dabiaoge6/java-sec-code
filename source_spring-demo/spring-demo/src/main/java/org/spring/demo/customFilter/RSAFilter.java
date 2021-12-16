package org.spring.demo.customFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RSAFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      HttpServletRequest request1 = (HttpServletRequest)request;
      HttpSession session = request1.getSession();
      Object obj = request1.getSession().getAttribute("pubKey"); // 原始数据
      if (null != obj) {
        // 移除原始session
        session.removeAttribute("pubKey");
      }
      String basePath = session.getServletContext().getRealPath("/");
      /*PublicKey publicKey = RSAUtils.getKeyPair(basePath).getPublic();
      String modulus = ((RSAPublicKey) publicKey).getModulus().toString(16);
      String pubExep = ((RSAPublicKey) publicKey).getPublicExponent().toString(16);
      byte[] keyBytes = publicKey.getEncoded();
      String s = (new BASE64Encoder()).encode(keyBytes);
      session.setAttribute("pubKey", s);
      session.setAttribute("modulus",modulus);
      session.setAttribute("pubExep",pubExep);*/
      chain.doFilter(request,response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void destroy() {

  }
}
