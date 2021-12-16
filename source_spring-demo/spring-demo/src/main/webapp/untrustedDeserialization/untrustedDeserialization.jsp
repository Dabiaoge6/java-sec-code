<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<script src="../static/js/jquery-3.1.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>不安全的反序列化</title>
</head>
<body>
<%--<p>Deserialize java bean</p>
<form id="JavaDeserial" action="../untrustedDeserialization/objectDeserial.do" method="post">
    UserName：<input class="name" type="text" name="name" id="name"/><br>
    Phone：<input class="mobile" type="text" name="mobile" id="mobile"/><br>
    <input type="submit" value="Test"/>
</form>--%>

<p>上报漏洞：不安全的反序列化</p>
<form id="XStream" action="" method="post" autocomplete="off">
    UserName：<input class="name" type="text" name="name1" id="name1"/><br>
    Phone：   <input class="mobile" type="text" name="mobile1" id="mobile1"/><br>
    Method: <select name="method" id="method">
    <option value="untrustedDeserialization/XStream/deserial001.do">XStream.fromXML(String)</option>
</select><br> <input type="button" onclick="sendXml()" value="submit">
</form>




<script type="text/javascript">
  function sendXml() {
    var name = $("#name1").val();
    var mobile = $("#mobile1").val();
    var method = $("#method").val();
    /*var xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
            + "<secverify>" + "<name>" + name + "</name>" + "<email>"
            + mobile + "</email>" + "</secverify>";*/
    /*var xmlString = "<secverify>" + "<name>" + name + "</name>" + "<email>"
        + mobile + "</email>" + "</secverify>";*/
    var xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
        + "<user>" + "<name>" + name + "</name>" + "<mobile>"
        + mobile + "</mobile>" + "</user>";
    $.ajax({
      url: "../" + method,
      data: 'xmlString=' + xmlString,
      type: "POST",
      success: function (data1, textStatus) {
        console.log("success");
        console.log(data1);
        if (data1 == null || "" == data1) {
          alert("Successfully parsed")
        } else {
          alert("Untrusted Deserialization");
        }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log("error");
      },
    });
  }
</script>
</body>
</html>