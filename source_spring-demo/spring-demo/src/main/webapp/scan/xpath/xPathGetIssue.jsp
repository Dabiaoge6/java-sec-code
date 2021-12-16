<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/6 0006
  Time: 下午 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xpath Injection(GET)</title>
</head>
<body>
更新注入参数数据：<br>
	<form id="insertForm" action="../../scanIssue/xpathGetIjection.do" method="get">
		employeeID:<input type="text" id="employeeID" name="employeeID"><br>
		<input type="submit" value="提交">
	</form>
	
	
</body>
</html>
