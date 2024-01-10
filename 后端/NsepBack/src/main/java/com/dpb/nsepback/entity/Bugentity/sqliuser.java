package com.dpb.nsepback.entity.Bugentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sqliuser")
public class sqliuser {

    @TableId(type = IdType.AUTO)
    private String id;
    // 姓名
    private String name;
    // 密码
    private String password;
    // 权限
    private String level;

    // 构造
    public sqliuser(String a,String b,String c,String d){
        id=a; name=b;password=c;level=d;
    }

}
