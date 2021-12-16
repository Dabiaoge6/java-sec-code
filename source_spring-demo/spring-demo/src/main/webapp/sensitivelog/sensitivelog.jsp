<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>sensitive-info-reveal-log</title>
</head>
<body>

	<p>Sensitive-Info-Reveal-Log</p>
	<div class='fir-tit'>
		<ul class='fir-detail'>
			<li><a
				href="../sensitivelog/sensitiveLog001.do?myInfo=pwd">pwd</a></li>
			<li><a
				href="../sensitivelog/sensitiveLog001.do?myInfo=org.slf4j.Logger.trace">org.slf4j.Logger.trace(java.lang.String)</a></li>
			<li><a
				href="../sensitivelog/sensitiveLog002.do?myInfo=org.slf4j.Logger.trace">org.slf4j.Logger.trace(java.lang.String,java.lang.Throwable)</a></li>
			<li><a href="../sensitivelog/sensitiveLog003.do?myInfo=org.slf4j.Logger.warn">org.slf4j.Logger.warn(java.lang.String)</a></li>
			<li><a href="../sensitivelog/sensitiveLog004.do?myInfo=org.slf4j.Logger.debug">org.slf4j.Logger.debug(java.lang.String)</a></li>
			<li><a href="../sensitivelog/sensitiveLog005.do?myInfo=org.slf4j.Logger.error">org.slf4j.Logger.error(java.lang.String)</a></li>
			<li><a href="../sensitivelog/sensitiveLog006.do?myInfo=org.slf4j.Logger.info">org.slf4j.Logger.info(java.lang.String)</a></li>
			<li><a href="../sensitivelog/sensitiveLog007.do?myInfo=java.util.logging.Logger.logp">java.util.logging.Logger.logp</a></li>
			<li><a href="../sensitivelog/sensitiveLog008.do?mobile=18325383859">测试手机号码打印到日志中</a></li>
		</ul>
	</div>

</body>
</html>


