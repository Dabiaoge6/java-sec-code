<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>Unvalidated Redirect</title>
</head>
<body>

<h3>直接跳转，不做校验</h3>
正确地址：/scanIssue/redirect.do<br/>
旁路地址：/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="directText" action="../../scanIssue/direct/redirect.do"/>
<br/>
<input type="button" value="跳转" id="direct"/>
<br/>
<p id="directRlt"/>
<br/>

<h3>http协议开头跳转</h3>
正确地址：http://{host}:{port}/scanIssue/redirect.do<br/>
旁路地址：http://{host}:{port}/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="httpText" action="../../scanIssue/http/redirect.do"/>
<br/>
<input type="button" value="跳转" id="http"/>
<br/>
<p id="httpRlt"/>
<br/>
<%--
<h3>https协议开头跳转</h3>
正确地址：https://{host}:{port}/scanIssue/redirect.do<br/>
旁路地址：https://{host}:{port}/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="httpsText" action="../../scanIssue/http/redirect.do"/>
<br/>
<input type="button" value="跳转" id="https"/>
<br/>
<p id="httpsRlt"/>
<br/>--%>

<h3>截取固定前缀+'/'分割跳转</h3>
正确地址：http://{host}:{port}/scanIssue/redirect.do<br/>
旁路地址：http://{host}:{port}http://{host}:{port}/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="regularPrefix_solidusText" action="../../scanIssue/regularPrefix_solidus/redirect.do"/>
<br/>
<input type="button" value="跳转" id="regularPrefix_solidus"/>
<br/>
<p id="regularPrefix_solidusRlt"/>
<br/>

<%--<h3>截取固定前缀+'@'分割跳转</h3>
正确地址：http://{host}:{port}/scanIssue/redirect.do<br/>
旁路地址：localhost@{host}:{port}/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="regularPrefix_atText" action="../../scanIssue/regularPrefix_at/redirect.do"/>
<br/>
<input type="button" value="跳转" id="regularPrefix_at"/>
<br/>
<p id="regularPrefix_atRlt"/>
<br/>

<h3>截取固定前缀+'.'分割跳转</h3>
正确地址：localhost.{host}:{port}/scanIssue/redirect.do<br/>
旁路地址：localhost.{host}:{port}/scanIssue/evil_redirect.do<br/>
url:<input type="text" value="" id="regularPrefix_dotText" action="../../scanIssue/regularPrefix_dot/redirect.do"/>
<br/>
<input type="button" value="跳转" id="regularPrefix_dot"/>
<br/>
<p id="regularPrefix_dotRlt"/>
<br/>--%>
<h3 style="color: limegreen">以下用例用域名http://spring.demo.com访问<%--,如果想跨域访问请使用ie浏览器测试--%></h3>
<h3>检查地址是否以一级域名demo.com结尾（本案例测试web端口为80）</h3>
hosts设置域名<br/>
windows:C:\Windows\System32\drivers\etc\hosts -> spring.demo.com 127.0.0.1<br/>
linux:/etc/hosts -> 127.0.0.1 spring.demo.com<br/>

正确地址：http://spring.demo.com<br/>
旁路地址：http://spring.demo.com/scanIssue/evil_redirect.do?a=demo.com<br/>
旁路地址：http://spring.demo.com/scanIssue/evil_redirect.do?a=.demo.com<br/>
url:<input type="text" value="" id="regularPostfix_top_domainText" action="../../scanIssue/regularPostfix_top_domain/redirect.do"/>
<br/>
<input type="button" value="跳转" id="regularPostfix_top_domain"/>
<br/>
<p id="regularPostfix_top_domainRlt"/>
<br/>

<h3>跳转到本机首页（本案例测试web端口为80）</h3>
hosts设置域名<br/>
windows:C:\Windows\System32\drivers\etc\hosts -> spring.demo.com 127.0.0.1<br/>
linux:/etc/hosts -> 127.0.0.1 spring.demo.com<br/>

正确地址：http://spring.demo.com<br/>
旁路地址：http://spring.demo.com/scanIssue/evil_redirect.do?http://spring.demo.com<br/>
url:<input type="text" value="" id="regularPostfix_domainText" action="../../scanIssue/regularPostfix_domain/redirect.do"/>
<br/>
<input type="button" value="跳转" id="regularPostfix_domain"/>
<br/>
<p id="regularPostfix_domainRlt"/>
<br/>

<h3>跳转地址不能包含'#', '\', ':'特殊字符</h3>
正确地址：/scanIssue/redirect.do<br/>
旁路地址：http%253A%252F%252Fspring.demo.com%252FscanIssue%252Fevil_redirect.do<br/>
url:<input type="text" value="" id="escape_special_characterText" action="../../scanIssue/escape_special_character/redirect.do"/>
<br/>
<input type="button" value="跳转" id="escape_special_character"/>
<br/>
<p id="escape_special_characterRlt"/>
<br/>

<script  type="text/javascript">
    $(function(){
        $("input[type=button]").click(function(){
            var id = $(this).attr("id");
            var textId = id+"Text";
            var rltId = id+"Rlt";
            var redirect = $("#"+textId).val();
            var url = $("#"+textId).attr("action");
            $.ajax({
                url : url,
                data : "url="+redirect,
                crossDomain: true,
                beforeSend: function(xhr){
                    xhr.withCredentials = true;
                },
                type : "GET",
                success : function(data) {
                    if (null != data && "" != data) {
                        $("#"+rltId).css({"color":"red"}).html(data);
                    }
                },
                error : function(errorThrown) {
                    $("#"+rltId).css({"color":"red"}).val(errorThrown.errorMessage);
                }
            });
        });
        /*$("#regularPostfix_domain").click(function(){
            var url="../../scanIssue/regularPostfix_domain/redirect.do";
            var redirect = $("#regularPostfix_top_domainText").val();
            $.cors(url, {"url="+redirect}, function (json) {
                if (json.code == 0) {}
            });
        })*/

    });
</script>
</body>
</html>
