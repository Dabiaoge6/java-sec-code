<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>页面缺少点击劫持保护	</title>
<%
	response.setHeader("X-XSS-Protection", "1; mode=block");
	response.setHeader("Content-Security-Policy", "referrer no-referrer");//csp响应头
	response.setHeader("X-Content-Type-Options", "nosniff");
	response.setHeader("X-Frame-Options", "");
	response.setHeader("Referrer-Policy", "no-referrer");//引用策略头
	response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader("Expires", 0); //prevent caching at the proxy server
	response.setHeader("Pragma", "no-cache");  //HTTP 1.0
%>
</head>
<body>
<p>上报漏洞：页面缺少点击劫持保护</p>
</body>
</html>