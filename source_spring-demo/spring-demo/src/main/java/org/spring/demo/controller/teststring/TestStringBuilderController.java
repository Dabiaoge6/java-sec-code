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
@RequestMapping("testStringBuilder")
public class TestStringBuilderController {

  public static void main(String[] args) {
    /**
     * update_start =0,update_stop=sb.length-1
     * orginal_start=p1,orginal_stop>p2
     * orginal_start<p1,orginal_stop>p2
     * orginal_start<p1,orginal_stop=p2
     * orginal_start<p1,orginal_stop<p2,p2=length
     * remvoe
     * p1=0 p2=length,os=p1,os<p2,p2=length
     *
     */
    StringBuilder stringBuilder = new StringBuilder("xyabc");
    //(xxx)xx p1=0 p2=3 ,os =p1,os>p2
    System.out.println(stringBuilder.delete(0,3));//bc
    //x(xx)xx p1=1 p2=3 ,os<p1,os>p2
    stringBuilder = new StringBuilder("xyabc");
    System.out.println(stringBuilder.delete(1,3));//xbc
    //x(xxx)x p1=1 p2=3 ,os<p1,os=p2
    stringBuilder = new StringBuilder("xyabc");
    System.out.println(stringBuilder.delete(1,4));//xbc
    //x(xxxx) p1=1 p2=length ,os<p1,os<p2,p2=length us=0 ue=sb.length-1
    stringBuilder = new StringBuilder("xyabc");
    System.out.println(stringBuilder.delete(1,"xyabc".length()));//x
    //(xxxxx) p1=0 p2=length ,os=p1,os<p2,p2=length
    stringBuilder = new StringBuilder("xyabc");
    System.out.println(stringBuilder.delete(0,"xyabc".length()));
  }

  //(***)** deleete(int,int)
  @RequestMapping("/xss000.do")
  public void xss000(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName);
    stringBuilder.delete(0,3);//c orginstart == param1 && orginstop==param2
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //(****)* deleete(int,int)
  @RequestMapping("/xss001.do")
  public void xss001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName);
    stringBuilder.delete(0,4);//c orginstart == param1 && orginstop==param2
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //**(***) deleete(int,int)
  @RequestMapping("/xss002.do")
  public void xss002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName);
    stringBuilder.delete(2,inputName.length());//xy orginstart < param1 && orginstop < param2
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //*(***)* delete(int,int)
  @RequestMapping("/xss003.do")
  public void xss003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName);
    stringBuilder.delete(1,4);//xc orginstart<param1 && orginstop==param2
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //(*****) deleete(int,int)
  @RequestMapping("/xss004.do")
  public void xss004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName);
    stringBuilder.delete(0,inputName.length());//返回为空，不跟踪
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss005.do")
  public void xss005(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("0123"+inputName);//0123xyabc
    //=(==)=***** delete(int,int) 【4,8】
    //stringBuilder.delete(1,3);//[2,6]
    //=(===)***** delete(int,int) 【4,8】
    stringBuilder.delete(1,4);//[1,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss006.do")
  public void xss006(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("0123"+inputName);//0123xyabc
    //=(===**)*** deleete(int,int)
    //stringBuilder.delete(1,6);//[4,8]->0abc [1,3]
    //=(===****)* deleete(int,int)
    stringBuilder.delete(1,8);//[4,8]->0c [1,1]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //==(==*****) deleete(int,int)
  @RequestMapping("/xss007.do")
  public void xss007(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str1 = "0123"+inputName;//0123xyabc
    StringBuilder stringBuilder = new StringBuilder(str1);//0123xyabc
    stringBuilder.delete(2,str1.length());
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss008.do")
  public void xss008(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("0123"+inputName);//0123xyabc
    //====*(**)** deleete(int,int) orgin_stop>param_2
//    stringBuilder.delete(5,7);//[4,8]->0123xbc[4,6]
    //====*(***)* deleete(int,int) orgin_stop=param_2
//    stringBuilder.delete(5,8);//[4,8]->0123xc[4,5]
    //====*(****) deleete(int,int) orgin_stop < param_2
    stringBuilder.delete(5,("0123"+inputName).length());//[4,8]->0123x[4,4]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss009.do")
  public void xss009(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    //*(**)**==== deleete(int,int) orginal_start<p1 orginal_stop>p2
    //update_start=0,update_stop=orign_stop-cla_length=4-2=2
//    stringBuilder.delete(1,3);//[0,4]->xbc0123 [0,2]

    //*(***)*==== deleete(int,int) orginal_start<p1 orginal_stop=p2
    //update_start=0,update_stop=orign_stop-cla_length=4-3=1
    stringBuilder.delete(1,4);//[0,4]->xc0123 [0,1]

    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //*(****==)== deleete(int,int)
  @RequestMapping("/xss010.do")
  public void xss010(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    //*(****)==== *(****==)== deleete(int,int) orginal_start<p1 orginal_stop<p2
    //update_start=0,update_stop=param_1-1
    stringBuilder.delete(2,7);//[0,4] -> x23[0,0]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss011.do")
  public void xss011(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    //(***)**==== orginal_start=param_1 orginal_stop>param2
    //us=0,ue=orgin_stop-calc_length
//    stringBuilder.delete(0,3);//[0,4]->bc0123[0,1]

    //(****)*==== orginal_start=param_1 orginal_stop=param2
    //us=0,ue=orgin_stop-calc_length
    stringBuilder.delete(0,4);//[0,4]->c0123[0,0]

    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // (*****)==== (*****===)= deleete(int,int)
  @RequestMapping("/xss012.do")
  public void xss012(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    stringBuilder.delete(0,8);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss013.do")
  public void xss013(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    //***(**)==== ***(**===)=  orginal_start<p1 orginal_stop<p2 orginal_stop>p1
    //update_start=0,update_stop=param_1-1
    stringBuilder.delete(3,8);//update_start=0,update_stop=param2-1
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss014.do")
  public void xss014(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder(inputName+"0123");//xyabc0123
    //****(*===)= deleete(int,int)
    //orginal_start<param_1 orginal_stop == param1 orginal_stop <param2 us=0,ue=param_1-1
//    stringBuilder.delete(4,8);//[0,4]-> xyab[0,3]

    //*****(===)= deleete(int,int)
    //orginal_start<param_1 orginal_stop < param1
    stringBuilder.delete(5,8);//[0,4]-> xyabc[0,4]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //(===)*****=== deleete(int,int)
  @RequestMapping("/xss015.do")
  public void xss015(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("012"+inputName+"456");//012xyabc456
    //(===)*****=== orginal_start=param_2
    //us = orginal_start-clac_lenght ue= orginal_stop-calc_length
//    stringBuilder.delete(0,3);//[3,7]->xyabc456 [0,4]

    //(==)=*****=== orginal_start>param_2
    stringBuilder.delete(0,2);//[3,7]->2xyabc456 [1,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //==(=***)**=== deleete(int,int)
  @RequestMapping("/xss016.do")
  public void xss016(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("012"+inputName+"456");//012xyabc456
    //==(=***)**=== orginal_start>param_1 orginal_stop> param2
//    stringBuilder.delete(2,6);//[3,7]->01bc456 [2,3]

    //==(=****)*=== orginal_start>param_1 orginal_stop= param2
    stringBuilder.delete(2,7);//[3,7]->01c456 [2,2]

    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // deleete(int,int)
  @RequestMapping("/xss017.do")
  public void xss017(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("012"+inputName+"456");//012xyabc456
    //==(=*****==)=  ==(=*****)=== orginal_start>param_1 orginal_stop<param2
    stringBuilder.delete(2,10);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //===(***)**=== deleete(int,int)
  @RequestMapping("/xss018.do")
  public void xss018(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    StringBuilder stringBuilder = new StringBuilder("012"+inputName+"456");//012xyabc456
    //===(***)**=== orginal_start =param1 orginal_stop>param2
//    stringBuilder.delete(3,6);//[3,7]->012bc456 [3,4]

    //===**(**)*=== orginal_start < param1 orginal_stop=param2
    stringBuilder.delete(5,7);//[3,7]->012xyc456 [3,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //===**(***=)== deleete(int,int)
  @RequestMapping("/xss019.do")
  public void xss019(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str = "012"+inputName+"456";
    StringBuilder stringBuilder = new StringBuilder(str);//012xyabc456
    //===**(***)=== ===***(**=)== orginal_start < param1 orginal_stop<param2
    stringBuilder.delete(6,9);//[3,7] ->012xya56 [3,5]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //===**(***===) deleete(int,int)
  @RequestMapping("/xss020.do")
  public void xss020(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str = "012"+inputName+"456";
    StringBuilder stringBuilder = new StringBuilder(str);//012xyabc456
    stringBuilder.delete(5,str.length());//[3,7]->012xy[3,4]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //===*****(==)= deleete(int,int)
  @RequestMapping("/xss021.do")
  public void xss021(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");//xyabc
    String str = "012"+inputName+"456";
    StringBuilder stringBuilder = new StringBuilder(str);//012xyabc456
    //===*****(==)= orgin_stop<param1
    stringBuilder.delete(8,10);//[3,7] -> 012xyabc6 [3,7]

    //===****(*==)= orginstart <param1 orign_stop=param1 orginstop <param2
    stringBuilder.delete(7,10);//[3,7]->012xyab [3,6]
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(stringBuilder.toString(), outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
