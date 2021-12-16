package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class LdapInjectionTest {
	
	@Test
	public void ldap1(){
		String path = "/ldapinjection/ldap1.do?filterExpr=(objectClass=*)";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void ldap2(){
		String path = "/ldapinjection/ldap2.do?filterExpr=(cn=*),(objectClass=*),(ou=*)";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void ldap3(){
		String path = "/ldapinjection/ldap3.do?filterExpr=(objectClass=*)";
		HttpClientUtil.get(path);
	}

}
