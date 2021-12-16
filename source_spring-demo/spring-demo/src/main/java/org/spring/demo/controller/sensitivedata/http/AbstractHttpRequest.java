/*
 *
 * FileName：HttpRequest
 * Description：HttpRequest
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2020/12/2 15:33
 */

package org.spring.demo.controller.sensitivedata.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mapf
 * @description
 * @date 2020/12/2 15:33
 */
public abstract class AbstractHttpRequest {
    protected static String requestURL;
    protected static HttpServletRequest request;
    protected static String method;

    public static RestApiResponseVo build(AbstractHttpRequest httpRequestType, HttpServletRequest httpRequest) throws IOException {
        String contextPath = StringUtils.removeEnd(httpRequest.getContextPath(), "/");
        requestURL = httpRequest.getScheme()+"://"+httpRequest.getServerName()+":"+httpRequest.getServerPort()+ contextPath +"/sensitivedata/handle.do";
        request = httpRequest;
        method = httpRequest.getMethod();

        String contentType = httpRequest.getHeader("Content-type");
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
            getQueryParam(request.getQueryString(), paramMap);
            return httpRequestType.doGet(paramMap);
        }
        else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
            if (StringUtils.isEmpty(contentType) || contentType.contains("application/x-www-form-urlencoded")) {
                getParamMap(paramMap);
                return httpRequestType.doPost(paramMap);
            }
            else if (contentType.contains("application/json")) {
                String jsonParam = getContentJsonParam();
                return httpRequestType.doPost(jsonParam);
            }else if(contentType.contains("application/xml") || contentType.contains("text/xml")){
                String jsonParam = getContentJsonParam();
                return httpRequestType.doPost(jsonParam);
            }else {
                System.out.println("contentType类型不支持：" + contentType);
            }
        }
        else {
            System.out.println("请求类型不支持：" + method);
        }
        return null;
    }

    private static void getQueryParam(String queryString, HashMap<String, Object> paramMap) {
        String[] split = StringUtils.split(queryString, "&");
        for (int i = 0; i < split.length; i++) {
            paramMap.put(StringUtils.substringBefore(split[i], "="), StringUtils.substringAfter(split[i], "="));
        }
    }
    private static void getParamMap(HashMap<String, Object> paramMap) {
        Map parameterMap = request.getParameterMap();
        for(Object key : parameterMap.keySet() ){
            paramMap.put((String) key, ((String[]) parameterMap.get(key))[0]);
        }
    }

    private static String getContentJsonParam() throws IOException {
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
        }
        return sb.toString().replaceAll("\\t","").replaceAll("\n","");
    }

    public abstract RestApiResponseVo doGet(HashMap<String, Object> paramMap) throws IOException;

    public abstract RestApiResponseVo doPost(Object paramMap) throws UnsupportedEncodingException;

    public abstract RestApiResponseVo doPut(Object paramMap);

}
