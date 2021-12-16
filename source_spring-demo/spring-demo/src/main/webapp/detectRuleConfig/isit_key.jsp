<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>测试检测规则配置-自定义监测数据</title>
</head>
<body>
<div>
    <p>测试“自定义监测数据”中正则表达式能否匹配key,比如seczone</p>
    <a href="../sensitiveQueryStr/testKey.do?seczone=self_data">测试queryString中包含key为seczone</a>
    <p>测试“自定义监测数据”中正则表达式能否匹配身份证号</p>
    <a href="../sensitiveQueryStr/testId.do?xx=340822199001200275">测试queryString中包含value为身份证号</a>
    <p>测试“自定义监测数据”中正则表达式能否匹配手机号码</p>
    <a href="../sensitiveQueryStr/testMobile.do?yy=13430800244">测试queryString中包含value为身份证号</a>
    <p>测试“自定义监测数据”中正则表达式能否匹配银行卡号</p>
    <a href="../sensitiveQueryStr/testBankCardNumber.do?zz=6227003325370110828">测试queryString中包含value为银行卡号</a>
</div>
</body>
</html>