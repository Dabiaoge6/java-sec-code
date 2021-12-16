package org.spring.demo.config;

import org.dao.demo.bean.User;
import org.dao.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserDao userDao;
  
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = this.userDao.findUserByName(name);
    if (user == null)
      throw new UsernameNotFoundException("用户名不存在！");
    return (UserDetails)user;
  }
}