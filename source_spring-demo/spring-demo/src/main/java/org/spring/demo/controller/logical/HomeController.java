package org.spring.demo.controller.logical;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("home")
public class HomeController {

	@RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "role", required = false) String role){
		if (StringUtils.isNotEmpty(role) && role.equals("ADMIN")) {
			return "admin/hello";
		} else {
			return "user/hello";
		}
    }
	
	@RequestMapping(value = "/toLogin")
    public String userLogin(){
        return "user/userLogin";
    }
	
	@RequestMapping(value = "/toRegister")
    public String userRegister(){
        return "user/userRegister";
    }
	
	@RequestMapping(value = "/toPay")
    public String toPay(){
        return "toPay";
    }
	
}
