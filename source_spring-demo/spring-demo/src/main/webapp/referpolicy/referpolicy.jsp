<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Security-Policy" content="script-src 'self'; object-src 'none'; style-src cdn.example.org third-party.org;">
<title>Test X-Content-Type-Options</title>
</head>
<body>
<dl>Referrer Policy 已配置</dl>
<%response.setHeader("Referrer-Policy","no-referrer-when-downgrade");%>
</body>
</html>