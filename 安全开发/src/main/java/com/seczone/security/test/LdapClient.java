package com.seczone.security.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LdapClient {
    public static void main(String[] args) throws NamingException {
        Context context = new InitialContext();
        context.lookup("ldap://127.0.0.1:9528/Muma1");
    }

}
