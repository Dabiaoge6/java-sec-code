package org.spring.demo.controller.vulnerFix.ssrf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.vulnerfix.ssrf.SSRFUrlCheck;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("ssrfFix")
public class SSRFControllerFix {

    /**
     * 白名单
     * @param request
     */
    @RequestMapping("/openConnectionFix.do")
    public void openConnection(HttpServletRequest request){
        String urlInput = request.getParameter("url");
        boolean isValid = SSRFUrlCheck.securitySSRFUrlCheck(urlInput);
        if(!isValid){
            return;
        }
        try {
            URL url = new URL(urlInput);
            url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
