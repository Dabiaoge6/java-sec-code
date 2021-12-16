package org.spring.demo;
import java.io.Serializable;

public class SecVerify implements Serializable {

  public static final long serialVersionUID = -1994551884802313999L;

  private String name;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
