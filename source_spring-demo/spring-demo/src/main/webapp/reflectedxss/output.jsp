<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>reflected-xss output</title>
</head>
<body>
<%
    String userName = (String) request.getSession().getAttribute("userName");
    String pwd = (String) request.getSession().getAttribute("pwd");
    String user = (String) request.getSession().getAttribute("user");
    char[] chars = (char[]) request.getSession().getAttribute("chars");
    String stringWithoutSource = (String) request.getSession().getAttribute("stringWithoutSource");
    String stringWithEncode = (String) request.getSession().getAttribute("stringWithEncode");
    String urlEncode = (String) request.getSession().getAttribute("URLEncode");
    String tagString = stringWithEncode;
    String untagString = StringEscapeUtils.unescapeHtml(tagString);
%>
<hr>
<p>经过source和sink（拼接字符串会报两个_user=userName+pwd）</p>
<p>
    JspWriter.print:userName:<%out.print(userName);%>
    <br>JspWriter.println:pwd:<%out.println(pwd);%>
    <br>JspWriter.write(String):userName+pwd:<%out.write(user);%>
    <br>write(String) with offset：<%out.write(userName, 1, 2);%>
    <br>write(char[])：<%out.write(chars);%>
    <br>write(char[],int,int)：<%out.write(chars,1,1);%></p>
<hr>
<p>没有经过source的字符串(不报)</p>
<p><%
    out.print("JspWriter.print:stringWithoutSource:" + stringWithoutSource + "\n");%><br><%
    out.println("JspWriter.println:stringWithoutSource:" + stringWithoutSource + "\n");%><br><%
    out.write("JspWriter.write(String):stringWithoutSource:" + stringWithoutSource);%></p>
<hr>
<p>经过source\tag和sink（不报）</p><%
    out.print("JspWriter.print:stringWithEncode:" + stringWithEncode + "\n");%><br><%
    out.println("JspWriter.println:stringWithEncode:" + stringWithEncode + "\n");%><br><%
    out.write("JspWriter.write(String):stringWithEncode:" + stringWithEncode + "\n");%>
<hr>
<p>经过source\tag\untag和sink（会报）</p>
<p>
    JspWriter.print:untagString: <%out.print(untagString);%>
    <br>JspWriter.println:untagString: <%out.println(untagString);%>
    <br>JspWriter.write(String):untagString: + <%out.write(untagString);%></p>
<p>经过spread函数处理（会报）
    <br>JspWriter.print:urlEncode: <%out.print(URLDecoder.decode(urlEncode,"UTF-8"));%>
    <br>JspWriter.println:urlEncode: <%out.println(URLDecoder.decode(urlEncode,"UTF-8"));%>
    <br>JspWriter.write(String):urlEncode: + <%out.write(URLDecoder.decode(urlEncode,"UTF-8"));%></p>
</body>

</html>
