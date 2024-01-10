package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 题目
@Data
@TableName("problem")
public class Problem {

    @TableId(type = IdType.AUTO)
    private String problemid;
    // 题目名称
    private String problemname;
    // 题目内容
    private String problemcontent;
    // 链接
    private String url;
    // 难度
    private String problemratio;

    private String flag;
}
