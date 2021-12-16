<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>Test Redos</title>
</head>
<body>
<form method="post" id="login" action="../redos/redos001.do">
    UserName:<input type="text" name="name" id="name"><br>
    Password:<input type="password" name="pwd" id="pwd"><br>
    <input type="submit" id="login-button" name="Submit" value="提交">
</form><br>
<p>使用request.getRequestURI作为污点参数</p>
<a href="../redos/redos002.do">使用request.getRequestURI作为污点参数</a>
</body>
</html>


