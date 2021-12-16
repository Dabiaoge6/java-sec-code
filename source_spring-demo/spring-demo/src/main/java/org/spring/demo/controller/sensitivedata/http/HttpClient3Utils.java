package org.spring.demo.controller.sensitivedata.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient3Utils extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient3Utils.class);

    @Override
    public RestApiResponseVo doGet(HashMap<String, Object> paramMap) {
        String resStr = "";
        int statusCode;
        HttpClient httpClient = new HttpClient();
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        String method = request.getMethod();
        HttpMethodBase httpMethodBase = null;
        if(method.equalsIgnoreCase("GET")){
            httpMethodBase = new GetMethod(requestURL+"?"+request.getQueryString());
        }else if(method.equalsIgnoreCase("DELETE")){
            httpMethodBase = new DeleteMethod(requestURL+"?"+request.getQueryString());
        }

        //GetMethod getMethod = new GetMethod(requestURL+"?"+request.getQueryString());
        httpMethodBase.addRequestHeader("Content-Type", "application/json");
        httpMethodBase.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            statusCode = httpClient.executeMethod(httpMethodBase);
            logger.info("statusCode==>" + statusCode);
            resStr = IOUtils.toString(httpMethodBase.getResponseBodyAsStream(), "utf-8");
            logger.info("resStr==>" + resStr);
            jiraResponseVo.setStatusCode(statusCode);
            jiraResponseVo.setResponseBody(resStr);
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
        HttpClient httpClient = new HttpClient();
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        String contentType = request.getContentType();
        PostMethod postMethod = new PostMethod(requestURL);

        if (paramMap instanceof String) {
            if(request.getContentType().contains("application/xml") || (request.getContentType().contains("text/xml"))){
                postMethod.setRequestEntity(new StringRequestEntity((String) paramMap, "text/xml", "utf-8"));
            }else{
                contentType = "application/json;charset=utf-8";
                RequestEntity re = new StringRequestEntity((String) paramMap , "application/json" , "UTF-8");
                postMethod.addParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                postMethod.setRequestEntity(re);
            }
        }
        else {
            HashMap<String, Object> params = (HashMap<String, Object>) paramMap;
            contentType = "application/x-www-form-urlencoded;charset=utf-8";

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                postMethod.addParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        postMethod.addRequestHeader("Content-Type", contentType);
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            logger.debug("statusCode==>" + statusCode);
            String resStr = IOUtils.toString(postMethod.getResponseBodyAsStream(), "utf-8");
            logger.debug("resStr==>" + resStr);
            jiraResponseVo.setStatusCode(statusCode);
            jiraResponseVo.setResponseBody(resStr);
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
