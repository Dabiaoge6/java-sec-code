package org.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hujj on 2020-06-22.
 */
@Controller
@RequestMapping("/springView")
public class HelloViewController {

  @RequestMapping("/testView")
  public String testView(){
    //HelloView类首字母小写
    return "helloView";
  }


}
