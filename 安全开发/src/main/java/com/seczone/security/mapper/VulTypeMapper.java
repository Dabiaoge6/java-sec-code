package com.seczone.security.mapper;

import com.seczone.security.dao.Http;
import com.seczone.security.dao.VulType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface VulTypeMapper {
    @Select("select * from vultype ")
    List<VulType> SearchVulType();

}
