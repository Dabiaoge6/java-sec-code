package com.seczone.security.mapper;

import com.seczone.security.dao.Http;
import com.seczone.security.dao.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface HttpMapper {

    @Select("select * from http  WHERE ip='${ip}' and user_agent ='${agent}' ")
    List<Http> searchByIpAndAngent(@Param("ip") String ip, @Param("agent") String agent);

}
