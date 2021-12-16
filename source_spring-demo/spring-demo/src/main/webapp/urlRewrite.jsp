<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Session URL Rewriting Demo</title>
</head>
<body>
<p>首先要禁用浏览器的cookie,然后再“点击试试”</p>
<a href="<c:url value="message.jsp"/>">点击试试</a>
</body>
</html>