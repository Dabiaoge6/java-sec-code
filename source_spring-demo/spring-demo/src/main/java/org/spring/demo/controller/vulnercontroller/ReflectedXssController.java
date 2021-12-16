package org.spring.demo.controller.vulnercontroller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spring.demo.AttackResult;
import org.spring.demo.Fruits;
import org.spring.demo.view.JSPView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.vulhunter.common.reflectedxss.ReflectedXssCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("reflectedXss")
public class ReflectedXssController {

  @RequestMapping("/xss006.do")
  @ResponseBody
  public void printWriterTestPrintf(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ReflectedXssCommon reflectedXssService = new ReflectedXssCommon();
    reflectedXssService.printWriterTestPrintf(request, response);
  }

}
