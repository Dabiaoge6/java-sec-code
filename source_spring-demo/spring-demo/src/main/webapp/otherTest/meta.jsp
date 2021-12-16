<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-Frame-Options" content="SAMEORIGIN">
    <meta http-equiv="Content-Security-Policy" content="default-src 'self'">
    <meta http-equiv="Pragma" content="no-cache">
    <title>Test Clickjacking/CSPMiss/CacheControl</title>
    <style type="text/css">
        .comments {
            width: 100%;
            overflow-y: visible;
            overflow: hidden;
            word-break: break-all;
        }
    </style>
</head>
<body>
<div>
		<textarea class="comments" rows="" cols="" id="text"
                  style="border: 0;"
                  onpropertychange="this.style.posHeight=this.scrollHeight ">该jsp页面meta中设置X-Frame-Option/CSP/CacheControl，所以请求该页面时不报"点击劫持"/"缺少CSP响应头"/"缓存控制"</textarea>
    <a href="javascript:history.back(-1)">back</a>
</div>
</body>
</html>