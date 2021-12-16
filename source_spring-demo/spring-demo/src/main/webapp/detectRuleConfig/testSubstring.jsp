<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>reflected-xss</title>
</head>
<body>
<p>当subString截取后的字符串长度小于或者等于“设置探针截取字符的最短长度”，就不会上报xss</p>
<p><a
        href="../testSubString/xss001.do?username=test-username">
    测试String.subString(4)，截取后长度为9</a></p>
<p><a
        href="../testSubString/xss002.do?username=test-username">
    测试String.subString(3,9)，截取后长度为6</a></p>
<p><a
        href="../testSubString/xss003.do?username=test-username">
    测试StringBuilder.subString(1)，截取后长度为12</a></p>
<p><a
        href="../testSubString/xss004.do?username=test-username">
    测试StringBuilder.subString(2,12)，截取后长度为10</a></p>
<p><a
        href="../testSubString/xss005.do?testString=test String">
    测试StringBuffer.subString(4)，截取后长度为7</a></p>
<p><a
        href="../testSubString/xss006.do?testString=test String">
    测试StringBuffer.subString(0,8)，截取后长度为8</a></p>
</body>
</html>
