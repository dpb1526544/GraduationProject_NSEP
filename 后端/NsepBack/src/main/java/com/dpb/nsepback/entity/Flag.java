package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("flag")
public class Flag {

    // 学生学号
    private String username;
    // 题目id
    private String problemid;
    // flag
    private String flag;
}
