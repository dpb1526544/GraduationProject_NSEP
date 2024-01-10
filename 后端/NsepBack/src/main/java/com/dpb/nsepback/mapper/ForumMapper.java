package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.Forum;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface ForumMapper extends BaseMapper<Forum> {

    // 发布
    @Insert("insert into forum (title,content,username) values (#{title},#{content},#{username})")
    int insert(String title,String content,String username);

    // 根据学号查询
    @Select("select * from forum where username=#{username} and state=#{state}")
    List<Forum> selectbyname(String username, int state);

    // 分页显示
    @Select("select * from  forum where state=#{state} and content like \"%${search}%\" or title like \"%${search}%\" ORDER BY time DESC limit #{beginPage},#{pageSize}")
    List<Forum> getpage(Integer beginPage, Integer pageSize, String search, int state);

    // 查找总个数
    @Select("select count(*) from  forum where state=#{state} and content like \"%${search}%\" ORDER BY time DESC")
    Integer gettotalpage(String search, int state);

    // 删除
    @Delete("delete from forum where forumid=#{forumid}")
    int delbyid(Integer forumid);
}
