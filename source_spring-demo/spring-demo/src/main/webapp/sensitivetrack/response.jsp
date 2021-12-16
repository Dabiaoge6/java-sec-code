<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>敏感信息在服务响应中出现泄漏</title>
</head>
<body>

<h3>敏感信息在服务响应中出现泄漏</h3>
<ul>
    <li><a href="../trackresponse/headerId.do">测试响应头中包含键值身份证号</a> </li>
    <li><a href="../trackresponse/bodyJson.do">测试Json格式响应体中包含银行卡号</a> </li>
    <li><a href="../trackresponse/bodyXml.do">测试XML格式响应体中包含身份证号</a> </li>
</ul>
</body>
</html>


