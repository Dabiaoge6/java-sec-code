package com.seczone.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/SSRF")
public class SSRFController {


    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("ipaddress",'0');
        return "SSRF";
    }



    @RequestMapping(value="/ping",method= RequestMethod.POST)
    public String Ping(String ipaddress ,Model model){
        System.out.println("访问的ip为:"+ipaddress);
        String line = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ipaddress);
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream()));
            long startTime = System.currentTimeMillis();
            while ((line = buf.readLine()) != null){
                long endTime = System.currentTimeMillis();
                System.out.println(line);
                 if ((endTime - startTime)>2000){
                     break;
                    }
            }
            System.out.println(line);
            if (line.indexOf("timeout") == -1){
                model.addAttribute("message","连接成功");

                return "SSRF";
            }else {
                model.addAttribute("message","timeout");
                return "SSRF";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        model.addAttribute("message","访问出错了");

          return "SSRF";
    }
}
