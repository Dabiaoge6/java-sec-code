<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>csrf</title>
</head>
<body>
<form action="../../scanIssue/csrf/unsafe.do" method="post">
    姓名：<input type="text" name="name" />
    <br/>
    年龄：<input type="text" name="age"/>
    <br/>
    <input type="submit" value="post">
</form>
<%--请求头是否包含token属性:
<input type="checkbox" id="chk"/>
<br/>
姓名：<input type="text" id="name"/>
<br/>
年龄：<input type="text" id="age"/>
<br/>
<input type="button" value="提交" onclick="queryTest()">
<p id="rlt"></p>
<script type="text/javascript">
    function queryTest() {
        /*var chk = $("#chk:checked").val();*/
        var name = $("#name").val();
        var age = $("#age").val();
        if($.trim(name) == "" || $.trim(age) == ""){
            $("#rlt").css({"color":"red"}).html("文本框值不能为空");
            return;
        }
        $.ajax({
            /*beforeSend: function (request) {
                request.setRequestHeader("token", chk == undefined ? "" : chk);
            },*/
            url : "../../scanIssue/csrf/unsafe.do",
            data : "name="+name+"&age="+age,
            type : "GET",
            success : function(data) {
                if (null != data && "" != data) {
                    $("#rlt").css({"color":"red"}).html(data);
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $("#rlt").css({"color":"red"}).val("ERROR");
            }
        });
    }
</script>--%>
</body>
</html>
