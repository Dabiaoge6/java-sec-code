<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ISIT_POST</title>
</head>
<body>
	<p>敏感数据通过请求传输</p>

	<form action="../isit/isit_post.do" method="post" autocomplete="off">
		身份证号(通过请求传输)：<input class="idCard" type="text" name="idCard" value="340822199001200275"/>
		<input  type="submit" value="提交">
	</form>

</body>
</html>