<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
	<title>HSTS</title>
</head>
<body>
<p>HSTS</p>
<div class='fir-tit'>
	<ul class='fir-detail'>
		<li><a href="../hsts/hsts.do">在response-headers中添加Strict-Transport-Security,max-age=10000</a></li>
		<li><a href="../hsts/maxage1.do">在response-headers中添加Strict-Transport-Security,max-age=0</a></li>
		<li><a href="../hsts/maxage_null.do">在response-headers中添加Strict-Transport-Security,max-age为空</a></li>
	</ul>
</div>

</body>
</html>