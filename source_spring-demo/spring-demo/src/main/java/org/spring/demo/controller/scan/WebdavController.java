package org.spring.demo.controller.scan;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.annotations.Param;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.client.methods.HttpPropfind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;

/**
 * @author mapf
 * @description
 * @date 2021/1/27 15:01
 */
@Controller
@RequestMapping("scanIssue")
public class WebdavController {
    @RequestMapping(value = "/webdav/propfind.do")
    public void testUnencryptedPasswordForm(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException, URISyntaxException {
        resp.setContentType("text/html;charset=utf-8");
        Writer writer = resp.getWriter();
        if(url.startsWith("https://")){
            writer.write("SSL/TLS安全请求");
            writer.close();
            return;
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPropfind find = new HttpPropfind(url, DavConstants.PROPFIND_ALL_PROP, DavConstants.DEPTH_1);
        HttpResponse response = httpClient.execute(find);
        int statusCode = response.getStatusLine().getStatusCode();
        Header header = response.getFirstHeader("Access-Control-Allow-Methods");
        boolean propfind = false;
        StringBuilder builder = new StringBuilder();
        builder.append("status="+statusCode+"<br/>");
        builder.append(header.getName()+"="+header.getValue()+"<br/>");
        if(header.getValue()!= null && header.getValue() != ""){
            propfind = header.getValue().toUpperCase().contains("PROPFIND");
        }
        builder.append("<br/>Allow-Methods ");
        builder.append((propfind ? "" : "不") + "包含PROPFIND,请求");
        builder.append((propfind ? "" : "不") + "存在安全隐患");

        writer.write(builder.toString());
        writer.close();
    }
}
