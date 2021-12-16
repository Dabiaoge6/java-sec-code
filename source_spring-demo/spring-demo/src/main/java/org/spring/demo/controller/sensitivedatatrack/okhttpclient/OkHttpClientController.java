package org.spring.demo.controller.sensitivedatatrack.okhttpclient;

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
@RequestMapping("okhttpclient")
public class OkHttpClientController {

  @RequestMapping("/testPost.do")
  public void testPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("uname");
    String password = request.getParameter("pwd");
    String serverUri = request.getParameter("jiraUri");
    Map<String, String> userInfoMap = new HashMap<String, String>();
    userInfoMap.put("username", username);
    userInfoMap.put("password", password);
    String jiraUserStr = JSONObject.toJSONString(userInfoMap);
    RestApiResponseVo responseVo = JiraRestUtil
        .doLoginByOkHttpClient(username, password, serverUri, jiraUserStr);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }


  @RequestMapping("/testSingeleQuery.do")
  public void testSingeleQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("name1");
    String password = request.getParameter("pass1");
    String serverUri = request.getParameter("serverUri1");
    RestApiResponseVo responseVo = JiraRestUtil
        .getGroupPickerByUserNameByOkHttpClient(username, password, serverUri);
    response.getWriter()
        .println((responseVo != null && responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

  @RequestMapping("/testMultiQuery.do")
  public void testMultiQuery(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("loginUserName1");
    String password = request.getParameter("loginPwd1");
    String serverUri = request.getParameter("loginUri1");
    String projectIds = request.getParameter("projectIds1");
    RestApiResponseVo responseVo = JiraRestUtil
        .getProjectMetaDataByProjectIdByOkHttpClient(username, password, serverUri, projectIds);
    response.getWriter()
        .println((responseVo.getStatusCode() == 200) ? "success" : "fail");
  }

}
