<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Expression Language Injection</title>
</head>

<body>
	<!-- /spring-demo/elinjection/jstl_el.jsp?message=%22${applicationScope}%22 tomcat7-->
	<spring:message text="${param.message}" code=""></spring:message>
</body>
</html>