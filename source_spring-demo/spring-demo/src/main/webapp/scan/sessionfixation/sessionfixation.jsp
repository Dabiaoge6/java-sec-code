<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>sessionfixation test</title>
</head>

<body>

<div>

<li>模拟登录：</li>
<form id="form" action="../../scanIssue/sessionFixationForm.do" method="post">
    userName:<input type="text" id="userName" name="userName">
    password:<input type="text" id="password" name="password">
    cookie:<input type="text" id="cookie" name="Set-Cookie" placeholder="JSESSIONID=secverify">
	<input type="submit" value="form提交">
	<input id="jsomSubmit" type="button" value="json提交">
</form>
<br>

</div>

<script type="text/javascript">
    $("#jsomSubmit").click(() => {
        var jsonParams = {
            userName : $("#userName").val(),
            password : $("#password").val(),
            "Set-Cookie" : $("#cookie").val()
        }
        $.ajax({
            type: "post",
            headers: {
              "Set-Cookie": $("#cookie").val()
            },
            url: "../../scanIssue/sessionFixationJson.do",
            data: JSON.stringify(jsonParams),
            contentType: "application/json;charset=utf-8",
            success:(res) => {
                alert(res)
            }
        });
    })
</script>

</body>
</html>


