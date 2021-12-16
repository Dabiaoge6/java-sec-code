<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
<title>CrytoBadMac index</title>
</head>
<body>
	<p>Please choose method and hashName</p>
	<form action="" method="post" autocomplete="off">
		<select name="method" id="method">
			<option value="../mac/mac001.do">java.security.MessageDigest.getInstance(String
				algorithm)</option>
		</select> <select name="hashName" id="hashName">
			<option value="SHA-1">Insecure SHA-1</option>
		</select> <input type="button" value="test" onclick="mactest()" />
		<hr />
	</form>
	<script type="text/javascript">
		function mactest() {
			var method = $("#method").val();
			var hashName = $("#hashName").val();
			$.ajax({
				url : method,
				data : "hashName=" + hashName,
				type : "POST",
				success : function(data1, textStatus) {
					console.log("success");
					if (null != data1 && "" != data1) {
						alert("Crypto-Bad-Mac");
					} else {
						alert("Crypto-Bad-Mac");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("error");
					alert("ERROR");
				},
			});
		}
	</script>
</body>
</html>