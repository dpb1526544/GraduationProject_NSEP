package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    // 插入
    @Insert("insert into message (messagetitle,messagecontent) values (#{title},#{content})")
    int insert(String title,String content);

    // 广播
    @Insert("insert into usermessage (messageid,userid) " +
            "select  messageid,userid from message natural join user WHERE messageid=" +
            "(SELECT max(messageid) from message )")
    int broadcast();

    // 根据userid查询所有
    @Select("select messagetitle,messagecontent,date,state from usermessage,message where userid=#{userid} and usermessage.messageid=message.messageid")
    List<Message> selectbyid(String userid);

    // 查询新消息
    @Select("select count(*) from usermessage where userid=#{userid} and state=0")
    int checknew(String userid);

    // 设置消息状态
    @Update("update usermessage set state = 1 where userid = #{userid}")
    int updatestate(String userid);


}
