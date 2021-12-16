package org.spring.demo.controller.sensitivedata.http;

import com.squareup.okhttp.*;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpClientUtils extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtils.class);

    @Override
    public RestApiResponseVo doGet(HashMap<String, Object> paramMap) {
        OkHttpClient httpClient = new OkHttpClient();
        requestURL += "?"+request.getQueryString();

        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        //Request getMethod = new Request.Builder().url(requestURL).addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        Request.Builder builder = new Request.Builder().url(requestURL).addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if(method.equalsIgnoreCase("GET")){
            builder.get();
        }else if(method.equalsIgnoreCase("DELETE")){
            builder.delete();
        }
        Request requestMethod = builder.build();

        try {
            Call call = httpClient.newCall(requestMethod);
            Response response = call.execute();
            jiraResponseVo.setStatusCode(response.code());
            jiraResponseVo.setResponseBody(response.body().string());
        }
        catch (Exception e) {
            logger.error("doGet execute fail: " + e.getMessage());
        }
        return jiraResponseVo;
    }

    @Override
    public RestApiResponseVo doPost(Object paramMap) {
        OkHttpClient htpClient = new OkHttpClient();
        RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
        RequestBody body = null;

        String contentType = "";
        if (paramMap instanceof String) {
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (String) paramMap);
            contentType = "application/json;charset=utf-8";
        }
        else {
            HashMap<String, Object> params = (HashMap<String, Object>) paramMap;
            contentType = "application/x-www-form-urlencoded;charset=utf-8";

            FormEncodingBuilder builder = new FormEncodingBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            body = builder.build();
        }
        //Request postMethod = new Request.Builder().url(requestURL).addHeader("Content-Type", contentType).addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8").post(
        //        body).build();
        Request.Builder builder = new Request.Builder().url(requestURL).addHeader("Content-Type", contentType).addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if(method.equalsIgnoreCase("POST")){
            builder.post(body);
        }else if(method.equalsIgnoreCase("PUT")){
            builder.put(body);
        }
        Request requestMethod = builder.build();
        try {
            Response response = htpClient.newCall(requestMethod).execute();
            jiraResponseVo.setStatusCode(response.code());
            jiraResponseVo.setResponseBody(response.body().string());
        }
        catch (Exception e) {
            logger.error("doPost execute fail: " + e.getMessage());
        }
        return jiraResponseVo;
    }

    @Override
    public RestApiResponseVo doPut(Object paramMap) {
        return null;
    }
}
