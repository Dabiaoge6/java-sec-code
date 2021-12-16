<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String p1 = request.getParameter("p");
    String path = p1 +".jsp";
    /*@include file="< % ="includes/" + p1 +".jsp"% >"
%>
<%--<%@ include file="<%=path%>"%>--%>
<jsp:include page="<%=path%>" flush="true"/>
<%
    String context = (String) session.getAttribute("content");
%>
<html>
<head>
    <title>rfi</title>
</head>
<body>
jsp文件中的内容：<input type="text" id="rfi"/>
p1="<%=p1%>"
<script type="text/javascript">
    window.onload = function (){
        document.getElementById("rfi").setAttribute("value","<%=context%>");
    }
</script>
</body>
</html>
