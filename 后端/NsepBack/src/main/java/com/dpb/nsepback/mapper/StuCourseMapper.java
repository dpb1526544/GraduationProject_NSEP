package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.CourseInfo;
import com.dpb.nsepback.entity.StuCourse;
import com.dpb.nsepback.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface StuCourseMapper extends BaseMapper<StuCourse> {

    // 插入
    @Insert("insert ignore into stucourse (userid,courseid) values (#{userid},#{courseid})")
    int saveinfo(String  userid,String courseid);

    // 删除某门课
    @Delete("delete from stucourse where courseid = #{courseid}")
    int deleteByid(String courseid);

    // 删除学生的某门课
    @Delete("delete from stucourse where courseid = #{courseid} and userid=#{userid}")
    int del(String courseid,Integer userid);

    // 删除学生
    @Delete("delete from stucourse where userid=#{userid}")
    int deletee(Integer userid);

    // 分页查找
    @Select("select user.userid ,username,realname,email,finished from  stucourse, user where stucourse.userid =user.userid and" +
            " courseid =#{courseid} and username like \"%${search}%\" limit #{beginPage},#{pageSize}")
    List<User> getpage(Integer beginPage, Integer pageSize, String search, Integer courseid);

    // 计数
    @Select("select count(*) from  stucourse, user where stucourse.userid =user.userid and courseid =#{courseid} and username like \"%${search}%\"")
    Integer gettotalpage(String search,Integer courseid);
//    and username like "%${search}%"

    // 查找某个学生的所选课程
    @Select("select coursename,realname from" +
            " (select * from user where role=2) a,course where" +
            " courseid in (select courseid from stucourse where userid=#{userid}) and a.userid=course.teacherid")
    List<CourseInfo> getcourseinfo(Integer userid);

}
