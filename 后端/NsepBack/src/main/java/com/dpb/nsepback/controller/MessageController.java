package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.Message;
import com.dpb.nsepback.mapper.MessageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    MessageMapper messageMapper;

    // 查找
    @GetMapping("/search")
    public Result search(@RequestParam() String userid){
        System.out.println(userid);
        //查出这个用户收到的全部通知
        List<Message> m=messageMapper.selectbyid(userid);
        return Result.success(m);
    }

    // 发布通知
    @GetMapping("/save")
    public Result save(@RequestParam() String title,@RequestParam() String content){
        //发布通知
        if(title!=null&&content!=null){
            messageMapper.insert(title,content);
            messageMapper.broadcast();
            return Result.success("发布成功！");
        }
        return Result.error(Constants.CODE_600, "参数错误");
    }

    @GetMapping("/checknew")
    public Result checknew(@RequestParam() String userid){
        //检查有无新通知
        return Result.success(messageMapper.checknew(userid));
    }

    @GetMapping("/read")
    public Result read(@RequestParam() String userid){
        //更新已读状态
        return Result.success(messageMapper.updatestate(userid));
    }


}
