<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/6 0006
  Time: 下午 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CmdInjection</title>
</head>
<body>
<ul>
    <li><a
            href="../cmdInjection/cmd001.do?cmd=calc.exe">测试cmdInjection1  exec(cmdStr)</a>
    </li>
    <li><a
            href="../cmdInjection/cmd002.do?cmd=cmd.exe&cmd=/c&cmd=dir">测试cmdInjection2  exec(new String[]{"cmd", "/c", cmdStr[2]})</a>
    </li>
    <li><a
            href="../cmdInjection/cmd003.do?cmd=cmd.exe&cmd=/c&cmd=dir">测试cmdInjection3  exec(cmdStr, null)</a>
    </li>
    <li><a
            href="../cmdInjection/cmd004.do?cmd=cmd.exe&cmd=/c&cmd=dir">测试cmdInjection4  exec(cmdStr, null, dir)</a>
    </li>
    <li><a
            href="../cmdInjection/cmd005.do?cmd=mspaint">测试cmdInjection5  自定义规则cmdStr2，exec(cmdStr2)</a>
    </li>
    <li><a
            href="../cmdInjection/cmd006.do?cmd=cmd.exe /c dir">测试cmdInjection6  exec(tempStr, null, dir)</a>
    </li>
    <li><a
            href="../cmdInjection/cmd007.do?cmd=cmd.exe&cmd=/c&cmd=dir">测试cmdInjection7   Process.start()</a>
    </li>
    <li><a
            href="../cmdInjection/cmd008.do?cmd=cmd.exe&cmd=/c&cmd=dir">测试cmdInjection8   Process.start()</a>
    </li>
    <li><a
            href="../cmdInjection/cmdVul?cmd=calc">测试cmdInjection9</a>
    </li>
</ul>
</body>
</html>
