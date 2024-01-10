package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.Forum;
import com.dpb.nsepback.entity.Page;
import com.dpb.nsepback.mapper.ForumMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Resource
    ForumMapper forumMapper;

    // 分页
    @GetMapping("/page")
    public Result findpage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5")  Integer pageSize,
                           @RequestParam(defaultValue = "")  String search){
        Integer beginPage=(pageNum-1)*pageSize;
        List<Forum> forumPage=forumMapper.getpage(beginPage,pageSize,search,1);
        Integer totalpage=forumMapper.gettotalpage(search,1);

        System.out.println(totalpage);
        com.dpb.nsepback.entity.Page page=new Page(forumPage,totalpage);
        return Result.success(page);
    }

    // 新增
    @PostMapping("/save")
    public Result save(@RequestBody Forum forum){
        String title=forum.getTitle();
        String content=forum.getContent();
        String username=forum.getUsername();
        if(title!=null&&content!=null){
            forumMapper.insert(title,content,username);
            return Result.success("发表成功！");
        }
        return Result.error(Constants.CODE_600, "参数错误");
    }

    // 根据username select
    @GetMapping("/mywriteup")
    public Result selectbyname(@RequestParam String username){
        List<Forum> forumList=forumMapper.selectbyname(username,1);
        return Result.success(forumList);
    }

    // 删除
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer forumid){
        int a=forumMapper.delbyid(forumid);
        if(a==1)
            return Result.success();
        else
            return Result.error();
    }
}
