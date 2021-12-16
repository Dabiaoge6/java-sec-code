package com.seczone.security.controller;


import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
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

    /**
     * http://localhost:9091/login
     *     用户名输入   a' or 1=1 #           可直接进入
     *      a' or 1=1 order by 3 #          可用作判断数据库字段数量
     * @param modelAndView
     * @param userVo
     * @return
     */
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

 
}
