package cn.webank.rmb.message;

/**
 * Created by hujj on 2020-07-06.
 */
public class Message {

  protected String content;

  protected Destination destination;

  protected AppHeader appHeader;

  protected SysHeader sysHeader;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

  public AppHeader getAppHeader() {
    return appHeader;
  }

  public void setAppHeader(AppHeader appHeader) {
    this.appHeader = appHeader;
  }

  public SysHeader getSysHeader() {
    return sysHeader;
  }

  public void setSysHeader(SysHeader sysHeader) {
    this.sysHeader = sysHeader;
  }
}
