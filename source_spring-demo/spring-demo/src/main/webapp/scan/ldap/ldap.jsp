<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Ldap Injection Test</title>
</head>

<body>

<div>
<li>Ldap Injection Test：</li>
<form id="form" action="../../scanIssue/testLdapInject.do" method="post">
    uid:<input type="text" id="uid" name="uid">
	<input type="submit" value="提交">
</form>
<br>
</div>

<script type="text/javascript">

</script>

</body>
</html>


