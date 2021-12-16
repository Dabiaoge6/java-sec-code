<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试检测规则配置-Url白名单</title>
</head>
<body>
<div>
    <p>测试“Url白名单”中url同时包含csrf和unvalidated-redirect</p>
    <a href="../urlWhiteList/testUrlWhite.do?text=test">同时包含csrf和unvalidated-redierct</a>
    <p>测试“Url白名单”中url只包含csrf</p>
    <a href="../csrf/csrf002.do?param=test">只包含csrf</a>
    <p>测试“Url白名单”中url只包含unvalidated-redirect</p>
    <a href="../redirect/redirect001.do?location=index.jsp">只包含unvalidated-redierct</a>
</div>
</body>
</html>