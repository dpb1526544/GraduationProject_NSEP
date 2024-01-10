package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stucourse")
public class StuCourse {

    @TableId(type = IdType.AUTO)
    private Integer courseid;
    private Integer userid;
}
