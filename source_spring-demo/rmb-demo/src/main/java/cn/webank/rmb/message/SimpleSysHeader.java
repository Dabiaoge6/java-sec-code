package cn.webank.rmb.message;

public class SimpleSysHeader extends SysHeader {

  @Override
  public void setBizSeqNo(String bizSeqNo) {
    super.setBizSeqNo(bizSeqNo);
  }

  @Override
  protected void setConsumerSeqNo(String consumerSeqNo) {
    super.setConsumerSeqNo(consumerSeqNo);
  }

  @Override
  protected void setRmbVersion(String rmbVersion) {
    super.setRmbVersion(rmbVersion);
  }

  @Override
  protected void setConsumerSvrId(String consumerSvrId) {
    super.setConsumerSvrId(consumerSvrId);
  }
}
