<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Sql Injection</title>
</head>
<body>

	<p>Form:</p>
	sql注入请求参数数据(POST)：<br>
	<form id="insertForm" action="../../scanIssue/sqlInjectionIssue.do" method="post">
		UserName:<input type="text" id="name" name="name"><br>
		Password:<input type="password" id="pwd" name="pwd"><br>
		Age:<input type="text" id="age" name="age"><br> 
		<input type="submit" value="提交">
	</form>
	<br>
	sql注入请求参数数据(GET)：<br>
	<form id="insertForm" action="../../scanIssue/sqlInjectionGetIssue.do" method="get">
		UserName:<input type="text" id="name" name="name"><br>
		<input type="submit" value="提交">
	</form>

	<div>
		<p>JSON:</p>
		sql注入参数数据(POST)：<br> 
		UserName:<input type="text" id="jsonName" name="jsonName"><br>
		Password:<input type="password" id="jsonPwd" name="jsonPwd"><br>
		Age:<input type="text" id="jsonAge" name="jsonAge"><br>
		<input type="button" value="提交" onclick="commitJsonCmd()" /> <br>
	</div>
	<p id="showResult"/>

	<script type="text/javascript">
		function commitJsonCmd() {
			var name = $('#jsonName').val();
			var pwd = $('#jsonPwd').val();
			var age = $('#jsonAge').val();
			var jsonData = {
				"name" : name,
				"pwd" : pwd,
				"age" : age
			};
			var url = "../../scanIssue/sqlJsonInjectionIssue.do";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				async : false,
				data : JSON.stringify(jsonData),
				success : function(data) {
                    if (null != data && "" != data) {
                        $("#showResult").css({"color":"red"}).html(data);
                    }
                },
                error : function(error) {
                	alert(error.statusText);
                }
			});
		}

	</script>


</body>
</html>


