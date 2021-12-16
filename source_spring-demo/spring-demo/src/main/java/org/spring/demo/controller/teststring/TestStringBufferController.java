package org.spring.demo.controller.teststring;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

@Controller
@RequestMapping("testStringBuffer")
public class TestStringBufferController {

  public static void main(String[] args) {
    StringBuffer sb = new StringBuffer("xyabc");
    String source = "0123456789";
    sb.replace(0,1,source);
    System.out.println("replace :"+sb.toString());
    //insert
    String inputName = "xyabc";
    sb = new StringBuffer("0123");
    //us=p1,ue=p1+p2.length-1
//    sb.insert(0,inputName);//[0,4] ->xyabc0123 [0,4]
    sb.insert(2,inputName);//[0,4] ->01xyabc23 [2,6]
    System.out.println("insert1 :"+sb.toString());
    sb = new StringBuffer("0123");
    //==()== + [===*****] ->==[===*****]==
    //us = p1+os,ue=p1+os+(oe-os)
    sb.insert(2,"456"+inputName);//[3,7] ->01456xyabc23 [5,9]
    //====() + [===*****] ->====[===*****]
    //us = p1+os,ue=p1+os+(oe-os)
    sb.insert(4,"456"+inputName);//[3,7] ->0123456xyabc [7,11]
    System.out.println("insert2 :"+sb.toString());
    sb = new StringBuffer("===");
    //us = os+p1,ue=oe+p1
    //=== + *****
//    sb.insert(0,inputName.toCharArray());//[0,4]->xyabc===[0,4]
//    sb.insert(2,inputName.toCharArray());//[0,4]->==xyabc=[2,6]

    //=== + ==*****
    sb.insert(0,("012"+inputName).toCharArray());//[3,7]->012xyabc===[3,7]
//    sb.insert(2,("012"+inputName).toCharArray());//[3,7]->==012xyabc=[5,9]
    System.out.println("insert3 :"+sb.toString());

    sb = new StringBuffer("===");
    sb.insert(0,'x');//[0,0]->x===[0,4]
//    sb.insert(2,'x');//[0,0]->==x=[2,6]
    System.out.println("insert4 :"+sb.toString());
    //case1
    sb = new StringBuffer("test");
    CharSequence charSequence = inputName;
    //不包含end所在字母
    sb.insert(1,charSequence,1,3);
    System.out.println("insert5 :"+sb.toString());
    //case2
    sb = new StringBuffer("test");
    charSequence = "012"+inputName;
    //不包含end所在字母
    sb.insert(2,charSequence,2,6);
    System.out.println("insert7 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName;
    //不包含end所在字母
    sb.insert(2,charSequence,3,6);
    System.out.println("insert8 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName;
    //不包含end所在字母
    sb.insert(4,charSequence,4,charSequence.length());
    System.out.println("insert9 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName;
    //不包含end所在字母
    sb.insert(1,charSequence,3,charSequence.length());
    System.out.println("insert10 :"+sb.toString());
    //case3
    sb = new StringBuffer("test");
    charSequence = inputName+"012";
    //不包含end所在字母
    sb.insert(1,charSequence,1,3);
    System.out.println("insert11 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = inputName+"012";
    //不包含end所在字母
    sb.insert(1,charSequence,0,4);
    System.out.println("insert12 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = inputName+"012";
    //不包含end所在字母
    sb.insert(1,charSequence,2,5);
    System.out.println("insert13 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = inputName+"012";
    //不包含end所在字母
    sb.insert(1,charSequence,2,4);
    System.out.println("insert14 :"+sb.toString());
    //case4
    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,1,5);
    System.out.println("case4-insert15 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,3,5);
    System.out.println("case4-insert16 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,0,7);
    System.out.println("case4-insert17 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,3,7);
    System.out.println("case4-insert18 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(4,charSequence,2,8);
    System.out.println("case4-insert19 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,5,9);
    System.out.println("case4-insert20 :"+sb.toString());

    sb = new StringBuffer("test");
    charSequence = "012"+inputName+"456";
    //不包含end所在字母
    sb.insert(2,charSequence,3,9);
    System.out.println("case4-insert21 :"+sb.toString());

    System.out.println("========================================");
    inputName = "xyabc";
    sb = new StringBuffer("test");
    char[] chars = inputName.toCharArray();
    //不包含end所在字母
    sb.insert(0,chars,1,3);
    System.out.println("chars-insert1 :"+sb.toString());

    charSequence = inputName;
    sb = new StringBuffer("test");
    //length=end-start
    sb.insert(0,charSequence,1,4);
    System.out.println("charSequence-insert1 :"+sb.toString());
  }

  //(*)**** deleteCharAt(int)
  @RequestMapping("/xss001.do")
  public void deleteCharAt001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(inputName);
    //**(*)** us = os,ue=oe-1
    stringBuffer.deleteCharAt(0);//[0,4] -> xybc [0,3]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //=(=)=***** deleete(int,int)
  @RequestMapping("/xss002.do")
  public void deleteCharAt002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("012"+inputName);
    //=(=)=***** us=os-1,ue=oe-1
    stringBuffer.deleteCharAt(2);//[3,7]->[2,6]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //****(*)=== delete(int,int)
  @RequestMapping("/xss003.do")
  public void deleteCharAt003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer(inputName+"456");
    //****(*)=== us = os ,ue = oe-1
    stringBuffer.deleteCharAt(4);//[0,4] ->[0,3]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss004.do")
  public void deleteCharAt004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("012"+inputName+"456");
    //===*****(=)==
    stringBuffer.deleteCharAt(8);//[3,7]->[3,7]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss005.do")
  public void deleteCharAt005(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//x
    StringBuffer stringBuffer = new StringBuffer("1"+inputName);
    //(*)
    stringBuffer.deleteCharAt(1);//[0,0]->remove
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss006.do")
  public void replace001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("0123");
    //(=)=== + ***** -> *****===
    stringBuffer.replace(0,1,inputName);//xyabc123 [0,4]
    //=(==)= + ***** -> =*****= us = p1,ue = p1+p3.length-1
//    stringBuffer.replace(1,3,inputName);//0xyabc3 [1,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss007.do")
  public void replace002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("6789");
    //==(==) + ===***** -> =====***** us = p1+os ,ue = p1+os+(oe-os)
    stringBuffer.replace(2,4,"012"+inputName);//[3,7]->67012xyabc [5,9]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss008.do")
  public void replace003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("6789");
    //=(==)= + *****=== -> =*****==== us = p1+os ,ue = p1+os+(oe-os)
    stringBuffer.replace(1,3,inputName+"012");//[0,4]->6xyabc0129 [1,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss009.do")
  public void replace004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("6789");
    //(====) + =*****=-> =*****= us = p1+os ,ue = p1+os+(oe-os)
    stringBuffer.replace(0,4,"0"+inputName+"0");//[1,5]->0xyabc0 [1,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss010.do")
  public void inserte001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("0123");
    //us=p1,ue=p1+p2.length-1
    stringBuffer.insert(0,inputName);//[0,4] ->xyabc0123 [0,4]
//    stringBuffer.insert(2,inputName);//[0,4] ->01xyabc23 [2,6]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss011.do")
  public void insert002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("0123");
    //==()== + [===*****] ->==[===*****]==
    //us = p1+os,ue=p1+os+(oe-os)
    stringBuffer.insert(2,"456"+inputName);//[3,7] ->01456xyabc23 [5,9]
    //====() + [===*****] ->====[===*****]
    //us = p1+os,ue=p1+os+(oe-os)
//    stringBuffer.insert(4,"456"+inputName);//[3,7] ->0123456xyabc [7,11]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss012.do")
  public void insert003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("===");
    //us = os+p1,ue=oe+p1
    //=== + *****
//    stringBuffer.insert(0,inputName.toCharArray());//[0,4]->xyabc===[0,4]
    stringBuffer.insert(2,inputName.toCharArray());//[0,4]->==xyabc=[2,6]

//    CharSequence charSequence = inputName;
//    stringBuffer.insert(0,charSequence);//[0,4]->xyabc===[0,4]
//    stringBuffer.insert(2,charSequence);//[0,4]->==xyabc=[2,6]

    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss013.do")
  public void insert004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("===");
    //us = os+p1,ue=oe+p1
    //=== + ==*****
//    stringBuffer.insert(0,("012"+inputName).toCharArray());//[3,7]->012xyabc===[3,7]
//    stringBuffer.insert(2,("012"+inputName).toCharArray());//[3,7]->==012xyabc=[5,9]

    CharSequence charSequence = "012"+inputName;
//    stringBuffer.insert(0,charSequence);//[3,7]->012xyabc===[3,7]
    stringBuffer.insert(2,charSequence);//[3,7]->==012xyabc=[5,9]

    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss014.do")
  public void insert005(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//x
    StringBuffer stringBuffer = new StringBuffer("===");
    char c = inputName.toCharArray()[0];
    stringBuffer.insert(0,c);//[0,0]->x===[0,0]
//    stringBuffer.insert(2,inputName.toCharArray()[0]);//[0,0]->==x=[2,2]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss015.do")
  public void insert006(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("test");
    CharSequence charSequence = inputName;
    stringBuffer.insert(1,charSequence,1,3);//[1,2]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss016.do")
  public void insert007(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("test");
    CharSequence charSequence = "012"+inputName;
    //不包含end所在字母
//    stringBuffer.insert(2,charSequence,2,6);//[3,5]

    //不包含end所在字母
//    stringBuffer.insert(2,charSequence,3,6);//[2,4]

    //不包含end所在字母
//    stringBuffer.insert(4,charSequence,4,charSequence.length());//[4,7]

    //不包含end所在字母
//    stringBuffer.insert(1,charSequence,3,charSequence.length());//[1,5]
    stringBuffer.insert(0,charSequence,0,3);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss017.do")
  public void insert008(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer sb = new StringBuffer("test");
    CharSequence charSequence = inputName+"012";
    //不包含end所在字母
//    sb.insert(1,charSequence,1,3);//[1,2]

    //不包含end所在字母
//    sb.insert(1,charSequence,0,4);//[1,4]

    //不包含end所在字母
//    sb.insert(1,charSequence,2,5);//[1,3]

    //不包含end所在字母
//    sb.insert(1,charSequence,2,4);//[1,2]

    sb.insert(1,charSequence,5,charSequence.length());
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(sb.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss018.do")
  public void insert009(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer sb = new StringBuffer("test");
    CharSequence charSequence = "012"+inputName+"456";
    //不包含end所在字母
//    sb.insert(2,charSequence,1,5);//[4,5]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,5);//[2,3]

    //不包含end所在字母
//    sb.insert(2,charSequence,0,7);//[5,8]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,7);//[2,5]

    //不包含end所在字母
//    sb.insert(4,charSequence,2,8);//[5,9]

    //不包含end所在字母
//    sb.insert(2,charSequence,5,9);//[2,4]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,9);//[2,6]
//    sb.insert(2,charSequence,0,3);
    sb.insert(2,charSequence,8,11);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(sb.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss019.do")
  public void insert010(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("test");
    char[]  charSequence = inputName.toCharArray();
    stringBuffer.insert(1,charSequence,1,3);//[1,3]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss020.do")
  public void insert011(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer stringBuffer = new StringBuffer("test");
    char[] charSequence = ("012"+inputName).toCharArray();
    //不包含end所在字母
    stringBuffer.insert(2,charSequence,2,4);//[3,5]

    //不包含end所在字母
//    stringBuffer.insert(2,charSequence,3,2);//[2,3]

    //不包含end所在字母
//    stringBuffer.insert(4,charSequence,4,4);//[4,7]

    //不包含end所在字母
//    stringBuffer.insert(1,charSequence,3,5);//[1,5]
//    stringBuffer.insert(0,charSequence,0,3);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuffer.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss021.do")
  public void insert012(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer sb = new StringBuffer("test");
    char[] charSequence = (inputName+"012").toCharArray();
    //不包含end所在字母
//    sb.insert(1,charSequence,1,2);//[1,2]

    //不包含end所在字母
    sb.insert(1,charSequence,0,4);//[1,4]

    //不包含end所在字母
//    sb.insert(1,charSequence,2,3);//[1,3]

    //不包含end所在字母
//    sb.insert(1,charSequence,2,2);//[1,2]

//    sb.insert(1,charSequence,5,3);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(sb.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss022.do")
  public void insert013(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuffer sb = new StringBuffer("test");
    char[] charSequence = ("012"+inputName+"456").toCharArray();
    //不包含end所在字母
    sb.insert(2,charSequence,1,4);//[4,5]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,2);//[2,3]

    //不包含end所在字母
//    sb.insert(2,charSequence,0,7);//[5,8]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,4);//[2,5]

    //不包含end所在字母
//    sb.insert(4,charSequence,2,6);//[5,9]

    //不包含end所在字母
//    sb.insert(2,charSequence,5,4);//[2,4]

    //不包含end所在字母
//    sb.insert(2,charSequence,3,6);//[2,6]
//    sb.insert(2,charSequence,0,3);
//    sb.insert(2,charSequence,8,3);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(sb.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



}
