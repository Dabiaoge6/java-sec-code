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
@RequestMapping("testStringBuilderAppend")
public class TestStringBuilderAppendController {

//两组合

    /**
     * append(int, java.lang.String); substring(int, int)
     */
    @RequestMapping("/xss001.do")
    public void testAppendSubstr001(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test")
                //初始下标范围[orginFirstIndex,orginLastIndex]=[0,"test".length-1]=[0,3]
                //污点数据下标范围[tainFirstIndex,tainLastIndex]=[tainFirstIndex,tainFirstIndex+tain.length-1]=[orginLastIndex+1,tain.length+tainFirstIndex-1]
                //获取污点数据下标；保存污点数据下标
                .append(inputName);//testseczone
        String str = stringBuilder.substring(0, 3);//获取stringBuffer污点数据下标//tes 不上报
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
    @RequestMapping("/xss002.do")
    public void testAppendSubstr002(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);
        stringBuilder.append("test");//seczonetest
        String str = stringBuilder.substring(7, 9);//获取stringBuffer污点数据下标  输出值te  不上报
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
    @RequestMapping("/xss003.do")
    public void testAppendSubstr003(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence);//testseczone
        String str = stringBuilder.substring(7, 10);//获取stringBuffer污点数据下标 输出值 zon 应上报
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
    @RequestMapping("/xss004.do")
    public void testAppendSubstr004(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence, 1, 3);//testec
        String str = stringBuilder.substring(0, 5);//获取stringBuffer污点数据下标teste  输出含有输出值e 应上报
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
    @RequestMapping("/xss005.do")
    public void testAppendSubstr005(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 1, 4);//testeczo
        String str = stringBuilder.substring(1, 6);//获取stringBuffer污点数据下标 estec  输出包含污点值ec 应上报
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
    @RequestMapping("/xss006.do")
    public void testAppendSubstr006(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars, 0, 4);//zone
        String str = stringBuilder.substring(1, 3);//on 应上报
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
    @RequestMapping("/xss007.do")
    public void testAppendDelete007(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName);
        stringBuilder.delete(0, 2);//czone 删除从第1个字符开始，删除2个字符，指定下标字符串   Y
        String str = stringBuilder.toString();
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

    @RequestMapping("/xss008.do")
    public void testAppendDelete008(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);
        stringBuilder.append("test");//seczonetest
        stringBuilder.delete(5, 9);//seczost  输出污点值seczo  Y
        String str = stringBuilder.toString();
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

    @RequestMapping("/xss009.do")
    public void testAppendDelete009(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence);//testseczone
        stringBuilder.delete(5, 10);//testse  污点值 se  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0010.do")
    public void testAppendDelete0010(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence, 1, 3);//testec
        stringBuilder.delete(0, 3);//tec Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0011.do")
    public void testAppendDelete0011(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars, 1, 4).append("test");//eczotest
        stringBuilder.delete(0, 5);//est N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0012.do")
    public void testAppendDelete0012(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);//zone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars, 0, 3).append("test");//zontest
        stringBuilder.delete(0, 5);//est  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0013.do")
    public void testAppendDeleteCharAt0013(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test").append(inputName);
        stringBuilder.deleteCharAt(2);//删除指定下标字符串s
        String str = stringBuilder.toString();//tetseczone Y
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
    @RequestMapping("/xss0014.do")
    public void testAppendDeleteCharAt0014(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);
        stringBuilder.append("test");
        stringBuilder.deleteCharAt(2);//sezonetest  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0015.do")
    public void testAppendDeleteCharAt0015(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence);
        stringBuilder.deleteCharAt(4);//testeczone Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0016.do")
    public void testAppendDeleteCharAt0016(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence, 1, 2);//tests
        stringBuilder.deleteCharAt(4);
        String str = stringBuilder.toString();//test
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
    @RequestMapping("/xss0017.do")
    public void testAppendDeleteCharAt0017(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 3, 1);//testz
        stringBuilder.deleteCharAt(4);//test N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0018.do")
    public void testAppendDeleteCharAt0018(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);//zone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars, 1, 1).append("test");//otest
        stringBuilder.deleteCharAt(0);//test N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0019.do")
    public void testAppendGetChars0019(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 1, 4);//testsecz  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0025.do")
    public void testRepalceSubstr0025(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName);
        stringBuilder.replace(0, 3, "test");//testzone
        String str = stringBuilder.substring(3,6);//tzo Y
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
    @RequestMapping("/xss0026.do")
    public void testRepalceDelete0026(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        stringBuilder.replace(0, 3, "test");//testzone 4,7
        stringBuilder.delete(3, 8);  //tes N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0027.do")
    public void testRepalceDeleteCharAt0027(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        stringBuilder.replace(0, 2, "test");//testczone
        stringBuilder.deleteCharAt(4);//testzone Y
        String str = stringBuilder.toString();
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
     * replace(int,int,String); getChars(int,int)
     */
    @RequestMapping("/xss0028.do")
    public void testRepalceGetChars0028(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(2, inputName.length(), chars, 0);//czone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars, 3, 2);//ne
        stringBuilder.append("hi");//nehi
        stringBuilder.replace(0, 4, "hi");//hi N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0029.do")
    public void testInsertSubstr0029(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(2, inputName);//teseczone
        String str = stringBuilder.substring(0, 6);//tesezon Y
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
    @RequestMapping("/xss0030.do")
    public void testInsertSubstr0030(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, chars);//tseczoneest
        String str = stringBuilder.substring(8, 11);//est  N
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
    @RequestMapping("/xss0031.do")
    public void testInsertSubstr0031(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence.charAt(4));
        String str = stringBuilder.substring(1, 3);//oe  o  Y
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
    @RequestMapping("/xss0032.do")
    public void testInsertSubstr0032(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence);//tseczoneest
        String str = stringBuilder.substring(0, 5);//tsecz
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
    @RequestMapping("/xss0033.do")
    public void testInsertSubstr0033(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(0, charSequence, 1, 4);
        String str = stringBuilder.substring(3, 6);
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
    @RequestMapping("/xss0034.do")
    public void testInsertSubstr0034(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder();//chars   seczone   zone
        stringBuilder.insert(0, chars, 1, 4);
        String str = stringBuilder.substring(1, 3);
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
    @RequestMapping("/xss0035.do")
    public void testInsertDelete0035(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(2, inputName);//teseczonest
        stringBuilder.delete(2, 5);//  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0036.do")
    public void testInsertDelete036(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, chars);
        stringBuilder.delete(0, 8);// est  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0037.do")
    public void testInsertDelete0037(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence.charAt(2));//tecst
        stringBuilder.delete(2, 5);//te  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0038.do")
    public void testInsertDelete0038(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence);//tseczoneest
        stringBuilder.delete(0, 5);//onnest  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0039.do")
    public void testInsertDelete0039(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(0, charSequence, 1, 4);//ecztest
        stringBuilder.delete(0, 4);//est  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0040.do")
    public void testInsertDelete0040(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.insert(0, chars, 1, 4);//eczo
        stringBuilder.delete(1, 2);//ezo  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0041.do")
    public void testInsertDeleteCharAt0041(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.deleteCharAt(3);//tesczonest
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0042.do")
    public void testInsertDeleteCharAt0042(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(5, inputName.length()-1, chars, 0);//seczone  n
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, chars);//tnest
        stringBuilder.deleteCharAt(1);//test
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0043.do")
    public void testInsertDeleteCharAt0043(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence.charAt(1));
        stringBuilder.deleteCharAt(3);//teest  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0044.do")
    public void xtestInsertDeleteCharAt0044(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(1, charSequence);//tseczonest
        stringBuilder.deleteCharAt(3);//teszonest  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0045.do")
    public void testInsertDeleteCharAt0045(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(0, charSequence, 3, 4);//testecz
        stringBuilder.deleteCharAt(0);//test
        String str = stringBuilder.toString();
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

    @RequestMapping("/xss0046.do")
    public void testInsertDeleteCharAt0046(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.insert(0, chars, 1, 4);//one
        stringBuilder.deleteCharAt(1);//oe
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0047.do")
    public void testInsertGetChars0047(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(3, inputName.length(), chars, 0);//zone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.insert(2, chars);//tezon   st   Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0048.do")
    public void testInsertGetChars0048(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(2, inputName.length(), chars, 0);//czone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.insert(0, chars, 1, 3);//zon
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0050.do")
    public void testAppendReplaceSubstr0050(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(1, 6, "hello");//thelloconee
        String str = stringBuilder.substring(0, 3);//the  N
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
    @RequestMapping("/xss0051.do")
    public void testAppendReplaceSubstr0051(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        stringBuilder.replace(3, 6, "hello");//sechelloetest
        String str = stringBuilder.substring(0, 3);//sech  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(StringBuffer) + replace(int,int,String) + substring(int,int)
    @RequestMapping("/xss0052.do")
    public void testAppendReplaceSubstr0052(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append(stringBuffer);//seczonetest
        stringBuilder.replace(5, 7, "hello");//seczohellotest
        String str = stringBuilder.substring(8, 13);//lotes  N
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
    @RequestMapping("/xss0053.do")
    public void testAppendReplaceSubstr0053(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence);//testseczone
        stringBuilder.replace(5, 7, "hello");//testshellozone
        String str = stringBuilder.substring(9, 13);//ozon Y
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
    @RequestMapping("/xss0054.do")
    public void testAppendSubstr0054(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence, 1, 5);//eczo
        stringBuilder.replace(2, 4, "test");//ectest
        String str = stringBuilder.substring(2, 5);//tes  N
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
    @RequestMapping("/xss0055.do")
    public void testAppendSubstr0055(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 1, 4);//testeczo
        stringBuilder.replace(1, 3, "hello");//thelloteczo
        String str = stringBuilder.substring(4, 10);//lotecz  Y
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
    @RequestMapping("/xss0056.do")
    public void testAppendReplaceSubstr0056(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(1, 6, "hello");//thelloczone
        stringBuilder.delete(6, 11);//thello N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0057.do")
    public void testAppendReplaceSubstr0057(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        stringBuilder.replace(3, 7, "hello");//sechellotest
        stringBuilder.delete(0, 3);//hellotest  N
        String str = stringBuilder.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(StringBuffer) + replace(int,int,String) + delete(int,int)
    @RequestMapping("/xss0058.do")
    public void testAppendReplaceSubstr0058(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append(stringBuffer);//seczonetest
        stringBuilder.replace(5, 7, "hello");//seczohellotest
        stringBuilder.delete(0, 2);//czohellotest Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0059.do")
    public void testAppendReplaceSubstr0059(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append(stringBuffer);//seczonetest
        stringBuilder.replace(5, 7, "hello");//seczohellotest
        stringBuilder.delete(0, 7);//llotest N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0060.do")
    public void testAppendReplaceSubstr0060(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence, 1, 5);//eczo
        stringBuilder.replace(3, 5, "test");//ecztest
        stringBuilder.delete(3, 5);//eczst  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0061.do")
    public void testAppendReplaceSubstr0061(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 1, 4);//testeczo
        stringBuilder.replace(1, 3, "hello");//thelloteczo
        stringBuilder.delete(2, 7);//theczo  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0062.do")
    public void testAppendReplaceGetChars0062(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        stringBuilder.replace(1, 6, "hello");//thellocone  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0063.do")
    public void testAppendReplaceGetChars0063(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        stringBuilder.replace(0, 7, "hello");//hellotest  N
        String str = stringBuilder.toString();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3  append(StringBuffer) + replace(int,int,String) + getChars(int,int,char[],int)
    @RequestMapping("/xss0064.do")
    public void testAppendReplaceGetChars0064(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append(stringBuffer);//seczonetest
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        stringBuilder.replace(0, 3, "hello");
        String str = stringBuilder.toString();//hellozonetest  Y
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
    @RequestMapping("/xss0065.do")
    public void testAppendReplaceGetChars0065(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append(stringBuffer);//seczonetest
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        stringBuilder.replace(0, 7, "hello");//hellotest  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0066.do")
    public void testAppendReplaceGetChars0066(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        CharSequence charSequence = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence, 1, 5);//eczo
        stringBuilder.replace(3, 5, "test");//ecztest
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        String str = stringBuilder.toString();//ecztest  Y
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
    @RequestMapping("/xss0067.do")
    public void testAppendReplaceGetChars0067(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(chars, 1, 4);//testeczo
        char[] charArr = new char[stringBuilder.length()];
        stringBuilder.getChars(0, stringBuilder.length(), charArr, 0);
        stringBuilder.replace(3, 8, "hello");//teshello  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss0068.do")
    public void testAppendInsertSubstr0068(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(1, 7);//ehello  N
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
    @RequestMapping("/xss0069.do")
    public void testAppendInsertSubstr0069(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test").append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(8, 15);//tseczon  Y
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
    @RequestMapping("/xss0070.do")
    public void testAppendInsertSubstr0070(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer).append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(0, 7);//tehello N
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
    @RequestMapping("/xss0071.do")
    public void testAppendInsertSubstr0071(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence).append("test");//seczonetest
        stringBuilder.insert(2, "hello");//sehelloczonetest
        String str = stringBuilder.substring(8, 15);//zonetes Y
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
    @RequestMapping("/xss0072.do")
    public void testAppendInsertSubstr0072(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 1, 6).append("test");//eczontest
        stringBuilder.insert(2, "hello");//echelloseczonetest
        String str = stringBuilder.substring(7, 11);//zont Y
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
    @RequestMapping("/xss0073.do")
    public void testAppendInsertSubstr0073(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.insert(2, "hello");//echellozonetest
        String str = stringBuilder.substring(8, 15);//onetest Y
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
    @RequestMapping("/xss0074.do")
    public void testAppendInsertSubstr0074(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonestseczone
        String str = stringBuilder.substring(1, 5);//esec Y
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
    @RequestMapping("/xss0075.do")
    public void testAppendInsertSubstr0075(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(1, 6);//esecz Y
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
    @RequestMapping("/xss0076.do")
    public void testAppendInsertSubstr0076(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(0, 2);//te  N
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
    @RequestMapping("/xss0077.do")
    public void testAppendInsertSubstr0077(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tesczonest
        String str = stringBuilder.substring(1, 7);//eseczo Y
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
    @RequestMapping("/xss0078.do")
    public void testAppendInsertSubstr0078(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//elsecoznel
        String str = stringBuilder.substring(1, 6);//lesecz Y
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
    @RequestMapping("/xss0079.do")
    public void testAppendInsertSubstr0079(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//
        stringBuilder.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//esseczonest
        String str = stringBuilder.substring(1, 6);//ssecz Y
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
    @RequestMapping("/xss0080.do")
    public void testAppendInsertSubstr0080(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, 'A');//teAstseczone
        String str = stringBuilder.substring(1, 5);//eAst N
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
    @RequestMapping("/xss0081.do")
    public void testAppendInsertSubstr0081(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        stringBuilder.insert(2, 'A');
        String str = stringBuilder.substring(1, 7);//eAczon Y
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
    @RequestMapping("/xss0082.do")
    public void testAppendInsertSubstr0082(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");
        stringBuilder.insert(2, 'A');//tehellostseczone
        String str = stringBuilder.substring(2, 8);//Aczone Y
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
    @RequestMapping("/xss0083.do")
    public void testAppendInsertSubstr0083(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence);
        stringBuilder.insert(2, 'A');
        String str = stringBuilder.substring(2, 6);//Aczo Y
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
    @RequestMapping("/xss0084.do")
    public void testAppendInsertSubstr0084(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);
        stringBuilder.insert(2, 'A');//czAon
        String str = stringBuilder.substring(1, 4);//zAo  Y
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
    @RequestMapping("/xss0085.do")
    public void testAppendInsertSubstr0085(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);//seczone
        stringBuilder.append(chars, 1, 4);//testeczo
        char c = 'A';
        stringBuilder.insert(2, c);//teAsteczo
        String str = stringBuilder.substring(1, 5);//eAst N
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
    @RequestMapping("/xss0086.do")
    public void testAppendInsertSubstr0086(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName);
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);
        String str = stringBuilder.substring(2, 6);//test N
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
    @RequestMapping("/xss0087.do")
    public void testAppendInsertSubstr0087(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello";
        stringBuilder.insert(2, charSequence);
        String str = stringBuilder.substring(1, 7);// ello N
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
    @RequestMapping("/xss0088.do")
    public void testAppendInsertSubstr0088(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);
        String str = stringBuilder.substring(1, 6);//etest  Y
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
    @RequestMapping("/xss0089.do")
    public void testAppendInsertSubstr0089(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1);//seczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(1, charSequence2);//stesteczone
        String str = stringBuilder.substring(1, 4);//tes  N
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
    @RequestMapping("/xss0090.do")
    public void testAppendInsertSubstr0090(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1, 2, 7);//czone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//cztestone
        String str = stringBuilder.substring(1, 5);//ztes Y
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
    @RequestMapping("/xss0091.do")
    public void testAppendInsertSubstr0091(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//eczo
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//ectestzo
        String str = stringBuilder.substring(1, 5);//ctes Y
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
    @RequestMapping("/xss0092.do")
    public void testAppendInsertSubstr0092(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "vulhunter";
        stringBuilder.insert(1, charSequence, 2, 6);//tlhunestseczone
        String str = stringBuilder.substring(1, 6);//lhune N
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
    @RequestMapping("/xss0093.do")
    public void testAppendInsertSubstr0093(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");
        CharSequence charSequence = "vulhunter";
        stringBuilder.insert(1, charSequence, 2, 7);//slhunteczonetest
        String str = stringBuilder.substring(9, 15);//onetes Y
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
    @RequestMapping("/xss0094.do")
    public void testAppendInsertSubstr0094(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(stringBuffer);
        CharSequence charSequence = "vulhunter";
        stringBuilder.insert(1, charSequence, 5, 9);//tnterestseczone
        String str = stringBuilder.substring(1, 7);//nteres N
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
    @RequestMapping("/xss0095.do")
    public void testAppendInsertSubstr0095(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1);
        CharSequence charSequence2 = "vulhunter";
        stringBuilder.insert(1, charSequence2, 5, 9);//tnterestseczone
        String str = stringBuilder.substring(3, 8);//erest  N
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
    @RequestMapping("/xss0096.do")
    public void testAppendInsertSubstr0096(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1, 2, 5);
        CharSequence charSequence2 = "vulhunter";
        stringBuilder.insert(1, charSequence2, 5, 9);//tnterestczo
        String str = stringBuilder.substring(7, 11);//tczo  Y
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
    @RequestMapping("/xss0097.do")
    public void testAppendInsertSubstr0097(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//eczo
        CharSequence charSequence = "vulhunter";
        stringBuilder.insert(2, charSequence, 3, 6);//echunzo
        String str = stringBuilder.substring(1, 5);//chun N
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
    @RequestMapping("/xss0098.do")
    public void testAppendInsertSubstr0098(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        String s = "vulhunter";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczoest
        String str = stringBuilder.substring(1, 7);//eczoes Y
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
    @RequestMapping("/xss0099.do")
    public void testAppendInsertSubstr0099(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczoest
        String str = stringBuilder.substring(1, 7);//eczoes Y
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
    @RequestMapping("/xss00100.do")
    public void testAppendInsertSubstr00100(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczoest
        String str = stringBuilder.substring(5, 7);//es  N
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
    @RequestMapping("/xss00101.do")
    public void testAppendInsertSubstr00101(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczoest
        String str = stringBuilder.substring(1, 5);//eczo Y
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
    @RequestMapping("/xss00102.do")
    public void testAppendInsertSubstr00102(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//leczolo
        String str = stringBuilder.substring(5, 7);//lo  N
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
    @RequestMapping("/xss00103.do")
    public void testAppendInsertSubstr00103(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//seczoeczon
        String str = stringBuilder.substring(1, 7);//eczoec  Y
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
    @RequestMapping("/xss00104.do")
    public void testAppendInsertDelete00104(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(9, 16);//tehellost N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00105.do")
    public void testAppendInsertDelete00105(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test").append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(2, 5);//telostseczone  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00106.do")
    public void testAppendInsertDelete00106(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer).append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(2, 10);//teeczone Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00107.do")
    public void testAppendInsertDelete00107(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence).append("test");//seczonetest
        stringBuilder.insert(2, "hello");//sehelloczonetest
        stringBuilder.delete(2, 5);//seloczonetest  Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00108.do")
    public void testAppendInsertDelete00108(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 1, 4).append("test");//eczontest
        stringBuilder.insert(0, "hello");
        stringBuilder.delete(2, 5);//heecztest Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00109.do")
    public void testAppendInsertDelete00109(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);//seczone
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.insert(2, "hello");//echellozonetest
        stringBuilder.delete(0, 11);//test N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00110.do")
    public void testAppendInsertDelete00110(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.delete(2, 5);//tezonest Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00111.do")
    public void testAppendInsertDelete00111(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.delete(2, 10);
        String str = stringBuilder.toString();//tet N
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
    @RequestMapping("/xss00112.do")
    public void testAppendInsertDelete00112(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.delete(2, 11);
        String str = stringBuilder.toString();//te  N
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
    @RequestMapping("/xss00113.do")
    public void testAppendInsertDelete00113(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();// Y
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
    @RequestMapping("/xss00114.do")
    public void testAppendInsertDelete00114(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//elzonel  Y
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
    @RequestMapping("/xss00115.do")
    public void testAppendInsertDelete00115(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//
        stringBuilder.append(char1, 1, 3);//est
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//eseczonet
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//eszonet Y
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
    @RequestMapping("/xss00116.do")
    public void testAppendInsertDelete00116(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, 'A');//tehellostseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//teseczone  Y
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
    @RequestMapping("/xss00117.do")
    public void testAppendInsertDelete00117(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        stringBuilder.insert(2, 'A');//seAczonetest
        stringBuilder.delete(0, 9);
        String str = stringBuilder.toString();//est  N
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
    @RequestMapping("/xss00118.do")
    public void testAppendInsertDelete00118(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        stringBuilder.insert(0, 'A');//Aseczonetest
        stringBuilder.delete(1, 9);//
        String str = stringBuilder.toString();//Aest  N
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
    @RequestMapping("/xss00119.do")
    public void testAppendInsertDelete00119(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence);//testseczone
        stringBuilder.insert(2, 'A');//tehellostseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//seone Y
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
    @RequestMapping("/xss00120.do")
    public void testAppendInsertDelete00120(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);//testseczone
        stringBuilder.insert(2, 'A');//czAon
        stringBuilder.delete(2, 5);//cz Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00121.do")
    public void testAppendInsertDelete00121(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        stringBuilder.insert(2, 'A');//teAsteczo
        stringBuilder.delete(2, 5);//  Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00122.do")
    public void testAppendInsertDelete00122(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello";
        stringBuilder.insert(2, charSequence);//hetestseczonello
        stringBuilder.delete(2, 5);//hetseczonello  Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00123.do")
    public void testAppendInsertDelete00123(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName);//seczonetest
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);
        stringBuilder.delete(2, 8);//seone  Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00124.do")
    public void testAppendInsertDelete00124(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);//seczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//setestzone
        stringBuilder.delete(2, 5);//setczone Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00125.do")
    public void testAppendInsertDelete00125(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1);//seczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(0, charSequence2);//testseczone [4,10]
        stringBuilder.delete(3, 11);//tes N
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00126.do")
    public void testAppendInsertDelete00126(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1, 2, 7);
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//cztestone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//cztone Y
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
    @RequestMapping("/xss00127.do")
    public void testAppendInsertDelete00127(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//eczo
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//ectestzo
        stringBuilder.delete(2, 7);
        String str = stringBuilder.toString();//ec Y
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
    @RequestMapping("/xss00128.do")
    public void testAppendInsertDelete00128(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.delete(9, 16);//t worlest  N
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00129.do")
    public void testAppendInsertDelete00129(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//s leczonetest  Y
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
    @RequestMapping("/xss00130.do")
    public void testAppendInsertDelete00130(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//l lestsecozne Y
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
    @RequestMapping("/xss00131.do")
    public void testAppendInsertDelete00131(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuilder.delete(9, 16);
        String str = stringBuilder.toString();//t worlest  N
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
    @RequestMapping("/xss00132.do")
    public void testAppendInsertDelete00132(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1, 2, 5);//testczo
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestczo
        stringBuilder.delete(9, 12);
        String str = stringBuilder.toString();//t worlest N
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
    @RequestMapping("/xss00133.do")
    public void testAppendInsertDelete00133(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//eczo
        CharSequence charSequence = "hello world";
        stringBuilder.insert(2, charSequence, 3, 6);//eclo zo
        stringBuilder.delete(1, 4);
        String str = stringBuilder.toString();//e zo  Y
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
    @RequestMapping("/xss00134.do")
    public void testAppendInsertDelete00134(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);
        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teelloestseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//teestseczone Y
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
    @RequestMapping("/xss00135.do")
    public void testAppendInsertDelete00135(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczest
        stringBuilder.delete(1, 5);
        String str = stringBuilder.toString();//test  N
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
    @RequestMapping("/xss00136.do")
    public void testAppendInsertDelete00136(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//teest  Y
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
    @RequestMapping("/xss00137.do")
    public void testAppendInsertDelete00137(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//teczoest
        stringBuilder.delete(3, 5);//  N
        String str = stringBuilder.toString();//tecest
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
    @RequestMapping("/xss00138.do")
    public void testAppendInsertDelete00138(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);
        stringBuilder.delete(2, 5);//leczolo Y
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00139.do")
    public void testAppendInsertDelete00139(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        stringBuilder.delete(2, 5);//seecon  Y
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00140.do")
    public void testAppendInsertGetChars00140(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(1, 7);//ehello  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //append(java.lang.String);  insert(int,java.lang.String); getChars(int,int,char[],int) @RequestMapping("/xss0069.do")
    @RequestMapping("/xss00141.do")
    public void testAppendInsertGetChars00141(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName).append("test");//testseczone
        stringBuilder.insert(2, "hello");//sehelloczonetest
        String str = stringBuilder.substring(7, 14);//czonete Y
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
    @RequestMapping("/xss00142.do")
    public void testAppendInsertGetChars00142(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer("test");
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(stringBuffer).append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(1, 7);//ehello  N
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
    @RequestMapping("/xss00143.do")
    public void testAppendInsertGetChars00143(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence).append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(8, 15);//tseczo Y
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
    @RequestMapping("/xss00144.do")
    public void testAppendInsertGetChars00144(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 1, 6).append("test");//eczontest
        stringBuilder.insert(2, "hello");//echellozontest
        String str = stringBuilder.substring(0, 6);//echell Y
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
    @RequestMapping("/xss00145.do")
    public void testAppendInsertGetChars00145(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.insert(2, "hello");//echellozonetest
        String str = stringBuilder.substring(8, 15);//onetest Y
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
    @RequestMapping("/xss00146.do")
    public void testAppendInsertGetChars00146(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonestseczone
        String str = stringBuilder.substring(1, 7);//eseczo  Y
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
    @RequestMapping("/xss00147.do")
    public void testAppendInsertGetChars00147(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(1, 5);//esec
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
    @RequestMapping("/xss00148.do")
    public void testAppendInsertGetChars00148(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(0, 5);//tesec Y
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
    @RequestMapping("/xss00149.do")
    public void testAppendInsertGetChars00149(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(4, 9);// czone  Y
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
    @RequestMapping("/xss00150.do")
    public void testAppendInsertGetChars00150(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//ell
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//elseczonel
        String str = stringBuilder.substring(1, 7);//lseczo  Y
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
    @RequestMapping("/xss00151.do")
    public void testAppendInsertGetChars00151(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);

        stringBuilder.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00152.do")
    public void testAppendInsertGetChars00152(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00153.do")
    public void testAppendInsertGetChars00153(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00154.do")
    public void testAppendInsertGetChars00154(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00155.do")
    public void testAppendInsertGetChars00155(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00156.do")
    public void testAppendInsertGetChars00156(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 4);//
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
    @RequestMapping("/xss00157.do")
    public void testAppendInsertGetChars00157(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00158.do")
    public void testAppendInsertGetChars00158(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00159.do")
    public void testAppendInsertGetChars00159(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00160.do")
    public void testAppendInsertGetChars00160(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00161.do")
    public void testAppendInsertGetChars00161(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00162.do")
    public void testAppendInsertGetChars00162(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        String str = stringBuilder.substring(1, 5);//
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
    @RequestMapping("/xss00163.do")
    public void testAppendInsertGetChars00163(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00164.do")
    public void testAppendInsertGetChars00164(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(3, 13);//worle
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
    @RequestMapping("/xss00165.do")
    public void testAppendInsertGetChars00165(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(9, 15);//
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
    @RequestMapping("/xss00166.do")
    public void testAppendInsertGetChars00166(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(9, 15);//
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
    @RequestMapping("/xss00167.do")
    public void testAppendInsertGetChars00167(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(3, 12);//
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
    @RequestMapping("/xss00168.do")
    public void testAppendInsertGetChars00168(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(7, 11);//
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
    @RequestMapping("/xss00169.do")
    public void testAppendInsertGetChars00169(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(2, charSequence, 3, 6);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00170.do")
    public void testAppendInsertGetChars00170(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");

        String s = "hello world";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00171.do")
    public void testAppendInsertGetChars00171(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00172.do")
    public void testAppendInsertGetChars00172(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00173.do")
    public void testAppendInsertGetChars00173(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00174.do")
    public void testAppendInsertGetChars00174(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00175.do")
    public void testAppendInsertGetChars00175(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00176.do")
    public void testAppendReplaceInsertSubstr00176(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(1, 7);//ehello
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
    @RequestMapping("/xss00177.do")
    public void testAppendReplaceInsertSubstr00177(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append("test").append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(8, 15);//tseczon
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
    @RequestMapping("/xss00178.do")
    public void testAppendReplaceInsertSubstr00178(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(stringBuffer).append(inputName);//testseczone
        stringBuilder.insert(2, "hello");//tehellostseczone
        String str = stringBuilder.substring(8, 15);//tseczon
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
    @RequestMapping("/xss00179.do")
    public void testAppendReplaceInsertSubstr00179(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence).append("test");//testseczone
        stringBuilder.insert(2, "hello");//sehelloczonetest
        String str = stringBuilder.substring(8, 15);//zonetes
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
    @RequestMapping("/xss00180.do")
    public void testAppendReplaceInsertSubstr00180(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence, 1, 6).append("test");//eczontest
        stringBuilder.insert(2, "hello");//echelloseczonetest
        String str = stringBuilder.substring(7, 11);//
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
    @RequestMapping("/xss00181.do")
    public void testAppendReplaceInsertSubstr00181(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.insert(2, "hello");//echellozonetest
        String str = stringBuilder.substring(8, 15);//onetest
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
    @RequestMapping("/xss00182.do")
    public void testAppendReplaceInsertSubstr00182(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonestseczone
        String str = stringBuilder.substring(1, 7);//eseczo
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
    @RequestMapping("/xss00183.do")
    public void testAppendReplaceInsertSubstr00183(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(1, 7);//eseczo
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
    @RequestMapping("/xss00184.do")
    public void testAppendReplaceInsertSubstr00184(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);//testseczone
        stringBuilder.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00185.do")
    public void testAppendReplaceInsertSubstr00185(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00186.do")
    public void testAppendReplaceInsertSubstr00186(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00187.do")
    public void testAppendReplaceInsertSubstr00187(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuilder.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00188.do")
    public void testAppendReplaceInsertSubstr00188(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(0, 2, "hi");
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00189.do")
    public void testAppendReplaceInsertSubstr00189(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        stringBuilder.replace(0, 2, "hi");
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00190.do")
    public void testAppendReplaceInsertSubstr00190(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00191.do")
    public void testAppendReplaceInsertSubstr00191(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00192.do")
    public void testAppendReplaceInsertSubstr00192(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 4);//
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
    @RequestMapping("/xss00193.do")
    public void testAppendReplaceInsertSubstr00193(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00194.do")
    public void testAppendReplaceInsertSubstr00194(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00195.do")
    public void testAppendReplaceInsertSubstr00195(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName).append("test");//testseczone

        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00196.do")
    public void testAppendReplaceInsertSubstr00196(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00197.do")
    public void testAppendReplaceInsertSubstr00197(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00198.do")
    public void testAppendReplaceInsertSubstr00198(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        String str = stringBuilder.substring(1, 5);//
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
    @RequestMapping("/xss00199.do")
    public void testAppendReplaceInsertSubstr00199(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00200.do")
    public void testAppendReplaceInsertSubstr00200(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(3, 13);//worle
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
    @RequestMapping("/xss00201.do")
    public void testAppendReplaceInsertSubstr00201(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(9, 15);//
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
    @RequestMapping("/xss00202.do")
    public void testAppendReplaceInsertSubstr00202(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(9, 15);//
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
    @RequestMapping("/xss00203.do")
    public void testAppendReplaceInsertSubstr00203(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(3, 12);//
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
    @RequestMapping("/xss00204.do")
    public void testAppendReplaceInsertSubstr00204(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        String str = stringBuilder.substring(7, 11);//
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
    @RequestMapping("/xss00205.do")
    public void testAppendReplaceInsertSubstr00205(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(2, charSequence, 3, 6);//tehellostseczone
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00206.do")
    public void testAppendReplaceInsertSubstr00206(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");

        String s = "hello world";
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00207.do")
    public void testAppendReplaceInsertSubstr00207(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00208.do")
    public void testAppendReplaceInsertSubstr00208(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00209.do")
    public void testAppendReplaceInsertSubstr00209(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00210.do")
    public void testAppendReplaceInsertSubstr00210(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);

        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00211.do")
    public void testAppendReplaceInsertSubstr00211(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);

        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        stringBuilder.replace(0, 2, "hi");
        String str = stringBuilder.substring(1, 7);//
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
    @RequestMapping("/xss00212.do")
    public void testAppendReplaceInsertDelete00212(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(0, 2, "hi");
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//ehello
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
    @RequestMapping("/xss00213.do")
    public void testAppendReplaceInsertDelete00213(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test").append(inputName);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//tseczon
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
    @RequestMapping("/xss00214.do")
    public void testAppendReplaceInsertDelete00214(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer).append(inputName);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//tehellostseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//tseczon
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
    @RequestMapping("/xss00215.do")
    public void testAppendReplaceInsertDelete00215(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence).append("test");//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//sehelloczonetest
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//zonetes
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
    @RequestMapping("/xss00216.do")
    public void testAppendReplaceInsertDelete00216(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 1, 6).append("test");//eczontest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//echelloseczonetest
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00217.do")
    public void testAppendReplaceInsertDelete00217(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//echellozonetest
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//onetest
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
    @RequestMapping("/xss00218.do")
    public void testAppendReplaceInsertDelete00218(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonestseczone
        stringBuilder.replace(5, 7, "hi");//tesechinestseczone
        stringBuilder.delete(2, 5);//tehinestseczone
        String str = stringBuilder.toString();
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
    @RequestMapping("/xss00219.do")
    public void testAppendReplaceInsertDelete00219(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//eseczo
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
    @RequestMapping("/xss00220.do")
    public void testAppendReplaceInsertDelete00220(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.delete(2, 5);
        stringBuilder.replace(5, 7, "hi");
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00221.do")
    public void testAppendReplaceInsertDelete00221(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00222.do")
    public void testAppendReplaceInsertDelete00222(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00223.do")
    public void testAppendReplaceInsertDelete00223(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuilder.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00224.do")
    public void testAppendReplaceInsertDelete00224(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00225.do")
    public void testAppendReplaceInsertDelete00225(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00226.do")
    public void testAppendReplaceInsertDelete00226(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00227.do")
    public void testAppendReplaceInsertDelete00227(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.delete(2, 5);
        stringBuilder.replace(5, 7, "hi");
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00228.do")
    public void testAppendReplaceInsertDelete00228(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00229.do")
    public void testAppendReplaceInsertDelete00229(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00230.do")
    public void testAppendReplaceInsertDelete00230(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone\
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00231.do")
    public void testAppendReplaceInsertDelete00231(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00232.do")
    public void testAppendReplaceInsertDelete00232(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00233.do")
    public void testAppendReplaceInsertDelete00233(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00234.do")
    public void testAppendReplaceInsertDelete00234(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00235.do")
    public void testAppendReplaceInsertDelete00235(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00236.do")
    public void testAppendReplaceInsertDelete00236(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//worle
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
    @RequestMapping("/xss00237.do")
    public void testAppendReplaceInsertDelete00237(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00238.do")
    public void testAppendReplaceInsertDelete00238(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00239.do")
    public void testAppendReplaceInsertDelete00239(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00240.do")
    public void testAppendReplaceInsertDelete00240(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00241.do")
    public void testAppendReplaceInsertDelete00241(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(2, charSequence, 3, 6);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00242.do")
    public void testAppendReplaceInsertDelete00242(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);
        stringBuilder.replace(5, 7, "hi");
        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00243.do")
    public void testAppendReplaceInsertDelete00243(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00244.do")
    public void testAppendReplaceInsertDelete00244(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00245.do")
    public void testAppendReplaceInsertDelete00245(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00246.do")
    public void testAppendReplaceInsertDelete00246(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00247.do")
    public void testAppendReplaceInsertDelete00247(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);
        stringBuilder.replace(5, 7, "hi");
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        stringBuilder.delete(2, 5);
        String str = stringBuilder.toString();//
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
    @RequestMapping("/xss00248.do")
    public void testAppendReplaceInsertDeleteSubstring00248(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        stringBuilder.replace(0, 2, "hi");//histseczone
        stringBuilder.insert(2, "hello");//hihellostseczone
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//ehello
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
    @RequestMapping("/xss00249.do")
    public void testAppendReplaceInsertDeleteSubstring00249(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        stringBuilder.replace(0, 2, "hi");//histseczone
        stringBuilder.insert(2, "hello");//hihellostseczone
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//ehello
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
    @RequestMapping("/xss00250.do")
    public void testAppendReplaceInsertDeleteSubstring00250(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer).append(inputName);//
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//
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
    @RequestMapping("/xss00251.do")
    public void testAppendReplaceInsertDeleteSubstring00251(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence).append("test");//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//sehelloczonetest
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//
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
    @RequestMapping("/xss00252.do")
    public void testAppendReplaceInsertDeleteSubstring00252(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 1, 6).append("test");//eczontest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//echelloseczonetest
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);////
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
    @RequestMapping("/xss00253.do")
    public void testAppendReplaceInsertDeleteSubstring00253(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 6).append("test");//eczonetest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.insert(2, "hello");//echellozonetest
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//;//onetest
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
    @RequestMapping("/xss00254.do")
    public void testAppendReplaceInsertDeleteSubstring00254(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonestseczone
        stringBuilder.replace(5, 7, "hi");//tesechinestseczone
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 12);//
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
    @RequestMapping("/xss00255.do")
    public void testAppendReplaceInsertDeleteSubstring00255(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(3, 9);//
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
    @RequestMapping("/xss00256.do")
    public void testAppendReplaceInsertDeleteSubstring00256(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//teseczonest
        stringBuilder.delete(2, 5);//tezonest
        stringBuilder.replace(5, 7, "hi");//tezonhit
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00257.do")
    public void testAppendReplaceInsertDeleteSubstring00257(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00258.do")
    public void testAppendReplaceInsertDeleteSubstring00258(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 1, 4);//testseczone
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(2, chars);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00259.do")
    public void testAppendReplaceInsertDeleteSubstring00259(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        String s = "test";
        char[] char1 = new char[s.length()];
        s.getChars(0, s.length(), char1, 0);//

        stringBuilder.append(char1, 1, 3);//testseczone
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(2, char2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00260.do")
    public void testAppendReplaceInsertDeleteSubstring00260(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00261.do")
    public void testAppendReplaceInsertDeleteSubstring00261(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00262.do")
    public void testAppendReplaceInsertDeleteSubstring00262(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00263.do")
    public void testAppendReplaceInsertDeleteSubstring00263(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.delete(2, 5);
        stringBuilder.replace(5, 7, "hi");
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00264.do")
    public void testAppendReplaceInsertDeleteSubstring00264(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = inputName;
        stringBuilder.append(charSequence, 2, 6);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00265.do")
    public void testAppendReplaceInsertDeleteSubstring00265(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        char c = 'A';
        stringBuilder.insert(2, c);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00266.do")
    public void testAppendReplaceInsertDeleteSubstring00266(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone\
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00267.do")
    public void testAppendReplaceInsertDeleteSubstring00267(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00268.do")
    public void testAppendReplaceInsertDeleteSubstring00268(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00269.do")
    public void testAppendReplaceInsertDeleteSubstring00269(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00270.do")
    public void testAppendReplaceInsertDeleteSubstring00270(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence1, 2, 7);//testseczone
        CharSequence charSequence2 = "test";
        stringBuilder.insert(2, charSequence2);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00271.do")
    public void testAppendReplaceInsertDeleteSubstring00271(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);

        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "test";
        stringBuilder.insert(2, charSequence);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00272.do")
    public void testAppendReplaceInsertDeleteSubstring00272(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");//
        stringBuilder.append(inputName);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00273.do")
    public void testAppendReplaceInsertDeleteSubstring00273(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputName).append("test");//seczonetest
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00274.do")
    public void testAppendReplaceInsertDeleteSubstring00274(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuffer stringBuffer = new StringBuffer(inputName);
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(stringBuffer);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(1, charSequence, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00275.do")
    public void testAppendReplaceInsertDeleteSubstring00275(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00276.do")
    public void testAppendReplaceInsertDeleteSubstring00276(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        CharSequence charSequence1 = inputName;
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(charSequence1, 2, 5);//testseczone
        CharSequence charSequence2 = "hello world";
        stringBuilder.insert(1, charSequence2, 5, 10);//t worlestseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00277.do")
    public void testAppendReplaceInsertDeleteSubstring00277(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.append(chars, 1, 4);//testseczone
        CharSequence charSequence = "hello world";
        stringBuilder.insert(2, charSequence, 3, 6);//tehellostseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00278.do")
    public void testAppendReplaceInsertDeleteSubstring00278(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder("test");
        stringBuilder.append(inputName);
        stringBuilder.replace(5, 7, "hi");
        String s = "hello world";
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00279.do")
    public void testAppendReplaceInsertDeleteSubstring00279(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");//teczohit
        stringBuilder.delete(1, 3);//tzohit
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00280.do")
    public void testAppendReplaceInsertDeleteSubstring00280(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer("test");
        stringBuilder.append(stringBuffer);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);//tzohit
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00281.do")
    public void testAppendReplaceInsertDeleteSubstring00281(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "test";
        stringBuilder.append(charSequence);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00282.do")
    public void testAppendReplaceInsertDeleteSubstring00282(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = "hello world";
        stringBuilder.append(charSequence, 2, 5);
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        stringBuilder.insert(1, chars, 1, 4);//testseczone
        stringBuilder.replace(5, 7, "hi");
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 5);//
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
    @RequestMapping("/xss00283.do")
    public void testAppendReplaceInsertDeleteSubstring00283(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder(inputName);
        char[] char1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char1, 0);
        stringBuilder.append(char1, 0, 6);
        stringBuilder.replace(5, 7, "hi");
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        stringBuilder.delete(1, 3);
        String str = stringBuilder.substring(0, 7);//
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
    @RequestMapping("/xss00284.do")
    public void testAppendReplaceInsertDelete00284(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");//seczone
        StringBuilder stringBuilder = new StringBuilder(inputName);
        stringBuilder.append("test");
        stringBuilder.replace(5, 7, "hi");//seczonhitest
        char[] char2 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), char2, 0);
        stringBuilder.insert(1, char2, 1, 4);//testseczone
        stringBuilder.delete(2, 5);
        stringBuilder.substring(5, 10);
        String str = stringBuilder.toString();//
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
