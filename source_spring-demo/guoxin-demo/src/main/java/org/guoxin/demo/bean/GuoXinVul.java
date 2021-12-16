package org.guoxin.demo.bean;

public class GuoXinVul {

  private String vulTitle;
  private String vulType;
  private String source;
  private String area;
  private String risklevel;
  private String isline;
  private String descriptio;
  private String details;
  private String solution;
  private String domain;
  private String state;

  public GuoXinVul(String vulTitle, String vulType, String source, String area,
      String risklevel, String isline, String descriptio, String details, String solution,
      String domain, String state) {
    this.vulTitle = vulTitle;
    this.vulType = vulType;
    this.source = source;
    this.area = area;
    this.risklevel = risklevel;
    this.isline = isline;
    this.descriptio = descriptio;
    this.details = details;
    this.solution = solution;
    this.domain = domain;
    this.state = state;
  }

  public String getVulTitle() {
    return vulTitle;
  }

  public void setVulTitle(String vulTitle) {
    this.vulTitle = vulTitle;
  }

  public String getVulType() {
    return vulType;
  }

  public void setVulType(String vulType) {
    this.vulType = vulType;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getRisklevel() {
    return risklevel;
  }

  public void setRisklevel(String risklevel) {
    this.risklevel = risklevel;
  }

  public String getIsline() {
    return isline;
  }

  public void setIsline(String isline) {
    this.isline = isline;
  }

  public String getDescriptio() {
    return descriptio;
  }

  public void setDescriptio(String descriptio) {
    this.descriptio = descriptio;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getSolution() {
    return solution;
  }

  public void setSolution(String solution) {
    this.solution = solution;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "GuoXinVul{" +
        "vul_title='" + vulTitle + '\'' +
        ", vul_type='" + vulType + '\'' +
        ", source='" + source + '\'' +
        ", area='" + area + '\'' +
        ", risklevel='" + risklevel + '\'' +
        ", isline='" + isline + '\'' +
        ", descriptio='" + descriptio + '\'' +
        ", details='" + details + '\'' +
        ", solution='" + solution + '\'' +
        ", domain='" + domain + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}
