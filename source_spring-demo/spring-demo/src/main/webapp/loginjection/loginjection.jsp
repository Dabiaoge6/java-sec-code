<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>log-injection</title>
</head>
<body>

	<p>Log Injection</p>
	<div class='fir-tit'>
		<ul class='fir-detail'>
			<li><a
				href="../logInjection/log001.do?msg=HttpServlet.log(java.lang.String)">测试javax.servlet.http.HttpServlet.log(java.lang.String)</a></li>
			<li><a
				href="../logInjection/log002.do?msg=HttpServlet.log(java.lang.String,java.lang.Throwable)">测试javax.servlet.http.HttpServlet.log(java.lang.String,java.lang.Throwable)</a></li>
<%--			<li><a--%>
<%--				href="../logInjection/log4j1.do?msg=test-log4j-logInjection">测试javax.servlet.http.HttpServlet.log(java.lang.String,java.lang.Throwable)</a></li>--%>
<%--			<li><a--%>
<%--				href="../logInjection/log4j2.do?msg=test-log4j-logInjection">测试javax.servlet.http.HttpServlet.log(java.lang.String,java.lang.Throwable)</a></li>--%>
<%--			<li><a--%>
<%--				href="../logInjection/log4j3.do?msg=test-log4j-logInjection">测试javax.servlet.http.HttpServlet.log(java.lang.String,java.lang.Throwable)</a></li>--%>
		</ul>
	</div>

</body>
</html>


