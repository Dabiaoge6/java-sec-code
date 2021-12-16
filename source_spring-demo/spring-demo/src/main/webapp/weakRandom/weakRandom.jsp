<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index weakRandom</title>
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
</head>
<body>
	<form action="" autocomplete="off">
		<select name="method" id="method">
			<option value="../weakRandom/weakRandom001.do">nextInt(10)</option>
		</select> <input type="button" value="test" onclick="weakRandomTest()" />
	</form>
	<script type="text/javascript">
		function weakRandomTest() {
			var method = $("#method").val();
			$.ajax({
				url : "../weakRandom/" + method,
				type : "GET",
				success : function(data1, textStatus) {
					console.log("success");
					console.log(data1);
					if (null != data1 && "" != data1) {
						alert(data1);
					} else {
						alert("Crypto-Weak-Randomness");
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