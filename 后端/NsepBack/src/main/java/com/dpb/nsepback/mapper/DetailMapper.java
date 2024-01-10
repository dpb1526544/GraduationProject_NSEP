package com.dpb.nsepback.mapper;

import com.dpb.nsepback.entity.Detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DetailMapper {

    // 根据课程id和学号查找某名同学的题目完成情况
    @Select("select username,realname, problemname,finishtime from stucourse NATURAL JOIN user NATURAL JOIN stuproblem NATURAL JOIN problem where courseid=#{courseid} and username=#{username}")
    List<Detail> sel(Integer courseid,String username);
}
