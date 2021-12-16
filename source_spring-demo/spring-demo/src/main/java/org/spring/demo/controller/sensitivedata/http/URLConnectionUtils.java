/*
 *
 * FileName：URLConnectionUtils
 * Description：URLConnectionUtils
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2020/12/2 19:21
 */

package org.spring.demo.controller.sensitivedata.http;

import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mapf
 * @description
 * @date 2020/12/2 19:21
 */
public class URLConnectionUtils extends AbstractHttpRequest {
    @Override
    public RestApiResponseVo doGet(HashMap<String, Object> paramMap) {
        BufferedReader bufferedReader = null;
        try {
            //拼接url
            requestURL += "?"+request.getQueryString();

            URL realUrl = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setReadTimeout(5000);
            connection.setRequestMethod(request.getMethod());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            //获取内容
            InputStream inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            final StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            System.out.println("urlconnection:=======>\n" + stringBuffer.toString());
            bufferedReader.close();
            RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
            jiraResponseVo.setStatusCode(connection.getResponseCode());
            jiraResponseVo.setResponseBody(stringBuffer.toString());
            connection.disconnect();
            return jiraResponseVo;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RestApiResponseVo doPost(Object paramMap) {
        BufferedReader bufferedReader = null;
        String contentType = "";

        try {
            URL httpUrl = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod(request.getMethod());
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            if (paramMap instanceof String) {
                contentType = "application/json;charset=utf-8";
                connection.setRequestProperty("Content-type", contentType);
                connection.connect();
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                //JSONObject obj = new JSONObject();
                //String json = java.net.URLEncoder.encode((String)paramMap, "utf-8");
                out.writeBytes((String)paramMap);
                out.flush();
                out.close();
            }
            else {
                HashMap<String, Object> params = (HashMap<String, Object>) paramMap;
                contentType = "application/x-www-form-urlencoded;charset=utf-8";
                connection.setRequestProperty("Content-type", contentType);
                connection.connect();

                StringBuilder builder = new StringBuilder();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.append(entry.getKey() + "=" + entry.getValue().toString() + "&");
                }
                OutputStream outputStream = connection.getOutputStream();
                //thisObj, return outputStream
                //
                outputStream.write(builder.toString().getBytes());
                //thisObj(outputStream), param
                outputStream.flush();
                outputStream.close();
            }

            //获取内容
            InputStream inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            connection.disconnect();
            RestApiResponseVo restApiResponseVo = new RestApiResponseVo();
            restApiResponseVo.setStatusCode(connection.getResponseCode());
            restApiResponseVo.setResponseBody(stringBuffer.toString());
            return restApiResponseVo;
        }
        catch (Exception e) {
        }
        return new RestApiResponseVo();
    }

    @Override
    public RestApiResponseVo doPut(Object paramMap) {
        return null;
    }
}
