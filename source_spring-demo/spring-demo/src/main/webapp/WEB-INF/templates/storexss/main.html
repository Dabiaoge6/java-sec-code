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
<style type="text/css">
.table1 {
	border: 1px solid #ddd;
	width: 900px;
}

thead {
	background-color: lightblue;
}
</style>
</head>
<body>
	<table border="0" width="900px">
		<tr>
			<td align="center" style="font-size: 24px; color: #666">人员管理</td>
		</tr>
		<tr>
			<td align="right"><a href="../person/addperson.do">添加</a></td>
		</tr>
	</table>
	<br />
	<table cellspacing="0" border="1" class="table1">
		<thead>
			<tr>
				<th width="300">姓名</th>
				<th width="300">身份证号</th>
				<th width="300">电话</th>
				<th width="300">省份</th>
				<th width="300">城市</th>
				<th width="300">县城</th>
				<th width="300">编辑</th>
				<th width="300">删除</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="p" items="${requestScope.personlist }">
				<tr>
					<td align="center">${p.name }</td>
					<td align="center">${p.idCard }</td>
					<td align="center">${p.phone }</td>
					<td align="center">${p.address.province }</td>
					<td align="center">${p.address.city }</td>
					<c:choose>
						<c:when test="${!empty p.address.country }">
							<td align="center">${p.address.country }</td>
						</c:when>
						<c:otherwise>
							<td align="center">--</td>
						</c:otherwise>
					</c:choose>
					<td align="center"><a href="../person/doupdate.do?id=${p.id}">编辑</a></td>
					<td align="center"><a
						href="../person/deletePersonById.do?id=${p.id}"
						onclick='return confirm("确认要删除吗?")'>删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>