<%--
  Created by IntelliJ IDEA.
  User: sz
  Date: 2021/9/3
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Frame-Options", "DENY ");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "no-referrer");//引用策略头
    response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
    response.setDateHeader("Expires", 0); //prevent caching at the proxy server
    response.setHeader("Pragma", "no-cache");  //HTTP 1%>
<body>
<p>上报漏洞：缺少CSP响应头</p>
</body>
</html>
