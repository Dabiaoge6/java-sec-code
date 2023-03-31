package com.seczone.security.controller;

import com.seczone.security.config.ShiroLogoutFilter;
import com.seczone.security.dao.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Controller
public class LoginController {

        @GetMapping("demo")
        public String toDemo(){
        return "demo";
    }

        @GetMapping(value = {"/","index"})
        public String toIndex(Model model){
            model.addAttribute("msg","hello,Shiro");
            return "index";
        }



        @GetMapping("toLogin")
        public String toLogin(){
            return "login";
        }

        @RequestMapping("/login2")
        public String login(String username,String password,Model model){
            //获取当前用户   自动tia
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //执行登陆方法，如果没有异常就OK了
            try{
                subject.login(token); //shiroManager设置登陆信息
                model.addAttribute("msg","登陆成功");

                final UserVo user = (UserVo) subject.getPrincipal();
                System.out.println(user.toString());
                return "index";
            }catch (UnknownAccountException e){
                //用户名不存在
                model.addAttribute("msg","用户名错误");
                return "login";
            }
            catch (IncorrectCredentialsException e){
                //密码不存在
                model.addAttribute("msg","密码错误");
                return "login";
            }
        }

        @RequestMapping("/noauth")
        @ResponseBody
        public String Unauthorized(){
            return "未经授权无法访问此页面";
        }

        @GetMapping("/logout")
        public String Logout(ServletRequest request, ServletResponse response) throws Exception {
            ShiroLogoutFilter shiroLogoutFilter= new ShiroLogoutFilter();
            shiroLogoutFilter.preHandle( request,  response);
        return "login";
    }

    }