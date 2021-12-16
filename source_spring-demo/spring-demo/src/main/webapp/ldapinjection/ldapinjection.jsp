<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>ldap-injection</title>
</head>
<body>

	<p>Ldap-Injection</p>
	<div class='fir-tit'>
		<ul class='fir-detail'>
			<li><a
				href="../ldapinjection/ldap1.do?filterExpr=(objectClass=*)">测试javax.naming.directory.DirContext.search(java.lang.String,java.lang.String,javax.naming.directory.SearchControls)</a></li>
			<li><a
				href="../ldapinjection/ldap2.do?filterExpr=(cn=*),(objectClass=*),(ou=*)">测试javax.naming.directory.DirContext.search(java.lang.String,java.lang.String,java.lang.Object[],javax.naming.directory.SearchControls)</a></li>
			<li><a
				href="../ldapinjection/ldap3.do?filterExpr=(objectClass=*)">测试javax.naming.directory.InitialDirContext.search(java.lang.String,java.lang.String,javax.naming.directory.SearchControls)</a></li>
		</ul>
	</div>

</body>
</html>


