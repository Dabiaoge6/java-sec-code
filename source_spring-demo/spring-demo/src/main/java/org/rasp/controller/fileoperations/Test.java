package org.rasp.controller.fileoperations;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;

public class Test {
  public static void main(String[] args) throws DocumentException {
    String jsonStr = "{\"id\": \"1\", \"name\": \"test\", \"info\": {\"mobile\": \"18237475689\", \"qq\": \"2333098\", \"email\": \"test@qq.com\" } }";
    jsonStr="{\"id\": \"1\",\"name\": \"test\",\"info\": {\"mobile\": \"18237475689\",\"qq\": \"2333098\",\"email\": \"test@qq.com\",\"card\":{\"id\":\"340823199307130449\",\"bank\":\"6216610200016587010\"}}}";
//    SensitiveType sensitiveType = null;
//    sensitiveType = isSensitiveJsonParam(jsonStr);
    System.out.println("=======json========");
//    System.out.println((sensitiveType != null) ? sensitiveType.getType() : "null");
    System.out.println("=======json========");
    System.out.println("\r\n");
//    sensitiveType = null;
    String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><applicant><family><father>bb</father><token>mm</token></family><id>340823199307130449</id><age>11</age><name>hjj</name><mobile>110</mobile><email>hujj@seczone.cn</email><address>hf</address></applicant>";
//    sensitiveType = isSensitiveXmlParam(xmlStr);
    if(xmlStr.length()>10){
      System.out.println(xmlStr.substring(0,xmlStr.indexOf("340823199307130449")+"340823199307130449".length()));
    }
    System.out.println("=======xml========");
//    System.out.println((sensitiveType != null) ? sensitiveType.getType() : "null");
    System.out.println("=======xml========");
    String formData = "username=hujj&pass=seczone";
//    sensitiveType = null;
//    sensitiveType = isSensitiveFormDataParam(formData);
    System.out.println("=======formdata1========");
//    System.out.println((sensitiveType != null) ? sensitiveType.getType() : "null");
    System.out.println("=======formdata1========");
    formData = "id=6216610200016587010";
//    sensitiveType = null;
//    sensitiveType = isSensitiveFormDataParam(formData);
    System.out.println("=======formdata2========");
//    System.out.println((sensitiveType != null) ? sensitiveType.getType() : "null");
    System.out.println("=======formdata2========");

  }
}
