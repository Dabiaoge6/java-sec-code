<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>backupDirectories test</title>
</head>

<body>

<div>

<li>下载指定文件：</li>
<form id="form" action="../../scanIssue/backupDirectories.do" method="post">
    directory:<input type="text" id="directory" name="directory" placeholder="指定文件夹">
    fileName:<input type="text" id="fileName" name="fileName" placeholder="指定文件名">
	<input type="submit" value="提交">
</form>
<br>

</div>

<script type="text/javascript">

</script>

</body>
</html>


