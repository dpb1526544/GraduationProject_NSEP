package com.dpb.nsepback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userid;
    // 学工号
    private String username;
    // 密码
    private String password;
    // 姓名
    private String realname;
    // 邮箱
    private String email;
    // 角色
    private Integer role;
    // 完成题目数
    private Integer finished;

}
