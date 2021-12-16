<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/6 0006
  Time: 下午 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vul</title>
</head>

<body>

<div><a href="autocomplete/autocomplete.jsp">Autocomplete错误配置</a></div>
<div><a href="./nodata/referrerPolicyMissing.jsp">缺少引用策略响应头</a></div>
<div><a href="./nodata/xXssProtectionHeaderDisabled.jsp">无效的X-XSS-Protection响应头</a></div>
<div><a href="./nodata/cachecontrolsmissing.jsp">缺少缓存配置</a></div>
<div><a href="./nodata/noclickjacking.jsp">页面缺少点击劫持保护</a></div>
<div><a href="./nodata/contentSecurityPolicyMissing.jsp">缺少CSP响应头</a></div>
<div><a href="./nodata/xContentTypeHeaderMissing.jsp">缺失X-Content-Type-Options响应头</a></div>
<div><a href="./cmdInjection/cmd.do?cmd=cmd.exe&cmd=/c&cmd=dir">命令行注入</a></div>
<div><a href="./xcontenttype/xcontenttype_insecure.jsp">内容安全策略不安全</a></div>
<div><a href="./cryptobadcipher/cipher.jsp">不安全的加密算法</a></div>
<div><a href="./cryptobadmac/mac.jsp">不安全的哈希算法</a></div>
<div><a href="./weakRandom/weakRandom.jsp">不安全的随机数算法</a></div>
<div><a href="./csrf/csrf.jsp">跨站请求伪造</a></div>
<div><a href="./hardkey/hardkey.do">硬编码密钥</a></div>
<div><a href="./hardpwd/hardpassword.do">硬编码密码</a></div>
<%--<div><a href="./isit/isit6.do?xx=340822199001200275">敏感信息通过RequestParameter传输</a></div>--%>
<%--<div><a href="./isit/340822199001200275/testIdNum.do">敏感信息通过Url传输</a></div>--%>
<div><a href="./isit/isit_post.jsp">敏感信息通过请求传输</a></div>
<div><a href="./isit/isit7.do?xx=340822199001200275">敏感信息通过log泄露</a></div>
<div><a href="./sensitivetrack/response.jsp">敏感信息在服务响应中出现泄漏</a></div>
<div><a href="./sensitivedata/sensitivedata.jsp">敏感信息在服务传播过程中出现泄漏</a></div>
<div><a href="./isit/isit_header.jsp">不安全的认证协议</a></div>
<%--<div><a href="./insecuresocket/insecure.jsp">不安全的套接字工厂</a></div>--%>
<div><a href="./ldapinjection/ldap1.do?filterExpr=(objectClass=*)">LDAP注入</a></div>
<div><a href="./nosqlInjection/nosql001.do?eval=db.version()">Nosql注入</a></div>
<div><a href="./pathtraversal/path002.do?path=D:pathString">目录遍历</a></div>
<div><a href="./reflectedXss/xss006.do?testString='test-fields'">反射型跨站脚本攻击</a></div>
<div> <a href="./person/addperson.do">存储型跨站脚本攻击</a></div>
<div><a href="./redos/redos001.do?pattern=aaaaaa&regex=abc">ReDoS攻击</a></div>
<div><a href="./sessionRewriting/checkoutpage.jsp;jsessionid=1943478FE2C4AD09464AA63922787E4B">会话Url重写</a></div>
<div><a href="./sqlInjection/sql001.do?username=aaa&pwd=123">SQL注入</a></div>
<div><a href="./sessionfixation/login1.do">会话固定</a></div>
<div><a href="./sessionTimeOut/session001.do?time=3000">不正确的会话时间配置</a></div>
<div><a href="./smtpInjection/send.do?from=1&password=1&recipients=1&text=1&content=1">SMTP头注入</a></div>
<div><a href="./ssrf/ssrf001.do?testURL=www.baidu.com">服务器端请求域名伪造</a></div>
<div><a href="./ssrf/ssrf002.do?testURL=news/index.jsp">服务器端请求路径伪造</a></div>
<div><a href="./ssrf/ssrf003.do?testURL=queryString=haha">服务器端请求参数伪造</a></div>
<div><a href="./trustboundary/tbv001.do?id=123aEsQd45">非可信数据混入可信区域</a></div>
<div><a href="./unsafeLogout/login.do?user1=admin&pwd1=password">不安全的退出</a></div>
<div><a href="./unsafeReadline/testReadline.do">不安全的readLine方法</a></div>
<div><a href="./unsafeAutoBinding/register.jsp">不安全的SpringMVC自动绑定</a></div>
<div><a href="./untrustedDeserialization/untrustedDeserialization.jsp">不安全的反序列化</a></div>
<div><a href="./redirect/redirect002.do?location=index.jsp">未经校验的客户端重定向</a></div>
<div><a href="./redirect/redirect005.do?name=/index.jsp">未经校验的服务端重定向</a></div>
<div><a href="./xpathInjection/xpath002.do?xpath=/resp/status">XPATH注入</a></div>
<div><a href="./xxe/xxe.jsp">XML外部实体处理</a></div>
<div><a href="./cookiehttp/cookieflag.do">cookie缺少httponly属性</a></div>
<div><a href="./unsafeConnection/unsafeTest.do?url=jdbc:h2:~/h2db;MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE">数据库连接字符串注入</a></div>
<div><a href="./unsafeConnection/unsafeDbConnection.do?url=jdbc:h2:~/h2db;MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE&username=sa&pwd=seczone@123">弱密码数据库连接</a></div>
<div><a href="./unsafeDbPwdStorge/unsafeDbPwdStorge.do">不安全的数据库密码存储</a></div>
<div><a href="./hqlInjection/hql001.do?test=test">HQL注入</a></div>
<div><a href="./unsafeWebCall/webCall001.do">web服务调用不安全</a></div>
<div><a href="./unsafefileaction/uploadFile.jsp">文件上传</a></div>
<div><a href="./download/download001.do?filename=WEB-INF/web.xml">任意文件下载</a></div>
<%--<div><a href="./elinjection/jstl_el_post.jsp">表达式攻击</a></div>--%>
<div><a href="./cookieSecure/secure_false.do">cookie缺少secure属性</a></div>
<div><a href="./hsts/maxage1.do">缺少hsts响应头</a></div>
<div><a href="./strackTrace/exception.do">系统异常信息泄露</a></div>
<div><a href="./headerInjection/headerInjection.do?testurl=test">Http响应头截断</a></div>
<div><a href="./logInjection/log001.do?msg=HttpServlet.log(java.lang.String)">日志注入</a></div>
<div><a href="./cookieInjection/cookie.do?test=test">Cookie注入</a></div>
<div><a href="./logical/logicalIssue.jsp">业务测试</a></div>

</body>
</html>
