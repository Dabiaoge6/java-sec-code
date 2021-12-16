package com.seczone.security.mapper;

import com.seczone.security.dao.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from user where username = '${username}' and password = '${password}'")
    List<UserVo> loginByUsernameAndPass(@Param("username") String username,@Param("password") String password);



}
