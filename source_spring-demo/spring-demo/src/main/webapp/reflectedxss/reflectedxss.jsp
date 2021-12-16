<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>reflected-xss</title>
</head>
<body>
<p><a
        href="../sqlInjection/sql014.do?username=xiong&pwd=123">
    测试sqlinjection</a></p>
<p><a
        href="../reflectedXss/xss001.do?username='test-username'">
    测试writeInternal</a></p>
<p><a
        href="../reflectedXss/writeInternal/abdc?username='test-username'">
    测试writeInternal-234</a></p>
<p><a
        href="../reflectedXss/writeInternal/123abc?username='test-username'">
    测试writeInternal-345</a></p>
<p><a
        href="../reflectedXss/writeInternal/1234567?username='test-username'">
    测试writeInternal-234</a></p>
<p><a
        href="../reflectedXss/writeInternal/1234abcdef?username='test-username'">
    测试writeInternal-345</a></p>
<%--<p><a--%>
<%--        href="../reflectedXss/xss002.do?username='test-username'&pwd='test-pwd'&encodingString='tag-fields">--%>
<%--    测试JspWriter</a></p>--%>
<p><a
        href="../reflectedXss/xss003.do?testString='test String'">
    测试ServletOutputStreamTest</a></p>
<p><a
        href="../reflectedXss/xss004.do?testString='test-fields'">
    测试printWriterTest.format</a></p>
<p><a
        href="../reflectedXss/xss005.do?testString='test-fields'">
    测试printWriterTest.print</a></p>
<p><a
        href="../reflectedXss/xss006.do?testString='test-fields'">
    测试printWriterTest.printf</a></p>
<p><a
        href="../reflectedXss/xss007.do?testString='test-fields'">
    测试printWriterTest.println</a></p>
<p><a
        href="../reflectedXss/xss008.do?testString='test-fields'">
    测试printWriterTest.write</a></p>
<p><a
        href="../reflectedXss/xss009.do?testString='test-fields'">
    测试writeRaw.write</a></p>
<%--<p><a
        href="../reflectedXss/writeRaw/123?testString='test-fields'">
    测试writeRaw.write</a></p>--%>
<p><a
        href="../reflectedXss/xss010.do?testString='test-fieldsabc'">
    测试HtmlResponseWriter.write</a></p>
<p><a
        href="../reflectedXss/ref-xss001?var=1&var2=3&ver=vul">
    测试ref-xss001上报</a></p>
<p><a
        href="../reflectedXss/ref-xss002?var=1&var2=3&ver=aaa">
    测试ref-xss002不上报</a></p>
</body>
</html>
