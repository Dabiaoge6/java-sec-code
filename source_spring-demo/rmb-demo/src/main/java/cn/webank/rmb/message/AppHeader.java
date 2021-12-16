package cn.webank.rmb.message;

public class AppHeader {
  private String transCode;

  public String getTransCode() {
    return transCode;
  }

  public void setTransCode(String transCode) {
    this.transCode = transCode;
  }

  @Override
  public String toString() {
    return "AppHeader{" +
        "transCode='" + transCode + '\'' +
        '}';
  }
}
