<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>XML External Entity Injection</title>
</head>
<body>

<h2>XML External Entity Injection</h2>
<pre>正常值xml请输入：
    &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;

模拟注入xml请输入<strong style="color: red">(windows)</strong>：
    &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;!DOCTYPE ANY [&lt;!ENTITY file SYSTEM &quot;file:///C:/Windows/win.ini&quot;&gt;]&gt;&lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;&amp;file&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;

模拟注入xml请输入<strong style="color: red">(linux)</strong>：
    &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;!DOCTYPE ANY [&lt;!ENTITY file SYSTEM &quot;file:///etc/passwd&quot;&gt;]&gt;&lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;&amp;file&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;

    对比结果会打印系统文件的内容
</pre>

<h4>get提交</h4>
<form action="">
    xml:<input type="text"  style="width:300px" name="xml" id="query" value="&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;">
    <br/>
    <input type="button" value="提交" onclick="queryTest()"/>
</form>
<div>
    打印上述文件中的内容：
    <div id="xxe0"></div>
</div>
<h4>form提交</h4>
<form action="" id="form1">
    xml:<input type="text"  style="width:300px" name="xml" id="xml" value="&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;">
    <br/>
    <input type="button" value="提交" onclick="formTest()"/>
</form>
<div>
    打印上述文件中的内容：
    <div id="xxe1"></div>
</div>
<h4>stream提交</h4>
<form action="">
    xml:<input type="text" style="width:300px" value="&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;" id="stream">
    <br/>
    <input type="button" value="提交" onclick="streamTest()"/>
</form>
<div>
    打印上述文件中的内容：
    <div id="xxe3"></div>

</div>

<h4>json提交</h4>
<form action="">
    xml:<input type="text" style="width:300px" value="&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
    &lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;" id="json">
    <br/>
    <input type="button" value="提交" onclick="jsonTest()"/>
</form>
<div>
    打印上述文件中的内容：
    <div id="xxe2"></div>
</div>
<p id="err"/>

<script type="text/javascript">
    function queryTest() {
        var xml = $("#query").val();
        $.ajax({
            url : "../../scanIssue/query/xxe.do",
            data : "xml="+encodeURIComponent(xml),
            type : "GET",
            success : function(data) {
                if (null != data && "" != data) {
                    $("#xxe0").css({"color":"red"}).html(data);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $("#err").css({"color":"red"}).val("ERROR");
            }
        });
    }
    function formTest() {
        var xml = $("#form").val();
        $.ajax({
            url : "../../scanIssue/form/xxe.do",
            data : $("#form1").serialize(),
            type : "POST",
            success : function(data) {
                if (null != data && "" != data) {
                    $("#xxe1").css({"color":"red"}).html(data);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $("#err").css({"color":"red"}).val("ERROR");
            }
        });
    }

    function jsonTest() {
        var xml = $("#json").val();
        var json  = {
            "xml":encodeURIComponent(xml),
            "chief":{
                "num":"no.1",
                "tech":{
                    "name":"zs",
                    "xml": encodeURIComponent(xml)
                }
            }
        };
        $.ajax({
            url : "../../scanIssue/json/xxe.do",
            data : JSON.stringify(json),
            contentType:'application/json;charset=UTF-8',
            type : "POST",
            success : function(data) {
                if (null != data && "" != data) {
                    $("#xxe2").css({"color":"red"}).html(data);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $("#err").css({"color":"red"}).val("ERROR");
            }
        });
    }

    function streamTest() {
        var xmlrequest;
        if(window.XMLHttpRequest){
            // DOM 2浏览器
            xmlrequest = new XMLHttpRequest();
        }
        // IE浏览器
        else if (window.ActiveXObject){
            try{xmlrequest = new ActiveXObject("Msxml2.XMLHTTP");
            }catch (e){
                try{xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
                }catch (e){}
            }
        }
        var url = "../../scanIssue/stream/xxe.do";
        xmlrequest.open("post",url,true);
        xmlrequest.onreadystatechange=function(){
            if(xmlrequest.readyState==4){
                if(xmlrequest.status==200){//接收后台发送的数据
                    $("#xxe3").css({"color":"red"}).html(xmlrequest.responseText);
                }
            }else{
                $("#err").css({"color":"red"}).val("ERROR");
            }
        }
        // 发送XML请求
        var xml = $("#stream").val();
        xmlrequest.send(xml);
    }
</script>

</body>
</html>


