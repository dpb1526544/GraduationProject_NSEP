package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Course {

    @TableId(type = IdType.AUTO)
    private Integer courseid;
    // 课程名
    private String coursename;
    // 老师id
    private String teacherid;
    // 创建时间
    private String createtime;

}
