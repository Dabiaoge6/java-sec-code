package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

@Controller
@RequestMapping("ldapinjection")
public class LdapInjectionController {

  private static Hashtable env = new Hashtable();

  static {
    String ipHostAddress = "ldap.hf.seczone.cn";
    String URL = "ldap://" + ipHostAddress + ":389";
    System.out.print("URL=======>" + URL);
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, URL);
  }

  private void print(NamingEnumeration<SearchResult> results, HttpServletResponse response)
      throws IOException, NamingException {
    PrintWriter out = response.getWriter();
    while (results != null && results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      String dn = sr.getName();
      out.println("Distinguished Name is " + dn);
      Attributes attrs = sr.getAttributes();
      for (NamingEnumeration ne = attrs.getAll(); ne.hasMoreElements(); ) {
        Attribute attr = (Attribute) ne.next();
        String attrID = attr.getID();
        out.println(attrID + ": ");
        for (Enumeration vals = attr.getAll(); vals.hasMoreElements(); ) {
          out.println("\t " + vals.nextElement());
        }
      }
      out.println("\n ");
    }
  }

  @RequestMapping("/ldap1.do")
  public void ldap1(@RequestParam("filterExpr") String filterExpr, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      DirContext dct = new InitialDirContext(env);
      SearchControls constraints = new SearchControls();
      constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
      NamingEnumeration<SearchResult> results = dct
          .search("dc=seczone,dc=cn", filterExpr, constraints);
      this.print(results, response);
      response.getWriter().println("上报漏洞：LADP注入");
    } catch (NamingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
