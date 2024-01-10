package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.StuProblem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface StuProblemMapper extends BaseMapper<StuProblem> {

    // 插入
    @Insert("insert into stuproblem (userid,problemid) values (#{userid},#{problemid})")
    int insertinfo(Integer userid,String problemid);

    // 查找
    @Select("select * from stuproblem where userid= #{userid} and problemid= #{problemid}")
    Object selectinfo(Integer userid,String problemid);

    // 删除
    @Delete("delete from stuproblem where userid= #{userid}")
    int deletee(Integer userid);
}
