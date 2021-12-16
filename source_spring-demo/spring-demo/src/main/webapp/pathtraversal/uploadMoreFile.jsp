<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>upload-file</title>
</head>
<body>
<p>Upload More File</p>
<form action="../pathtraversal/uploadMore.do" enctype="multipart/form-data" method="post">
    select first file:<input type="file" name="files"/><br>
    select second file:<input type="file" name="files"/><br>
</form><br>
<p>multiple="multiple"</p>
<form action="../pathtraversal/uploadMore.do" enctype="multipart/form-data" method="post">
    current name:<input type="text" name="name" id="name"><br>
    current file-number:<input type="text" name="number" id="number"><br>
    select more files:<input type="file" multiple="multiple" name="files"/><br>
    <input type="submit" value="upload">
</form>
<br>
<p>test MultipartStream</p>
<form action="../pathtraversal/testMultiPartStream.do" enctype="multipart/form-data" method="post">
    current name:<input type="text" name="name" id="cname"><br>
    current file-number:<input type="text" name="number" id="cnumber"><br>
    select more files:<input type="file" multiple="multiple" name="cfiles"/><br>
    <input type="submit" value="upload">
</form>
</body>
</html>