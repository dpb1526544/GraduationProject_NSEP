package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.FlagMake;
import com.dpb.nsepback.mapper.FlagMapper;
import com.dpb.nsepback.mapper.XssstoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

@RestController
@RequestMapping("/xss")
public class XssController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @Resource
    XssstoreMapper xssstoreMapper;

    @Resource
    FlagMapper flagMapper;

    @GetMapping("/reflect")
    public @ResponseBody
    Result reflect(@RequestParam(value = "xss") String xss,
                   @RequestParam(value = "username") String username) {
        System.out.println(xss + ":ok1");

        String problemid = "pro012";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
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
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);

                //将flag存入数据库(插入)，提供给用户使用SQL注入获得flag
                sql_flag = "insert into sqlifl4g (`username`, `problemid`, `fl4g`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql_flag);

            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
        }
        if (xss.equals("<script>alert(" + username + ")</script>")) {
            return Result.success("<script>alert(\"" + "弹窗正确！flag为：" + flag + "\")</script>");
        } else
            return Result.error();
    }

    @GetMapping("/stored/store")
    public @ResponseBody
    Result store(@RequestParam(value = "xss") String xss,
                 @RequestParam(value = "username") String username) {

        if (xssstoreMapper.sel(username) == 0) {
            xssstoreMapper.add(username, xss);
        } else {
            xssstoreMapper.refresh(username, xss);
        }

        String problemid = "pro011";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
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
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);
                //将flag存入数据库(插入)，提供给用户使用SQL注入获得flag
                sql_flag = "insert into sqlifl4g (`username`, `problemid`, `fl4g`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql_flag);

            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
        }

        return Result.success();
    }

    @GetMapping("/stored/show")
    public @ResponseBody
    Result show(@RequestParam(value = "username") String username) {

        String problemid = "pro011";
        String xss = xssstoreMapper.getContent(username);
        String flag = flagMapper.getFlag(username, problemid);
        xss = xss.toLowerCase();
        if (xss.equals("<script>console.log(\"" + username + "\")</script>"))
            return Result.success(xss, "flag为：" + flag);
        else
            return Result.error(Constants.CODE_500, xss);
    }

    @RequestMapping("/safe")
    @ResponseBody
    public static String safe(String xss) {
        return encode(xss);
    }

    private static String encode(String origin) {
        origin = StringUtils.replace(origin, "&", "&amp;");
        origin = StringUtils.replace(origin, "<", "&lt;");
        origin = StringUtils.replace(origin, ">", "&gt;");
        origin = StringUtils.replace(origin, "\"", "&quot;");
        origin = StringUtils.replace(origin, "'", "&#x27;");
        origin = StringUtils.replace(origin, "/", "&#x2F;");
        return origin;
    }
}
