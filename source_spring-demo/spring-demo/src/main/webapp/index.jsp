<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Seczone-IAST 官方测试用例集合</title>
	<link rel="stylesheet" href="static/assets/css/bootstrap.min.css" media="screen">
	<script src="static/assets/js/angular.min.js" charset="utf-8"></script>
	<style media="screen">
		thead tr td {
			background-color: #f1f1f1
		}
	</style>
</head>

<body>
<div ng-app="myapp" ng-controller="main">
	<div class="container" id="main">
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-sm-offset-2">
				<h3 class="text-center">IAST测试用例集合</h3>
				<br/>
				<table class="table table-striped">
					<thead>
					<tr>
						<td>测试用例</td>
						<td>用例路径</td>
					</tr>
					</thead>
					<tbody>
					<tr ng-repeat="a in testcases">
						<td>{{a.name}}</td>
						<td><a target="_blank" ng-href="{{a.path}}">{{a.path}}</a></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var app = angular.module('myapp', []);

	app.controller('main', ['$scope', '$http',
		function ($scope, $http) {

			$scope.testcases = [
				{"name": "001 - Autocomplete错误配置", "path": 'autocomplete/login.jsp'},
				{"name": "002 - 缺少缓存配置", "path": 'cachecontrol/meta/nocache.jsp'},
				{"name": "003 - 页面缺少点击劫持保护", "path": 'clickcontrol/meta/noclickjacking.jsp'},
				{"name": "004 - 命令行注入", "path": 'cmd/cmdInjection.jsp'},
				{"name": "005 - 内容安全策略不安全", "path": 'xcontenttype/xcontenttype_insecure.jsp'},
				{"name": "006 - Cookie注入", "path": 'cookieinjection/cookieInjection.jsp'},
				{"name": "007 - 不安全的加密算法", "path": 'cryptobadcipher/cipher.jsp'},
				{"name": "008 - 不安全的哈希算法", "path": 'cryptobadmac/mac.jsp'},
				{"name": "009 - 不安全的随机数算法", "path": 'weakRandom/weakRandom.jsp'},
				{"name": "0010 - 跨站请求伪造", "path": 'csrf/csrf.jsp'},
				{"name": "0011 - 表达式攻击", "path": 'elinjection/jstl_el_post.jsp'},
				{"name": "0012 - 硬编码密钥", "path": 'hardkey/hardkey.jsp'},
				{"name": "0013 - 硬编码密码", "path": 'hardpassword/hardpassword.jsp'},
				{"name": "0014 - Http响应头截断", "path": 'headInjection/headInjection.jsp'},
				{"name": "0015 - 缺少hsts响应头", "path": 'https/hsts.jsp'},
				{"name": "0016 - 敏感信息通过请求传输", "path": 'isit/isit_url.jsp'},
				{"name": "0017 - 不安全的认证协议", "path": 'isit/isit_header.jsp'},
				{"name": "0018 - 不安全的套接字工厂", "path": 'insecuresocket/insecure.jsp'},
				{"name": "0019 - LDAP注入", "path": 'ldapinjection/ldapinjection.jsp'},
				{"name": "0020 - 日志注入", "path": 'loginjection/loginjection.jsp'},
				{"name": "0021 - Nosql注入", "path": 'nosqlinjection/nosqlinjection.jsp'},
				{"name": "0022 - 目录遍历", "path": 'pathtraversal/pathTraversal.jsp'},
				{"name": "0023 - 反射型跨站脚本攻击", "path": 'reflectedxss/reflectedxss.jsp'},
				{"name": "0024 - 存储型跨站脚本攻击", "path": 'storexss/main.jsp'},
				{"name": "0025 - ReDoS攻击", "path": 'redos/redos.jsp'},
				{"name": "0026 - 敏感信息通过log泄露", "path": 'sensitivelog/sensitivelog.jsp'},
				{"name": "0027 - 会话Url重写", "path": 'sessionRewriting/login.jsp'},
				{"name": "0028 - SQL注入", "path": 'sqlinjection/sqlinjection.jsp'},
				{"name": "0029 - sensitivetrack-response", "path": 'sensitivetrack/response.jsp'},
				{"name": "0030 - 会话固定", "path": 'sessionfixation/sessionfixation.jsp'},
				{"name": "0031 - 不正确的会话时间配置", "path": 'sessionTimeout/timeOut.jsp'},
				{"name": "0032 - SMTP头注入", "path": 'smtpinjection/smtpinjection.jsp'},
				{"name": "0033 - 服务器端请求域名伪造", "path": 'ssrf/ssrf.jsp'},
				{"name": "0034 - ssrf-okhttp", "path": 'ssrf/ssrf-okhttp.jsp'},
				{"name": "0035 - ssrf-okhttp3", "path": 'ssrf/ssrf-okhttp3.jsp'},
				{"name": "0036 - 系统异常信息泄露", "path": 'stracktrace/exception.jsp'},
				{"name": "0037 - 非可信数据混入可信区域", "path": 'trustboundary/trustboundary.jsp'},
				{"name": "0038 - 不安全的退出", "path": 'unsafeLogout/loginout.jsp;jsessionid=1943478FE2C4AD09464AA63922787E4B'},
				{"name": "0039 - 不安全的readLine方法", "path": 'unsafeReadline/unsafeReadline.jsp'},
				{"name": "0040 -不安全的SpringMVC自动绑定", "path": 'unsafeAutoBinding/register.jsp'},
				{"name": "0041 - 不安全的反序列化", "path": 'untrustedDeserialization/untrustedDeserialization.jsp'},
				{"name": "0042 - 未经校验的服务端重定向", "path": 'redirect/forward.jsp'},
				{"name": "0043 - 未经校验的客户端重定向", "path": 'redirect/redirect.jsp'},
				{"name": "0044 - XPATH注入", "path": 'xpath/xpath.jsp'},
				{"name": "0045 - XML外部实体处理", "path": 'xxe/xxe.jsp'},
				{"name": "0046 - cookie缺少httponly属性", "path": 'httponly/cookiehttp.jsp'},
				{"name": "0047 - cookie缺少secure属性", "path": 'https/cookiesecure.jsp'},
				{"name": "0048 - 021-nio-file.jsp", "path": 'unsafeDatabaseConnection/unsafeDatabaseConnection.jsp'},
				{"name": "0049 - HQL注入", "path": 'hql/hqlInjection.jsp'},
				{"name": "0050 - 不安全的数据库密码存储", "path": 'unsafePwdStorge/unsafePwdStorge.jsp'},
				{"name": "0051 - web服务调用不安全", "path": 'unsafewebcall/unsafewebcall.jsp'},
				{"name": "0052 - 业务测试", "path": 'logical/logicalIssue.jsp'},
				{"name": "0053 - sensitive-data-check", "path": 'sensitivedata/sensitivedata.jsp'},
				{"name": "0054 - 文件上传", "path": 'unsafefileaction/uploadFile.jsp'},
				{"name": "0055 - 任意文件下载", "path": 'unsafefileaction/downloadFile.jsp'},
				{"name": "0056 -  ", "path": ' '},
				{"name": "0057 -  ", "path": ' '},
				{"name": "0058 -  ", "path": ' '},
				{"name": "0059 -  ", "path": ' '},
				{"name": "0060 -  ", "path": ' '},
				{"name": "0061 -  ", "path": ' '},
				{"name": "0062 -  ", "path": ' '},
				{"name": "0063 -  ", "path": ' '},
				{"name": "0064 -  ", "path": ' '},
				{"name": "0065 -  ", "path": ' '},
				{"name": "0066 -  ", "path": ' '},
				{"name": "0067 -  ", "path": ' '},
				{"name": "0068 -  ", "path": ' '},
				{"name": "0069 -  ", "path": ' '},
				{"name": "0070 -  ", "path": ' '},
			]
		}
	]);
</script>

</body>
<!-- design, implemented by c0debreak -->
</html>
