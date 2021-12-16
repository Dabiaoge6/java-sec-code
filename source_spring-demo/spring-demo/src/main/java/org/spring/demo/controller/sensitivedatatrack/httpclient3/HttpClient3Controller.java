package org.spring.demo.controller.sensitivedatatrack.httpclient3;

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
@RequestMapping("httpclient3")
public class HttpClient3Controller {

  @RequestMapping("/testPost.do")
  public void testPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String serverUri = request.getParameter("serverUri");
    Map<String, String> userInfoMap = new HashMap<String, String>();
    userInfoMap.put("username", username);
    userInfoMap.put("password", password);
    String jiraUserStr = JSONObject.toJSONString(userInfoMap);
    RestApiResponseVo responseVo = JiraRestUtil
        .doLoginByHttpClient3(username, password, serverUri, jiraUserStr);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }


  @RequestMapping("/testSingeleQuery.do")
  public void testSingeleQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("loginName");
    String password = request.getParameter("loginPassword");
    String serverUri = request.getParameter("loginServerUri");
    RestApiResponseVo responseVo = JiraRestUtil
        .getGroupPickerByUserNameByHttpClient3(username, password, serverUri);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

  @RequestMapping("/testMultiQuery.do")
  public void testMultiQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("loginUserName");
    String password = request.getParameter("loginPwd");
    String serverUri = request.getParameter("loginUri");
    String projectIds = request.getParameter("projectIds");
    RestApiResponseVo responseVo = JiraRestUtil
        .getProjectMetaDataByProjectIdByHttpClient3(username, password, serverUri, projectIds);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

}
