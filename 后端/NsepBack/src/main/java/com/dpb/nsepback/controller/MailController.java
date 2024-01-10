package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.UserDTO;
import com.dpb.nsepback.entity.User;
import com.dpb.nsepback.service.IUserService;
import com.dpb.nsepback.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Mail")
public class MailController {
    @Resource
    private IUserService userService;
    //注册时验证邮箱
    @RequestMapping("/verifyMail")
    public Result verifyMail(@RequestParam("email") String email) {
        String code = "";
        //使用随机数生成六位正整数验证码
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        code = "" + num;
        try {
            MailUtil.sendMail(email, num);
            return Result.success(code);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(Constants.CODE_600, "服务器异常，发送失败");
        }
    }
    //找回密码时验证邮箱
    @RequestMapping("/verifyMailTwo")
    public Result verifyMailTwo(@RequestParam("username") String username) {
        String email = userService.getUserEmail(username);
        if(email==null){
            return Result.error(Constants.CODE_500, "未查询到该用户");
        }else{
            String code = "";
            //使用随机数生成六位正整数验证码
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            code = "" + num;
            try {
                MailUtil.sendMail(email, num);
                return Result.success(code);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(Constants.CODE_600, "服务器异常，发送失败");
            }
        }
    }
}
