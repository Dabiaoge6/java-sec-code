<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="./static/js/jquery-3.1.1.js"></script>
    <title>login JSON</title>
</head>
<body>
<form method="post" id="login" action="">
    UserName:<input type="text" name="name" id="name"><br>
    Password:<input type="password" name="pwd" id="pwd"><br>
    <input type="button" id="login-button" name="Submit" value="提交">
</form>

<%--<hr>

<form method="post"name="login" action="j_security_check">
    UserName:<input type="text" name="j_username"><br>
    Password:<input type="password" name="j_password"><br>
    <input type="submit" name="Submit" value="提交">
</form>--%>

<script type="application/javascript">
  $(document).ready(function () {
    $("#login-button").click(function () {
      var name = $("#name").val();
      var pwd = $("#pwd").val();
      var obj = {
        "name": name,
        "pwd": pwd
      };
      $.ajax({
        type: "POST",
        url: "./login_1.do",
        data: JSON.stringify(obj),
        /*contentType: "text/plain",*/
        /*contentType: "application/octet-stream",*/
        contentType: "application/json;charset=utf-8",
        /*dataType:"application/x-java-serialized-object",*/
        /*contentType: "application/x-java-serialized-object",*/
        success: function (data) {
          var mes = $(data).find("res").children("mes").text();
          var user = $(data).find("res").children("user").text()
          alert(mes + "  用户：" + user);
        },
        error: function (jqXHR) {
          alert("发生错误：" + jqXHR.status);
        },
      });
    });
  });
</script>

</body>
</html>