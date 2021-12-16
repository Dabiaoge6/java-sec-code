package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.demo.controller.vulnercontroller.otherTest.TestHardKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.hardkey.EncryptionTest;
import org.vulhunter.common.hardkey.HardCodeKeyTest;
import org.vulhunter.common.hardkey.SecretHardKeyTest;

@Controller
@RequestMapping("hardkey")
public class HardKeyController {

    @RequestMapping("/hardkey.do")
    public void hardKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HardCodeKeyTest hardkey = new HardCodeKeyTest();
        hardkey.test();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("上报漏洞：硬编码密钥");
    }
}
