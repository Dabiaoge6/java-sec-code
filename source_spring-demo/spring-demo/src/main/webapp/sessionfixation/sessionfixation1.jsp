<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
    <title>session-fixation</title>
</head>
<body>
<p>Session-Fixation</p>
jessionid:<input type="text" id="fix" value="admin"><br/>
<input type="button" value="提交" onclick="sent()"><br/>
<p id="rlt"></p>
<script type="application/javascript">
    function sent(){
        var sesssion = $("#fix").val();
        if($.trim(sesssion) == ""){
            alert("sessionid不能为空")
        }else {
            $.ajax({
                url : "../sessionfixation/login1.do?sessionid="+sesssion,
                type : "get",
                success : function(data, textStatus) {
                    $("#rlt").css({"color":"red"}).html(data);
                }
            });
        }
    }
</script>

</body>
</html>


