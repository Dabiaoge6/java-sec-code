package org.spring.demo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class RegisterUser {

  private String protectedKey;
  private String name;
  private String pwd;

  public String getName() {
    return name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getProtectedKey() {
    return protectedKey;
  }

  public void setProtectedKey(String protectedKey) {
    this.protectedKey = protectedKey;
  }

}
