package org.service.demo.service;

import org.dao.demo.bean.User;

import java.util.List;
public interface UserService {

    public boolean insert(String id, String name, String password, int age);
    
    public List<String> selectId(String name);

    public List<String> selectUser(String name, String pwd);

    public List<User> selectUsers(String name, String pwd);

    public List<String> fuzzyQueryId(String name);

    public List<String> fuzzyQueryIdPre(String name);

    public boolean updatePwd(String name, String newpwd, String oldpwd);
    
    public List<String> selectName();
    
    public boolean deleteUser(String userName);

    public boolean login(String name,String pwd);

}
