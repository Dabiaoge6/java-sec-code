package org.guoxin.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.guoxin.demo.bean.GuoXinApp;
import org.guoxin.demo.bean.GuoXinResponseVo;
import org.guoxin.demo.bean.GuoXinVul;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuoXinApiController {

  @RequestMapping(value = "api/area", method = RequestMethod.GET)
  @ResponseBody
  public List<GuoXinApp> getGuoXinApp(HttpServletRequest request, HttpServletResponse response) {
    GuoXinApp guoXinApp = new GuoXinApp();
    GuoXinApp guoXinApp1 = new GuoXinApp();
    GuoXinApp guoXinApp2 = new GuoXinApp();
    guoXinApp.setArea("金太阳");
    guoXinApp1.setArea("test-app");
    guoXinApp2.setArea("CRM:core");
    List<GuoXinApp> guoXinAppList = new ArrayList<GuoXinApp>(3);
    guoXinAppList.add(guoXinApp);
    guoXinAppList.add(guoXinApp1);
    guoXinAppList.add(guoXinApp2);
    return guoXinAppList;
  }

  @RequestMapping(value = "/index.php/api/orders/add", method = RequestMethod.POST)
  @ResponseBody
  public GuoXinResponseVo sendVul(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String vulTitle = request.getParameter("vul_title");
    String vulType = request.getParameter("vul_type");
    String source = request.getParameter("source");
    String area = request.getParameter("area");
    String risklevel = request.getParameter("risklevel");
    String isline = request.getParameter("isline");
    String description = request.getParameter("description");
    String details = request.getParameter("details");
    String solution = request.getParameter("solution");
    String domain = request.getParameter("domain");
    String state = request.getParameter("state");
    GuoXinVul vul = new GuoXinVul(vulTitle, vulType, source, area, risklevel, isline, description,
        details, solution, domain, state);
    System.out.println("accept body===>" + vul.toString());
    GuoXinResponseVo guoXinResponseVo = new GuoXinResponseVo();
    guoXinResponseVo.setStatus("successful");
    guoXinResponseVo.setInfo("3");
    return guoXinResponseVo;
  }

}
