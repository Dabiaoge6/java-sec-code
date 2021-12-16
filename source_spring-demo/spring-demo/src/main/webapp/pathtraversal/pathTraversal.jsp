<%--
  Created by IntelliJ IDEA.
  User: lht
  Date: 2021/6/9
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>path-traversal</title>
</head>
<body>
<ul>
    <li><a
            href="../pathtraversal/path002.do?path=D:pathString">测试pathtraversal</a>
    </li>
    <li><a
            href="../pathtraversal/path003.do?path1=D:&path2=pathString">测试pathtraversal</a>
    </li>

    <li><a
            href="../pathtraversal/path004.do?path1=D:&path2=pathString">测试pathtraversal</a>
    </li>
    <li><a
            href="../pathtraversal/path005.do?path=file:///D:pathString">测试pathtraversal</a>
    </li>
</ul>
</body>
</html>
