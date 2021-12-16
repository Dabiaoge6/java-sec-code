<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Expression Language Injection</title>
</head>

<body>
	<form action="../elinjection/jstl_el_post.do"
		method="post">
		Enter an el expression like "\${xxxScope}":
		<input type="text" name="message" placeholder="please input" />
		<input type="submit" name="submit" value="GO!"></br>
	</form>
	<hr />
	<spring:message text="${sessionScope.message}"
		code="${sessionScope.message }"></spring:message>

</body>
</html>