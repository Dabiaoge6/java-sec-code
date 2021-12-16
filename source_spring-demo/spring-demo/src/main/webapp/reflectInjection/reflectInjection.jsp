<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reflect Injection</title>
</head>
<body>
<a href="../reflectionInjection/reflect001.do?declaredField=name">测试getDeclarField方法</a><br>
<a href="../reflectionInjection/reflect002.do?field=test">测试getField方法</a><br>
<a href="../reflectionInjection/reflect003.do?declaredMethod=getPwd">测试getDeclaredMethod方法</a><br>
<a href="../reflectionInjection/reflect004.do?method=getName">测试getMethod方法</a><br>
</body>
</html>