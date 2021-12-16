package org.spring.demo.util;

import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.LoginInfo;
import com.atlassian.jira.rest.client.internal.json.LoginInfoJsonParser;
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;
import org.spring.demo.controller.sensitivedatatrack.httpclient3.HttpClient3Util;
import org.spring.demo.controller.sensitivedatatrack.httpclient4.HttpClient4Util;
import org.spring.demo.controller.sensitivedatatrack.okhttpclient.OkHttpClientUtil;

public class JiraRestUtil {

  private static final Logger logger = LoggerFactory.getLogger(JiraRestUtil.class);

  /**
   * 登录jira
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @param jsonParameters request数据
   * @return jiraResponseVo
   */
  public static RestApiResponseVo doLoginByOkHttpClient(String username, String password, String serverUri,
      String jsonParameters) {
    String apiUri = "/rest/auth/1/session";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri;
    JSONObject jsonObject = null;
    LoginInfo loginInfo = null;
    LoginInfoJsonParser loginInfoJsonParser = new LoginInfoJsonParser();
    RestApiResponseVo jiraResponseVo = OkHttpClientUtil
        .doPost(username, password, reqUri, jsonParameters);
    logger.warn(username + " login server " + serverUri + " by parameters "
        + jsonParameters + " result is " + jiraResponseVo.getStatusCode() + ", " + jiraResponseVo
        .getResponseBody());
    return jiraResponseVo;
  }

  /**
   * 登录jira
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @param jsonParameters request数据
   * @return jiraResponseVo
   */
  public static RestApiResponseVo doLoginByHttpClient3(String username, String password, String serverUri,
      String jsonParameters) {
    String apiUri = "/rest/auth/1/session";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri;
    JSONObject jsonObject = null;
    LoginInfo loginInfo = null;
    LoginInfoJsonParser loginInfoJsonParser = new LoginInfoJsonParser();
    RestApiResponseVo jiraResponseVo = HttpClient3Util.doPost1(username, password, reqUri, jsonParameters);
    logger.warn(username + " login server " + serverUri + " by parameters "
        + jsonParameters + " result is " + jiraResponseVo.getStatusCode() + ", " + jiraResponseVo
        .getResponseBody());
    System.out.println(username + " login server " + serverUri + " by parameters "
        + jsonParameters + " result is " + jiraResponseVo.getStatusCode() + ", " + jiraResponseVo
        .getResponseBody());
    return jiraResponseVo;
  }

  /**
   * 登录jira
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @param jsonParameters request数据
   * @return jiraResponseVo
   */
  public static RestApiResponseVo doLoginByHttpClient4(String username, String password, String serverUri,
      String jsonParameters) {
    String apiUri = "/rest/auth/1/session";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri;
    JSONObject jsonObject = null;
    LoginInfo loginInfo = null;
    LoginInfoJsonParser loginInfoJsonParser = new LoginInfoJsonParser();
    RestApiResponseVo jiraResponseVo = HttpClient4Util.doPost(username, password, reqUri, jsonParameters);
    logger.warn(username + " login server " + serverUri + " by parameters "
        + jsonParameters + " result is " + jiraResponseVo.getStatusCode() + ", " + jiraResponseVo
        .getResponseBody());
    return jiraResponseVo;
  }

  /**
   * 根据projectKeys获取已选的project可分配的用户列表
   *
   * @param username 用户名
   * @param password 密码
   * @param projectKeys 选择的projectkey
   * @param serverUri jira服务端uri
   */
  public static RestApiResponseVo getAssigneeByProjectKey(String username, String password,
      String projectKeys, String serverUri) {
    String apiUri = "/rest/api/2/user/assignable/multiProjectSearch";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri;
    ArrayList<BasicUser> userArrayList = null;
    String reqUri_query = reqUri + "?projectKeys=" + projectKeys;
    RestApiResponseVo jiraResponseVo = HttpClient3Util.doGet(username, password, reqUri_query);
    return jiraResponseVo;
  }

  /**
   * 根据projectId获取当前project的所有meta数据
   *
   * @return 返回VulInteMetaData
   */
  public static RestApiResponseVo getProjectMetaDataByProjectIdByOkHttpClient(String username, String password,
      String serverUri,
      String projectId) {
    String apiUri = "/rest/api/2/issue/createmeta";
    String reqUri =
        (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
            : serverUri) + apiUri + "?projectIds=" + projectId
            + "&expand=projects.issuetypes.fields";
    return OkHttpClientUtil.doGet(username, password, reqUri);
  }

  /**
   * 根据projectId获取当前project的所有meta数据
   *
   * @return 返回VulInteMetaData
   */
  public static RestApiResponseVo getProjectMetaDataByProjectIdByHttpClient3(String username, String password,
      String serverUri,
      String projectId) {
    String apiUri = "/rest/api/2/issue/createmeta";
    String reqUri =
        (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
            : serverUri) + apiUri + "?projectIds=" + projectId
            + "&expand=projects.issuetypes.fields";
    return HttpClient3Util.doGetForAviodRepeat(username, password, reqUri);
  }

  /**
   * 根据projectId获取当前project的所有meta数据
   *
   * @return 返回VulInteMetaData
   */
  public static RestApiResponseVo getProjectMetaDataByProjectIdByHttpClient4(String username, String password,
      String serverUri,
      String projectId) {
    String apiUri = "/rest/api/2/issue/createmeta";
    String reqUri =
        (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
            : serverUri) + apiUri + "?projectIds=" + projectId
            + "&expand=projects.issuetypes.fields";
    return HttpClient4Util.doGetForAviodRepeat(username, password, reqUri);
  }

  /**
   * 根据当前登录的用户名获取所有用户组
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @return jiraResponseVo
   */
  public static RestApiResponseVo getGroupPickerByUserNameByOkHttpClient(String username, String password,
      String serverUri) {
    String apiUri = "/rest/api/2/groups/picker";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri + "?userName=" + username;
    RestApiResponseVo jiraResponseVo = null;
    try {
      jiraResponseVo = OkHttpClientUtil.doGetAsync(username, password, reqUri);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return jiraResponseVo;
  }


  /**
   * 根据当前登录的用户名获取所有用户组
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @return jiraResponseVo
   */
  public static RestApiResponseVo getGroupPickerByUserNameByHttpClient3(String username, String password,
      String serverUri) {
    String apiUri = "/rest/api/2/groups/picker";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri + "?userName=" + username;
    RestApiResponseVo jiraResponseVo = HttpClient3Util.doGet(username, password, reqUri);
    return jiraResponseVo;
  }

  /**
   * 根据当前登录的用户名获取所有用户组
   *
   * @param username 用户名
   * @param password 密码
   * @param serverUri jira服务端ip
   * @return jiraResponseVo
   */
  public static RestApiResponseVo getGroupPickerByUserNameByHttpClient4(String username, String password,
      String serverUri) {
    String apiUri = "/rest/api/2/groups/picker";
    String reqUri = (serverUri.endsWith("/") ? serverUri.substring(0, serverUri.lastIndexOf("/"))
        : serverUri) + apiUri + "?userName=" + username;
    RestApiResponseVo jiraResponseVo = HttpClient4Util.doGet(username, password, reqUri);
    return jiraResponseVo;
  }

}
