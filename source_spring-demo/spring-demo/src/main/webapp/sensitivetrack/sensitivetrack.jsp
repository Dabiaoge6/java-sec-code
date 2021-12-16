<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>sensitive-data-flow-tracking</title>
</head>
<body>

<h3>commons-httpclient-3.1</h3>
<h5>登录jira服务器</h5>
<form method="post" id="loginJira-httpclient3" action="../httpclient3/testPost.do">
    UserName:<input type="text" name="username" id="username"><br>
    Password:<input type="password" name="password" id="password"><br>
    ServerUri:<input type="text" name="serverUri" id="serverUri"><br>
    <input type="submit" id="button1-httpclient3" name="Submit" value="提交">
</form>
<br>
<h5>获取用户信息</h5>
<form method="post" id="getUser-httpclient3" action="../httpclient3/testSingeleQuery.do">
    UserName:<input type="text" name="loginName" id="loginName"><br>
    Password:<input type="password" name="loginPassword" id="loginPassword"><br>
    ServerUri:<input type="text" name="loginServerUri" id="loginServerUri"><br>
    <input type="submit" id="button2-httpclient3" name="Submit" value="提交">
</form>
<br>
<h5>获取项目信息</h5>
<form method="post" id="getProject-httpclient3" action="../httpclient3/testMultiQuery.do">
    UserName:<input type="text" name="loginUserName" id="loginUserName"><br>
    Password:<input type="password" name="loginPwd" id="loginPwd"><br>
    ServerUri:<input type="text" name="loginUri" id="loginUri"><br>
    ProjectIds:<input type="text" name="projectIds" id="projectIds"><br>
    <input type="submit" id="button3-httpclient3" name="Submit" value="提交">
</form>
<br>
<br>
<h3>httpclient-4.5.9</h3>
<h5>登录jira服务器</h5>
<form method="post" id="loginJira" action="../httpclient4/testPost.do">
    UserName:<input type="text" name="name" id="name"><br>
    Password:<input type="password" name="pass" id="pass"><br>
    ServerUri:<input type="text" name="uri" id="uri"><br>
    <input type="submit" id="button1" name="Submit" value="提交">
</form>
<br>
<h5>获取用户信息</h5>
<form method="post" id="getUser" action="../httpclient4/testSingeleQuery.do">
    UserName:<input type="text" name="loginUName" id="loginUName"><br>
    Password:<input type="password" name="loginUPassword" id="loginUPassword"><br>
    ServerUri:<input type="text" name="jiraServerUri" id="jiraServerUri"><br>
    <input type="submit" id="button2" name="Submit" value="提交">
</form>
<br>
<h5>获取项目信息</h5>
<form method="post" id="getProject" action="../httpclient4/testMultiQuery.do">
    UserName:<input type="text" name="jiraUserName" id="jiraUserName"><br>
    Password:<input type="password" name="jiraUserPwd" id="jiraUserPwd"><br>
    ServerUri:<input type="text" name="jiraUrl" id="jiraUrl"><br>
    ProjectIds:<input type="text" name="jiraProjectIds" id="jiraProjectIds"><br>
    <input type="submit" id="button3" name="Submit" value="提交">
</form>
<br>
<br>
<h3>OkHttpClient</h3>
<h5>登录jira服务器</h5>
<form method="post" id="loginJira-okhttpclient" action="../okhttpclient/testPost.do">
    UserName:<input type="text" name="uname" id="uname"><br>
    Password:<input type="password" name="pwd" id="pwd"><br>
    ServerUri:<input type="text" name="jiraUri" id="jiraUri"><br>
    <input type="submit" id="button1-okhttpclient" name="Submit" value="提交">
</form>
<br>
<h5>获取用户信息</h5>
<form method="post" id="getUser-okhttpclient" action="../okhttpclient/testSingeleQuery.do">
    UserName:<input type="text" name="name1" id="name1"><br>
    Password:<input type="password" name="pass1" id="pass1"><br>
    ServerUri:<input type="text" name="serverUri1" id="serverUri1"><br>
    <input type="submit" id="button2-okhttpclient" name="Submit" value="提交">
</form>
<br>
<h5>获取项目信息</h5>
<form method="post" id="getProject-okhttpclient" action="../okhttpclient/testMultiQuery.do">
    UserName:<input type="text" name="loginUserName1" id="loginUserName1"><br>
    Password:<input type="password" name="loginPwd1" id="loginPwd1"><br>
    ServerUri:<input type="text" name="loginUri1" id="loginUri1"><br>
    ProjectIds:<input type="text" name="projectIds1" id="projectIds1"><br>
    <input type="submit" id="button3-okhttpclient" name="Submit" value="提交">
</form>
</body>
</html>


