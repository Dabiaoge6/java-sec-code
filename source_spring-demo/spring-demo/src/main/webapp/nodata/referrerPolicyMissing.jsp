<%--
  Created by IntelliJ IDEA.
  User: sz
  Date: 2021/9/3
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>缺少引用策略响应头</title>
</head>
<%
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Content-Security-Policy", "referrer no-referrer");//csp响应头
    response.setHeader("X-Frame-Options", "DENY ");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
    response.setDateHeader("Expires", 0); //prevent caching at the proxy server
    response.setHeader("Pragma", "no-cache");  //HTTP 1.0
%>
<body>
<p>上报漏洞：缺少引用策略响应头</p>
</body>
</html>
