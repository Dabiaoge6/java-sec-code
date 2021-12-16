<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试异常中的xss</title>
</head>
<body>
<div>
    <form method="post" id="test" action="../exceptionXss.do">
        start:<input type="text" name="start"><br>
        <input type="submit" id="testButton" name="Submit" value="提交">
    </form>
</div>
</body>
</html>