<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sql-Injection</title>
    <script type="text/javascript" src="../static/js/jquery-3.1.1.js"></script>
</head>
<hr>
<p>测试sql-injection</p>
<form id="testSqlInjectionForm">
    <input type="button" value="username:">
    <input name="username" value="username">
    <br>
    <input type="button" value="pwd:">
    <input name="pwd" value="pwd">
</form>
<hr>
<input id="statementAddBatchTestBtn" type="button" value="测试Statement.addBatch"/><br>
<input id="statementAddBatchTestBtn_1" type="button" value="测试Statement.addBatch_1"/>
</hr>
<hr>
<input id="statementExecuteSQLTestBtn" type="button" value="测试Statement.execute(sql)"/><br>
<input id="statementExecuteSQLIntTestBtn" type="button" value="测试Statement.execute(sql,int)"/><br>
<input id="statementExecuteSQLIntArrTestBtn" type="button" value="测试Statement.execute(sql,intArr)"/><br>
<input id="statementExecuteSQLStringArrTestBtn" type="button" value="测试Statement.execute(sql,stringArr)"/>
<hr>
<%-- java.sql.Statement.executeLargeUpdate since jdk1.8
<input id="statementExecuteLargeUpdateSQLTestBtn" type="button" value="测试Statement.executeLargeUpdate(sql)"/><br>
<input id="statementExecuteLargeUpdateSQLIntTestBtn" type="button" value="测试Statement.executeLargeUpdate(sql,int)"/><br>
<input id="statementExecuteLargeUpdateSQLIntArrTestBtn" type="button" value="测试Statement.executeLargeUpdate(sql,intArr)"/><br>
<input id="statementExecuteLargeUpdateSQLStringArrTestBtn" type="button" value="测试Statement.executeLargeUpdate(sql,stringArr)"/>
<hr>--%>
<input id="statementExecuteQueryTestBtn" type="button" value="测试Statement.executeQuery(sql)"/>
<hr>
<input id="statementExecuteUpdateSQLTestBtn" type="button" value="测试Statement.executeUpdate(sql)"/><br>
<input id="statementExecuteUpdateSQLIntTestBtn" type="button" value="测试Statement.executeUpdate(sql,int)"/><br>
<input id="statementExecuteUpdateSQLIntArrTestBtn" type="button" value="测试Statement.executeUpdate(sql,intArr)"/><br>
<input id="statementExecuteUpdateSQLStringArrTestBtn" type="button" value="测试Statement.executeUpdate(sql,stringArr)"/>
<hr>
<input id="connectionPrepareCallTestBtn" type="button" value="测试Connection.prepareCall(string)"/><br>
<input id="connectionPrepareCallInt2TestBtn" type="button" value="测试Connection.prepareCall(string,int,int)"/><br>
<input id="connectionPrepareCallInt3TestBtn" type="button" value="测试Connection.prepareCall(string,int,int,int)"/>
<hr>
<input id="connectionPrepareStatementTestBtn" type="button" value="测试Connection.prepareStatement(string)"/><br>
<input id="connectionPrepareStatementIntTestBtn" type="button" value="测试Connection.prepareStatement(string,int)"/><br>
<input id="connectionPrepareStatementIntArrTestBtn" type="button" value="测试Connection.prepareStatement(string,int[])"/><br>
<input id="connectionPrepareStatementInt2TestBtn" type="button" value="测试Connection.prepareStatement(string,int,int)"/><br>
<input id="connectionPrepareStatementInt3TestBtn" type="button" value="测试Connection.prepareStatement(string,int,int,int)"/><br>
<input id="connectionPrepareStatementStringArrTestBtn" type="button" value="测试Connection.prepareStatement(string,string[])"/>
<hr>
<input id="createNativeQueryTestBtn" type="button" value="测试EntityManager.createNativeQuery(string)"/><br>
<input id="createNativeQueryStringTestBtn" type="button" value="测试EntityManager.createNativeQuery(string,string)"/><br>
<input id="createNativeQueryClassTestBtn" type="button" value="测试EntityManager.createNativeQuery(String,Class)"/>

<script>
    $("#statementAddBatchTestBtn").click(function () {
        $.post("../sqlInjection/sql001.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
        		alert(data);
            });
    });

    /*execute*/
    $("#statementExecuteSQLTestBtn").click(function () {
      $.post("../sqlInjection/sql002.do",
          $("#testSqlInjectionForm").serialize(), function (data) {
            if (data.length >= 1){
                alert(data)
            }else {
                alert("no match any user");
            }

          });
    });
    $("#statementExecuteSQLIntTestBtn").click(function () {
      $.post("../sqlInjection/sql003.do",
          $("#testSqlInjectionForm").serialize(), function (data) {
            alert("测试Statement.execute(sql,int)");
          });
    });
    $("#statementExecuteSQLIntArrTestBtn").click(function () {
      $.post("../sqlInjection/sql004.do",
          $("#testSqlInjectionForm").serialize(), function (data) {
            alert("测试Statement.execute(sql,intArr)");
          });
    });
    $("#statementExecuteSQLStringArrTestBtn").click(function () {
      $.post("../sqlInjection/sql005.do",
          $("#testSqlInjectionForm").serialize(), function (data) {
            alert("测试Statement.execute(sql,StringArr)");
          });
    });
    /*execute end*/

    /*executeLargeUpdate*/
    $("#statementExecuteLargeUpdateSQLTestBtn").click(function () {
        $.post("../sqlInjection/statementExecuteLargeUpdate-sql.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeLargeUpdate(sql)");
            });
    });
    $("#statementExecuteLargeUpdateSQLIntTestBtn").click(function () {
        $.post("../sqlInjection/statementExecuteLargeUpdate-sql-int.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeLargeUpdate(sql,int)");
            });
    });
    $("#statementExecuteLargeUpdateSQLIntArrTestBtn").click(function () {
        $.post("../sqlInjection/statementExecuteLargeUpdate-sql-intArr.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeLargeUpdate(sql,intArr)");
            });
    });
    $("#statementExecuteLargeUpdateSQLStringArrTestBtn").click(function () {
        $.post("../sqlInjection/statementExecuteLargeUpdate-sql-StringArr.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeLargeUpdate(sql,StringArr)");
            });
    });
    /*executeLargeUpdate end*/

    /*executeQuery*/
    $("#statementExecuteQueryTestBtn").click(function () {
        $.post("../sqlInjection/sql006.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeQuery(sql)");
            });
    });

    /*executeUpdate*/
    $("#statementExecuteUpdateSQLTestBtn").click(function () {
        $.post("../sqlInjection/sql007.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeUpdate(sql)");
            });
    });
    $("#statementExecuteUpdateSQLIntTestBtn").click(function () {
        $.post("../sqlInjection/sql008.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeUpdate(sql,int)");
            });
    });
    $("#statementExecuteUpdateSQLIntArrTestBtn").click(function () {
        $.post("../sqlInjection/sql009.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeUpdate(sql,intArr)");
            });
    });
    $("#statementExecuteUpdateSQLStringArrTestBtn").click(function () {
        $.post("../sqlInjection/sql010.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Statement.executeUpdate(sql,StringArr)");
            });
    });
    /*executeUpdate end*/

    /*connection.prepareCall*/
    $("#connectionPrepareCallTestBtn").click(function () {
        $.post("../sqlInjection/sql011.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareCall(string)");
            });
    });
    $("#connectionPrepareCallInt2TestBtn").click(function () {
        $.post("../sqlInjection/sql012.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareCall(string,int,int)");
            });
    });
    $("#connectionPrepareCallInt3TestBtn").click(function () {
        $.post("../sqlInjection/sql013.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareCall(string,int,int,int)");
            });
    });
    /*connection.prepareCall end*/

    /*Connection.prepareStatement*/
    $("#connectionPrepareStatementTestBtn").click(function () {
        $.post("../sqlInjection/sql014.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string)");
            });
    });
    $("#connectionPrepareStatementIntTestBtn").click(function () {
        $.post("../sqlInjection/sql015.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string,int)");
            });
    });
    $("#connectionPrepareStatementIntArrTestBtn").click(function () {
        $.post("../sqlInjection/sql016.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string,int[])");
            });
    });
    $("#connectionPrepareStatementInt2TestBtn").click(function () {
        $.post("../sqlInjection/sql017.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string,int,int)");
            });
    });
    $("#connectionPrepareStatementInt3TestBtn").click(function () {
        $.post("../sqlInjection/sql018.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string,int,int,int)");
            });
    });
    $("#connectionPrepareStatementStringArrTestBtn").click(function () {
        $.post("../sqlInjection/sql019.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试Connection.prepareStatement(string,String[])");
            });
    });
    /*Connection.prepareStatement end*/

    /*EntityManager.createNativeQuery*/
    $("#createNativeQueryTestBtn").click(function () {
        $.post("../sqlInjection/sql020.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试EntityManager.createNativeQuery(string)");
            });
    });
    $("#createNativeQueryStringTestBtn").click(function () {
        $.post("../sqlInjection/sql021.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试EntityManager.createNativeQuery(String,String)");
            });
    });
    $("#createNativeQueryClassTestBtn").click(function () {
        $.post("../sqlInjection/sql022.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert("测试EntityManager.createNativeQuery(String,Class)");
            });
    });
    /*EntityManager.createNativeQuery end*/

    $("#statementAddBatchTestBtn_1").click(function () {
        $.post("../sqlInjection/sql023.do",
            $("#testSqlInjectionForm").serialize(), function (data) {
                alert(data);
            });
    });
</script>
</body>
</html>
