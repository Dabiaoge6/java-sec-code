package org.spring.demo.controller.sensitivedata.http;

import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient4Utils extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient4Utils.class);

    private static RestApiResponseVo getResponseVo(CloseableHttpResponse response) throws IOException {
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        logger.debug("statusCode==>" + response.getStatusLine().getStatusCode());
        String resStr = IOUtils.toString(response.getEntity().getContent(), "utf-8");
        logger.debug("resStr==>" + resStr);
        jiraResponseVo.setStatusCode(response.getStatusLine().getStatusCode());
        jiraResponseVo.setResponseBody(resStr);
        return jiraResponseVo;
    }

    @Override
    public RestApiResponseVo doGet(HashMap<String, Object> paramMap) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        HttpRequestBase httpMethodBase = null;
        if(method.equalsIgnoreCase("GET")){
            httpMethodBase = new HttpGet(requestURL+"?"+request.getQueryString());
        }else if(method.equalsIgnoreCase("DELETE")){
            httpMethodBase = new HttpDelete(requestURL + "?" + request.getQueryString());
        }

        httpMethodBase.setHeader("Content-Type", "application/json");
        httpMethodBase.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            CloseableHttpResponse response = httpclient.execute(httpMethodBase);
            jiraResponseVo = getResponseVo(response);
        }
        catch (Exception e) {
            logger.error("doGet execute fail: " + e.getMessage());
        }
        finally {
            httpMethodBase.releaseConnection();
        }
        return jiraResponseVo;
    }

    @Override
    public RestApiResponseVo doPost(Object paramMap) throws UnsupportedEncodingException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        String contentType = request.getContentType();

        HttpPost postMethod = new HttpPost(requestURL);
        if (paramMap instanceof String) {
            if(request.getContentType().contains("application/xml") || (request.getContentType().contains("text/xml"))){
                postMethod.setEntity(new StringEntity((String) paramMap, "text/xml", "utf-8"));
            }else{
                contentType = "application/json;charset=utf-8";
                StringEntity re = new StringEntity((String) paramMap , "application/json" , "UTF-8");
                postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                postMethod.setEntity(re);
            }
        }
        else {
            HashMap<String, String> params = (HashMap<String, String>) paramMap;
            contentType = "application/x-www-form-urlencoded;charset=utf-8";

            for (Map.Entry<String, String> entry : params.entrySet()) {
                postMethod.getParams().setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        postMethod.setHeader("Content-Type", contentType);
        postMethod.setHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            CloseableHttpResponse response = httpclient.execute(postMethod);
            jiraResponseVo = getResponseVo(response);
        }
        catch (Exception e) {
            logger.error("doPost execute fail: " + e.getMessage());
        }
        finally {
            postMethod.releaseConnection();
        }
        return jiraResponseVo;
    }

    @Override
    public RestApiResponseVo doPut(Object paramMap) {
        return null;
    }
}
