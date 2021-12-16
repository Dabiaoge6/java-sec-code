package org.spring.demo.controller.vulnerFix.cmdinjection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.vulnerfix.cmdinjection.CmdInjectionFixCommon;
import org.vulhunter.vulnerfix.untrustdeserial.UntrustDeserialFixCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@Controller
@RequestMapping("cmdFix")
public class CmdInjectionFixController {
    private Logger logger = Logger.getLogger(CmdInjectionFixController.class);

    private void end(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("CmdInjection");
        out.close();
    }
    /**
     * 黑名单
     */
    ///http://localhost:8086/spring-demo/cmdInjection/cmdInjection1.do?cmd=calc
    @RequestMapping("/cmdInjection1Fix.do")
    public void cmdInjection1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cmdStr = request.getParameter("cmd");
        boolean isValid = CmdInjectionFixCommon.isValid(cmdStr);
        if(!isValid){
            logger.info("Incorrect input parameters");
            return;
        }
        System.out.println("============tempStr:========="+cmdStr+"================");
        Process pro = Runtime.getRuntime().exec(cmdStr);
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String msg = null;
        while ((msg = bufReader.readLine()) != null) {
            System.out.println(msg+"<----msg");
        }
        end(response);
    }

    // http://localhost:8086/spring-demo/cmdInjection/cmdInjection2.do?cmd=cme.exe&cmd=/c&cmd=dir
    @RequestMapping("/cmdInjection2Fix.do")
    public void cmdInjection2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //禁止jvm执行外部命令
        UntrustDeserialFixCommon.setSecurity();
        String[] cmdStr = request.getParameterValues("cmd");
        System.out.println("============cmd:========="+cmdStr+"================");
        Process pro = Runtime.getRuntime().exec(new String[] {"cmd","/c", cmdStr[2]});
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String msg = null;
        while ((msg = bufReader.readLine()) != null) {
            System.out.println(msg+"<----msg");
        }
        end(response);
    }
}
