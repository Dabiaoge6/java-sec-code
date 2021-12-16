<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="../static/js/jquery-3.1.1.js"></script>
    <title>Register</title>
</head>
<body>
<%
    response.setHeader("X-XSS-Protection","1; mode=block");
    response.setHeader("Content-Security-Policy","referrer no-referrer");//csp响应头
    response.setHeader("X-Frame-Options","DENY ");
    response.setHeader("X-Content-Type-Options","nosniff");
    response.setHeader("Referrer-Policy","no-referrer");
    response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
    response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
    response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0
%>
<h1>注册用户</h1>
<form method="post" name="register" action="../unsafeAutoBinding/binding001.do" autocomplete="off">
    <input type="hidden" id="protectedKey" name="protectedKey" value="${sessionScope.protectedKey}">
    UserName:<input type="text" name="name" id="name"><br>
    Password:<input type="password" name="pwd" id="pwd"><br>
    <input type="submit" id="registerForm" name="Submit" value="提交">
</form>
</body>
</html>