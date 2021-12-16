package org.vulhunter.vulnerfix.ssrf;

import com.google.common.net.InternetDomainName;

import java.net.URL;

public class SSRFUrlCheck {
    private static String[] urlwhitelist = {"https://www.baidu.com"};
//    private static Logger logger = Logger.getLogger(SSRFUrlCheck.class);

   public static boolean securitySSRFUrlCheck(String url) {
        try {
            URL u = new URL(url);
            // 只允许http和https的协议通过
            if (!u.getProtocol().startsWith("http") && !u.getProtocol().startsWith("https")) {
                return  false;
            }
            // 获取域名，并转为小写
            String host = u.getHost().toLowerCase();
            // 获取一级域名
            String rootDomain = InternetDomainName.from(host).topPrivateDomain().toString();

            for (String whiteurl: urlwhitelist){
                if (rootDomain.equals(whiteurl)) {
//                    logger.info(u+" is valid");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
