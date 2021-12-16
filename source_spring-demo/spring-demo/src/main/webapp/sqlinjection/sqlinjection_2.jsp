<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.spring.demo.util.RSAUtils"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sql-Injection</title>
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
</head>
<body>
<form id="form1" action="../sqlInjection/testEncryption.do" method="post">
    name:<input type="text" id="name" name="name" value="<%=RSAUtils.encrypt("hujj")%>"/>
    passwd:<input type="text" id="passwd" name="passwd" value="<%=RSAUtils.encrypt("pwd-1")%>"/>
    <div>
        <input type="submit" id="submitForm" value="save" name="save"/>
    </div>
</form>

</body>
</html>
