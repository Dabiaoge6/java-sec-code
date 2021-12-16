package org.spring.demo.controller.sensitivedatatrack.trackresponse;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trackresponse")
public class TrackResponseController {


  @RequestMapping("/headerId.do")
  public void testResponseHeader4(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setHeader("id", "340823199307130449");
    response.getWriter().println("上报漏洞：敏感信息在服务响应中出现泄漏");
  }

  @RequestMapping("/bodyJson.do")
  public void testResponseBody1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setHeader("Content-Type", "application/json");
    response.getWriter().println("{\"id\": \"1\",\"name\": \"test\",\"info\": {\"mobile\": \"18237475689\",\"qq\": \"2333098\",\"email\": \"test@qq.com\",\"card\":{\"bank\":\"6216610200016587010\"}}}");
  }


  @RequestMapping("/bodyXml.do")
  public void testResponseBody3(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setHeader("Content-Type", "application/xml");
    response.getWriter().println("<?xml version=\"1.0\" encoding=\"utf-8\"?><applicant><family><father>bb</father><token>mm</token></family><id>340823199307130449</id><age>11</age><name>hjj</name><mobile>110</mobile><email>hujj@seczone.cn</email><address>hf</address></applicant>");
  }
}
