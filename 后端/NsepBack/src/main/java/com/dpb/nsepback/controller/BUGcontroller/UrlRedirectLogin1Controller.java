package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.FlagMake;
import org.apache.tomcat.jni.Address;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;

@RestController
public class UrlRedirectLogin1Controller {

    @Autowired
    DataSource dataSource;
    public String sql;

    //接收任何的用户名密码均正确，返回用户对应的flag
    @RequestMapping("/url/login1")
    public @ResponseBody
    Result login(@RequestParam(value = "username") String username,
                 @RequestParam(value = "password") String password,
                 @RequestParam(value = "username1") String username1
    ) throws SQLException {
        System.out.println(username1);

        String problemid = "pro010";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();
            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username1);
            st.setString(2, problemid);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            if (row == 1) {
                rs.next();
                flag = rs.getString("flag");
                System.out.println("flag已存在:" + flag);
            } else {
                FlagMake flagMake = new FlagMake();
                flag = flagMake.getFlag();
                System.out.println("生成flag:" + flag);
                //将flag MD5加密后存入数据库
                flagMD5 = MD5.getMD5String(flag);
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username1 +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);

            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
        }

        Result res = new Result(Constants.CODE_200, null, flag);
        return res;

    }

    class Param {
        public User user;
        public Address address;
    }
}
