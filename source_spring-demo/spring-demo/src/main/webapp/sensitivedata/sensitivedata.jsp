<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>sensitive-data-flow-tracking</title>
</head>
<p>上报漏洞：1.敏感信息在服务传播过程中出现泄漏 2.反射型跨站脚本攻击 3.web服务调用不安全 4.服务器端请求参数伪造 </p>
<form action="" autocomplete="off">
    参数名:<input type="text" name="url-key" id="url-key" value="password"/><br>
    参数值:<input type="text" name="url-val" id="url-val" value="123456"/><br>
    <input type="button" value="发送请求" onclick="send()">
</form>

<script type="text/javascript">
    function send() {
        var url_key = $("#url-key").val();
        var url_val = $("#url-val").val();
        $.ajax({
            url : "../sensitivedata/urlConnection.do",
            type : "GET",
            contentType: 'application/json;charset=utf-8',
            data: url_key+"="+url_val,
            success : function(data1, textStatus) {
                console.log("success");
                console.log(data1);
                if (null != data1 && "" != data1) {
                    alert(data1);
                } else {
                    alert("done");
                }
            },
            error : function(result) {
                console.log(result.msg);
                alert(result.msg);
            },
        });
    }
</script>
</body>
</html>


