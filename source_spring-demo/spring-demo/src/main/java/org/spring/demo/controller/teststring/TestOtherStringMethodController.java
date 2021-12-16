package org.spring.demo.controller.teststring;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

@Controller
@RequestMapping("testOtherStringMethod")
public class TestOtherStringMethodController {
    /**
     * write(char[],int,int)
     */
    @RequestMapping("/xss2001.do")
    public void testWriter2001(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        char[] chars = new char[inputName.length()];
        inputName.getChars(0, inputName.length(), chars, 0);
        StringWriter sw = new StringWriter();
        sw.write(chars, 1, 3);
        String str = sw.toString();

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
     * write(java.lang.String)
     */
    @RequestMapping("/xss2002.do")
    public void testWriter2002(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringWriter sw = new StringWriter();
        sw.write(inputName);
        String str = sw.toString();

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
     * write(java.lang.String,int,int)
     */
    @RequestMapping("/xss2003.do")
    public void testWriter2003(HttpServletRequest request, HttpServletResponse response) {
        String inputName = request.getParameter("username");
        StringWriter sw = new StringWriter();
        sw.write(inputName, 1, 3);
        String str = sw.toString();

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
