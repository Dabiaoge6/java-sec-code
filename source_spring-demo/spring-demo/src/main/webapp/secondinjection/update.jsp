<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="../static/js/jquery-3.1.1.js"></script>
	<title>修改密码</title>
</head>
<body>
<form id="updateForm" action="../secondInjection/update.do" method="post">
	OldPassword:<input type="password" id="pwd_update" name="pwd_update"><br>
	NewPassword:<input type="password" id="newpwd" name="newpwd"><br>
	ConfirmPassword:<input type="password" id="confirmpwd"
						   name="confirmpwd"><br> <input type="submit"
														 value="Update">
</form>
</body>
</html>