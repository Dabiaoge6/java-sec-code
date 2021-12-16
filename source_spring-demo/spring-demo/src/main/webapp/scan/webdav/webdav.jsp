<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>webdav</title>
</head>
<body>

<h3>检测逻辑：协议如果是https，则请求安全；如果PropFind请求返回值的allow-method中包含PROPFIND则请求存在安全隐患</h3>
若status为404,可先访问http://www.webdavserver.com生成cookie,在发送请求。该地址支持http和https。
<br/>
allow-method设置方法参考<a href="http://wiki.seczone.cn/display/DAST/allowed-methods">wiki</a>
<br/>
请求url:<input type="text" value="http://www.webdavserver.com/User9ec38b6/Products" id="propfind" action="../../scanIssue/webdav/propfind.do"/>
<br/>
<input type="button" id="btn" value="提交"/>
<br/>

<p id="p"/>
<br/>

<script  type="text/javascript">
    $(function(){
        $("#btn").click(function(){
            var url = $("#propfind").val();
            var action = $("#propfind").attr("action");
            $.ajax({
                url : action,
                data : "url="+url,
                type : "GET",
                success : function(data) {
                    if (null != data && "" != data) {
                        $("#p").css({"color":"red"}).html(data);
                    }
                },
                error : function(errorThrown) {
                    $("#p").css({"color":"red"}).val(errorThrown.errorMessage);
                }
            });
        });
    })
</script>
</body>
</html>
