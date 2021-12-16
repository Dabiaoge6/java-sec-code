<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="script-src 'unsafe-inline';form-action 'self';frame-ancestors 'none';plugin-types application/pdf;reflected-xss 1;base-uri 'self';referer 'self'">
<title>CSP-Meta-Insecure Demo</title>
</head>
<body>
	<form action="../cspInsecure/cspInsecure.do">
		<input type="submit" value="执行试试">
	</form>

</body>
</html>