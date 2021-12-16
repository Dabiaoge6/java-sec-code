package cn.webank.rmb.message;

/**
 * Created by hujj on 2020-07-07.
 */
public class SimpleDestination extends Destination {

  private String serviceOrEventId;
  private String scenario;
  private String dcnNo;
  private String organizationId;
  private String organizationIdInputFlag;

  public SimpleDestination() {
  }

  public String getServiceOrEventId() {
    return serviceOrEventId;
  }

  public void setServiceOrEventId(String serviceOrEventId) {
    this.serviceOrEventId = serviceOrEventId;
  }

  public String getScenario() {
    return scenario;
  }

  public void setScenario(String scenario) {
    this.scenario = scenario;
  }

  public String getDcnNo() {
    return dcnNo;
  }

  public void setDcnNo(String dcnNo) {
    this.dcnNo = dcnNo;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public String getOrganizationIdInputFlag() {
    return organizationIdInputFlag;
  }

  public void setOrganizationIdInputFlag(String organizationIdInputFlag) {
    this.organizationIdInputFlag = organizationIdInputFlag;
  }

  @Override
  public String toString() {
    return "SimpleDestination{" +
        "serviceOrEventId='" + serviceOrEventId + '\'' +
        ", scenario='" + scenario + '\'' +
        ", dcnNo='" + dcnNo + '\'' +
        ", organizationId='" + organizationId + '\'' +
        ", organizationIdInputFlag='" + organizationIdInputFlag + '\'' +
        '}';
  }
}
