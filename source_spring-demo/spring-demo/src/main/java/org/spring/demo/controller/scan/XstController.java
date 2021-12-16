/*
 *
 * FileName：XstController
 * Description：XstController
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2021/1/6 17:14
 */

package org.spring.demo.controller.scan;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author mapf
 * @description
 * @date 2021/1/6 17:14
 */
@Controller
@RequestMapping("scanIssue")
public class XstController {

    @RequestMapping(value = "/xst/trace.do")
    public void testUnencryptedPasswordForm(@Param("url") String url, HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpTrace trace = new HttpTrace(l);
        HttpResponse response = httpClient.execute(trace);
        resp.setCharacterEncoding(charset);
        PrintWriter out = resp.getWriter();
        try {
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String content = EntityUtils.toString(resEntity, charset);
                    Header[] headers = response.getAllHeaders();
                    int code = response.getStatusLine().getStatusCode();
                    String result = String.format("status:%d,content:%s, headers:%s", code, content, JSON.toJSONString(headers));
                    if(response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotEmpty(result)){
                        out.write("http trace success and result:{"+result+"}");
                    }else if(code == 405){
                        out.write("http trace not allowed and result:{"+result+"}");
                    }else{
                        out.write("http trace fail and result:{"+result+"}");
                    }
                }
            }else {
                out.write("http trace fail and has no trace threat");
            }
        }catch (Exception e){
            out.write("error:" + ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
