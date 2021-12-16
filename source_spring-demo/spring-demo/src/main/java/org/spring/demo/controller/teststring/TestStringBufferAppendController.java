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
@RequestMapping("testStringBufferAppend")
public class TestStringBufferAppendController {

/*
  @RequestMapping("/xss1001.do")
  public void testAppendSubstr1001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    StringBuffer sb = new StringBuffer();
 sb.append("test")
    //初始下标范围[orginFirstIndex,orginLastIndex]=[0,"test".length-1]=[0,3]
    //污点数据下标范围[tainFirstIndex,tainLastIndex]=[tainFirstIndex,tainFirstIndex+tain.length-1]=[orginLastIndex+1,tain.length+tainFirstIndex-1]
    //获取污点数据下标；保存污点数据下标
    .append(inputName);
    String str = stringBuffer.substring(0,3);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss1002.do")
  public void testAppendSubstr1002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    StringBuffer sb = new StringBuffer(inputName);
    StringBuffer sb1 = new StringBuffer("test");
    stringBuffer1.append(sb);
    String str = stringBuffer1.substring(7,9);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss1003.do")
  public void testAppendSubstr1003(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    CharSequence charSequence = inputName;
    StringBuffer sb = new StringBuffer("test");
 sb.append(charSequence);
    String str = stringBuffer.substring(7,10);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss1004.do")
  public void testAppendSubstr1004(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    CharSequence charSequence = inputName;
    StringBuffer sb = new StringBuffer("test");
 sb.append(charSequence,1,3);
    String str = stringBuffer.substring(0,5);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss1005.do")
  public void testAppendSubstr1005(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    char[] chars = new char[inputName.length()];
    inputName.getChars(0,inputName.length(),chars,0);
    StringBuffer sb = new StringBuffer("test");
 sb.append(chars,1,4);
    String str = stringBuffer.substring(0,3);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss1006.do")
  public void testAppendSubstr1006(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    char[] chars = new char[inputName.length()];
    inputName.getChars(3,inputName.length(),chars,0);
    StringBuffer sb = new StringBuffer("test");
 sb.append(chars,1,4);
    String str = stringBuffer.substring(0,4);//获取stringBuffer污点数据下标
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(str, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
*/

    /**
     * append(int, java.lang.String); substring(int, int)
     */
    @RequestMapping("/xss1001.do")
    public void testAppendSubstr1001(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test")//
                //初始下标范围[orginFirstIndex,orginLastIndex]=[0,"test".length-1]=[0,3]
                //污点数据下标范围[tainFirstIndex,tainLastIndex]=[tainFirstIndex,tainFirstIndex+tain.length-1]=[orginLastIndex+1,tain.length+tainFirstIndex-1]
                //获取污点数据下标；保存污点数据下标
                .append(inputName);//testseczone
        String str = stringBuffer.substring(0, 3);//获取stringBuffer污点数据下标//tes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(int, java.lang.StringBuffer); substring(int, int)
     */
    @RequestMapping("/xss1002.do")
    public void testAppendSubstr1002(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);
        stringBuffer.append("test");//seczonetest
        String str = stringBuffer.substring(7, 9);//获取stringBuffer污点数据下标
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(int, java.lang.CharSequence); substring(int, int)
     */
    @RequestMapping("/xss1003.do")
    public void testAppendSubstr1003(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence);
        String str = stringBuffer.substring(7, 10);//获取stringBuffer污点数据下标
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.CharSequence,int,int); substring(int, int)
     */
    @RequestMapping("/xss1004.do")
    public void testAppendSubstr1004(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence, 1, 3);
        String str = stringBuffer.substring(0, 5);//获取stringBuffer污点数据下标
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[], int, int); substring(int, int)
     */
    @RequestMapping("/xss1005.do")
    public void testAppendSubstr1005(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);
        String str = stringBuffer.substring(1, 6);//获取stringBuffer污点数据下标
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[], int, int); substring(int, int)
     */
    @RequestMapping("/xss1006.do")
    public void testAppendSubstr1006(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(chars, 0, 4);
        String str = stringBuffer.substring(1, 3);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.String); delete(int, int)
     */
    @RequestMapping("/xss1007.do")
    public void testAppendDelete1007(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName);
        stringBuffer.delete(0, 2);//czone 删除从第1个字符开始，删除2个字符，指定下标字符串
        String str = stringBuffer.toString();

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.StringBuffer); delete(int, int)
     */

    @RequestMapping("/xss1008.do")
    public void testAppendDelete1008(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);
        stringBuffer.append("test");//seczonetest
        stringBuffer.delete(5, 9);//seczost
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.CharSequence); delete(int, int)
     */

    @RequestMapping("/xss1009.do")
    public void testAppendDelete1009(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence);
        stringBuffer.delete(5, 10);//tests
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.CharSequence, int, int); delete(int, int)
     */
    @RequestMapping("/xss10010.do")
    public void testAppendDelete10010(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence, 1, 3);//testec
        stringBuffer.delete(0, 3);//tec
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[], int, int); delete(int, int)
     */
    @RequestMapping("/xss10011.do")
    public void testAppendDelete10011(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testeczo
        stringBuffer.delete(1, 3);//tteczo
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[], int, int); delete(int, int)
     */
    @RequestMapping("/xss10012.do")
    public void testAppendDelete10012(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);//zone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(chars, 0, 4);
        stringBuffer.delete(1, 3);//ze
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.String); deleteCharAt(int)
     */
    @RequestMapping("/xss10013.do")
    public void testAppendDeleteCharAt10013(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test").append(inputName);
        stringBuffer.deleteCharAt(2);//删除指定下标字符串
        String str = stringBuffer.toString();//tes
        //tes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.StringBuffer); deleteCharAt(int)
     */
    @RequestMapping("/xss10014.do")
    public void testAppendDeleteCharAt10014(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);
        stringBuffer.append("test");
        stringBuffer.deleteCharAt(2);
        String str = stringBuffer.toString();

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.CharSequence); deleteCharAt(int)
     */
    @RequestMapping("/xss10015.do")
    public void testAppendDeleteCharAt10015(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence);
        stringBuffer.deleteCharAt(4);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(java.lang.CharSequence,int,int); deleteCharAt(int)
     */
    @RequestMapping("/xss10016.do")
    public void testAppendDeleteCharAt10016(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence, 1, 3);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[],int,int); deleteCharAt(int)
     */
    @RequestMapping("/xss10017.do")
    public void testAppendDeleteCharAt10017(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append(char[],int,int); deleteCharAt(int)
     */
    @RequestMapping("/xss10018.do")
    public void testAppendDeleteCharAt10018(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(chars, 0, 4);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * append(); getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
     */
    @RequestMapping("/xss10019.do")
    public void testAppendGetChars10019(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testsecz
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * replace(int,int,String);substring(int,int)
     */
    @RequestMapping("/xss10025.do")
    public void testRepalceSubstr10025(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName);
        stringBuffer.replace(0, 3, "test");//testzone
        String str = stringBuffer.substring(0, 3);//tes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * replace(int,int,String); delete(int,int)
     */
    @RequestMapping("/xss10026.do")
    public void testRepalceDelete10026(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);
        sb.replace(0, 3, "test");//testzone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * replace(int,int,String); deleteCharAt(int)
     */
    @RequestMapping("/xss10027.do")
    public void testRepalceDeleteCharAt10027(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence);
        stringBuffer.replace(0, 4, "test");
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * replace(int,int,String); delete(int,int)
     */
    @RequestMapping("/xss10028.do")
    public void testRepalceGetChars10028(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testeczon
        stringBuffer.replace(1, 3, "hi");//thiteczon
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int, java.lang.String); substring(int,int)
     */
    @RequestMapping("/xss10029.do")
    public void testInsertSubstr10029(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(2, inputName);
        String str = stringBuffer.substring(0, 6);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int, char[]); substring(int,int)
     */
    @RequestMapping("/xss10030.do")
    public void testInsertSubstr10030(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, chars);
        String str = stringBuffer.substring(1, 6);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int, char); substring(int,int)
     */
    @RequestMapping("/xss10031.do")
    public void testInsertSubstr10031(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence.charAt(4));
        String str = stringBuffer.substring(1, 3);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence); substring(int,int)
     */
    @RequestMapping("/xss10032.do")
    public void testInsertSubstr10032(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence);
        String str = stringBuffer.substring(0, 5);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence,int,int); substring(int,int)
     */
    @RequestMapping("/xss10033.do")
    public void testInsertSubstr10033(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(0, charSequence, 1, 4);
        String str = stringBuffer.substring(1, 6);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[],int,int); substring(int,int)
     */
    @RequestMapping("/xss10034.do")
    public void testInsertSubstr10034(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, chars, 1, 4);
        String str = stringBuffer.substring(1, 3);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.String); delete(int,int)
     */
    @RequestMapping("/xss10035.do")
    public void testInsertDelete10035(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(2, inputName);
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[]); delete(int,int)
     */
    @RequestMapping("/xss10036.do")
    public void testInsertDelete036(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, chars);
        stringBuffer.delete(1, 6);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char); delete(int,int)
     */
    @RequestMapping("/xss10037.do")
    public void testInsertDelete10037(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        charSequence.charAt(1);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence.charAt(1));
        stringBuffer.delete(1, 6);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence); delete(int,int)
     */
    @RequestMapping("/xss10038.do")
    public void testInsertDelete10038(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence);
        stringBuffer.delete(0, 5);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence,int,int); delete(int,int)
     */
    @RequestMapping("/xss10039.do")
    public void testInsertDelete10039(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(0, charSequence, 1, 4);
        stringBuffer.delete(1, 6);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[],int,int); delete(int,int)
     */
    @RequestMapping("/xss10040.do")
    public void testInsertDelete10040(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, chars, 1, 4);
        stringBuffer.delete(1, 2);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.String); deleteCharAt(int)
     */
    @RequestMapping("/xss10041.do")
    public void testInsertDeleteCharAt10041(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, chars);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[]); deleteCharAt(int)
     */
    @RequestMapping("/xss10042.do")
    public void testInsertDeleteCharAt10042(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, chars);
        stringBuffer.deleteCharAt(6);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char); deleteCharAt(int)
     */
    @RequestMapping("/xss10043.do")
    public void testInsertDeleteCharAt10043(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        charSequence.charAt(1);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence.charAt(1));
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence); deleteCharAt(int)
     */
    @RequestMapping("/xss10044.do")
    public void xtestInsertDeleteCharAt10044(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(1, charSequence);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,java.lang.CharSequence,int,int); deleteCharAt(int)
     */
    @RequestMapping("/xss10045.do")
    public void testInsertDeleteCharAt10045(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.insert(0, charSequence, 1, 4);
        stringBuffer.deleteCharAt(3);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[],int,int); deleteCharAt(int)
     */

    @RequestMapping("/xss10046.do")
    public void testInsertDeleteCharAt10046(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, chars, 1, 4);
        stringBuffer.deleteCharAt(1);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[]); getChars(int,int,char[],int)
     */
    @RequestMapping("/xss10047.do")
    public void testInsertGetChars10047(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, chars);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insert(int,char[],int,int); getChars(int,int,char[],int)
     */
    @RequestMapping("/xss10048.do")
    public void testInsertGetChars10048(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(2, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, chars, 1, 5);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 三组合1
     * append();  replace(); substring()
     */
    //1  append(Object) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10050.do")
    public void testAppendReplaceSubstr10050(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(1, 6, "hello");//thelloconee
        String str = stringBuffer.substring(0, 3);//the
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2  append(String) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10051.do")
    public void testAppendReplaceSubstr10051(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        stringBuffer.replace(3, 6, "hello");//sechelloetest
        String str = stringBuffer.substring(0, 3);//sech
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(sb) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10052.do")
    public void testAppendReplaceSubstr10052(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append(sb);//seczonetest
        stringBuffer.replace(5, 7, "hello");//seczohellowtest
        String str = stringBuffer.substring(0, 7);//seczohe
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4  append(CharSequence) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10053.do")
    public void testAppendReplaceSubstr10053(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence);//testseczone
        stringBuffer.replace(5, 7, "hello");//testhelloseczone
        String str = stringBuffer.substring(9, 13);//seczohe
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //5  append(CharSequence,int,int) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10054.do")
    public void testAppendSubstr10054(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence, 1, 5);//eczo
        stringBuffer.replace(2, 4, "test");//ectest
        String str = stringBuffer.substring(1, 4);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //6  append(char[],int,int) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss10055.do")
    public void testAppendSubstr10055(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testeczo
        stringBuffer.replace(1, 3, "hello");//thelloteczo
        String str = stringBuffer.substring(4, 10);//lotecz
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 三组合2
     * append();  replace(); delete()
     */
    //1  append(Object) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10056.do")
    public void testAppendReplaceSubstr10056(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(1, 6, "hello");//thellocone
        stringBuffer.delete(0, 3);//lloczone
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2  append(String) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10057.do")
    public void testAppendReplaceSubstr10057(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        stringBuffer.replace(3, 6, "hello");//sechellotest
        stringBuffer.delete(0, 3);//hellotest
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(sb) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10058.do")
    public void testAppendReplaceSubstr10058(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append(sb);//seczonetest
        stringBuffer.replace(5, 7, "hello");//seczohellowtest
        stringBuffer.delete(0, 2);//czohellowtest
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4  append(CharSequence) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10059.do")
    public void testAppendReplaceSubstr10059(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append(sb);//seczonetest
        stringBuffer.replace(5, 7, "hello");//seczohellotest
        stringBuffer.delete(0, 7);//llotest
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //5  append(CharSequence,int,int) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10060.do")
    public void testAppendReplaceSubstr10060(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence, 1, 5);//eczo
        stringBuffer.replace(3, 5, "test");//ecztest
        stringBuffer.delete(3, 5);//eczst
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //6  append(char[],int,int) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss10061.do")
    public void testAppendReplaceSubstr10061(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testeczo
        stringBuffer.replace(1, 3, "hello");//thelloteczo
        stringBuffer.delete(2, 7);//thezo
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 三组合3
     * append();  replace(); getChars()
     */
    //1  append(Object) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10062.do")
    public void testAppendReplaceGetChars10062(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(1, 6, "hello");//thellocone
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2  append(String) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10063.do")
    public void testAppendReplaceGetChars10063(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        stringBuffer.replace(3, 6, "hello");//sechellotest
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(sb) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10064.do")
    public void testAppendReplaceGetChars10064(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append(sb);//seczonetest
        stringBuffer.replace(5, 7, "hello");//seczohellowtest
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4  append(CharSequence) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10065.do")
    public void testAppendReplaceGetChars10065(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append(sb);//seczonetest
        stringBuffer.replace(5, 7, "hello");//seczohellowtest
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //5  append(CharSequence,int,int) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10066.do")
    public void testAppendReplaceGetChars10066(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence, 1, 5);//eczo
        stringBuffer.replace(3, 5, "test");//ecztest
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //6  append(char[],int,int) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss10067.do")
    public void testAppendReplaceGetChars10067(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(chars, 1, 4);//testeczo
        stringBuffer.replace(1, 3, "hello");//thelloteczo
        char[] charArr = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), charArr, 0);

        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 三组合4
     * append();  insert(); substring()
     */
    //append(java.lang.Object); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10068.do")
    public void testAppendInsertSubstr10068(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(1, 7);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String);  insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10069.do")
    public void testAppendInsertSubstr10069(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test").append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(8, 15);//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10070.do")
    public void testAppendInsertSubstr10070(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb).append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(8, 14);//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10071.do")
    public void testAppendInsertSubstr10071(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence).append("test");//testseczone
        stringBuffer.insert(2, "hello");//sehelloczonetest
        String str = stringBuffer.substring(8, 15);//zonetes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10072.do")
    public void testAppendInsertSubstr10072(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.insert(2, "hello");//echelloseczonetest
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss10073.do")
    public void testAppendInsertSubstr10073(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.insert(2, "hello");//echellozonetest
        String str = stringBuffer.substring(8, 15);//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10074.do")
    public void testAppendInsertSubstr10074(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        String str = stringBuffer.substring(1, 5);//esec
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10075.do")
    public void testAppendInsertSubstr10075(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 6);//esecz
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10076.do")
    public void testAppendInsertSubstr10076(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10077.do")
    public void testAppendInsertSubstr10077(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10078.do")
    public void testAppendInsertSubstr10078(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 8);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss10079.do")
    public void testAppendInsertSubstr10079(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        String str = stringBuffer.substring(1, 9);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char); substring(int,int)
    @RequestMapping("/xss10080.do")
    public void testAppendInsertSubstr10080(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char); substring(int,int)
    @RequestMapping("/xss10081.do")
    public void testAppendInsertSubstr10081(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char); substring(int,int)
    @RequestMapping("/xss10082.do")
    public void testAppendInsertSubstr10082(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char); substring(int,int)
    @RequestMapping("/xss10083.do")
    public void testAppendInsertSubstr10083(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char); substring(int,int)
    @RequestMapping("/xss10084.do")
    public void testAppendInsertSubstr10084(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 4);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char); substring(int,int)
    @RequestMapping("/xss10085.do")
    public void testAppendInsertSubstr10085(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10086.do")
    public void testAppendInsertSubstr10086(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10087.do")
    public void testAppendInsertSubstr10087(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10088.do")
    public void testAppendInsertSubstr10088(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10089.do")
    public void testAppendInsertSubstr10089(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10090.do")
    public void testAppendInsertSubstr10090(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss10091.do")
    public void testAppendInsertSubstr10091(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10092.do")
    public void testAppendInsertSubstr10092(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 13);//worle
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10093.do")
    public void testAppendInsertSubstr10093(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10094.do")
    public void testAppendInsertSubstr10094(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10095.do")
    public void testAppendInsertSubstr10095(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10096.do")
    public void testAppendInsertSubstr10096(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss10097.do")
    public void testAppendInsertSubstr10097(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss10098.do")
    public void testAppendInsertSubstr10098(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        String s = "hello world";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss10099.do")
    public void testAppendInsertSubstr10099(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100100.do")
    public void testAppendInsertSubstr100100(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100101.do")
    public void testAppendInsertSubstr100101(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100102.do")
    public void testAppendInsertSubstr100102(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100103.do")
    public void testAppendInsertSubstr100103(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 三组合5
     * append();  insert(); delete()
     */
    // 6 x 6 =36 种
    //append(java.lang.Object); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100104.do")
    public void testAppendInsertDelete100104(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String);  insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100105.do")
    public void testAppendInsertDelete100105(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test").append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100106.do")
    public void testAppendInsertDelete100106(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb).append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100107.do")
    public void testAppendInsertDelete100107(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence).append("test");//testseczone
        stringBuffer.insert(2, "hello");//sehelloczonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//zonetes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100108.do")
    public void testAppendInsertDelete100108(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.insert(2, "hello");//echelloseczonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100109.do")
    public void testAppendInsertDelete100109(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.insert(2, "hello");//echellozonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100110.do")
    public void testAppendInsertDelete100110(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100111.do")
    public void testAppendInsertDelete100111(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100112.do")
    public void testAppendInsertDelete100112(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100113.do")
    public void testAppendInsertDelete100113(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100114.do")
    public void testAppendInsertDelete100114(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100115.do")
    public void testAppendInsertDelete100115(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char); delete(int,int)
    @RequestMapping("/xss100116.do")
    public void testAppendInsertDelete100116(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100117.do")
    public void testAppendInsertDelete100117(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char); delete(int,int)
    @RequestMapping("/xss100118.do")
    public void testAppendInsertDelete100118(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char); delete(int,int)
    @RequestMapping("/xss100119.do")
    public void testAppendInsertDelete100119(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char); delete(int,int)
    @RequestMapping("/xss100120.do")
    public void testAppendInsertDelete100120(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char); delete(int,int)
    @RequestMapping("/xss100121.do")
    public void testAppendInsertDelete100121(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100122.do")
    public void testAppendInsertDelete100122(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100123.do")
    public void testAppendInsertDelete100123(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100124.do")
    public void testAppendInsertDelete100124(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100125.do")
    public void testAppendInsertDelete100125(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100126.do")
    public void testAppendInsertDelete100126(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100127.do")
    public void testAppendInsertDelete100127(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100128.do")
    public void testAppendInsertDelete100128(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//worle
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100129.do")
    public void testAppendInsertDelete100129(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100130.do")
    public void testAppendInsertDelete100130(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100131.do")
    public void testAppendInsertDelete100131(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100132.do")
    public void testAppendInsertDelete100132(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100133.do")
    public void testAppendInsertDelete100133(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100134.do")
    public void testAppendInsertDelete100134(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);

        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100135.do")
    public void testAppendInsertDelete100135(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100136.do")
    public void testAppendInsertDelete100136(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100137.do")
    public void testAppendInsertDelete100137(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100138.do")
    public void testAppendInsertDelete100138(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100139.do")
    public void testAppendInsertDelete100139(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 三组合6
     * append();  insert(); getChars()
     */

    //append(java.lang.Object);  insert(int,java.lang.String); getChars(int,int)
    @RequestMapping("/xss100140.do")
    public void testAppendInsertGetChars100140(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(1, 7);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String);  insert(int,java.lang.String); getChars(int,int,char[],int) @RequestMapping("/xss10069.do")
    @RequestMapping("/xss100141.do")
    public void testAppendInsertGetChars100141(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName).append("test");//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(1, 7);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.String); getChars(int,int,char[],int)
    @RequestMapping("/xss100142.do")
    public void testAppendInsertGetChars100142(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer("test");
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(sb).append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(1, 7);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.String); getChars(int,int,char[],int)
    @RequestMapping("/xss100143.do")
    public void testAppendInsertGetChars100143(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence).append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//sehelloczonetest
        String str = stringBuffer.substring(8, 15);//zonetes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.String); getChars(int,int,char[],int)
    @RequestMapping("/xss100144.do")
    public void testAppendInsertGetChars100144(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.insert(2, "hello");//echelloseczonetest
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.String); getChars(int,int,char[],int)
    @RequestMapping("/xss100145.do")
    public void testAppendInsertGetChars100145(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.insert(2, "hello");//echellozonetest
        String str = stringBuffer.substring(8, 15);//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100146.do")
    public void testAppendInsertGetChars100146(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        String str = stringBuffer.substring(1, 7);//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100147.do")
    public void testAppendInsertGetChars100147(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 7);//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100148.do")
    public void testAppendInsertGetChars100148(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100149.do")
    public void testAppendInsertGetChars100149(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100150.do")
    public void testAppendInsertGetChars100150(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[]); getChars(int,int,char[],int)
    @RequestMapping("/xss100151.do")
    public void testAppendInsertGetChars100151(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100152.do")
    public void testAppendInsertGetChars100152(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100153.do")
    public void testAppendInsertGetChars100153(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100154.do")
    public void testAppendInsertGetChars100154(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100155.do")
    public void testAppendInsertGetChars100155(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100156.do")
    public void testAppendInsertGetChars100156(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 4);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char); getChars(int,int,char[],int)
    @RequestMapping("/xss100157.do")
    public void testAppendInsertGetChars100157(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100158.do")
    public void testAppendInsertGetChars100158(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100159.do")
    public void testAppendInsertGetChars100159(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100160.do")
    public void testAppendInsertGetChars100160(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100161.do")
    public void testAppendInsertGetChars100161(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100162.do")
    public void testAppendInsertGetChars100162(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence);  getChars(int,int,char[],int)
    @RequestMapping("/xss100163.do")
    public void testAppendInsertGetChars100163(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100164.do")
    public void testAppendInsertGetChars100164(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 13);//worle
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100165.do")
    public void testAppendInsertGetChars100165(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100166.do")
    public void testAppendInsertGetChars100166(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100167.do")
    public void testAppendInsertGetChars100167(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100168.do")
    public void testAppendInsertGetChars100168(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,java.lang.CharSequence,int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100169.do")
    public void testAppendInsertGetChars100169(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100170.do")
    public void testAppendInsertGetChars100170(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        String s = "hello world";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100171.do")
    public void testAppendInsertGetChars100171(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100172.do")
    public void testAppendInsertGetChars100172(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100173.do")
    public void testAppendInsertGetChars100173(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100174.do")
    public void testAppendInsertGetChars100174(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); insert(int,char[],int,int);  getChars(int,int,char[],int)
    @RequestMapping("/xss100175.do")
    public void testAppendInsertGetChars100175(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 四组合1
     * append(); replace(); insert(); substring()
     */

    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100176.do")
    public void testAppendReplaceInsertSubstr100176(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(1, 7);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100177.do")
    public void testAppendReplaceInsertSubstr100177(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append("test").append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(8, 15);//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100178.do")
    public void testAppendReplaceInsertSubstr100178(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(sb).append(inputName);//testseczone
        stringBuffer.insert(2, "hello");//tehellostseczone
        String str = stringBuffer.substring(8, 15);//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100179.do")
    public void testAppendReplaceInsertSubstr100179(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence).append("test");//testseczone
        stringBuffer.insert(2, "hello");//sehelloczonetest
        String str = stringBuffer.substring(8, 15);//zonetes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100180.do")
    public void testAppendReplaceInsertSubstr100180(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.insert(2, "hello");//echelloseczonetest
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.String); substring(int,int)
    @RequestMapping("/xss100181.do")
    public void testAppendReplaceInsertSubstr100181(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.insert(2, "hello");//echellozonetest
        String str = stringBuffer.substring(8, 15);//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100182.do")
    public void testAppendReplaceInsertSubstr100182(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        String str = stringBuffer.substring(1, 7);//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100183.do")
    public void testAppendReplaceInsertSubstr100183(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 7);//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100184.do")
    public void testAppendReplaceInsertSubstr100184(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        stringBuffer.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100185.do")
    public void testAppendReplaceInsertSubstr100185(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100186.do")
    public void testAppendReplaceInsertSubstr100186(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[]); substring(int,int)
    @RequestMapping("/xss100187.do")
    public void testAppendReplaceInsertSubstr100187(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100188.do")
    public void testAppendReplaceInsertSubstr100188(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(0, 2, "hi");
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100189.do")
    public void testAppendReplaceInsertSubstr100189(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        stringBuffer.replace(0, 2, "hi");
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100190.do")
    public void testAppendReplaceInsertSubstr100190(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100191.do")
    public void testAppendReplaceInsertSubstr100191(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100192.do")
    public void testAppendReplaceInsertSubstr100192(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 4);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char); substring(int,int)
    @RequestMapping("/xss100193.do")
    public void testAppendReplaceInsertSubstr100193(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100194.do")
    public void testAppendReplaceInsertSubstr100194(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100195.do")
    public void testAppendReplaceInsertSubstr100195(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName).append("test");//testseczone

        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100196.do")
    public void testAppendReplaceInsertSubstr100196(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100197.do")
    public void testAppendReplaceInsertSubstr100197(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100198.do")
    public void testAppendReplaceInsertSubstr100198(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        String str = stringBuffer.substring(1, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  substring(int,int)
    @RequestMapping("/xss100199.do")
    public void testAppendReplaceInsertSubstr100199(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100200.do")
    public void testAppendReplaceInsertSubstr100200(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 13);//worle
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100201.do")
    public void testAppendReplaceInsertSubstr100201(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100202.do")
    public void testAppendReplaceInsertSubstr100202(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(9, 15);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100203.do")
    public void testAppendReplaceInsertSubstr100203(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100204.do")
    public void testAppendReplaceInsertSubstr100204(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuffer.substring(7, 11);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  substring(int,int)
    @RequestMapping("/xss100205.do")
    public void testAppendReplaceInsertSubstr100205(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100206.do")
    public void testAppendReplaceInsertSubstr100206(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        String s = "hello world";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100207.do")
    public void testAppendReplaceInsertSubstr100207(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100208.do")
    public void testAppendReplaceInsertSubstr100208(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100209.do")
    public void testAppendReplaceInsertSubstr100209(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100210.do")
    public void testAppendReplaceInsertSubstr100210(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[],int,int);  substring(int,int)
    @RequestMapping("/xss100211.do")
    public void testAppendReplaceInsertSubstr100211(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        stringBuffer.replace(0, 2, "hi");
        String str = stringBuffer.substring(1, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 四组合2
     * append(); replace(); insert(); delete()
     */
    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100212.do")
    public void testAppendReplaceInsertDelete100212(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(0, 2, "hi");
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100213.do")
    public void testAppendReplaceInsertDelete100213(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test").append(inputName);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100214.do")
    public void testAppendReplaceInsertDelete100214(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb).append(inputName);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//tehellostseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//tseczon
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100215.do")
    public void testAppendReplaceInsertDelete100215(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence).append("test");//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//sehelloczonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//zonetes
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100216.do")
    public void testAppendReplaceInsertDelete100216(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//echelloseczonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100217.do")
    public void testAppendReplaceInsertDelete100217(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//echellozonetest
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100218.do")
    public void testAppendReplaceInsertDelete100218(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        stringBuffer.replace(5, 7, "hi");//tesechinestseczone
        stringBuffer.delete(2, 5);//tehinestseczone
        String str = stringBuffer.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100219.do")
    public void testAppendReplaceInsertDelete100219(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//eseczo
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100220.do")
    public void testAppendReplaceInsertDelete100220(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.delete(2, 5);
        stringBuffer.replace(5, 7, "hi");
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100221.do")
    public void testAppendReplaceInsertDelete100221(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100222.do")
    public void testAppendReplaceInsertDelete100222(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100223.do")
    public void testAppendReplaceInsertDelete100223(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100224.do")
    public void testAppendReplaceInsertDelete100224(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100225.do")
    public void testAppendReplaceInsertDelete100225(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100226.do")
    public void testAppendReplaceInsertDelete100226(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100227.do")
    public void testAppendReplaceInsertDelete100227(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        stringBuffer.replace(5, 7, "hi");
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100228.do")
    public void testAppendReplaceInsertDelete100228(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100229.do")
    public void testAppendReplaceInsertDelete100229(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100230.do")
    public void testAppendReplaceInsertDelete100230(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone\
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100231.do")
    public void testAppendReplaceInsertDelete100231(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100232.do")
    public void testAppendReplaceInsertDelete100232(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100233.do")
    public void testAppendReplaceInsertDelete100233(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100234.do")
    public void testAppendReplaceInsertDelete100234(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100235.do")
    public void testAppendReplaceInsertDelete100235(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100236.do")
    public void testAppendReplaceInsertDelete100236(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//worle
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100237.do")
    public void testAppendReplaceInsertDelete100237(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100238.do")
    public void testAppendReplaceInsertDelete100238(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100239.do")
    public void testAppendReplaceInsertDelete100239(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100240.do")
    public void testAppendReplaceInsertDelete100240(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100241.do")
    public void testAppendReplaceInsertDelete100241(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100242.do")
    public void testAppendReplaceInsertDelete100242(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);
        stringBuffer.replace(5, 7, "hi");
        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100243.do")
    public void testAppendReplaceInsertDelete100243(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100244.do")
    public void testAppendReplaceInsertDelete100244(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100245.do")
    public void testAppendReplaceInsertDelete100245(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100246.do")
    public void testAppendReplaceInsertDelete100246(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100247.do")
    public void testAppendReplaceInsertDelete100247(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);
        stringBuffer.replace(5, 7, "hi");
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 五组合1
     * append(); replace(); insert(); delete(); substring()
     */
    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100248.do")
    public void testAppendReplaceInsertDeleteSubstring100248(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        stringBuffer.replace(0, 2, "hi");//histseczone
        stringBuffer.insert(2, "hello");//hihellostseczone
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100249.do")
    public void testAppendReplaceInsertDeleteSubstring100249(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        stringBuffer.replace(0, 2, "hi");//histseczone
        stringBuffer.insert(2, "hello");//hihellostseczone
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//ehello
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100250.do")
    public void testAppendReplaceInsertDeleteSubstring100250(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb).append(inputName);//
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100251.do")
    public void testAppendReplaceInsertDeleteSubstring100251(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence).append("test");//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//sehelloczonetest
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100252.do")
    public void testAppendReplaceInsertDeleteSubstring100252(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 1, 6).append("test");//eczontest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//echelloseczonetest
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);////
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.String); delete(int,int)
    @RequestMapping("/xss100253.do")
    public void testAppendReplaceInsertDeleteSubstring100253(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 6).append("test");//eczonetest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.insert(2, "hello");//echellozonetest
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//;//onetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100254.do")
    public void testAppendReplaceInsertDeleteSubstring100254(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonestseczone
        stringBuffer.replace(5, 7, "hi");//tesechinestseczone
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 12);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100255.do")
    public void testAppendReplaceInsertDeleteSubstring100255(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(3, 9);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100256.do")
    public void testAppendReplaceInsertDeleteSubstring100256(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//teseczonest
        stringBuffer.delete(2, 5);//tezonest
        stringBuffer.replace(5, 7, "hi");//tezonhit
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100257.do")
    public void testAppendReplaceInsertDeleteSubstring100257(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100258.do")
    public void testAppendReplaceInsertDeleteSubstring100258(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(2, chars);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[]); delete(int,int)
    @RequestMapping("/xss100259.do")
    public void testAppendReplaceInsertDeleteSubstring100259(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuffer.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(2, char2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100260.do")
    public void testAppendReplaceInsertDeleteSubstring100260(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100261.do")
    public void testAppendReplaceInsertDeleteSubstring100261(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100262.do")
    public void testAppendReplaceInsertDeleteSubstring100262(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100263.do")
    public void testAppendReplaceInsertDeleteSubstring100263(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.delete(2, 5);
        stringBuffer.replace(5, 7, "hi");
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100264.do")
    public void testAppendReplaceInsertDeleteSubstring100264(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = inputName;
        stringBuffer.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char); delete(int,int)
    @RequestMapping("/xss100265.do")
    public void testAppendReplaceInsertDeleteSubstring100265(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuffer.insert(2, c);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100266.do")
    public void testAppendReplaceInsertDeleteSubstring100266(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone\
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100267.do")
    public void testAppendReplaceInsertDeleteSubstring100267(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100268.do")
    public void testAppendReplaceInsertDeleteSubstring100268(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100269.do")
    public void testAppendReplaceInsertDeleteSubstring100269(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100270.do")
    public void testAppendReplaceInsertDeleteSubstring100270(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuffer.insert(2, charSequence2);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence);  delete(int,int)
    @RequestMapping("/xss100271.do")
    public void testAppendReplaceInsertDeleteSubstring100271(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuffer.insert(2, charSequence);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100272.do")
    public void testAppendReplaceInsertDeleteSubstring100272(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");//
        stringBuffer.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100273.do")
    public void testAppendReplaceInsertDeleteSubstring100273(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100274.do")
    public void testAppendReplaceInsertDeleteSubstring100274(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer sb = new StringBuffer(inputName);
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(sb);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100275.do")
    public void testAppendReplaceInsertDeleteSubstring100275(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100276.do")
    public void testAppendReplaceInsertDeleteSubstring100276(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuffer.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,java.lang.CharSequence,int,int);  delete(int,int)
    @RequestMapping("/xss100277.do")
    public void testAppendReplaceInsertDeleteSubstring100277(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuffer.insert(2, charSequence, 3, 6);//tehellostseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //append(java.lang.Object); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100278.do")
    public void testAppendReplaceInsertDeleteSubstring100278(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuffer.append(inputName);
        stringBuffer.replace(5, 7, "hi");
        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100279.do")
    public void testAppendReplaceInsertDeleteSubstring100279(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");//teczohit
        stringBuffer.delete(1, 3);//tzohit
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.StringBuffer); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100280.do")
    public void testAppendReplaceInsertDeleteSubstring100280(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer("test");
        stringBuffer.append(sb);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);//tzohit
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100281.do")
    public void testAppendReplaceInsertDeleteSubstring100281(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "test";
        stringBuffer.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.CharSequence,int,int); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100282.do")
    public void testAppendReplaceInsertDeleteSubstring100282(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence charSequence = "hello world";
        stringBuffer.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuffer.insert(1, chars, 1, 4);//testseczone
        stringBuffer.replace(5, 7, "hi");
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 5);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(char[],int,int); replace(int,int,String); insert(int,char[],int,int);  delete(int,int)
    @RequestMapping("/xss100283.do")
    public void testAppendReplaceInsertDeleteSubstring100283(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuffer.append(char1, 0, 6);
        stringBuffer.replace(5, 7, "hi");
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        stringBuffer.delete(1, 3);
        String str = stringBuffer.substring(0, 7);//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //append(); replace(); insert(); substring(); delete(); getChars()
    @RequestMapping("/xss1100284.do")
    public void testAppendReplaceInsertDelete100284(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        stringBuffer.append("test");
        stringBuffer.replace(5, 7, "hi");//seczonhitest
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuffer.insert(1, char2, 1, 4);//testseczone
        stringBuffer.delete(2, 5);
        stringBuffer.substring(5, 10);
        String str = stringBuffer.toString();//
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
