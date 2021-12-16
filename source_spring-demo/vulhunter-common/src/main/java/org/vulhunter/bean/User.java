package org.vulhunter.bean;

import java.io.Serializable;

public class User implements Serializable {

  private String name;
  private String pwd;
  private String url;
  public String test;

  public User() {
  }

  public User(String name, String pwd) {
    this.name = name;
    this.pwd = pwd;
  }

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void test(String name){
    System.out.println("test");
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", pwd='" + pwd + '\'' +
        ", url='" + url + '\'' +
        ", test='" + test + '\'' +
        '}';
  }
}
