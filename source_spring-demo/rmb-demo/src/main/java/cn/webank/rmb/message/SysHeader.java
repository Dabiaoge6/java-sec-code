package cn.webank.rmb.message;

import java.io.Serializable;

public class SysHeader implements Serializable {

  protected String bizSeqNo;
  protected String consumerSeqNo;
  protected String rmbVersion;
  protected String consumerSvrId;

  public String getBizSeqNo() {
    return bizSeqNo;
  }

  protected void setBizSeqNo(String bizSeqNo) {
    this.bizSeqNo = bizSeqNo;
  }

  public String getConsumerSeqNo() {
    return consumerSeqNo;
  }

  protected void setConsumerSeqNo(String consumerSeqNo) {
    this.consumerSeqNo = consumerSeqNo;
  }

  public String getRmbVersion() {
    return rmbVersion;
  }

  protected void setRmbVersion(String rmbVersion) {
    this.rmbVersion = rmbVersion;
  }

  public String getConsumerSvrId() {
    return consumerSvrId;
  }

  protected void setConsumerSvrId(String consumerSvrId) {
    this.consumerSvrId = consumerSvrId;
  }

  @Override
  public String toString() {
    return "SysHeader{" +
        "bizSeqNo='" + bizSeqNo + '\'' +
        ", consumerSeqNo='" + consumerSeqNo + '\'' +
        ", rmbVersion='" + rmbVersion + '\'' +
        ", consumerSvrId='" + consumerSvrId + '\'' +
        '}';
  }
}
