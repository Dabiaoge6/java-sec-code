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
    <title>Xss</title>
</head>
<body>

	<form action="../../scanIssue/xssIssue.do" method="post">
		<input type="text" name="message" placeholder="please input" /><br> <input
			type="submit" name="submit" value="提交"></br>
	</form>
	<hr />
	<%-- <spring:message text="${sessionScope.message}"
		code="${sessionScope.message }"></spring:message> --%>
</body>
</html>
