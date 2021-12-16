package org.spring.demo.controller.vulnercontroller.storexss;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Address implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String city;
  
  @Column(nullable = false)
  private String province;
  
  @Transient
  private String country;
  
  public Address() {}
  
  public Address(String city, String province, String country) {
    this.city = city;
    this.province = province;
    this.country = country;
  }
  
  public String getCity() {
    return this.city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCountry() {
    return this.country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getProvince() {
    return this.province;
  }
  
  public void setProvince(String province) {
    this.province = province;
  }
}