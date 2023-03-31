package com.seczone.security.controller;

import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/CSRF")
public class CSRFController {
    @Autowired
    UserMapper userMapper;

    //跳转到前端修改密码
    @RequestMapping("/modifilepassword")
    public String turntoModifiy(Model model , HttpServletRequest request, HttpServletResponse response){
        //获取当前用户
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("name",userVo.getUserName());
//        final Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                if(cookie.getName().equals("JSESSIONID")){
//                    model.addAttribute("JSESSIONID",cookie.getValue());
//                    System.out.println("JSESSIONID==============="+cookie.getValue());
//                }
//                if (cookie.getName().equals("Idea-6ae259c8")){
//                    model.addAttribute("Idea-6ae259c8",cookie.getValue());
//                    System.out.println("Idea-6ae259c8==========="+cookie.getValue());
//                }
//            }
//        }else {
//            System.out.println("===================null==================");
//        }
        return "modifilePassword";
    }

    //接受前端跳转
    @ResponseBody
    @RequestMapping("/modPass")
    public String seeUser(String password,Model model){
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getPrincipal();
        String username = userVo.getUserName();
        final int i = userMapper.updateSecurity(username, password);
        final List<UserVo> userVos = userMapper.seeUserByname(username);
        return userVos.toString();
    }



}
