<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
  <title>smtp-injection</title>
</head>
<body>
<p>Smtp-Injection</p>
<div class='fir-tit'>
  <form id="smtpInjectionForm">
    发件人：<input type="text" name="from">
    <br/>
    密码（仅支持QQ邮箱，为QQ邮箱授权码）：<input type="text" name="password">
    <br/>
    收件人：<input type="text" name="recipients">
    <br/>
    主题：<input name="text" name="subject">
    <br/>
    内容：<input type="text" name="content">
  </form>
  <input id="button" type="button" value="发送邮件"/>
  <script>
    $("#button").click(function () {
      $.post("../smtpInjection/send.do",
        $("#smtpInjectionForm").serialize(), function (data) {
          alert(data);
        });
    });
  </script>
</div>
</body>
</html>


