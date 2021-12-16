<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cryptoBadCipher</title>
<script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
</head>
<body>
<form action="" autocomplete="off">
<p>Please choose method and transformation</p>
<select name="method" id="method" >
	<option value="../cipher/cipher002.do">javax.crypto.Cipher.getInstance(String transformation, Provider provider)</option>
</select>
<select name="transformation" id="transformation">
	<option value="DES/CBC/NoPadding">DES/CBC/NoPadding (56)</option>
</select>
<input type="button" value="test" onclick="cipherTest()"/>
</form>
<script type="text/javascript">
		function cipherTest() {
			var method = $("#method").val();
			var transformation = $("#transformation").val();
			$.ajax({
				url :  method,
				data:  'transformation=' + transformation,
				type : "POST",
				success : function(data1, textStatus) {
					console.log("success");
					console.log(data1);
					if (null != data1 && "" != data1) {
						alert(data1);
					} else {
						alert("Crypto-Bad-Cipher");
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