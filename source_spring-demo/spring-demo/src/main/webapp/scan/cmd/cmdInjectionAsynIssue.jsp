<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../../static/js/jquery-3.1.1.js"></script>
<title>CmdInjectionIssue</title>
</head>
<body>
	<p>Form:</p>
	cmd注入参数数据(异步)：
	<br>
	<form id="insertForm" action="../../scanIssue/cmdAsynIssue.do"
		method="post">
		cmd:<input type="text" id="cmd" name="cmd"> <input
			type="submit" value="提交">
	</form>

	cmd注入参数数据(POST)：<br>
	<form id="insertForm" action="../../scanIssue/cmdInjectionIssue.do" method="post">
		cmd:<input type="text" id="cmd" name="cmd"><input type="submit" value="提交">
	</form>

	cmd注入参数数据(GET)：
	<br>
	<form id="insertGetForm" action="../../scanIssue/cmdInjectionGetIssue.do"
		method="get">
		cmd:<input type="text" id="cmdGet" name="cmdGet"> <input
			type="submit" value="提交">
	</form>

	<div>
		<p>JSON:</p>
		cmd注入参数数据：<br> <input type="text" id="jsonCmdText"
			name="jsonCmdText"> <input type="button" value="提交"
			onclick="commitJsonCmd()" /> <br>
	</div>
	<p id="showResult"/>



	<script type="text/javascript">
		function commitJsonCmd() {
			var name = $('#jsonCmdText').val();
			var jsonData = {
				"name" : name
			};
			var url = "../../scanIssue/cmdJsonInjectionIssue.do";
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
