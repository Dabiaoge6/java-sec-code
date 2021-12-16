<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Test Clickjacking-Control-Missing</title>
<style type="text/css">
.comments {
	width: 100%;
	overflow-y: visible;
	overflow: hidden;
	word-break: break-all;
}
</style>
</head>
<%
	response.setHeader("X-XSS-Protection","1; mode=block");
	response.setHeader("X-Frame-Options","DENY ");
	response.setHeader("X-Content-Type-Options","nosniff");
	response.setHeader("Referrer-Policy","no-referrer");
%>
<body>
	<div>
		<textarea class="comments" rows="" cols="" id="text"
			style="border: 0;"
			onpropertychange="this.style.posHeight=this.scrollHeight ">该jsp页面meta中未设置X-Frame-Option，所以请求该页面时报clickjacking-control-missing</textarea>
		<a href="javascript:history.back(-1)">back</a>
	</div>
</body>
</html>