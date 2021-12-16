package org.spring.demo.controller.vulnerFix.ldap;

import org.owasp.esapi.ESAPI;
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
@RequestMapping("ldapFix")
public class LdapFixController {
    private Hashtable start() throws NamingException{
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.2.168:389");
        return env;
    }

    private void print(NamingEnumeration<SearchResult> results,HttpServletResponse response) throws IOException, NamingException{
        PrintWriter out = response.getWriter();
        while (results != null && results.hasMore()) {
            SearchResult sr = (SearchResult) results.next();
            String dn = sr.getName();
            out.println("Distinguished Name is " + dn);
            Attributes attrs = sr.getAttributes();
            for (NamingEnumeration ne = attrs.getAll(); ne.hasMoreElements();) {
                Attribute attr = (Attribute) ne.next();
                String attrID = attr.getID();
                out.println(attrID + ": ");
                for (Enumeration vals = attr.getAll(); vals.hasMoreElements();) {
                    out.println("\t " + vals.nextElement());
                }
            }
            out.println("\n ");
        }
    }
    /**
     * ldap 编码
     */
    @RequestMapping("/ldapEncode.do")
    public void ldap1(HttpServletRequest request, HttpServletResponse response) {
        try {
            String filterExpr = request.getParameter("filterExpr");
            String filterExprEncode = ESAPI.encoder().encodeForLDAP(filterExpr);
            Hashtable env = this.start();
            DirContext dct = new InitialDirContext(env);
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> results = dct.search("dc=mycompany,dc=com", filterExprEncode, constraints);
            this.print(results, response);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
