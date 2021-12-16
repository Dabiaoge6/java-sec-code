<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="../static/js/jquery-3.1.1.js"></script>
<title>CSRF</title>
</head>
<style>
a{text-decoration:none}

</style>
<body>
	<p>Modify the file</p>
	<form action="" method="post">
		<input type="text" id="param" name="param"><br> <select id="method">
			<option value="csrf/csrf001.do">FileOutputStream</option>
		    <option value="csrf/csrf001/abc">FileOutputStream-234</option>
		    <option value="csrf/csrf001/123abc">FileOutputStream-345</option>
			<option value="csrf/csrf002.do">PrintWriter</option>
		</select> <br> <input type="button" value="test" onclick="modifyFile()">
	</form>
	<hr>
	<p>Database :insert/update/delete</p>
	<!-- insert form -->
	<form id="insertForm" action="../csrf/csrf003.do" method="post">
		UserName:<input type="text" id="name" name="name"><br>
		Password:<input type="password" id="pwd" name="pwd"><br>
		Age:<input type="text" id="age" name="age"><br> <input
			type="submit" value="Add">
	</form>
	<br>
	<!-- update form -->
	<form id="updateForm" action="../csrf/csrf004.do" method="post">
		UserName:<input type="text" id="name_update" name="name_update"><br>
		OldPassword:<input type="password" id="pwd_update" name="pwd_update"><br>
		NewPassword:<input type="password" id="newpwd" name="newpwd"><br>
		ConfirmPassword:<input type="password" id="confirmpwd"
			name="confirmpwd"><br> <input type="submit"
			value="Update">
	</form>
	<br>
	<!-- delete form -->
	<p>Delete please click</p>
	<form id="getUserForm" action="../csrf/getUser.do" method="post">
		<input type="submit" value="GetUser">
	</form>
	<br>
	<form id="deleteForm" action="../csrf/csrf005.do" method="post">
		<select name="username" id="username">
			<c:forEach var="name" items="${userList}">
				<option value="${name}">${name}</option>
			</c:forEach>
		</select> <input type="submit" value="Delete">
	</form>
	<hr>
	<script type="text/javascript">
		function modifyFile() {
			var param = $("#param").val();
			var method = $("#method").val();
			$.ajax({
				url : "../" + method,
				data : 'param=' + param,
				type : "POST",
				success : function(data1, textStatus) {
					console.log("success");
					console.log(data1);
					if (null != data1 && "" != data1) {
						alert(data1);
					} else {
						alert("CSRF");
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