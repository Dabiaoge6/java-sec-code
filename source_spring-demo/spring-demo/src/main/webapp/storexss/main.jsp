<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<html>
<head>
</head>
<body>
	<table border="0" width="900px">
	</table>
	<br />
	<table cellspacing="0" border="1" class="table1">
		<thead>
			<tr>
				<th width="300">姓名</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="p" items="${requestScope.personlist }">
				<tr>
					<td align="center">${p.name }</td>
				</tr>
			</c:forEach>
		</tbody>
		&nbsp;<a href="javascript:history.go(-1)">退回</a>
	</table>
	<br />
</body>
</html>