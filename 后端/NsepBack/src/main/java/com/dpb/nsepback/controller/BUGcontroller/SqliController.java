package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.Bugentity.sqliuser;
import com.dpb.nsepback.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// SQL注入
@RestController
@RequestMapping("/sqli/")
public class SqliController {

    @Autowired
    DataSource dataSource;
    public String sql;
    public String error;
    public Object[] value = new Object[5];

    //数字型SQL注入漏洞(测试payload：-1 union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #)
    @RequestMapping("num")
    public Result sqli_num(@RequestParam(value = "id") String id,
                           @RequestParam(value = "username") String username,
                           Model model) {

        //防止用户恶意修改其他数据
        String begin = id.toLowerCase();

        //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
        //不想被包含的表名（确保需要被利用的表名中无这些关键字）
        String pattern = "|user|flag|delete|update|insert";

        Pattern r = Pattern.compile(pattern);
        Matcher isMatch = r.matcher(begin);
        if (!isMatch.find()) {

            //危险请求参数
            Result res = new Result(Constants.CODE_200, null, "涉及到系统安全问题，此SQL禁止执行");
            return res;
        }

        String problemid = "pro004";
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
            return Result.error(e.toString());
        }

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            sql = "select * from usersqli where id = " + id;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            sqliuser[] s = new sqliuser[row];

            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果

        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
    }


    //字符型SQL注入漏洞（测试payload：-1' union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #）
    @RequestMapping("char")
    public Result sqli_char(@RequestParam(value = "id") String id,
                            @RequestParam(value = "username") String username,
                            Model model) {

        //防止用户恶意修改其他数据
        String begin = id.toLowerCase();

        //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
        //不想被包含的表名（确保需要被利用的表名中无这些关键字）
        String pattern = "|user|flag|delete|update|insert";
        //-1' union select 1,group_concat(fl4g),3,4 from `sqlifl4g` #
        Pattern r = Pattern.compile(pattern);
        Matcher isMatch = r.matcher(begin);
        if (!isMatch.find()) {

            //危险请求参数
            Result res = new Result(Constants.CODE_200, null, "涉及到系统安全问题，此SQL禁止执行");
            return res;
        }

        String problemid = "pro003";
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
            return Result.error(e.toString());
        }


        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            sql = "select * from sqliuser where id = '" + id + "'";

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            sqliuser[] s = new sqliuser[row];
            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果

        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }

    }

    //括号+单引号SQL注入漏洞(测试payload：-1') union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #)
    @RequestMapping("brackets")
    public Result sqli_brackets(@RequestParam(value = "id") String id,
                                @RequestParam(value = "username") String username,
                                Model model) {

        //防止用户恶意修改其他数据
        String begin = id.toLowerCase();

        //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
        //不想被包含的表名（确保需要被利用的表名中无这些关键字）
        String pattern = "|user|flag|delete|update|insert";

        Pattern r = Pattern.compile(pattern);
        Matcher isMatch = r.matcher(begin);
        if (!isMatch.find()) {
            //危险请求参数
            Result res = new Result(Constants.CODE_200, null, "涉及到系统安全问题，此SQL禁止执行");
            return res;
        }

        String problemid = "pro002";
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
            return Result.error(e.toString());
        }

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            sql = "select * from sqliuser where id = ('" + id + "')";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            sqliuser[] s = new sqliuser[row];
            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
    }


    @RequestMapping("load")
    public Result loaddata(Model model) {
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            sql = "select * from sqliuser";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            sqliuser[] s = new sqliuser[row];
            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
    }


    //括号+单引号SQL注入漏洞(测试payload：1&' union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #)
    @RequestMapping("search")
    public Result sqli_search(@RequestParam(value = "id", required = false) String id, @RequestParam() String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        sql = "select * from sqliuser where id like '%" + id + "%'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);

        // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
        rs.last();
        sqliuser[] s = new sqliuser[rs.getRow()];
        System.out.println(rs.getRow());


        rs.beforeFirst();
        int i = 0;
        while (rs.next()) {

            //获取列对应的值。
            String a = rs.getString("id");
            //获取列对应的值。
            String b = rs.getString("name");
            //获取列对应的值。
            String c = rs.getString("level");

            s[i] = new sqliuser(a, b, "", c);
            i++;
        }
        for (int j = 0; j < value.length; j++) {
            if (value[j] != null) {
                System.out.println(value[j]);
                String c = "id" + j;
                System.out.println(c);
                value[j] = null;
            }
        }
        rs.close();
        connection.close();
        return Result.success(s);//返回结果
    }

    public static String TransactSQLInjection(String str) {

        return str.replaceAll(".*([';]+|(--)+).*", " ");

    }


    @RequestMapping("delete")
    public String sqli_delete(@RequestParam(value = "messahe", required = false) String messahe, Model model) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        sql = "insert into messages(id,message) value(null,'" + messahe + "')";
        System.out.println(sql);
        stmt.executeUpdate(sql);

        sql = "select id from messages where message = '" + messahe + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Object value = rs.getObject("id");//获取列对应的值。

            System.out.println(value);
            model.addAttribute("message", messahe);
            model.addAttribute("id", value);
        }
        rs.close();

        connection.close();
        System.out.println("model:" + model);
        return "sqli_delete";
    }

    @RequestMapping("delete1")
    public String sqli_delete1(@RequestParam(value = "messahe", required = false) String messahe, Model model) {

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            sql = "delete from messages where message='" + messahe + "'";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            connection.close();
            System.out.println("model:" + model);
        } catch (SQLException ex) {
            System.out.println(ex);
            error = ex.toString();
            model.addAttribute("error", error);
            return "sqli_delete1";

        }

        return "sqli_delete";
    }

    //括号+单引号+不安全的过滤SQL注入漏洞(测试payload：-1') union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #)
    @RequestMapping("unsafe")
    public Result sqli_unsafe(@RequestParam(value = "id") String id,
                              @RequestParam(value = "username") String username,
                              Model model) {
        //防止用户恶意修改其他数据
        String begin = id.toLowerCase();

        //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
        String pattern = "|user|flag|delete|update|insert";  //不想被包含的表名（确保需要被利用的表名中无这些关键字）

        Pattern r = Pattern.compile(pattern);
        Matcher isMatch = r.matcher(begin);
        if (!isMatch.find()) {
            //危险请求参数
            Result res = new Result(Constants.CODE_200, null, "涉及到系统安全问题，此SQL禁止执行");
            return res;
        }

        String problemid = "pro002";
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

        try {
            id = id.replace("select", "");
            id = id.replace("delete", "");
            id = id.replace("update", "");
            id = id.replace("union", "");
            id = id.replace("concat", "");
            id = id.replace("from", "");

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            sql = "select * from sqliuser where id = ('" + id + "')";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            sqliuser[] s = new sqliuser[row];
            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    //括号+单引号+不安全的过滤SQL注入漏洞(测试payload：-1') union select group_concat(username),group_concat(problemid),3,group_concat(fl4g) from `sqlifl4g` #)
    @RequestMapping("unsafe2")
    public Result sqli_unsafe2(@RequestParam(value = "id") String id,
                               @RequestParam(value = "username") String username,
                               Model model) {
        //防止用户恶意修改其他数据
        String begin = id.toLowerCase();

        //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
        String pattern = "|user|flag|delete|update|insert";  //不想被包含的表名（确保需要被利用的表名中无这些关键字）

        Pattern r = Pattern.compile(pattern);
        Matcher isMatch = r.matcher(begin);
        if (!isMatch.find()) {
            //危险请求参数
            Result res = new Result(Constants.CODE_200, null, "涉及到系统安全问题，此SQL禁止执行");
            return res;
        }

        String problemid = "pro002";
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
            //return "输入内容错误，数据库查询错误";
        }

        try {
            //对id进行过滤
            id = id.replace("select", "");
            id = id.replace("delete", "");
            id = id.replace("update", "");
            id = id.replace("union", "");
            id = id.replace("concat", "");
            id = id.replace("from", "");

            //防止用户恶意修改其他数据
            //可以通过配置文件，去配置这些特殊字符，以便随时添加一些关键字。
            String patternsqli = "|select|union|user|flag|delete|update|insert";  //不想被包含的表名（确保需要被利用的表名中无这些关键字）

            Pattern rsqli = Pattern.compile(patternsqli);
            Matcher isMatchsqli = rsqli.matcher(begin);
            if (!isMatchsqli.find()) {
                //危险请求参数
                Result res = new Result(Constants.CODE_200, null, "bad hacker");
                return res;
            }

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            sql = "select * from sqliuser where id = ('" + id + "')";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            sqliuser[] s = new sqliuser[row];
            int count = 0;
            rs.next();

            for (int i = 0; i < row; i++) {
                String resultName = rs.getString("name");
                String resultID = rs.getString("id");
                String resultLevel = rs.getString("level");

                s[i] = new sqliuser(resultID, resultName, "", resultLevel);
                rs.next();
            }
            rs.close();
            connection.close();

            return Result.success(s);//返回结果
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}
