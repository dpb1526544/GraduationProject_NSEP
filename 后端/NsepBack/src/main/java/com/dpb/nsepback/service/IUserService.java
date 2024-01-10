package com.dpb.nsepback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dpb.nsepback.controller.dto.UserDTO;
import com.dpb.nsepback.entity.User;

public interface IUserService extends IService<User> {

    // 登录
    UserDTO login(UserDTO userDTO);

    // 注册
    User register(UserDTO userDTO);

    String getUserEmail(String username);
}
