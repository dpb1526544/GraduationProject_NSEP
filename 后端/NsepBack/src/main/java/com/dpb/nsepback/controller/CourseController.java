package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.config.RequireRole;
import com.dpb.nsepback.entity.Course;
import com.dpb.nsepback.mapper.CourseMapper;
import com.dpb.nsepback.mapper.StuCourseMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseMapper courseMapper;

    @Resource
    StuCourseMapper stuCourseMapper;

    //通过教师id获取课程列表
    @GetMapping("/getcourselist")
    public Result getlist(@RequestParam() Integer teacherid){
        List<Course> courseList= courseMapper.getlist(teacherid);
        return Result.success(courseList);
    }


    //根据课程id获取到课程名
    @GetMapping("/getcoursename")
    public Result getname(@RequestParam() String  courseid){
        String  coursename= courseMapper.getname(courseid);
        return Result.success(coursename);
    }

    // 删除课程
    @DeleteMapping("/delete")
    @RequireRole({2,3})
    public Result delete(@RequestParam() String  courseid) {
        int a=courseMapper.deleteByid(courseid);
        int b=stuCourseMapper.deleteByid(courseid);
        if(a==1&&b==1)
            return Result.success();
        else
            return Result.error();
    }

    // 添加课程
    @PostMapping("/save")
    @RequireRole({2,3})
    public Result save(@RequestParam() String  coursename,@RequestParam() String  teacherid) {
        int a=courseMapper.add(coursename,teacherid);
        if(a==1)
            return Result.success();
        else
            return Result.error();
    }
}
