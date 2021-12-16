<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    // 设置 name 和 url cookie
    Cookie name = new Cookie("name",
            request.getParameter("name"));
    // 设置cookie过期时间为24小时。
    name.setMaxAge(60*60*24);
    // 在响应头部添加cookie
    response.addCookie(name);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设置 Cookie</title>
</head>
<body>
</body>
</html>