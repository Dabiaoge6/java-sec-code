<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<%
	    //allow access only if session exists
	    String user = null;
	    if (session.getAttribute("user") == null) {
	        response.sendRedirect("login.jsp");
	    } else
	        user = (String) session.getAttribute("user");
	    String userName = null;
	    String sessionID = null;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("user"))
	                userName = cookie.getValue();
	            if (cookie.getName().equals("JSESSIONID"))
	                sessionID = cookie.getValue();
	        }
	    } else {
	        sessionID = session.getId();
	    }
	%>
	<h3>
		Login successful.</h3>
	<br>
	<br>
	<form action="<%=response.encodeURL("../unsafeLogout/logout.do")%>" method="post" autocomplete="off">
		<input type="submit" value="Logout">
	</form>
</body>
</html>