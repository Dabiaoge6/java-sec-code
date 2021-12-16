package org.spring.demo.controller.vulnerFix.sensitiveInfoLog;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("sensitiveInfoLogFix")
public class SensitiveInfoLogFixController {
    private static Logger logger = Logger.getLogger("SensitiveInfoLogFixController");

    /**
     * 不将敏感字段放在log文件中
     *
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "testLogpFix.do")
    public void testLogp() throws NoSuchAlgorithmException {
        String myInfo = "java.util.logging.Logger.logp(java.util.logging.Level,java.lang.String,java.lang.String,java.lang.String)";
        MessageDigest digest = MessageDigest.getInstance("MD5");
        ByteBuffer input = ByteBuffer.wrap(myInfo.getBytes());
        digest.update(input);
        byte[] result = digest.digest();
        String resultStr = new String(result, 1, result.length - 1);
        logger.logp(Level.WARNING, "LoggingController", "testLogp", "test");
    }

    @RequestMapping(value = "LogpFix.do")
    public void LogpFix() throws NoSuchAlgorithmException {
        String myInfo = "java.util.logging.Logger.logp(java.util.logging.Level,java.lang.String,java.lang.String,java.lang.String)";
        MessageDigest digest = MessageDigest.getInstance("MD5");
        ByteBuffer input = ByteBuffer.wrap(myInfo.getBytes());
        digest.update(input);
        byte[] result = digest.digest();
        String resultStr = new String(result, 1, result.length - 1);
        String resultMask = doMask(resultStr);
        logger.logp(Level.WARNING, "LoggingController", "testLogp", resultMask);
    }

    private String doMask(String result) {
        if (StringUtils.isBlank(result)) {
            return "";
        }
        return StringUtils.left(result, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(result, 2), StringUtils.length(result), "*"), "***"));
    }
}
