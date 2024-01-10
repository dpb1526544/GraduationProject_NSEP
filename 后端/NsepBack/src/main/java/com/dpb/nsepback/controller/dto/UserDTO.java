package com.dpb.nsepback.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private Integer userid;
    private String username;
    private String password;
    private String realname;
    private String email;
    private int role;
    private String token;
}