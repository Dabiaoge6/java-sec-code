<%--
  Created by IntelliJ IDEA.
  User: sz
  Date: 2021/9/3
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Pragma" content="public">
<head>
    <title>缺少缓存配置</title>
</head>
<%
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Content-Security-Policy", "referrer no-referrer");//csp响应头
    response.setHeader("X-Frame-Options", "DENY ");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "no-referrer");//引用策略头
%>
<body>
<p>上报漏洞：缺少缓存配置</p>
</body>
</html>
