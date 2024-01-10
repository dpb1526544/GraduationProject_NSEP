package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.Problem;
import com.dpb.nsepback.mapper.ProblemMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Resource
    ProblemMapper problemMapper;

    // 根据id获取
    @GetMapping("/getinfobyid")
    public Result getinfobyid(@RequestParam() String problemid){
        Problem a= problemMapper.SearchProbleminfo_byid(problemid);
        return Result.success(a);
    }

    // 根据类型获取
    @GetMapping("/getinfobytype")
    public Result getinfobytype(@RequestParam() String problemtype){
        System.out.println(problemtype);
        List<Problem> a= problemMapper.SearchProbleminfo_bytype(problemtype);
        return Result.success(a);
    }
}
