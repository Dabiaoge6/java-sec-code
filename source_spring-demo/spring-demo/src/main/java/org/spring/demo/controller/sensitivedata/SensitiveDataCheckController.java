package org.spring.demo.controller.sensitivedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.spring.demo.controller.sensitivedata.file.FileOutputHandle;
import org.spring.demo.controller.sensitivedata.http.*;
import org.spring.demo.controller.sensitivedata.sql.SqlHandle;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mapf
 * @description
 * @date 2020/12/2 14:36
 */
@Controller
@RequestMapping("/sensitivedata")
public class SensitiveDataCheckController {

    @RequestMapping("/**")
    public void controllerMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestType = StringUtils.substringAfter(request.getRequestURI(), "/sensitivedata");

        RestApiResponseVo responseVo = null;
        if ("/httpClient3.do".equals(requestType)) {
            responseVo = AbstractHttpRequest.build(new HttpClient3Utils(), request);
        }else if("/httpClient4.do".equals(requestType)){
            responseVo = AbstractHttpRequest.build(new HttpClient4Utils(), request);
        }else if("/urlConnection.do".equals(requestType)){
            responseVo = AbstractHttpRequest.build(new URLConnectionUtils(), request);
        }else if("/okHttp.do".equals(requestType)){
            responseVo =  AbstractHttpRequest.build(new OkHttpClientUtils(), request);
        }else if("/fileOutputStream.do".equalsIgnoreCase(requestType)){
            responseVo = FileOutputHandle.handle(request.getParameter("content"));
        }else if("/sql.do".equalsIgnoreCase(requestType)){
            responseVo = SqlHandle.sqlHandle(request.getParameter("sql"));
        }else {
            response.getWriter().println("请求路径错误");
        }
        response.getWriter().println(responseVo.toString());
    }

    @RequestMapping("/handle.do")
    public void handleRequest(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        JSONObject json = new JSONObject();
        json.put("uri", requestURI);
        json.put("method", method);
        json.put("params", getRequestParams(request));

        response.getWriter().println(json.toJSONString());
    }
    private String getContentJsonParam(HttpServletRequest request) throws IOException {
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

    @RequestMapping("/file.do")
    public void writeFile(HttpServletRequest request, HttpServletResponse response){
        try {
            File file = new File("C:\\a.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(request.getQueryString().getBytes());
            outputStream.flush();
            outputStream.close();
            file.deleteOnExit();
            response.getWriter().println("文件内容检测执行完成");
        }catch (Exception e){
        }
    }

    /*@RequestMapping("/sql.do")
    public void sqlStatement(HttpServletRequest request, HttpServletResponse response){
        try {
            String[] split = StringUtils.split(request.getQueryString(), "&");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                builder.append(StringUtils.substringBefore(split[i], "=")).append("=\"").append(StringUtils.substringAfter(split[i], "=")).append("\" and ");
            }
            String arg = StringUtils.removeEnd(builder.toString(), "and ");
            String sql = "select * from app1_user where %s;";
            ExecuteSql.execute(String.format(sql, arg));

            PrintWriter out = response.getWriter();
            out.println("test Statement.execute(sql)");
            response.getWriter().println("sql敏感数据检测执行完成,sql="+String.format(sql, arg));
        }catch (Exception e){
        }
    }*/

    public Object getRequestParams(HttpServletRequest request) throws IOException {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        String method = request.getMethod();
        if (method.equalsIgnoreCase("GET")) {
            getQueryParam(request.getQueryString(), paramMap);
        }
        else if (method.equalsIgnoreCase("POST")) {
            String contentType = request.getContentType();
            if (StringUtils.isEmpty(contentType) || contentType.contains("application/x-www-form-urlencoded")) {
                getParamMap(paramMap, request);
            }
            else if (contentType.contains("application/json")) {
                String jsonParam = getContentJsonParam(request);
                HashMap<String, Object> mapType = JSON.parseObject(jsonParam,HashMap.class);
                paramMap = mapType;//(HashMap<String, Object>) JSON.parse(jsonParam);
            }else if (contentType.contains("application/xml") || contentType.contains("text/xml")) {
                paramMap.put("xml",getContentJsonParam(request));
            }
            else {
                System.out.println("contentType类型不支持：" + contentType);
            }
        }
        else {
            System.out.println("请求类型不支持：" + method);
        }
        return paramMap;
    }

    private static void getQueryParam(String queryString, HashMap<String, Object> paramMap) {
        String[] split = StringUtils.split(queryString, "&");
        for (int i = 0; i < split.length; i++) {
            paramMap.put(StringUtils.substringBefore(split[i], "="), StringUtils.substringAfter(split[i], "="));
        }
    }
    private static void getParamMap(HashMap<String, Object> paramMap,HttpServletRequest request) {
        Map parameterMap = request.getParameterMap();
        for(Object key : parameterMap.keySet() ){
            paramMap.put((String) key, ((String[]) parameterMap.get(key))[0]);
        }
    }
}
