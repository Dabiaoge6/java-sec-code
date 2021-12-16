<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>reflected-xss</title>
</head>
<body>
<p>截取后的字符串中仍存在污点数据，则上报xss，反之，不上报xss</p>
<%--<p>StringBuilder.append方法+substring</p>
<p><a
        href="../testStringBuilderAppendAppend/xss001.do?username=seczone">
    测试StringBuilder.append(java.lang.String)，污点数据下标范围[4,10],截取字符串下标范围[0,3],N</a></p>
<p><a
        href="../testStringBuilderAppendAppend/xss002.do?username=seczone">
    测试StringBuilder.append(java.lang.StringBuffer)，污点数据下标范围[0,6],截取字符串下标范围[7,9],N</a></p>
<p><a
        href="../testStringBuilderAppendAppend/xss003.do?username=seczone">
    测试StringBuilder.append(java.lang.CharSequence)，污点数据下标范围[4,10],截取字符串下标范围[7,10],Y</a></p>
<p><a
        href="../testStringBuilderAppendAppend/xss004.do?username=seczone">
    测试StringBuilder.append(java.lang.CharSequence,int,int)，污点数据下标范围[4,5],截取字符串下标范围[0,5],Y</a></p>
<p><a
        href="../testStringBuilderAppendAppend/xss005.do?username=seczone">
    测试StringBuilder.append(char[],int,int)，污点数据下标范围[4,7],截取字符串下标范围[1,6],Y</a></p>
<p><a
        href="../testStringBuilderAppendAppend/xss006.do?username=seczone">
    测试StringBuilder.append(char[],int,int)，污点数据下标范围[0,3],截取字符串下标范围[1,3],Y</a></p>

<p>StringBuffer.append方法+substring</p>
<p><a
        href="../testStringBufferAppendAppend/xss001.do?username=seczone">
    测试StringBuffer.append(java.lang.String)，污点数据下标范围[4,10],截取字符串下标范围[0,3],N</a></p>
<p><a
        href="../testStringBufferAppendAppend/xss002.do?username=seczone">
    测试StringBuffer.append(java.lang.StringBuffer)，污点数据下标范围[0,6],截取字符串下标范围[7,9],N</a></p>
<p><a
        href="../testStringBufferAppendAppend/xss003.do?username=seczone">
    测试StringBuffer.append(java.lang.CharSequence)，污点数据下标范围[4,10],截取字符串下标范围[7,10],Y</a></p>
<p><a
        href="../testStringBufferAppendAppend/xss004.do?username=seczone">
    测试StringBuffer.append(java.lang.CharSequence,int,int)，污点数据下标范围[4,6],截取字符串下标范围[0,5],Y</a></p>
<p><a
        href="../testStringBufferAppendAppend/xss005.do?username=seczone">
    测试StringBuffer.append(char[],int,int)，污点数据下标范围[1,4],截取字符串下标范围[0,3],Y</a></p>
<p><a
        href="../testStringBufferAppendAppend/xss006.do?username=seczone">
    测试StringBuffer.append(char[],int,int)，污点数据下标范围[2,5],截取字符串下标范围[4,7],Y</a></p>--%>


<h3>StringBuilder</h3>
<p>两组合</p>
<p>StringBuilder.append()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss001.do?username=seczone">
        测试StringBuilder.append(java.lang.String)+substring(int,int)，污点数据下标范围[4,10],截取字符串下标范围[0,3],N</a><br/>
    <a href="../testStringBuilderAppend/xss002.do?username=seczone">
        测试StringBuilder.append(java.lang.StringBuffer)+substring(int,int)，污点数据下标范围[0,6],截取字符串下标范围[7,9],N</a><br/>
    <a href="../testStringBuilderAppend/xss003.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence)+substring(int,int)，污点数据下标范围[4,10],截取字符串下标范围[7,10],Y</a><br/>
    <a href="../testStringBuilderAppend/xss004.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence,int,int)+substring(int,int)，污点数据下标范围[4,5],截取字符串下标范围[0,5],Y</a><br/>
    <a href="../testStringBuilderAppend/xss005.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+substring(int,int)，污点数据下标范围[4,7],截取字符串下标范围[1,6],Y</a><br/>
    <a href="../testStringBuilderAppend/xss006.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+substring(int,int)，污点数据下标范围[0,3],截取字符串下标范围[1,3],Y</a><br/>
</p>

<p>StringBuilder.append()+delete()</p>
<p>
    <a href="../testStringBuilderAppend/xss007.do?username=seczone">
        测试StringBuilder.append(java.lang.String)+delete(int,int)，污点数据下标范围[0,6],删除字符串下标范围[0,2],Y</a><br/>
    <a href="../testStringBuilderAppend/xss008.do?username=seczone">
        测试StringBuilder.append(java.lang.StringBuffer)+delete(int,int)，污点数据下标范围[0,6],删除字符串下标范围[5,9],Y</a><br/>
    <a href="../testStringBuilderAppend/xss009.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence)+delete(int,int)，污点数据下标范围[4,10],删除字符串下标范围[7,10],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0010.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence,int,int)+delete(int,int)，污点数据下标范围[4,5],删除字符串下标范围[0,3],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0011.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+delete(int,int)，污点数据下标范围[0,4],删除字符串下标范围[0,5],N</a><br/>
    <a href="../testStringBuilderAppend/xss0012.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+delete(int,int)，污点数据下标范围[0,3],删除字符串下标范围[0,4],N</a><br/>
</p>

<p>StringBuilder.append()+deleteCharAt()</p>
<p>
    <a href="../testStringBuilderAppend/xss0013.do?username=seczone">
        测试StringBuilder.append(java.lang.String)+deleteCharAt(int),污点数据下标范围[4,10],删除字符串下标2,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0014.do?username=seczone">
        测试StringBuilder.append(java.lang.StringBuffer)+deleteCharAt(int),污点数据下标范围[0,6],删除字符串下标2,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0015.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence)+deleteCharAt(int),污点数据下标范围[4,10],删除字符串下标4,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0016.do?username=seczone">
        测试StringBuilder.append(java.lang.CharSequence,int,int)+deleteCharAt(int),污点数据下标4,删除字符串下标4,N</a><br/>
    <a href="../testStringBuilderAppend/xss0017.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+deleteCharAt(int),污点数据下标4,删除字符串下标4,N</a><br/>
    <a href="../testStringBuilderAppend/xss0018.do?username=seczone">
        测试StringBuilder.append(char[],int,int)+deleteCharAt(int),污点数据下标0,删除字符串下标0,N</a><br/>
</p>

<p>StringBuilder.append()+getChars()</p>
<p>
    <a href="../testStringBuilderAppend/xss0019.do?username=seczone">
        测试StringBuilder.append(java.lang.String)+getChars(int,int,char[],int), Y</a><br/>
</p>

<p>StringBuilder.replace()</p>
<p>
    <a href="../testStringBuilderAppend/xss0025.do?username=seczone">
        测试StringBuilder.replace(int,int,String)+substring(int,int),污点数据下标范围[4,7],截取字符串下标范围[3,6],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0026.do?username=seczone">
        测试StringBuilder.replace(int,int,String)+delete(int,int),污点数据下标范围[4,7],删除字符串下标范围[3,8],N</a><br/>
    <a href="../testStringBuilderAppend/xss0027.do?username=seczone">
        测试StringBuilder.replace(int,int,String)+deleteCharAt(int),污点数据下标范围[4,8],删除字符下标4,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0028.do?username=seczone">
        测试StringBuilder.replace(int,int,String)+getChars(int,int,char[],int),getchars污点数据下标范围[0,2],替换字符串下标范围[0,2],N</a><br/>
</p>

<p>StringBuilder.insert()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss0029.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.String)+substring(int,int),污点数据下标范围[2,8],截取字符串下标范围[0,6],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0030.do?username=seczone">
        测试StringBuilder.insert(int,char[])+substring(int,int),污点数据下标范围[1,7],截取字符串下标范围[8,11],N</a><br/>
    <a href="../testStringBuilderAppend/xss0031.do?username=seczone">
        测试StringBuilder.insert(int,char)+substring(int,int),污点数据下标1,截取字符串下标范围[1,2],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0032.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence)+substring(int,int),污点数据下标范围[1,7],截取字符串下标范围[0,5],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0033.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence,int,int)+substring(int,int),污点数据下标范围[0,2],截取字符串下标范围[3,6],N</a><br/>
    <a href="../testStringBuilderAppend/xss0034.do?username=seczone">
        测试StringBuilder.insert(int,char[],int,int)+substring(int,int),污点数据下标范围[0,3],截取字符串下标范围[1,3],Y</a><br/>
</p>

<p>StringBuilder.insert()+delete()</p>
<p>
    <a href="../testStringBuilderAppend/xss0035.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.String)+delete(int,int),污点数据下标范围[2,8],删除字符串下标范围[2,5],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0036.do?username=seczone">
        测试StringBuilder.insert(int,char[])+delete(int,int),污点数据下标范围[1,7],删除字符串下标范围[0,8],N</a><br/>
    <a href="../testStringBuilderAppend/xss0037.do?username=seczone">
        测试StringBuilder.insert(int,char)+delete(int,int),污点数据下标2,删除字符串下标范围[2,5],N</a><br/>
    <a href="../testStringBuilderAppend/xss0038.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence)+delete(int,int),污点数据下标范围[1,7],删除字符串下标范围[2,5],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0039.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence,int,int)+delete(int,int),污点数据下标范围[0,3],删除字符串下标范围[0,4],N</a><br/>
    <a href="../testStringBuilderAppend/xss0040.do?username=seczone">
        测试StringBuilder.insert(int,char[],int,int)+delete(int,int),污点数据下标范围[0,3],删除字符串下标范围[1,2],Y</a><br/>
</p>

<p>StringBuilder.insert()+deleteCharAt()</p>
<p>
    <a href="../testStringBuilderAppend/xss0041.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.String)+deleteCharAt(int),污点数据下标范围[2,8],删除字符下标3,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0042.do?username=seczone">
        测试StringBuilder.insert(int,char[])+deleteCharAt(int),污点数据下标范围1,删除字符下标1,N</a><br/>
    <a href="../testStringBuilderAppend/xss0043.do?username=seczone">
        测试StringBuilder.insert(int,char)+deleteCharAt(int),污点数据下标范围2,删除字符下标3,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0044.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence)+deleteCharAt(int),污点数据下标范围[1,7],删除字符下标3,Y</a><br/>
    <a href="../testStringBuilderAppend/xss0045.do?username=seczone">
        测试StringBuilder.insert(int,java.lang.CharSequence,int,int)+deleteCharAt(int),污点数据下标0,删除字符下标0,N</a><br/>
    <a href="../testStringBuilderAppend/xss0046.do?username=seczone">
        测试StringBuilder.insert(int,char[],int,int)+deleteCharAt(int),污点数据下标范围[0,3],删除字符下标1,Y</a><br/>
</p>

<p>StringBuilder.insert()+getChars()</p>
<p>
    <a href="../testStringBuilderAppend/xss0047.do?username=seczone">测试StringBuilder.insert(int,char[])+getChars(int,int,char[],int),污点数据下标范围[0,3],插入后污点数据下标[2,3],Y</a><br/>
    <a href="../testStringBuilderAppend/xss0048.do?username=seczone">测试StringBuilder.insert(int,char[],int,int)+getChars(int,int,char[],int),污点数据下标范围[0,5],插入后污点数据下标[1,3],Y</a><br/>
</p>

<p>三组合</p>
<p>StringBuilder.append()+replace()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss0050.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0051.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0052.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0053.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0054.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0055.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)</a><br/>
</p>

<p>StringBuilder.append()+replace()+delete()</p>
<p>
    <a href="../testStringBuilderAppend/xss0056.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0057.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0058.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0059.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0060.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0061.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+delete(int,int)</a><br/>
</p>

<p>StringBuilder.append()+replace()+getChars()</p>
<p>
    <a href="../testStringBuilderAppend/xss0062.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss0063.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss0064.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss0065.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss0066.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss0067.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
</p>

<p>StringBuilder.append()+insert()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss0068.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0069.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0070.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0071.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0072.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0073.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss0074.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0075.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0076.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0077.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0078.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0079.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss0080.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0081.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0082.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0083.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0084.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0085.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss0086.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0087.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0088.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0089.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0090.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0091.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss0092.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0093.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0094.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0095.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0096.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0097.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss0098.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0099.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00100.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00101.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00102.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00103.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <br/>
</p>


<p>StringBuilder.append()+insert()+delete()</p>
<p>
    <a href="../testStringBuilderAppend/xss00104.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00105.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00106.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00107.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00108.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00109.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00110.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[]+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00111.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[]+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00112.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[]+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00113.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[]+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00114.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[]+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00115.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[]+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00116.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00117.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00118.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00119.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00120.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00121.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00122.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00123.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00124.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00125.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00126.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00127.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00128.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00129.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00130.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00131.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00132.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00133.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00134.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00135.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00136.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00137.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00138.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00139.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <br/>
</p>

<p>StringBuilder.append()+insert()+getChars()</p>
<p>
    <a href="../testStringBuilderAppend/xss00140.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00141.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00142.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00143.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00144.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00145.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00146.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00147.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00148.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00149.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00150.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00151.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00152.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00153.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00154.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00155.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00156.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00157.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00158.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00159.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00160.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00161.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00162.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00163.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00164.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00165.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00166.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00167.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00168.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00169.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00170.do?username=seczone">测试StringBuilder.append(java.lang.String)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00171.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00172.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00173.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00174.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBuilderAppend/xss00175.do?username=seczone">测试StringBuilder.append(char[],int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <br/>
</p>

<p>四组合</p>
<p>StringBuilder.append()+insert()+replace()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss00176.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00177.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00178.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00179.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00180.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00181.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00182.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00183.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00184.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00185.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00186.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00187.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00188.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00189.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00190.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00191.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00192.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss0019 3.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00194.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00195.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00196.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00197.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00198.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00199.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00200.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00201.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00202.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00203.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00204.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00205.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00206.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00207.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00208.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00209.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00210.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00211.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <br/>
</p>

<p>StringBuilder.append()+insert()+delete()</p>
<p>
    <a href="../testStringBuilderAppend/xss00212.do?username=seczone">测试StringBuilder.append(java.lang.String)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00213.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00214.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00215.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00216.do?username=seczone">测试StringBuilder.append(char[],int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00217.do?username=seczone">测试StringBuilder.append(char[],int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00218.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00219.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00220.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00221.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00222.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00223.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00224.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00225.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00226.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00227.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00228.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00229.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00230.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00231.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00232.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00233.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00234.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00235.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00236.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00237.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00238.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00239.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00240.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00241.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00242.do?username=seczone">测试StringBuilder.append(java.lang.String)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00243.do?username=seczone">测试StringBuilder.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00244.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00245.do?username=seczone">测试StringBuilder.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00246.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00247.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <br/>
</p>

<p>五组合</p>
<p>append()+replace()+substring()+delete()+substring()</p>
<p>
    <a href="../testStringBuilderAppend/xss00248.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00249.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00250.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00251.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00252.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00253.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00254.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00255.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00256.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00257.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00258.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00259.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00260.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00261.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00262.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00263.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00264.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00265.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00266.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00267.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00268.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00269.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00270.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00271.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00272.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00273.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00274.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00275.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00276.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00277.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBuilderAppend/xss00278.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00279.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00280.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00281.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00282.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBuilderAppend/xss00283.do?username=seczone">测试StringBuilder.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
</p>


<h3>StringBuffer</h3>
<p>两组合</p>
<p>StringBuffer.append()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss1001.do?username=seczone">
        测试StringBuffer.append(java.lang.String)+substring(int,int)，污点数据下标范围[4,10],截取字符串下标范围[0,3],N</a><br/>
    <a href="../testStringBufferAppend/xss1002.do?username=seczone">
        测试StringBuffer.append(java.lang.StringBuffer)+substring(int,int)，污点数据下标范围[0,6],截取字符串下标范围[7,9],N</a><br/>
    <a href="../testStringBufferAppend/xss1003.do?username=seczone">
        测试StringBuffer.append(java.lang.CharSequence)+substring(int,int)，污点数据下标范围[4,10],截取字符串下标范围[7,10],Y</a><br/>
    <a href="../testStringBufferAppend/xss1004.do?username=seczone">
        测试StringBuffer.append(java.lang.CharSequence,int,int)+substring(int,int)，污点数据下标范围[4,5],截取字符串下标范围[0,5],Y</a><br/>
    <a href="../testStringBufferAppend/xss1005.do?username=seczone">
        测试StringBuffer.append(char[],int,int)+substring(int,int)，污点数据下标范围[4,7],截取字符串下标范围[1,6],Y</a><br/>
    <a href="../testStringBufferAppend/xss1006.do?username=seczone">
        测试StringBuffer.append(char[],int,int)+substring(int,int)，污点数据下标范围[0,3],截取字符串下标范围[1,3],Y</a><br/>
</p>

<p>StringBuffer.append()+delete()</p>
<p>
    <a href="../testStringBufferAppend/xss1007.do?username=seczone">
        测试StringBuffer.append(java.lang.String)+delete(int,int)，污点数据下标范围[0,6],删除字符串下标范围[0,2],Y</a><br/>
    <a href="../testStringBufferAppend/xss1008.do?username=seczone">
        测试StringBuffer.append(java.lang.StringBuffer)+delete(int,int)，污点数据下标范围[0,6],删除字符串下标范围[5,9],Y</a><br/>
    <a href="../testStringBufferAppend/xss1009.do?username=seczone">
        测试StringBuffer.append(java.lang.CharSequence)+delete(int,int)，污点数据下标范围[4,10],删除字符串下标范围[7,10],Y</a><br/>
    <a href="../testStringBufferAppend/xss10010.do?username=seczone">
        测试StringBuffer.append(java.lang.CharSequence,int,int)+delete(int,int)，污点数据下标范围[4,5],删除字符串下标范围[0,3],Y</a><br/>
    <a href="../testStringBufferAppend/xss10011.do?username=seczone">
        测试StringBuffer.append(char[],int,int)+delete(int,int)，污点数据下标范围[4,7],删除字符串下标范围[1,6],N</a><br/>
    <a href="../testStringBufferAppend/xss10012.do?username=seczone">
        测试StringBuffer.append(char[],int,int)+delete(int,int)，污点数据下标范围[0,3],截取字符串下标范围[1,3],N</a><br/>
</p>

<p>StringBuffer.append()+deleteCharAt()</p>
<p>
    <a href="../testStringBufferAppend/xss10013.do?username=seczone">测试StringBuffer.append(java.lang.String)+deleteCharAt(int),Y</a><br/>
    <a href="../testStringBufferAppend/xss10014.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+deleteCharAt(int),Y</a><br/>
    <a href="../testStringBufferAppend/xss10015.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+deleteCharAt(int),Y</a><br/>
    <a href="../testStringBufferAppend/xss10016.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+deleteCharAt(int),Y</a><br/>
    <a href="../testStringBufferAppend/xss10017.do?username=seczone">测试StringBuffer.append(char[],int,int)+deleteCharAt(int),N</a><br/>
    <a href="../testStringBufferAppend/xss10018.do?username=seczone">测试StringBuffer.append(char[],int,int)+deleteCharAt(int),N</a><br/>
</p>

<p>StringBuffer.append()+getChars()</p>
<p>
    <a href="../testStringBufferAppend/xss10019.do?username=seczone">测试StringBuffer.append(java.lang.String)+getChars(int,int,char[],int)</a><br/>
</p>

<p>StringBuffer.replace()</p>
<p>
    <a href="../testStringBufferAppend/xss10025.do?username=seczone">测试StringBuffer.replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10026.do?username=seczone">测试StringBuffer.replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10027.do?username=seczone">测试StringBuffer.replace(int,int,String)+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10028.do?username=seczone">测试StringBuffer.replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
</p>

<p>StringBuffer.insert()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss10029.do?username=seczone">测试StringBuffer.insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10030.do?username=seczone">测试StringBuffer.insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10031.do?username=seczone">测试StringBuffer.insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10032.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10033.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10034.do?username=seczone">测试StringBuffer.insert(int,char[],int,int)+substring(int,int)</a><br/>
</p>

<p>StringBuffer.insert()+delete()</p>
<p>
    <a href="../testStringBufferAppend/xss10035.do?username=seczone">测试StringBuffer.insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10036.do?username=seczone">测试StringBuffer.insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10037.do?username=seczone">测试StringBuffer.insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10038.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10039.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10040.do?username=seczone">测试StringBuffer.insert(int,char[],int,int)+delete(int,int)</a><br/>
</p>

<p>StringBuffer.insert()+deleteCharAt()</p>
<p>
    <a href="../testStringBufferAppend/xss10041.do?username=seczone">测试StringBuffer.insert(int,java.lang.String)+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10042.do?username=seczone">测试StringBuffer.insert(int,char[])+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10043.do?username=seczone">测试StringBuffer.insert(int,char)+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10044.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence)+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10045.do?username=seczone">测试StringBuffer.insert(int,java.lang.CharSequence,int,int)+deleteCharAt(int)</a><br/>
    <a href="../testStringBufferAppend/xss10046.do?username=seczone">测试StringBuffer.insert(int,char[],int,int)+deleteCharAt(int)</a><br/>
</p>

<p>StringBuffer.insert()+getChars()</p>
<p>
    <a href="../testStringBufferAppend/xss10047.do?username=seczone">测试StringBuffer.insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10048.do?username=seczone">测试StringBuffer.insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
</p>

<p>三组合</p>
<p>StringBuffer.append()+replace()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss10050.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10051.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10052.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10053.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10054.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10055.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)</a><br/>
</p>

<p>StringBuffer.append()+replace()+delete()</p>
<p>
    <a href="../testStringBufferAppend/xss10056.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10057.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10058.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10059.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10060.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10061.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+delete(int,int)</a><br/>
</p>

<p>StringBuffer.append()+replace()+getChars()</p>
<p>
    <a href="../testStringBufferAppend/xss10062.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10063.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10064.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10065.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10066.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss10067.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+getChars(int,int,char[],int)</a><br/>
</p>

<p>StringBuffer.append()+insert()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss10068.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10069.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10070.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10071.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10072.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10073.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss10074.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10075.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10076.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10077.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10078.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10079.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[])+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss10080.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10081.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10082.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10083.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10084.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10085.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss10086.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10087.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10088.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10089.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10090.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10091.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss10092.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10093.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10094.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10095.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10096.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10097.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss10098.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss10099.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100100.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100101.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100102.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100103.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <br/>
</p>


<p>StringBuffer.append()+insert()+delete()</p>
<p>
    <a href="../testStringBufferAppend/xss100104.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100105.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100106.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100107.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100108.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100109.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100110.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[]+
        delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100111.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[]+
        delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100112.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[]+
        delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100113.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[]+
        delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100114.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[]+
        delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100115.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[]+
        delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100116.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100117.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100118.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100119.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100120.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100121.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100122.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100123.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100124.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100125.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100126.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100127.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100128.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100129.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100130.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100131.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100132.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100133.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100134.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100135.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100136.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100137.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100138.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100139.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <br/>
</p>

<p>StringBuffer.append()+insert()+getChars()</p>
<p>
    <a href="../testStringBufferAppend/xss100140.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100141.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100142.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100143.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100144.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100145.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.String)+getChars(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100146.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100147.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100148.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100149.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100150.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100151.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[])+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100152.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100153.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100154.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100155.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100156.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100157.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100158.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100159.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100160.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100161.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100162.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100163.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100164.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100165.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100166.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100167.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100168.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100169.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,java.lang.CharSequence,int,int)+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100170.do?username=seczone">测试StringBuffer.append(java.lang.String)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100171.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100172.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100173.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100174.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testStringBufferAppend/xss100175.do?username=seczone">测试StringBuffer.append(char[],int,int)+insert(int,char[],int,int)+getChars(int,int,char[],int)</a><br/>
    <br/>
</p>

<p>四组合</p>
<p>StringBuffer.append()+insert()+replace()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss100176.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100177.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100178.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100179.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100180.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100181.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.String)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100182.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100183.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100184.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100185.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100186.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100187.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+
        substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100188.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100189.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100190.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100191.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100192.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100123.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100194.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100195.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100196.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100197.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100198.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100199.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100200.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100201.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100202.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100203.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100204.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100205.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String+insert(int,java.lang.CharSequence,int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100206.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100207.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100208.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100209.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100210.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100211.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+substring(int,int)</a><br/>
    <br/>
</p>

<p>StringBuffer.append()+insert()+delete()</p>
<p>
    <a href="../testStringBufferAppend/xss100212.do?username=seczone">测试StringBuffer.append(java.lang.String)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100213.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100214.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100215.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100216.do?username=seczone">测试StringBuffer.append(char[],int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100217.do?username=seczone">测试StringBuffer.append(char[],int,int)+
        replace(int,int,String)+insert(int,java.lang.String)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100218.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100219.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100220.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100221.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100222.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100223.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[])+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100224.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100225.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100226.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100227.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100228.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100229.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100230.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100231.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100232.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100233.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100234.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100235.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100236.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100237.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100238.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100239.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100240.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100241.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,java.lang.CharSequence,int,int)+delete(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100242.do?username=seczone">测试StringBuffer.append(java.lang.String)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100243.do?username=seczone">测试StringBuffer.append(java.lang.StringBuffer)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100244.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100245.do?username=seczone">测试StringBuffer.append(java.lang.CharSequence,int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100246.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100247.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+insert(int,char[],int,int)+delete(int,int)</a><br/>
    <br/>
</p>

<p>五组合</p>
<p>append()+replace()+substring()+delete()+substring()</p>
<p>
    <a href="../testStringBufferAppend/xss100248.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100249.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100250.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100251.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100252.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100253.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100254.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100255.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100256.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100257.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100258.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100259.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100260.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100261.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100262.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100263.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100264.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100265.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100266.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100267.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100268.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100269.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100270.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100271.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100272.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100273.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100274.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100275.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100276.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100277.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
    <a href="../testStringBufferAppend/xss100278.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100279.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100280.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100281.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100282.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <a href="../testStringBufferAppend/xss100283.do?username=seczone">测试StringBuffer.append(char[],int,int)+replace(int,int,String)+substring(int,int)+delete(int,int)+substring(int,int)</a><br/>
    <br/>
</p>

<h3>StringWriter</h3>
<p>
    <a href="../testOtherStringMethod/xss2001.do?username=seczone">测试StringWriter.write(char[],int,int)</a><br/>
    <a href="../testOtherStringMethod/xss2002.do?username=seczone">测试StringWriter.write(java.lang.String)</a><br/>
    <a href="../testOtherStringMethod/xss2003.do?username=seczone">测试StringWriter.write(java.lang.String,int,int)</a><br/>
</p>
<h3>String</h3>
<p>
    <a href="../testSubStringAppend/xss3001.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)</a><br/>
    <a href="../testSubStringAppend/xss3002.do?username=seczone">测试String.init(byte[],int,int)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss3003.do?username=seczone">测试String.init(byte[],int,int)+replace()</a><br/>
    <a href="../testSubStringAppend/xss3004.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss3005.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+replace()</a><br/>
    <a href="../testSubStringAppend/xss3006.do?username=seczone">测试String.init(byte[],int,int)+concat(String)+replace()</a><br/>
    <a href="../testSubStringAppend/xss3007.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)+replace()</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss3008.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss3009.do?username=seczone">测试String.init(byte[],int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30010.do?username=seczone">测试String.init(byte[],int,int)+replace()+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30011.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30012.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+replace()+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30013.do?username=seczone">测试String.init(byte[],int,int)+concat(String)+replace()+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30014.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)+replace()+getChars(int,int,char[],int)</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss30015.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30016.do?username=seczone">测试String.init(byte[],int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30017.do?username=seczone">测试String.init(byte[],int,int)+replace()+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30018.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30019.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+replace()+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30020.do?username=seczone">测试String.init(byte[],int,int)+concat(String)+replace()+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30021.do?username=seczone">测试String.init(byte[],int,int)+substring(int,int)+concat(String)+replace()+getBytes(int,int,byte[],int)</a><br/>
    <%--<a href="../testSubStringAppend/xss30022.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30023.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30024.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30025.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30026.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30027.do?username=seczone">测试String.init(byte[],int,int,java.lang.String)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss30028.do?username=seczone">测试String.init(char[],int,int)+substring(int,int)</a><br/>
    <a href="../testSubStringAppend/xss30029.do?username=seczone">测试String.init(char[],int,int)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30030.do?username=seczone">测试String.init(char[],int,int)+substring+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30031.do?username=seczone">测试String.init(char[],int,int)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30032.do?username=seczone">测试String.init(char[],int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30033.do?username=seczone">测试String.init(char[],int,int)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30034.do?username=seczone">测试String.init(char[],int,int)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30035.do?username=seczone">测试String.init(char[],int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30036.do?username=seczone">测试String.init(char[],int,int)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss30037.do?username=seczone">测试String.init(java.lang.String)+substring(int,int)</a><br/>
    <a href="../testSubStringAppend/xss30038.do?username=seczone">测试String.init(java.lang.String)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30039.do?username=seczone">测试String.init(java.lang.String)+substring+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30040.do?username=seczone">测试String.init(java.lang.String)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30041.do?username=seczone">测试String.init(java.lang.String)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30042.do?username=seczone">测试String.init(java.lang.String)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30043.do?username=seczone">测试String.init(java.lang.String)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30044.do?username=seczone">测试String.init(java.lang.String)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30045.do?username=seczone">测试String.init(java.lang.String)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss30046.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring(int,int)</a><br/>
    <a href="../testSubStringAppend/xss30047.do?username=seczone">测试String.init(java.lang.StringBuffer)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30048.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30049.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30050.do?username=seczone">测试String.init(java.lang.StringBuffer)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30051.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30052.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30053.do?username=seczone">测试String.init(java.lang.StringBuffer)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30054.do?username=seczone">测试String.init(java.lang.StringBuffer)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <br/>
    <a href="../testSubStringAppend/xss30055.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring(int,int)</a><br/>
    <a href="../testSubStringAppend/xss30056.do?username=seczone">测试String.init(java.lang.StringBuilder)+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30057.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring+concat(String)</a><br/>
    <a href="../testSubStringAppend/xss30058.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring(int,int)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30059.do?username=seczone">测试String.init(java.lang.StringBuilder)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30060.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring(int,int)+concat(String)+getChars(int,int,char[],int)</a><br/>
    <a href="../testSubStringAppend/xss30061.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring(int,int)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30062.do?username=seczone">测试String.init(java.lang.StringBuilder)+concat(String)+getBytes(int,int,byte[],int)</a><br/>
    <a href="../testSubStringAppend/xss30063.do?username=seczone">测试String.init(java.lang.StringBuilder)+substring(int,int)+concat(String)+getBytes(int,int,byte[],int)</a><br/>--%>
</p>


</body>
</html>
