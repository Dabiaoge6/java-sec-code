/*
 *
 * FileName：UnvalidRedirectController
 * Description：UnvalidRedirectController
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2021/1/25 9:26
 */

package org.spring.demo.controller.scan;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mapf
 * @description
 * @date 2021/1/25 9:26
 */

@Controller
@RequestMapping("scanIssue")
public class UnvalidatedRedirectController {
    private static String PATTERN_L2DOMAIN = "\\w*\\.\\w*";
    private static String PATTERN_IP = "(\\d*\\.){3}\\d*";
    private static String DOMAIN = "spring.demo.com";
    private static String TOP_LEVEL_DOMAIN = "demo.com";

    /**
     * 直接跳转
     */
    @RequestMapping(value = "/direct/redirect.do")
    public void direct(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            resp.sendRedirect(url);
        }catch (Exception e){
            resp.setStatus(500);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * prepend http
     */
    @RequestMapping(value = "/http/redirect.do")
    public void httpdirect(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        if(l.startsWith("http")){
            resp.sendRedirect(l);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("跳转地址只能是http协议开头");
        }
    }

    /**
     * prepend https
     */
    @RequestMapping(value = "/https/redirect.do")
    public void httpsdirect(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        if(l.startsWith("https")){
            resp.sendRedirect(l);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("跳转地址只能是https协议开头");
        }
    }

    /**
     * regular prefix 截取固定前缀+'/'后面的url作为跳转地址
     * url=localhost/localhost:8085/scanIssue/send_redirect.do
     */
    @RequestMapping(value = "/regularPrefix_solidus/redirect.do")
    public void regularPrefixSolidus(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String l = URLDecoder.decode(url);
        String host = getHostAndPort(req);
        if(l.startsWith(host)){
            l = StringUtils.substringAfter(l, host);
            resp.sendRedirect(l);
        }else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("地址不正确");
        }
    }

    /**
     * regular prefix 截取固定前缀+'@'后面的url作为跳转地址
     * url=localhost@localhost:8085/scanIssue/send_redirect.do
     */
    /*@RequestMapping(value = "/regularPrefix_at/redirect.do")
    public void regularPrefixAt(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        String localhost = getHost(req) + "@";
        l = StringUtils.substringAfter(l, localhost);
        resp.sendRedirect(l);
    }*/

    /**
     * regular prefix 截取固定前缀+'.'后面的url作为跳转地址
     * url=localhost.localhost:8085/scanIssue/send_redirect.do
     */
    /*@RequestMapping(value = "/regularPrefix_dot/redirect.do")
    public void regularPrefixDot(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        String localhost = getHost(req) + ".";
        l = StringUtils.substringAfter(l, localhost);
        resp.sendRedirect(l);
    }*/

    /**
     * regular postfix 检查地址是否以一级域名demo.com结尾
     * /demo.com或构造域名www.scanner.com?a=.demo.com 、 www.scanner.com/.demo.com
     * url=localhost:8085/scanIssue/send_redirect.do?
     */
    @RequestMapping(value = "/regularPostfix_top_domain/redirect.do")
    public void regularPostfixTopDomain(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*//解决cors
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Cache-Control","no-cache");
        //允许请求方式
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //访问控制允许凭据，true为允许
        resp.setHeader("Access-Control-Allow-Credentials", "true");*/
        System.out.println(url+"\n");
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        if(l.endsWith(TOP_LEVEL_DOMAIN)){
            resp.sendRedirect(l);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("请求地址不是以"+TOP_LEVEL_DOMAIN+"域名结尾，禁止跳转");
        }
    }

    /**
     * regular postfix 检查地址是否以服务地址结尾
     * url=localhost:8085/scanIssue/evil_redirect.do?localhost:8085
     * url=localhost:8085/scanIssue/evil_redirect.do\localhost:8085
     * url=localhost:8085/scanIssue/evil_redirect.do#localhost:8085
     * url=localhost:8085/scanIssue/evil_redirect.do\\localhost:8085
     */
    @RequestMapping(value = "/regularPostfix_domain/redirect.do")
    public void regularPostfixDomain(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "UTF-8";
        String l = URLDecoder.decode(url, charset);
        String localhost = getHost(req);
        if(l.endsWith(localhost)){
            resp.sendRedirect(l);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("请求地址不是以"+localhost+"域名结尾，禁止跳转");
        }
    }

    /**
     * 判断url种是否存在特殊字符，存在就返回
     * url=localhost:8085/scanIssue/send_redirect.do?
     */
    @RequestMapping(value = "/escape_special_character/redirect.do")
    public void  specialCharacter(@Param("url") String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!StringUtils.containsAny(url, new char[] {'#', '\\', ':'})){
            resp.sendRedirect(URLDecoder.decode(url));
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("请求地址包含特殊字符，禁止跳转");
        }
    }

    /**
     * send redirect后的地址
     */
    @RequestMapping(value = "/redirect.do")
    public void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("跳转到了正确地址");
    }

    /**
     * 跳转到了非法地址
     */
    @RequestMapping(value = "/evil_redirect.do")
    public void evilRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*//解决cors
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Cache-Control","no-cache");
        //允许请求方式
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //访问控制允许凭据，true为允许
        resp.setHeader("Access-Control-Allow-Credentials", "true");*/
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("跳转到了非法地址");
    }

    private boolean isIP(String url) {
        Pattern ipPattern = Pattern.compile(PATTERN_IP);
        Matcher matcher = ipPattern.matcher(url);
        if (matcher.find()) {
            return true;
        }/*else{
            Pattern pattern = Pattern.compile(PATTERN_L2DOMAIN);
            matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return false;
            }
        }*/
        return false;
    }

    private String getHost(HttpServletRequest req){
        return "http://"+req.getServerName();
    }

    private String getHostAndPort(HttpServletRequest req){
        int port = req.getLocalPort();
        return "http://"+req.getServerName() + (port == 80 ? "": ":"+port);
    }
}
