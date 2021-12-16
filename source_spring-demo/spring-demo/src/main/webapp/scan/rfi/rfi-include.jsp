<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMethod" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Remote File Inclusion Test</title>
</head>

<body>

<div>
    <li>Remote File Inclusion Test：</li>
    <%
        String path = request.getParameter("filePath");
        String[] split = path.split(":");
        if ("http".equalsIgnoreCase(split[0]) || "https".equalsIgnoreCase(split[0])) {
            // 从接口下载文件
            URL url = new URL(path);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod(RequestMethod.GET.name());
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", StandardCharsets.UTF_8.name());
            //状态码 200：成功连接
            int code = httpURLConnection.getResponseCode();
            if (code == 200) {
                try (InputStream inputStream = httpURLConnection.getInputStream();
                     PrintWriter writer = response.getWriter();) {
                    int len = 0;
                    while ((len = inputStream.read()) != -1) {
                        writer.write(len);
                    }
                }
            } else {
                out.print("<p>获取远程文件失败</p>");
            }
        } else {
            // 丛磁盘取文件
            File file = new File(path);
            if (!file.isDirectory() && file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = "";
                    while (StringUtils.isNotBlank(line = reader.readLine())) {
                        out.print("<p>" + line + "</p>");
                    }
                }
            }
        }
    %>
<br>
</div>

<script type="text/javascript">
</script>

</body>
</html>


