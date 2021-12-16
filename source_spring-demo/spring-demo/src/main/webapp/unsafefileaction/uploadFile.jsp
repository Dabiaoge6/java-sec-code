<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
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
<h3>1.文件上传漏洞 2.跨站请求伪造</h3><br>
<form action="../upload/upload002.do" enctype="multipart/form-data" method="post" autocomplete="off">
    <input type="file" name="file"><br>
    <input type="submit" value="upload">
</form>
</body>
</html>