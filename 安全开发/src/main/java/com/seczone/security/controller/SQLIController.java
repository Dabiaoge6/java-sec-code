package com.seczone.security.controller;


import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/test")
    public String test(){
        return "hellow";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value = "/cookie")
    public ModelAndView cookie(ModelAndView modelAndView){
        modelAndView.setViewName( "cookieDemo");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "getCookie")
    public String getCookie(HttpServletRequest request){
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

        return userVos.toString();
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


    @PostMapping(value = "checkCookie")
    public String checkCookie(String inputcookie,HttpServletResponse response){
        String encode = URLEncoder.encode(inputcookie);
        // 新建Cookie
        Cookie inputcookie1 = new Cookie("inputcookie", encode);

        // 输出到客户端
        response.addCookie(inputcookie1);


        return "redirect:getCookie";
    }

}
