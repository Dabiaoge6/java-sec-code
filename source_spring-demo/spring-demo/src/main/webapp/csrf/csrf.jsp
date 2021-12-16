<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript"
            src="../static/js/jquery-3.1.1.js"></script>
    <title>CSRF</title>
</head>
<style>
    a {
        text-decoration: none
    }
</style>
<body>
<form action=" " method="post" autocomplete="off">
    修改文件添加内容<input type="text" id="param" name="param">
    <input type=hidden id="method" value="csrf/csrf001.do">
    <input type="button" value="test" onclick="modifyFile()">
</form>
<script type="text/javascript">
    function modifyFile() {
        var param = $("#param").val();
        var method = $("#method").val();
        $.ajax({
            url: "../" + method,
            data: 'param=' + param,
            type: "POST",
            success: function (data1, textStatus) {
                console.log("success");
                console.log(data1);
                if (null != data1 && "" != data1) {
                    alert(data1);
                } else {
                    alert("CSRF");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
                alert("ERROR");
            },
        });
    }
</script>
</body>
</html>