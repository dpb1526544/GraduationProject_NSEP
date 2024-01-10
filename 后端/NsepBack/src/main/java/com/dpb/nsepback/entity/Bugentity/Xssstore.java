package com.dpb.nsepback.entity.Bugentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("xssstore")
public class Xssstore {

    @TableId(type = IdType.AUTO)
    private String username;
    // xss的payload
    private String xsscontent;

}
