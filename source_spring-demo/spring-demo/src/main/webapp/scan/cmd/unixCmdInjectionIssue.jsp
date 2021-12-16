<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UnixCmdInjectionIssue</title>
</head>
<body>
更新注入参数数据：<br>
	<form id="insertForm" action="../../scanIssue/cmdInjectionIssue.do" method="post">
		cmd:<input type="text" id="cmd" name="cmd"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>
