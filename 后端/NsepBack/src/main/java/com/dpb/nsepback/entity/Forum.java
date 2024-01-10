package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// writeup
@Data
@TableName("forum")
public class Forum {

    @TableId(type = IdType.AUTO)
    private Integer forumid;
    // 题目
    private String title;
    // 内容
    private String content;
    // 学号
    private String username;
    // 发表时间
    private String time;
    // 状态
    private Integer state;
}
