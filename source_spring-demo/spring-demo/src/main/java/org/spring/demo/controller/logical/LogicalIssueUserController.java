package org.spring.demo.controller.logical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("logical-issue/user")
public class LogicalIssueUserController {

	@RequestMapping(value = "/index")
    public String show(){
        return "user/show";
    }
	
	
}
