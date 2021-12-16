<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>upload-file</title>
</head>
<body>
<p>Upload File</p>
<form action="../pathtraversal/path001.do" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/><br>
    <input type="submit" value="upload">
</form>
</body>
</html>