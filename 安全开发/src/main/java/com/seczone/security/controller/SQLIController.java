package com.seczone.security.controller;


import com.seczone.security.dao.Http;
import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.HttpMapper;
import com.seczone.security.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/")
public class SQLIController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpMapper httpMapper;

    @GetMapping("/test")
    public String test(){
        return "hellow";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/toPostInjectIndex")
    public ModelAndView PostInject(ModelAndView modelAndView){
        modelAndView.setViewName("PostInject");
        return modelAndView;
    }
    @RequestMapping(value = "/PostInject", method = RequestMethod.POST)
    public String login(String username,String password,Model model){

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登陆方法，如果没有异常就OK了

        List<UserVo> userVos = userMapper.loginByUsernameAndPass(username, password);
        if (userVos.size()>0){
            model.addAttribute("message","登陆成功");
            model.addAttribute("userinfo",userVos);
        }else{
            model.addAttribute("message","用户名或密码错误");
        }
        return "PostInject";
    }


    @GetMapping(value = "/cookie")
    public ModelAndView cookie(ModelAndView modelAndView){
        modelAndView.setViewName( "cookieDemo");
        return modelAndView;
    }

    /**
     * joychou' or '1'='1' #
     * @param request
     * @return
     */
    @RequestMapping(value = "getCookie")
    public String getCookie(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        String   username = null;
        for ( Cookie cookie : cookies){
            System.out.println(cookie.getName());
            if (cookie.getName().equals("inputcookie")){
            username = cookie.getValue();
                System.out.println(username);
            }
        }
        List<UserVo> userVos = userMapper.searchByname(URLDecoder.decode(username));

        model.addAttribute("information",userVos);
        return "cookieDemo";
    }



    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, UserVo userVo){

        String userName = userVo.getUserName();
        String password = userVo.getPassword();


        String s = userVo.toString();
        List<UserVo> userVos = userMapper.loginByUsernameAndPass(userName, password);

        if(userVos.size()==0){
            modelAndView.addObject("error","账号或密码错误！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("userName",userName);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/checkCookie",method= RequestMethod.POST)
    public String checkCookie(String inputcookie,HttpServletResponse response){
        String encode = URLEncoder.encode(inputcookie);
        // 新建Cookie
        Cookie inputcookie1 = new Cookie("inputcookie", encode);

        // 输出到客户端
        response.addCookie(inputcookie1);


        return "redirect:getCookie";
    }

    //Http注入

    @RequestMapping(value = "/toHttp",method = RequestMethod.GET)
    public String ToHttp(){
        return "http";
    }


    @RequestMapping(value="/http",method= RequestMethod.GET)
    public String httpSearch(HttpServletRequest request,Model model ){
        String ip = request.getHeader("x-forwarded-for");
        if(ip ==null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip ==null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip ==null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        final String agent = request.getHeader("user-agent");
        model.addAttribute("ip",ip);
        model.addAttribute("agent",agent);
        final List<Http> https = httpMapper.searchByIpAndAngent(ip, agent);

        if (https.size()==0){
            model.addAttribute("message","暂无该条记录");
            return "HttpIndex";
        }else{
            model.addAttribute("message",https);
            return "HttpIndex";
        }
    }


    @RequestMapping(value="/http2",method= RequestMethod.GET)
    public String httpSearch2(Model model , String  ip, String  agent){

        System.out.println();
        final List<Http> https = httpMapper.searchByIpAndAngent(ip, agent);
        model.addAttribute("ip",ip);
        model.addAttribute("agent",agent);
        if (https.size()==0){
            model.addAttribute("message","暂无该条记录");
            return "HttpIndex";
        }else{
            model.addAttribute("message",https);
            return "HttpIndex";
        }
    }
}
