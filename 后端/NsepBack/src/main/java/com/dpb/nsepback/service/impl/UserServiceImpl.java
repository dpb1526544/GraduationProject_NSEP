package com.dpb.nsepback.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.controller.dto.UserDTO;
import com.dpb.nsepback.entity.User;
import com.dpb.nsepback.exception.ServiceException;
import com.dpb.nsepback.mapper.UserMapper;
import com.dpb.nsepback.service.IUserService;
import com.dpb.nsepback.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Autowired
    public UserMapper userMapper;

    // 登录
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            if(one.getPassword().equals(userDTO.getPassword())){
                BeanUtil.copyProperties(one, userDTO, true);
                //设置token
                String token = TokenUtils.genToken(one.getUserid().toString(), one.getPassword().toString());
                userDTO.setToken(token);
                return userDTO;
            }else{
                throw new ServiceException(Constants.CODE_600, "密码错误，请重新输入");
            }
        } else {
            throw new ServiceException(Constants.CODE_600, "未查询到该用户，请检查用户名是否输入正确");
        }
    }

    // 注册
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            // 默认学生
            one.setRole(1);
            // 把 copy完之后的用户对象存储到数据库
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    //根据用户名获取邮箱
    public String getUserEmail(String username) {
        String email;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user;
        try {
            // 从数据库查询用户信息
            user = getOne(queryWrapper);
            if(user == null){
                email = null;
            }else{
                email = user.getEmail();
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return email;
    }

    // 获取用户信息
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        User one;
        try {
            // 从数据库查询用户信息
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

}
