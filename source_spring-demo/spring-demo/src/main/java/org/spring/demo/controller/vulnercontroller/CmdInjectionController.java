package org.spring.demo.controller.vulnercontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;
import org.vulhunter.customrule.CustomRule;

@Controller
@RequestMapping("cmdInjection")
public class CmdInjectionController {
    @RequestMapping("/cmd.do")
    public void cmdInjection2(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String[] cmdStr = request.getParameterValues("cmd");
        System.out.println("============cmd:=========" + cmdStr + "================");
        Process pro = Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmdStr[2]});
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String msg = null;
        while ((msg = bufReader.readLine()) != null) {
            System.out.println(msg + "<----msg");
        }
        PrintWriter out = response.getWriter();
        out.println("上报漏洞：命令行注入");
    }

}
