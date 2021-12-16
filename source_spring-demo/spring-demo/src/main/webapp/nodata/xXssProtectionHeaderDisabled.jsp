<%--
  Created by IntelliJ IDEA.
  User: sz
  Date: 2021/9/3
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无效的X-XSS-Protection响应头</title>
</head>
<%
    response.setHeader("Content-Security-Policy", "referrer no-referrer");//csp响应头
    response.setHeader("X-Frame-Options", "DENY ");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "no-referrer");//引用策略头
    response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
    response.setDateHeader("Expires", 0); //prevent caching at the proxy server
    response.setHeader("Pragma", "no-cache");  //HTTP 1.0
%>
<body>
<p>上报漏洞：无效的X-XSS-Protection响应头</p>
</body>
</html>
