<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Remote File Inclusion Test</title>
</head>

<body>

<div>
<li>Remote File Inclusion Test：</li>
<form id="form" action="../../scanIssue/rfiTest.do" method="post">
    fileName:<input type="text" id="fileName" name="fileName" required placeholder="磁盘位置或下载接口">
	<input type="submit" placeholder="文件下载地址" value="提交远程文件">
</form>
<br>
</div>

<script type="text/javascript">
</script>

</body>
</html>


