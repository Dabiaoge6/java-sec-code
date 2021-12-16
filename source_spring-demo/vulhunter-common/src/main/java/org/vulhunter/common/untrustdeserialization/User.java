package org.vulhunter.common.untrustdeserialization;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

@XStreamAlias("user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String mobile;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", mobile='" + mobile + '\'' +
        '}';
  }
}
