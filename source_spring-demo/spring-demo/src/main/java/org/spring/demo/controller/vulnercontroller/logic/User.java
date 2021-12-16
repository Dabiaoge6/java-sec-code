package org.spring.demo.controller.vulnercontroller.logic;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
public class User implements Serializable, UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "username")
  private String username;
  
  @Column(name = "password")
  private String password;
  
  @Column(name = "tel")
  private String tel;
  
  @Column
  private String role;
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setTel(String tel) {
    this.tel = tel;
  }
  
  public void setRole(String role) {
    this.role = role;
  }
  
  public String toString() {
    return "User(id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + ", tel=" + getTel() + ", role=" + getRole() + ")";
  }
  
  public Long getId() {
    return this.id;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getTel() {
    return this.tel;
  }
  
  public String getRole() {
    return this.role;
  }
  
  public boolean isAccountNonExpired() {
    return true;
  }
  
  public boolean isAccountNonLocked() {
    return true;
  }
  
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  public boolean isEnabled() {
    return true;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    String[] roles = getRole().split(",");
    for (String role : roles)
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); 
    return (Collection)authorities;
  }
}
