<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>Content-Security-Policy-Missing</title>
</head>
<body>
<p>测试response-header Content-Security-Policy</p>
<a href="../xssProtection/testDisable.do">测试在response-header中设置X-XSS-Protection为0</a><br>
<a href="../xssProtection/testAble.do">测试在response-header中设置X-XSS-Protection为1; mode=block</a>
</body>
</html>


