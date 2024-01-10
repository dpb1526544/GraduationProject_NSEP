package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 通知
@Data
@TableName("message")
public class Message {

    @TableId(type = IdType.AUTO)
    private Integer messageid;
    // 题目
    private String messagetitle;
    // 内容
    private String messagecontent;
    // 时间
    private String date;
}
