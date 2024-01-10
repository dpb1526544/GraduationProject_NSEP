package com.dpb.nsepback.entity;

import lombok.Data;

// 学生做题情况
@Data
public class Detail {

    // 学生学号
    private String username;
    // 学生姓名
    private String realname;
    // 题目
    private String problemname;
    // 完成时间
    private String finishtime;
}
