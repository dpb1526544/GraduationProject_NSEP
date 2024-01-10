package com.dpb.nsepback.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlagMapper {

    // 查找flag
    @Select("select flag from flag where username=#{username} and problemid=#{problemid}")
    String getFlag(String username,String problemid);

}
