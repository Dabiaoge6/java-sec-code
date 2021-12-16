<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="../static/js/jquery-3.1.1.js"></script>
    <title>Test JSON</title>
</head>
<body>
<form method="post" id="login" action="">
    <input type="hidden" id="token" name="token" value="123424352wer">
    用户名:<input type="text" name="name" id="name"><br>
    <input type="button" id="login-button" name="Submit" value="提交">
</form>

<script type="application/javascript">
  $(document).ready(function () {
    $("#login-button").click(function () {
      var name = $("#name").val();
      var token = $("#token").val();
      var student = {
        "token":token,
        "id": 111,
        "name": "" + name + "",
        "teachers": [{"id": 100, "name": "teacher1", "students": []}]
      };
      $.ajax({
        type: "POST",
        url: "../testJson/testToken.do",
        data: JSON.stringify(student),
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