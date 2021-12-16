<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Xrap Scan</title>
</head>

<body>

<div>

<p>Form:</p>
<li>Xrap扫描请求地址(POST)：</li>
<form id="insertForm" action="../../scanIssue/xrapScan.do" method="post">
	Path:<input type="text" id="path" name="path">
	<input type="submit" value="提交">
</form>
<br>
<li>Xrap获取扫描漏洞结果(GET)：</li>
<form id="insertForm" action="../../scanIssue/xrapScanResult.do" method="get">
	Path:<input type="text" id="fileName" name="fileName">
	<input type="submit" value="提交">
</form>

</div>

<script type="text/javascript">


</script>

</body>
</html>


