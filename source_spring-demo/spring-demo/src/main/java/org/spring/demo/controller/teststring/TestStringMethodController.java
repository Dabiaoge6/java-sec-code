package org.spring.demo.controller.teststring;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

@Controller
@RequestMapping("testString")
public class TestStringMethodController {

  @RequestMapping("/xss001.do")
  public void xss001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "test";
    byte[] bytes = test.getBytes();
    inputName.getBytes(1,3,bytes,1);
    String str2 = new String(bytes);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str2, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss002.do")
  public void xss002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901
    String str4= str3.substring(8,10);//49
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss003.do")
  public void xss003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901
    String str4 = str3.substring(1,9);//12xyabc4
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss004.do")
  public void xss004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901
    String str4 = str3.substring(4,10);//yabc49
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss005.do")
  public void xss005(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901
    String str4 = str3.substring(1,5);//12xy
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss006.do")
  public void xss006(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901
    String str4 = str3.substring(0,3);//012
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss007.do")
  public void xss007(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "012345678901";
    byte[] bytes = test.getBytes();
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    //return = 012xyabc456 this=012xyabc [3,7]
    String newInputName = str2.concat("456");
    newInputName.getBytes(1,9,bytes,1);
    String str3 = new String(bytes);//012xyabc4901 [3,7]
    String str4= str3.substring(4,7);//yab [0,2]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss008.do")
  public void xss008(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "test";
    byte[] bytes = test.getBytes();
    inputName.getBytes(1,3,bytes,0);//yast
    String str2 = new String(bytes);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str2, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss034.do")
  public void xss034(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "test";
    byte[] bytes = test.getBytes();
    inputName.getBytes(1,inputName.length(),bytes,0);//yabc
    String str2 = new String(bytes);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str2, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @RequestMapping("/xss009.do")
  public void xss009(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = inputName.concat("987");//xyabc987
    newInputName.getBytes(0,4,bytes,1);//0xyab56789
    String str3 = new String(bytes);//0xyab56789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss033.do")
  public void xss033(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = inputName.concat("987");//xyabc987
    newInputName.getBytes(0,newInputName.length(),bytes,1);//0xyabc9879
    String str3 = new String(bytes);//0xyabc9879
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss029.do")
  public void xss029(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = inputName.concat("987");//xyabc987
    newInputName.getBytes(2,5,bytes,1);//0abc456789
    String str3 = new String(bytes);//0xyab56789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss032.do")
  public void xss032(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = inputName.concat("987");//xyabc987
    newInputName.getBytes(2,newInputName.length(),bytes,1);//0abc987789
    String str3 = new String(bytes);//0abc987789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //不上报xss
  @RequestMapping("/xss010.do")
  public void xss010(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = inputName.concat("987");//xyabc987
    newInputName.getBytes(5,7,bytes,1);//0983456789
    String str3 = new String(bytes);//0983456789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //不上报xss
  @RequestMapping("/xss011.do")
  public void xss011(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = "===".concat(inputName);//===xyabc
    newInputName.getBytes(0,2,bytes,1);//0==3456789
    String str3 = new String(bytes);//0==3456789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss012.do")
  public void xss012(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = "===".concat(inputName);//===xyabc
    newInputName.getBytes(2,6,bytes,1);//0=xya5678901
    String str3 = new String(bytes);//0=xya5678901
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss030.do")
  public void xss030(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = "===".concat(inputName);//===xyabc
    newInputName.getBytes(2,newInputName.length(),bytes,1);//0=xyabc789
    String str3 = new String(bytes);//0=xyabc789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss013.do")
  public void xss013(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = "===".concat(inputName);//===xyabc
    newInputName.getBytes(3,5,bytes,1);//0xy345678901
    String str3 = new String(bytes);//0xy345678901
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss031.do")
  public void xss031(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String test = "0123456789";
    byte[] bytes = test.getBytes();
    String newInputName = "===".concat(inputName);//===xyabc
    newInputName.getBytes(3,newInputName.length(),bytes,1);//0xyabc6789
    String str3 = new String(bytes);//0xyabc6789
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str3, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //subString(int,int)
  @RequestMapping("/xss014.do")
  public void xss014(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str4= inputName.substring(2,4);//01
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //不上报xss
  @RequestMapping("/xss015.do")
  public void xss015(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    String str4= str2.substring(0,2);//01
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss016.do")
  public void xss016(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    String str4= str2.substring(1,7);//12xyab
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss017.do")
  public void xss017(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    String str4= str2.substring(3,8);//xyabc
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss018.do")
  public void xss018(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = xyabc012
    String str2 = inputName.concat(str1);
    String str4= str2.substring(0,2);//xy
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss019.do")
  public void xss019(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = xyabc012
    String str2 = inputName.concat(str1);
    String str4= str2.substring(2,6);//abc0
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  //不上报xss
  @RequestMapping("/xss020.do")
  public void xss020(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = xyabc012
    String str2 = inputName.concat(str1);
    String str4= str2.substring(5,7);//01
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //substring(int)
  @RequestMapping("/xss021.do")
  public void xss021(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str4= inputName.substring(2);//abc
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss022.do")
  public void xss022(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    String str4= str2.substring(4);//yabc
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss023.do")
  public void xss023(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = 012xyabc [3,7]
    String str2 = str1.concat(inputName);
    String str4= str2.substring(1);//12xyabc
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss024.do")
  public void xss024(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = xyabc012
    String str2 = inputName.concat(str1);
    String str4= str2.substring(2);//abc012
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //不上报xss
  @RequestMapping("/xss025.do")
  public void xss025(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    //return = xyabc012
    String str2 = inputName.concat(str1);
    String str4= str2.substring(5);//012
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss026.do")
  public void xss026(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    String str2 = "345";
    String str5 = str1.concat(inputName);//012xyabc
    //return = xyabc012
    String str3 = str5.concat(str2);//012xyabc345 [3,7]
    String str4= str3.substring(1);//12xyabc345 [2,6]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @RequestMapping("/xss027.do")
  public void xss027(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    String str2 = "345";
    String str5 = str1.concat(inputName);//012xyabc
    //return = xyabc012
    String str3 = str5.concat(str2);//012xyabc345
    String str4= str3.substring(4);//yabc345
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss028.do")
  public void xss028(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "012";
    String str2 = "345";
    String str5 = str1.concat(inputName);//012xyabc
    //return = xyabc012
    String str3 = str5.concat(str2);//012xyabc345
    String str4= str3.substring(8);//01
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str4, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
