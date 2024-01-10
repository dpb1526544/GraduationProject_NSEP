package com.dpb.nsepback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpb.nsepback.controller.dto.UserDTO;
import com.dpb.nsepback.controller.dto.UserPasswordDTO;
import com.dpb.nsepback.entity.User;
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
public interface UserMapper extends BaseMapper<User>  {

    // 更新密码，登录后
    @Update("update user set password = #{newpassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);
    // 更新密码，邮箱重置
    @Update("update user set password = #{password} where username = #{username}")
    int updatePasswordTwo(String username,String password);

    // 更新邮箱
    @Update("update user set email = #{email} where username = #{username} and password = #{password}")
    int updateEmail(UserDTO userDTO);

    // 分页查找
    @Select("select username,realname,email,password,role from  user where username like \"%${search}%\" limit #{beginPage},#{pageSize}")
    List<User> getpage(Integer beginPage, Integer pageSize, String search);

    // 查询总数
    @Select("select count(*) from user where username like \"%${search}%\"")
    Integer gettotalpage(String search);

    // 根据id查姓名
    @Select("select realname from user where userid = #{userid}")
    String getname(String userid);

    // 根据学号查邮箱
    @Select("select email from user where username=#{username}")
    String selectbyun(String username);

    // 更新靶场完成数
    @Update("update user set finished = finished+1 where userid = #{userid}")
    int updateFinished(Integer userid);

    // 根据学号查询
    @Select("select userid from user where username=#{username}")
    Integer seleteIdByName(String username);

    // 插入
    @Update("insert into user (username,password,realname,email,role) values (#{username},#{password},#{realname},#{email},#{role})")
    Integer insertuser(String username,String password, String realname,String email,String role);

    // 删除
    @Update("DELETE FROM user WHERE username = #{username}")
    Integer deleteeuser(String username);

    // 更新
    @Update("UPDATE user set password =#{password}, realname=#{realname},email=#{email},role=#{role} where username =#{username}")
    Integer updateuser(String username,String password, String realname,String email,String role);

}
