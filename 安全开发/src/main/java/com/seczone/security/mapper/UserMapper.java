package com.seczone.security.mapper;

import com.seczone.security.dao.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from user where username = '${username}' and password = '${password}'")
    List<UserVo> loginByUsernameAndPass(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where username like '%${username}%' ")
    List<UserVo> searchByname(@Param("username") String username);

    //修改密码
    @Update("UPDATE user set password = '${password}' WHERE username='${username}'")
    int updateSecurity(@Param("username") String username,@Param("password") String password);

    //根据用户名查找用户

    @Select("select * from user where username = '${username}'")
    List<UserVo> seeUserByname(@Param("username") String username);

}
