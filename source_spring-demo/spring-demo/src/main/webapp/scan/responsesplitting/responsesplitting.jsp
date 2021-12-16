<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>responseSplitting test</title>
</head>

<body>

<div>

<li>指定responseSplitting：</li>
<form id="form">
	key:<input type="text" id="key" name="key" placeholder="指定key">
	value:<input type="text" id="value" name="value" placeholder="指定value">
	<input id = "submit" type="button" value="提交">
</form>
<br>
    <p id="p"/>
<br>

</div>

<script type="text/javascript">

    $("#submit").click(()=>{
        $.ajax({
            url : "../../scanIssue/responseSplitting.do",
            data : {
                /* key : $("#key").val(),
                value : $("#value").val() */
                key : "akey",
                value : "getakey\r\nname:yajun"
            },
            type : "POST",
            success : function(data,status,xmlHttpRequest) {
                var aa =xmlHttpRequest.getAllResponseHeaders();
                aa = "response headers: " + aa;
                $("#p").css({"color":"red"}).html(aa);
            },
            error : function(errorThrown) {
                $("#p").css({"color":"red"}).val(errorThrown.errorMessage);
            }
        });
    });

</script>

</body>
</html>


