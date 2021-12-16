<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="./static/js/jquery-3.1.1.js"></script>
    <title>Register</title>
</head>
<body>
<h1>form表单</h1>
<form method="post" name="login" action="./registerFormData.do">
    <input type="hidden" id="protectedKey" name="protectedKey" value="${sessionScope.protectedKey}">
    UserName:<input type="text" name="name" id="name"><br>
    Password:<input type="password" name="pwd" id="pwd"><br>
    <input type="submit" id="registerForm" name="Submit" value="提交">
</form>
<%--<script type="application/javascript">
  $(document).ready(function () {
    $("#register").click(function () {
      /*var user = $("#username").val();
      var pwd = $("#psw").val();
      var data="<user><protectedKey>"+protectedKey+"</protectedKey><name>"+user+"</name><pwd>"+pwd+"</pwd></user>";*/
     /* var formdata = document.getElementById("login").values;*/
      var user = $("#username").val();
      var pwd = $("#psw").val();
      var formdata = new FormData();
      formdata.append("user",user);
      formdata.append("pwd",pwd);
      $.ajax({
        type: "POST",
        url: "./registerFormData.do",
        data: formdata,
        contentType:false, //- 必须false才会自动加上正确的Content-Type
        processData: false, //- 必须false才会避开jQuery对 formdata 的默认处理,XMLHttpRequest会对 formdata 进行正确的处理
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
</script>--%>
</body>
</html>