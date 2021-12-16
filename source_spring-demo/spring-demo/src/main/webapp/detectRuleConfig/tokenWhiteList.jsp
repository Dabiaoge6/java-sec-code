<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试检测规则配置-Token白名单</title>
</head>
<body>
<form action="../csrfFix/csrfToken.do" method="post">
    <input type="hidden" id="protectedKey" name="protectedKey" value="${sessionScope.protectedKey}">
    UserName:<input type="text" id="name" name="name"><br>
    Password:<input type="password" id="pwd" name="pwd"><br>
    Age:<input type="text" id="age" name="age"><br> <input
        type="submit" value="Add">
</form>
</body>
</html>