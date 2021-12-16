<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript"
            src="../static/js/jquery-3.1.1.js"></script>
    <title>CSRF</title>
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