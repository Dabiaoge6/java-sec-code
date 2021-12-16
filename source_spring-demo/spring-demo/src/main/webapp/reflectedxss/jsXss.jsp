<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="../static/js/jquery-3.1.1.js"></script>
	<title>login</title>
</head>
<body>
<form method="post" id="login" action="">
	UserName:<input type="text" name="name" id="name"><br>
	<input type="button" id="login-button" name="Submit" value="提交">
</form>

<div id="test"></div>


<script type="application/javascript">
  $(document).ready(function () {
    $("#login-button").click(function () {
      var name = $("#name").val();
      $.ajax({
        type: "POST",
        url: "../jsXss.do",
        data: "name="+name,
        dataType: "json",
        success: function (data) {
          console.log(data.name);
          $('#test').text(data.name)
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