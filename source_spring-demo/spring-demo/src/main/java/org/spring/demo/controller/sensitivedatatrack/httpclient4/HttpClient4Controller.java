package org.spring.demo.controller.sensitivedatatrack.httpclient4;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;
import org.spring.demo.util.JiraRestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("httpclient4")
public class HttpClient4Controller {

  @RequestMapping("/testPost.do")
  public void testPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("name");
    String password = request.getParameter("pass");
    String serverUri = request.getParameter("uri");
    Map<String, String> userInfoMap = new HashMap<String, String>();
    userInfoMap.put("username", username);
    userInfoMap.put("password", password);
    String jiraUserStr = JSONObject.toJSONString(userInfoMap);
    RestApiResponseVo responseVo = JiraRestUtil.doLoginByHttpClient4(username,password,serverUri,jiraUserStr);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }


  @RequestMapping("/testSingeleQuery.do")
  public void testSingeleQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("loginUName");
    String password = request.getParameter("loginUPassword");
    String serverUri = request.getParameter("jiraServerUri");
    RestApiResponseVo responseVo = JiraRestUtil.getGroupPickerByUserNameByHttpClient4(username,password,serverUri);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

  @RequestMapping("/testMultiQuery.do")
  public void testMultiQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("jiraUserName");
    String password = request.getParameter("jiraUserPwd");
    String serverUri = request.getParameter("jiraUrl");
    String projectIds = request.getParameter("jiraProjectIds");
    RestApiResponseVo responseVo = JiraRestUtil.getProjectMetaDataByProjectIdByHttpClient4(username,password,serverUri,projectIds);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

}
