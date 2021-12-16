<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="../static/js/jquery-3.1.1.js"></script>
	<title>登录</title>
</head>
<body>
<form method="post" id="login" action="../secondInjection/login.do">
	UserName:<input type="text" name="name" id="name"><br>
	Password:<input type="password" name="pwd" id="pwd"><br>
	<input type="submit" id="login-button" name="Submit" value="login">
	<br>
	<p><a href="update.jsp">update current user pwd,please click!</a></p>
</form>
</body>
</html>