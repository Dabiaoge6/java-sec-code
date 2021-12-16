<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>isit</title>
</head>
<body>

	<p>ISIT</p>
	<form id="XStream" action="" method="get" autocomplete="off">
		Password：<input class="password" type="password" name="password" id="password" /><br>
		<!-- Phone：<input class="mobile" type="text" name="mobile1" id="mobile1" /><br> -->
		Method: <select name="method" id="method">
			<option value="isit1_header.do">javax.crypto.Cipher.update(byte[])</option>
		</select><br> <input type="button" onclick="send()" value="提交">
	</form>
	
	<script type="text/javascript">
		function send() {
			var password = $("#password").val();
			var method = $("#method").val();
			$.ajax({
				url : "../isit/" + method,
				type : "get",
				beforeSend: function (XMLHttpRequest) {  
	                XMLHttpRequest.setRequestHeader("password", password);
					XMLHttpRequest.setRequestHeader("Authorization", "Digest username=\"Mufasa\"");
				},
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


