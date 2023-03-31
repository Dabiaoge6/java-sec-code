package com.seczone.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;

/**
 * 此页面用于其他注入方法的描写
 */
@Controller
@RequestMapping("/otherInject")
public class OtherInjectController {
    //OS注入
    @RequestMapping("osInject")
    public String OsInject(String command){
        Runtime run = Runtime.getRuntime();
        String mess = command;
        System.out.println(mess+"message======");
        try{

//            String[] cmd={"/usr/bin/java","-version"};
//            Process process = run.exec("cmd.exe /k start  "+ "dir");
            Process process = run.exec(mess);
            //           InputStream in = process.getInputStream();
            InputStream in = process.getInputStream();

            while (in.read() != -1){
                mess = String.valueOf(in.read());
                System.out.println(in.read());
            }

            in.close();

            process.waitFor();

        } catch (Exception e){

            e.printStackTrace();

        }

        return "osinject";
    }


    //LDAP注入

}
