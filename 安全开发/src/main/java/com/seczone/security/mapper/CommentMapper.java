package com.seczone.security.mapper;

import com.seczone.security.dao.Comment;
import com.seczone.security.dao.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment limit 6")
    List<Comment> SearchAllComment();

    int InsertComment(Comment comment);
}
