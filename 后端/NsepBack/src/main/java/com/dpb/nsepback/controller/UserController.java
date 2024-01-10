package com.dpb.nsepback.controller;

import cn.hutool.core.util.StrUtil;
import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.UserDTO;
import com.dpb.nsepback.controller.dto.UserPasswordDTO;
import com.dpb.nsepback.entity.Page;
import com.dpb.nsepback.entity.User;
import com.dpb.nsepback.mapper.UserMapper;
import com.dpb.nsepback.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    // 新增用户，注册
    @GetMapping("/save")
    public Result save(@RequestParam() String username, @RequestParam() String password,
                       @RequestParam() String realname, @RequestParam() String email, @RequestParam() String role){
        if (userMapper.selectbyun(username)==null){
            userMapper.insertuser(username,password,realname,email,role);
            return Result.success("success");
        }else{
            return Result.error(Constants.CODE_600,"用户已存在");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @GetMapping("/page")
    public Result findpage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5")  Integer pageSize,
                           @RequestParam(defaultValue = "")  String search){
        Integer beginPage=(pageNum-1)*pageSize;

        List<User> userPage=userMapper.getpage(beginPage,pageSize,search);
        Integer totalpage=userMapper.gettotalpage(search);

        System.out.println(totalpage);
        Page page=new Page(userPage,totalpage);
        return Result.success(page);
    }

    //根据id获取到姓名
    @GetMapping("/getname")
    public Result getname(@RequestParam() String  userid){
        String username=userMapper.getname(userid);
        return Result.success(username);
    }

    //登录后修改密码
    @PostMapping("/repw")
    public Result repw(@RequestBody UserPasswordDTO userPasswordDTO) {
        String username = userPasswordDTO.getUsername();
        String password = userPasswordDTO.getPassword();
        String newpassword = userPasswordDTO.getNewpassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) || StrUtil.isBlank(newpassword)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        userMapper.updatePassword(userPasswordDTO);
        return Result.success();
    }

    //邮箱重置密码
    @GetMapping("/resetpwd")
    public Result resetPwd(@RequestParam() String  username,@RequestParam() String password){
        userMapper.updatePasswordTwo(username,password);
        return Result.success();
    }

    @PostMapping("/reemail")
    public Result reemail(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)||StrUtil.isBlank(email)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        userMapper.updateEmail(userDTO);
        return Result.success();
    }

    @PostMapping("/selbyun")
    public Result selectbyname(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String email=userMapper.selectbyun(username);
        userDTO.setEmail(email);
        return Result.success(userDTO);
    }

    @GetMapping("/update")
    public Result updatebyname(@RequestParam() String username,@RequestParam() String password,
                               @RequestParam() String realname,@RequestParam() String email,@RequestParam() String role){
        userMapper.updateuser(username,password,realname,email,role);
        return Result.success("success");
    }

    @GetMapping("/delete")
    public Result deletebyname(@RequestParam() String username){
        userMapper.deleteeuser(username);
        return Result.success("success");
    }
}
