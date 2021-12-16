<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Test Weak Password Policy</title>
</head>

<body>
<!--只负责让seeker能收集到id和name为pwd1的密码框，用于测试weak-password-policy-->
<form method="post" id="login" action="../weakPasswordPolicy/weakPwdPolicy001.do?pwd1=test">
    UserName:<input type="text" name="name" id="name"/><br>
    Password:<input type="password" name="pwd1" id="pwd1"/><br>
    <input type="button" id="login-button" name="Submit" value="提交"/>
</form>
</body>
</html>