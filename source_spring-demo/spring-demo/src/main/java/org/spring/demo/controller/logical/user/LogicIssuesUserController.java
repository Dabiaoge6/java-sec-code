package org.spring.demo.controller.logical.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/logic-issues/user")
public class LogicIssuesUserController {

	@RequestMapping(value = "/index")
    public String show(){
        return "user/show";
    }
	
}
