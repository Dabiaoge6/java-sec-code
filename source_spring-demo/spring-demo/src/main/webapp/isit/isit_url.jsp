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
	<div class='fir-tit'>
		<ul class='fir-detail'>
			<li><a
				href="../isit/isit001.do?password=hujj@seczone.cn">测试javax.crypto.Cipher.update(byte[])</a></li>
			<li><a
				href="../isit/isit002.do?account=8792749207414">测试javax.crypto.Cipher.update(byte[],int,int)</a></li>
			<li><a
				href="../isit/isit003.do?mobile=18736529067">测试javax.crypto.Cipher.update(byte[],int,int,byte[])</a></li>
			<li><a
				href="../isit/isit004.do?name=start">测试javax.crypto.Cipher.update(byte[],int,int,byte[],int)</a></li>
			<li><a
					href="../isit/isit5.do?xx=340822199001200275">18位身份证号--加密</a></li>
			<li><a
					href="../isit/isit6.do?xx=340822199001200275">18位身份证号--不加密(敏感信息通过RequestRequestParameter传输)</a></li>
			<li><a
					href="../isit/isit7.do?xx=adgcsgagwtergdhdh">15位身份证号--不加密</a></li>
			<li><a
					href="../isit/18155765421/testPhone.do">手机号码明文传输</a></li>
			<li><a
					href="../isit/340822199001200275/testIdNum.do">身份证号通过Url传输(敏感信息通过Url传输)</a></li>
			<li><a
					href="../isit/isit_post.jsp">post请求</a></li>
		</ul>
	</div>

</body>
</html>


