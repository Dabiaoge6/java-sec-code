<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>csrf</title>
</head>
<body>
<form action="../../scanIssue/csrf/login.do" method="post">
    Username: <input type="text" name="name" value="admin"> <br>
    Password: <input type="password" name="pwd" value="111"> <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
