<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="../../static/js/jquery-3.1.1.js"></script>
	<title>Xst Test</title>
</head>
<body>
<h2>Cross-Site-Tracing</h2>
	<form>
		<input type="text" id="url" placeholder="输入一个测试接口,该请求将以http trace进行测试" value="http://iast.hf.seczone.cn"/>
		<input type="button" value="提交" onclick="xstTest()">
	</form>
	<p>curl -v -X OPTIONS http://localhost:8080/{contextPath}/scanIssue/xst/trace.do可以查看接口Allow的方法</p>
	<p>curl --trace -v http://localhost:8080/{contextPath}/scanIssue/xst/trace.do?url=xxx可以查看接口http trace的结果</p>
	<p> 以tomcat为例，在conf/web.xml文件里添加如下配置关闭不安全的请求类型
		<!-- 关闭不安全的HTTP方法 -->
		<security-constraint>
			<web-resource-collection>
				<web-resource-name>baseproject</web-resource-name>
				<url-pattern>/*</url-pattern>
				<http-method>HEAD</http-method>
				<http-method>OPTIONS</http-method>
				<http-method>TRACE</http-method>
				<http-method>PUT</http-method>
				<http-method>DELETE </http-method>
				<http-method>CONNECT </http-method>
			</web-resource-collection>
			<auth-constraint>
				<description>baseproject</description>
				<role-name>All Role</role-name>
			</auth-constraint>
			<user-data-constraint>
				<transport-guarantee>NONE</transport-guarantee>
			</user-data-constraint>
		</security-constraint>
		<!-- 关闭不安全的HTTP方法 -->
		url-pattern 参数定义了你要禁用的链接，默认是/*
		http-method参数包含了你要禁用的HTTP方法
	</p>
	<p>程序允许Trace请求是在conf/server.xml的&lt;Connector&gt;中添加allowTrace="true"属性</p>
	<br/>
	<p id="p" style="visibility: hidden"></p>

	<script type="text/javascript">
		function xstTest() {
			var url = $("#url").val();
			$.ajax({
				url : "../../scanIssue/xst/trace.do",
				data : 'url=' + url,
				type : "GET",
				//dataType: 'json',
				success : function(data) {
					if (null != data && "" != data) {
						$("#p").css({"visibility":"visible","color":"red"}).html(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#p").css({"visibility":"visible"}).val("ERROR");
				}
			});
		}
	</script>
</body>
</html>
