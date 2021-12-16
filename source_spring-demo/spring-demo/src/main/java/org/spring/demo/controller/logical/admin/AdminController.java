package org.spring.demo.controller.logical.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/logic-issues/admin")
public class AdminController {

	@RequestMapping(value = "/index")
    public String show(){
        return "admin/show";
    }
	
}
