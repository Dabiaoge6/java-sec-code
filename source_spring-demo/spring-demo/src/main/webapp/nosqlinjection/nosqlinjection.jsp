<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>nosql-injection</title>
</head>
<body>

<p>Log Injection</p>
<div class='fir-tit'>
    <ul class='fir-detail'>
        <li><a
                href="../nosqlInjection/nosql001.do?eval=db.version()">测试mongodb eval方法</a>
        </li>
        <%--<a href="../nosqlInjection/nosql001.do?eval=db.version()">测试mongodb eval方法，该方法mongodb 3.0.0就已经弃用了</a>--%>
    </ul>
</div>
</body>
</html>


