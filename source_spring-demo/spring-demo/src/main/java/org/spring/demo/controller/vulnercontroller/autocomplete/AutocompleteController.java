package org.spring.demo.controller.vulnercontroller.autocomplete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.security.Security;


        import java.security.Provider;
        import java.security.Security;

        import javax.crypto.Cipher;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("autocomplete")
public class AutocompleteController {

    @RequestMapping("/autocomplete.do")
    public void autocomplete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("上报漏洞：Autocomplete");
    }
}
