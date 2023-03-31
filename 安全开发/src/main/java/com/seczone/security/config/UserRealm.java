package com.seczone.security.config;

import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// 自定义的 UserRealm;进行登陆验证和权限读取     继承 AuthorizingRealm
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权");

        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
//        info.addStringPermission("user:update");

//        拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        UserVo currentUser = (UserVo) subject.getPrincipal();

//        设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }


    //    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证");
        //用户名，密码~  从数据库中取
//        String name="root";
//        String password="12345";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实的数据库
        String password = new String(userToken.getPassword());
        List<UserVo> users=userMapper.loginByUsernameAndPass(userToken.getUsername(),password);
        if (users.size()==0){
            //没有这个人
            return null;
        }
        //在已登录用户的session上保存用户的信息
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",users.get(0));

        //密码认证，由shiro做~~~
        return new SimpleAuthenticationInfo(users.get(0),users.get(0).getPassword(),"userRealm");
    }
}