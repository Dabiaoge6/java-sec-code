package org.spring.demo.controller.teststring;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

@Controller
@RequestMapping("testSubStringAppend")
public class TestSubStringAppendController {

    //<init>(byte[],int,int); substring(int,int)
    @RequestMapping("/xss3001.do")
    public void xss3001(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String str1 = str.substring(1, 4);//ecz  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); concat(java.lang.String)
    @RequestMapping("/xss3002.do")
    public void xss3002(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String str2 = str.concat("test");//seczontest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss3003.do")
    public void xss3003(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String str2 = str.concat("vulhunter");//seczonevulhunter
        String str3 = str2.substring(7, 13);//vulhun   N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); substring(int,int); getChars()
    @RequestMapping("/xss3004.do")
    public void xss3004(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String str1 = str.substring(1, 6);//eczon
        char[] chars = new char[3];
        str1.getChars(1, 4, chars, 0);
        String str3 = new String(chars);//czo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); concat(int,int); getChars()
    @RequestMapping("/xss3005.do")
    public void xss3005(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 1, 6);
        String str2 = str.concat("test");//seczonetest
        char[] chars = new char[3];
        str2.getChars(7, 10, chars, 0);
        String str3 = new String(chars);//est N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss3006.do")
    public void xss3006(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 6);
        String str2 = str.substring(1, 6);//eczon
        String str3 = str2.concat("test");//eczontest
        char[] chars = new char[5];
        str3.getChars(1, 6, chars, 0);
        String s = new String(chars);//czont  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); substring(int,int); getBytes()
    @RequestMapping("/xss3007.do")
    public void xss3007(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        byte[] bytes = new byte[5];
        str.getBytes(0, 5, bytes, 0);
        String str2 = new String(bytes);//seczo
        String s = str2.substring(1, 5);//eczo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); concat(int,int); getBytes()
    @RequestMapping("/xss3008.do")
    public void xss3008(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        byte[] bytes = new byte[5];
        str.getBytes(1, 6, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//eczontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss3009.do")
    public void xss3009(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String s1 = str.substring(1, 6);//eczon
        byte[] bytes = new byte[4];
        s1.getBytes(1, 5, bytes, 0);//czon
        String s2 = new String(bytes);
        String s = s2.concat("test");//czontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int)
    @RequestMapping("/xss30010.do")
    public void xss30010(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str1 = str.substring(1, 4);//ecz  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); concat(java.lang.String)
    @RequestMapping("/xss30011.do")
    public void xss30011(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str2 = str.concat("test");//seczontest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30012.do")
    public void xss30012(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str2 = str.concat("vulhunter");//seczonevulhunter
        String str3 = str2.substring(7, 13);//vulhun   N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int); getChars()
    @RequestMapping("/xss30013.do")
    public void xss30013(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str1 = str.substring(1, 6);//eczon
        char[] chars = new char[3];
        str1.getChars(1, 4, chars, 0);
        String str3 = new String(chars);//czo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); concat(int,int); getChars()
    @RequestMapping("/xss30014.do")
    public void xss30014(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str2 = str.concat("test");//seczonetest
        char[] chars = new char[3];
        str2.getChars(7, 10, chars, 0);
        String str3 = new String(chars);//tes N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30015.do")
    public void xss30015(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        String str2 = str.substring(1, 6);//eczon
        String str3 = str2.concat("test");//eczontest
        char[] chars = new char[5];
        str3.getChars(1, 6, chars, 0);
        String s = new String(chars);//czont  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int); getBytes()
    @RequestMapping("/xss30016.do")
    public void xss30016(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, java.nio.charset.Charset.defaultCharset());
        byte[] bytes = new byte[5];
        str.getBytes(0, 5, bytes, 0);
        String str2 = new String(bytes);//seczo
        String s = str2.substring(1, 5);//eczo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); concat(int,int); getBytes()
    @RequestMapping("/xss30017.do")
    public void xss30017(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        byte[] bytes = new byte[5];
        str.getBytes(1, 6, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//eczontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.nio.charset.Charset); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30018.do")
    public void xss30018(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7);
        String s1 = str.substring(1, 6);//eczon
        byte[] bytes = new byte[4];
        s1.getBytes(1, 5, bytes, 0);//czon
        String s2 = new String(bytes);
        String s = s2.concat("test");//czontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(byte[],int,int,java.lang.String); substring(int,int)
    @RequestMapping("/xss30019.do")
    public void xss30019(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str1 = str.substring(1, 4);//ecz  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); concat(java.lang.String)
    @RequestMapping("/xss30020.do")
    public void xss30020(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str2 = str.concat("test");//seczontest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30021.do")
    public void xss30021(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str2 = str.concat("vulhunter");//seczonevulhunter
        String str3 = str2.substring(7, 13);//vulhun   N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); substring(int,int); getChars()
    @RequestMapping("/xss30022.do")
    public void xss30022(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str1 = str.substring(1, 6);//eczon
        char[] chars = new char[3];
        str1.getChars(1, 4, chars, 0);
        String str3 = new String(chars);//czo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); concat(int,int); getChars()
    @RequestMapping("/xss30023.do")
    public void xss30023(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str2 = str.concat("test");//seczonetest
        char[] chars = new char[3];
        str2.getChars(7, 10, chars, 0);
        String str3 = new String(chars);//tes N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30024.do")
    public void xss30024(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str2 = str.substring(1, 6);//eczon
        String str3 = str2.concat("test");//eczontest
        char[] chars = new char[5];
        str3.getChars(1, 6, chars, 0);
        String s = new String(chars);//czont  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); substring(int,int); getBytes()
    @RequestMapping("/xss30025.do")
    public void xss30025(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[5];
        str.getBytes(0, 5, bytes, 0);
        String str2 = new String(bytes);//seczo
        String s = str2.substring(1, 5);//eczo  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(byte[],int,int,java.lang.String); concat(int,int); getBytes()
    @RequestMapping("/xss30026.do")
    public void xss30026(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[5];
        str.getBytes(1, 6, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//eczontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(byte[],int,int,java.lang.String); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30027.do")
    public void xss30027(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = null;
        try {
            str = new String(inputName.getBytes(StandardCharsets.UTF_8), 0, 7, "Utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s1 = str.substring(1, 5);
        byte[] bytes = new byte[s1.length()];
        str.getBytes(1, 4, bytes, 0);
        String s2 = new String(bytes);
        String s = s2.concat("test");//ecztest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(char[],int,int); substring(int,int)
    @RequestMapping("/xss30028.do")
    public void xss30028(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        String str1 = str.substring(1, 4);//ecz Y

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); concat(java.lang.String)
    @RequestMapping("/xss30029.do")
    public void xss30029(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        String str2 = str.concat("test");//seczonetest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30030.do")
    public void xss30030(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        String str2 = str.concat("vulhunter");
        String s = str2.substring(10, 14);//hunt  N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); substring(int,int); getChars()
    @RequestMapping("/xss30031.do")
    public void xss30031(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars1, 0);
        String str = new String(chars1, 0, 7);
        String str1 = str.substring(3, 6);//zon  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); concat(int,int); getChars()
    @RequestMapping("/xss30032.do")
    public void xss30032(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars1, 0);
        String str = new String(chars1, 0, 7);
        String str1 = str.concat("test");//seczonetest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30033.do")
    public void xss30033(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars1 = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars1, 0);
        String str = new String(chars1, 0, 7);
        String str2 = str.substring(2, 4);//cz
        String str3 = str2.concat("test");//cztest
        char[] chars = new char[4];
        str3.getChars(2, 6, chars, 0);
        String s = new String(chars);//test  N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); substring(int,int); getBytes()
    @RequestMapping("/xss30034.do")
    public void xss30034(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        byte[] bytes = new byte[5];
        str.getBytes(1, 6, bytes, 0);//eczon
        String str2 = new String(bytes);
        String s = str2.substring(1, 5);//czon Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); concat(int,int); getBytes();
    @RequestMapping("/xss30035.do")
    public void xss30035(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        byte[] bytes = new byte[5];
        str.getBytes(2, 7, bytes, 0);//czone
        String str2 = new String(bytes);
        String s = str2.concat("test");//czonetest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(char[],int,int); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30036.do")
    public void xss30036(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        String str = new String(chars, 0, 7);
        String s1 = str.substring(1, 6);//eczon
        byte[] bytes = new byte[4];
        s1.getBytes(1, 5, bytes, 0);//czon
        String s2 = new String(bytes);
        String s = s2.concat("test");//czontest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(java.lang.String); substring(int,int)
    @RequestMapping("/xss30037.do")
    public void xss30037(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String str1 = str.substring(2, 5);//czo Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); concat(java.lang.String)
    @RequestMapping("/xss30038.do")
    public void xss30038(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String str2 = str.concat("test");//seczonetest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30039.do")
    public void xss30039(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String str2 = str.concat("vulhunter");
        String str3 = str2.substring(7, 10); //vul N
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str3, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); substring(int,int); getChars()
    @RequestMapping("/xss30040.do")
    public void xss30040(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String str1 = str.substring(1, 7);//eczone
        char[] chars = new char[str.length()];
        str1.getChars(1, 5, chars, 0);//czon   Y
        String s = new String(chars);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); concat(int,int); getChars()
    @RequestMapping("/xss30041.do")
    public void xss30041(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        char[] chars = new char[5];
        str.getChars(1, 6, chars, 0);
        String s = str.concat("test");//seczonetest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30042.do")
    public void xss30042(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String str2 = str.substring(1, 6);
        char[] chars = new char[4];
        str.getChars(1, 5, chars, 0);
        String s = str2.concat("test");//eczotest
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); substring(int,int); getBytes()
    @RequestMapping("/xss30043.do")
    public void xss30043(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        byte[] bytes = new byte[5];
        str.getBytes(2, 7, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.substring(1, 5);//zone  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); concat(int,int); getBytes()
    @RequestMapping("/xss30044.do")
    public void xss30044(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        byte[] bytes = new byte[5];
        str.getBytes(1, 6, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//eczontest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.String); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30045.do")
    public void xss30045(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        String str = new String(inputName);
        String s1 = str.substring(1, 6);
        byte[] bytes = new byte[4];
        str.getBytes(1, 5, bytes, 0);
        String s2 = new String(bytes);
        String s = s2.concat("test");//eczotest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(java.lang.StringBuffer); substring(int,int)
    @RequestMapping("/xss30046.do")
    public void xss30046(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String s = str.substring(1, 4);//ecz  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); concat(java.lang.String)
    @RequestMapping("/xss30047.do")
    public void xss30047(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String str2 = str.concat("test");//seczonetest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30048.do")
    public void xss30048(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String str2 = str.concat("vulhunter");
        String s = str2.substring(5, 10);//nevul Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); substring(int,int); getChars()
    @RequestMapping("/xss30049.do")
    public void xss30049(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String s = str.substring(1, 5);//eczo
        char[] chars = new char[s.length()];
        s.getChars(1, 4, chars, 0);
        String s1 = new String(chars);//czo Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); concat(int,int); getChars()
    @RequestMapping("/xss30050.do")
    public void xss30050(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String s = str.concat("test");
        char[] chars = new char[str.length()];
        s.getChars(2, 6, chars, 0);
        String s1 = new String(chars);//czon  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s1, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30051.do")
    public void xss30051(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String str2 = str.substring(2, 6);//czon
        char[] chars = new char[str2.length()];
        str.getChars(1, 4, chars, 0);
        String s = str2.concat("test");//zontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); substring(int,int); getBytes()
    @RequestMapping("/xss30052.do")
    public void xss30052(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        byte[] bytes = new byte[str.length()];
        str.getBytes(1, 6, bytes, 0);//eczon
        String str2 = new String(bytes);
        String s = str2.substring(1, 5);//czon  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); concat(int,int); getBytes()
    @RequestMapping("/xss30053.do")
    public void xss30053(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        byte[] bytes = new byte[str.length()];
        str.getBytes(1, 4, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//ecztest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuffer); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30054.do")
    public void xss30054(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuffer stringBuffer = new StringBuffer(inputName);
        String str = new String(stringBuffer);
        String s1 = str.substring(1, 6);//eczon
        byte[] bytes = new byte[s1.length()];
        str.getBytes(1, 5, bytes, 0);//czon
        String s2 = new String(bytes);
        String s = s2.concat("test");//czontest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //<init>(java.lang.StringBuilder); substring(int,int)
    @RequestMapping("/xss30055.do")
    public void xss30055(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String s = str.substring(1, 4);//ecz  Y

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); concat(java.lang.String)
    @RequestMapping("/xss30056.do")
    public void xss30056(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String str2 = str.concat("test");//seczonetest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(str2, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); substring(int,int); concat(java.lang.String)
    @RequestMapping("/xss30057.do")
    public void xss30057(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String str2 = str.concat("vulhunter");
        String s = str2.substring(3, 7);//zone Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); substring(int,int); getChars()
    @RequestMapping("/xss30058.do")
    public void xss30058(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String s1 = str.substring(1, 5);//eczo
        char[] chars = new char[3];
        s1.getChars(1, 4, chars, 0);
        String s = new String(chars);//czo Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); concat(int,int); getChars()
    @RequestMapping("/xss30059.do")
    public void xss30059(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        char[] chars = new char[str.length()];
        str.getChars(1, 6, chars, 0);
        String s = str.concat("test");//seczonetest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); substring(int,int); concat(int,int); getChars()
    @RequestMapping("/xss30060.do")
    public void xss30060(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String str2 = str.substring(2, 6);
        char[] chars = new char[str2.length()];//czon
        str.getChars(1, 4, chars, 0);
        String s = str2.concat("test");//czontest Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); substring(int,int); getBytes()
    @RequestMapping("/xss30061.do")
    public void xss30061(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        byte[] bytes = new byte[str.length()];
        str.getBytes(1, 4, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.substring(1, 3);//cz  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); concat(int,int); getBytes()
    @RequestMapping("/xss30062.do")
    public void xss30062(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        byte[] bytes = new byte[str.length()];
        str.getBytes(1, 4, bytes, 0);
        String str2 = new String(bytes);
        String s = str2.concat("test");//ectest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<init>(java.lang.StringBuilder); substring(int,int); concat(int,int); getBytes()
    @RequestMapping("/xss30063.do")
    public void xss30063(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringBuilder stringBuilder = new StringBuilder(inputName);
        String str = new String(stringBuilder);
        String s1 = str.substring(1, 6);
        byte[] bytes = new byte[s1.length()];
        str.getBytes(1, 4, bytes, 0);
        String s2 = new String(bytes);
        String s = s2.concat("test");//ecztest  Y
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
            stringHttpMessageConverterExtends
                    .getWriteInternal(s, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
