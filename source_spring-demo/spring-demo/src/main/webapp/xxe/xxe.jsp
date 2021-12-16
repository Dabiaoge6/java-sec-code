<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/javascript; charset=utf-8">
    <script type ="application/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>XML External Entity Injection</title>
</head>

<body>
<p>上报漏洞：XML外部实体处理</p>
<div class='fir-tit'>
    <button onclick="xmlReader()">点击上报：XML外部实体处理</button>
</div>

<script type="text/javascript">
  function xmlReader() {
    var name = 'test';
    var age = 10;
    var mobile = '1008609';
    var email = 'test@qq.com';
    var address = 'testaddress';
    var xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
        + "<applicant>" + "<age>" + age + "</age>" + "<name>"
        + name + "</name>" + "<mobile>" + mobile + "</mobile>"
        + "<email>" + email + "</email>" + "<address>" + address
        + "</address>" + "</applicant>";
    $.ajax({
      url: "../xxe/xxe016.do",
      data: 'xmlString=' + xmlString,
      type: "POST",
      success: function (data, textStatus) {
        console.log("success");
        console.log(data);
        alert("XML外部实体处理")
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log("error");
      },
    });
  }
</script>

</body>
</html>


