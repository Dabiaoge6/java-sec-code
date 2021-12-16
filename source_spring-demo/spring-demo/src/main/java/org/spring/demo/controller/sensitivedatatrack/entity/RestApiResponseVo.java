package org.spring.demo.controller.sensitivedatatrack.entity;

public class RestApiResponseVo {

    private int statusCode;
    private String responseBody;

    public RestApiResponseVo(){}

    public RestApiResponseVo(int statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "RestApiResponseVo{" + "statusCode=" + statusCode + ", responseBody='" + responseBody + '\'' + '}';
    }
}
