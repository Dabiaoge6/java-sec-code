<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Path Traversal(unix)</title>
</head>

<body>

<div>
<li>遍历文件路径(unix)：</li>
<form id="insertForm" action="../../scanIssue/pathTraversal2.do" method="post">
	Path:<input type="text" id="path" name="path">
	<input type="submit" value="提交">
</form>

		<!-- <textarea id="path" type="text" name="data"></textarea><br>
<input type="button" value="提交" onclick="testXxe()"> -->
</div>

<script type="text/javascript">

	function testXxe() {
		var path = document.getElementById("path").value;
		$.ajax({
			url : "../../scanIssue/getPath.do",
			data : 'path=' + path,
			type : "GET",
			success : function(data1, textStatus) {
				console.log("success");
				console.log(data1);
				alert(data1)
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("error");
			},
		});
	}

</script>

</body>
</html>


