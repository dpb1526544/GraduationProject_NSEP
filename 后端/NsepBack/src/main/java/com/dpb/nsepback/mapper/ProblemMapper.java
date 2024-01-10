package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {

    // 根据id查询
    @Select("select * from problem where problemid = #{problemid}")
    Problem SearchProbleminfo_byid(String problemid);

    // 根据类型查询
    @Select("select * from problem where kind = #{kind}")
    List<Problem> SearchProbleminfo_bytype(String kind);
}
