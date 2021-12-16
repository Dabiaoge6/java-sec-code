package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.hardpassword.EncodePasswordTest;
import org.vulhunter.common.hardpassword.HardCodePasswordTest;
import org.vulhunter.common.hardpassword.ShortPasswordTest;

@Controller
@RequestMapping("hardpwd")
public class HardPasswordController {

    @RequestMapping("/hardpassword.do")
    public void hardPassword(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HardCodePasswordTest hardpassword = new HardCodePasswordTest();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("上报漏洞：硬编码密码");
    }
}
