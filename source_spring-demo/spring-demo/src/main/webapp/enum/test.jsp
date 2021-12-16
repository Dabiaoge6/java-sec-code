<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>ENUM</title>
</head>
<body>
<h5>从下列中选择最爱的水果</h5>
<form action="../reflectedXss/xss011.do" method="post">
	梨子：<input type="radio" name="fruits" value="pear" />
	<br>
	苹果：<input type="radio" name="fruits" value="apple" />
	<br>
	香蕉：<input type="radio" name="fruits" value="banana" />
	<br>
	<input type="submit" name="Submit" value="提交" />
</form>
</body>
</html>


