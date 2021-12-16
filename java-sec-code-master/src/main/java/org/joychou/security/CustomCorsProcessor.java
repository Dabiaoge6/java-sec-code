package org.joychou.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.DefaultCorsProcessor;

import java.util.List;

public class CustomCorsProcessor extends DefaultCorsProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CustomCorsProcessor.class);


    /**
     * 跨域请求，会通过此方法检测请求源是否被允许
     *
     * @param config        CORS 配置
     * @param requestOrigin 请求源
     * @return 如果请求源被允许，返回请求源；否则返回 null
     */
    @Override
    protected String checkOrigin(CorsConfiguration config, String requestOrigin) {

        // 支持checkOrigin原装的域名配置
        String result = super.checkOrigin(config, requestOrigin);
        if (result != null) {
            return result;
        }

        List<String> allowedOrigins = config.getAllowedOrigins();
        if (StringUtils.isBlank(requestOrigin)
                || CollectionUtils.isEmpty(allowedOrigins)) {
            return null;
        }

        return customCheckOrigin(allowedOrigins, requestOrigin);
    }


    /**
     * 用host的endsWith来校验requestOrigin
     */
    private String customCheckOrigin(List<String> allowedOrigins, String requestOrigin) {

        // list转String[]
        String[] arrayAllowOrigins = allowedOrigins.toArray(new String[allowedOrigins.size()]);

        if ( SecurityUtil.checkURLbyEndsWith(requestOrigin, arrayAllowOrigins) != null) {
            logger.info("[+] Origin: "  + requestOrigin );
            return requestOrigin;
        }
        logger.error("[-] Origin: " + requestOrigin );
        return null;
    }


}