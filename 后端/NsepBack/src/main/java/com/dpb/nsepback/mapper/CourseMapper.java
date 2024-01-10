package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    // 查找某位老师的课程列表
    @Select("select * from course where teacherid = #{teacherid}")
    List<Course> getlist(Integer teacherid);

    // 根据课程id查找课程名
    @Select("select coursename from course where courseid = #{courseid}")
    String getname(String courseid);

    // 根据课程id删除某课程
    @Delete("delete from course where courseid = #{courseid}")
    int deleteByid(String  courseid);

    // 新建课程
    @Insert("insert into course (coursename,teacherid) values (#{coursename},#{teacherid})")
    int add(String  coursename,String  teacherid);
}
