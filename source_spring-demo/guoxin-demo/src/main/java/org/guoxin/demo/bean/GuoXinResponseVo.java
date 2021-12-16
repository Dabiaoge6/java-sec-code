package org.guoxin.demo.bean;

/**
 * @Author: hujj
 * @Date: 2019/10/31 22:19
 */
public class GuoXinResponseVo {

  private String status;
  private String info;

  public GuoXinResponseVo() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return "GuoXinResponseVo{" +
        "status='" + status + '\'' +
        ", info='" + info + '\'' +
        '}';
  }
}
