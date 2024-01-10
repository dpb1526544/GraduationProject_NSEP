package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stuproblem")
public class StuProblem {

    @TableId(type = IdType.AUTO)
    private Integer userid;
    private String problemid;
    private String finishtime;
}
