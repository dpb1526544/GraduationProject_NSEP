package com.dpb.nsepback.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface XssstoreMapper {

    // 根据学号查询计数
    @Select("select count(*) from xssstore where username=#{username}")
    int sel(String username);

    // 新增
    @Insert("insert into xssstore (username,xsscontent) values (#{username},#{xsscontent})")
    int add(String username,String xsscontent);

    // 更新
    @Update("update xssstore set xsscontent=#{xsscontent} where username=#{username}")
    int refresh(String username,String xsscontent);

    // 根据学号查内容
    @Select("select xsscontent from xssstore where username=#{username}")
    String getContent(String username);
}
