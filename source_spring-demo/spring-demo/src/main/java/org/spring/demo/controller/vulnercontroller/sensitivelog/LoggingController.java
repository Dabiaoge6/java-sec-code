package org.spring.demo.controller.vulnercontroller.sensitivelog;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("sensitivelog")
public class LoggingController {
    private static Logger logger = Logger.getLogger("Logging");

    @RequestMapping(value = "testLogp.do")
    public void testLogp() throws NoSuchAlgorithmException {
        String myInfo = "java.util.logging.Logger.logp(java.util.logging.Level,java.lang.String,java.lang.String,java.lang.String)"; 
        MessageDigest digest = MessageDigest.getInstance("MD5");  
        ByteBuffer input = ByteBuffer.wrap(myInfo.getBytes());
        digest.update(input);  
        byte[] result = digest.digest(); 
        String resultStr = new String(result,1,result.length-1);
        logger.logp(Level.WARNING, "LoggingController", "testLogp", resultStr);
    }
    
    @RequestMapping(value = "sensitiveLog007.do")
    public void testLogp1(@RequestParam String myInfo,HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");  
        byte[] result = myInfo.getBytes();
        digest.update(result);  
        String resultStr = new String(result,1,result.length-1);
        logger.logp(Level.WARNING, "LoggingController", "testLogp", resultStr);
    }
    
}
