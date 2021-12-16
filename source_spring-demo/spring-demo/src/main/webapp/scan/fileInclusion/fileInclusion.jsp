<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../static/js/jquery-3.1.1.js"></script>
    <title>File Inclusion</title>
</head>

<body>

<div>

<p>Form:</p>
<li>文件包含(POST)：</li>
<form id="insertForm" action="../../scanIssue/fileInclusion.do" method="post">
	Path:<input type="text" id="path" name="path">
	<input type="submit" value="提交">
</form>
<br>
<li>文件包含(GET)：</li>
<form id="insertForm" action="../../scanIssue/fileInclusionGet.do" method="get">
	Path:<input type="text" id="path" name="path">
	<input type="submit" value="提交">
</form>

	<div>
		<p>JSON:</p>
		文件包含：<br> <input type="text" id="jsonPath"
			name="jsonPath"> <input type="button" value="提交"
			onclick="commitJsonFileInclusion()" /> <br>
	</div>
	<p id="showResult"/>
	
<br>
<a href="../../scanIssue/sendFileInclusionRequestTest.do">Send Request Test</a>

</div>

<script type="text/javascript">

function commitJsonFileInclusion() {
	var path = $('#jsonPath').val();
	var jsonData = {
		"path" : path
	};
	var url = "../../scanIssue/jsonFileInclusion.do";
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		async : false,
		data : JSON.stringify(jsonData),
		success : function(data) {
            if (null != data && "" != data) {
                $("#showResult").css({"color":"red"}).html(data);
            }
        },
        error : function(error) {
        	alert(error.statusText);
        }
	});
}

</script>

</body>
</html>


