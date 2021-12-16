<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Allowed Methods</title>
</head>

<body>

<div>
	<input type="button" onclick="doReq('OPTIONS')" value="测试allowed-methods OPTIONS">
	<input type="button" onclick="doReq('GET')" value="测试allowed-methods GET">
	<input type="button" onclick="doReq('HEAD')" value="测试allowed-methods HEAD">
	<input type="button" onclick="doReq('POST')" value="测试allowed-methods POST">
	<input type="button" onclick="doReq('PUT')" value="测试allowed-methods PUT">
	<input type="button" onclick="doReq('PATCH')" value="测试allowed-methods PATCH">
	<input type="button" onclick="doReq('DELETE')" value="测试allowed-methods DELETE">
	<input type="button" onclick="doReq('TRACE')" value="测试allowed-methods TRACE">
	<input type="button" onclick="doReq('CONNECT')" value="测试allowed-methods CONNECT">

</div>

<script type="text/javascript">
    function doReq(method) {
        $.ajax({
            url:"../../scanIssue/allowedMethods.do",
            type:method,
            success:(res,status,xmlHttpRequest)=>{
                if (method && method == "OPTIONS") {
                    alert(xmlHttpRequest.getAllResponseHeaders());
                } else {
                    alert(res);
                }
            },
            error:(res)=>{
                alert(res);
            }});
    }

</script>

</body>
</html>


