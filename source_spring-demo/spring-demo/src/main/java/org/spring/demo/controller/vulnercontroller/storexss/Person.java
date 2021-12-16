package org.spring.demo.controller.vulnercontroller.storexss;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
  private String id;
  
  private String name;
  
  private String idCard;
  
  private String phone;
  
  private Address address;
  
  public Person() {}
  
  public Person(String name, String idCard, String phone, Address address) {
    this.name = name;
    this.idCard = idCard;
    this.phone = phone;
    this.address = address;
  }
  
  @Id
  @Column(name = "id", nullable = false, unique = true)
  @GenericGenerator(name = "generator", strategy = "uuid")
  @GeneratedValue(generator = "generator")
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  @Column(name = "name", nullable = false, length = 32)
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Column(name = "idCard", nullable = false, length = 32)
  public String getIdCard() {
    return this.idCard;
  }
  
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }
  
  @Column(name = "phone", nullable = false, length = 32)
  public String getPhone() {
    return this.phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  @Embedded
  public Address getAddress() {
    return this.address;
  }
  
  public void setAddress(Address address) {
    this.address = address;
  }
  
  public String toString() {
    return "Person [id=" + this.id + ", name=" + this.name + ", idCard=" + this.idCard + ", phone=" + this.phone + ", address=" + this.address + "]";
  }
}
