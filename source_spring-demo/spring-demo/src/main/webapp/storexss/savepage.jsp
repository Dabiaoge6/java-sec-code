<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<html>
<head></head>
<body>
<h3>人员添加</h3>
<br/>
<br/>
<form id="saveForm" action="../person/saveperson.do" method="post" autocomplete="off">
    <table style="font-size:16px">
        <tr>
            <td>姓名：</td>
            <td><input type="text" value="" name="name"/></td>
            <td align="right"><input type="submit" value="添加"/>
            <td><a href="javascript:history.go(-1)">返回</a></td>
        </tr>
    </table>
</form>
</body>
</html>