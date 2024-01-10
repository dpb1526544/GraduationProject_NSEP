package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.OPDTO;
import com.dpb.nsepback.entity.FlagMake;
import org.apache.tomcat.jni.Address;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;

@RestController
public class OpGetInfoController {

    @Autowired
    DataSource dataSource;
    public String sql;
    public String sql1;

    @RequestMapping("/op1/person_center/information")
    public @ResponseBody
    Result login(@CookieValue("username") String username,
                 @RequestParam("username1") String username1) throws SQLException {
        String flag = null;
        String op_flag;
        String problemid = "pro024";
        if (username.equals("lucy")) {
            try {
                Connection connection1 = dataSource.getConnection();
                Statement stmt1 = connection1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                //使用预编译防止SQL注入
                String sql1 = "select * from opuser where username= ? and username1= ?";
                PreparedStatement st1 = connection1.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                st1.setString(1, username);
                st1.setString(2, username1);

                System.out.println(sql);
                ResultSet rs1 = st1.executeQuery();

                rs1.last();
                int row = rs1.getRow();

                rs1.beforeFirst();
                System.out.println("查找到行数为" + row);


                if (row == 0) {
                    FlagMake flagMake = new FlagMake();
                    flag = flagMake.getFlag();
                    System.out.println("生成flag:" + flag);

                    sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username +
                            "', '" + problemid + "', '" + flag + "');";
                    System.out.println(sql);

                    op_flag = "insert into opuser (`username`, `password`, `phone`,`add`,`username1`) " +
                            "values ('" + username +
                            "', '" + "hacker" + "', '" + flag + "','" + "hacker" + "','" + username1 + "');";
                    System.out.println(op_flag);
                    Statement stmt = connection1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                }

                if (row != 0 && row != 1) {
                    Result res = new Result(Constants.CODE_200, null, "用户错误");
                    return res;
                }

                String[] result = new String[3];
                String result1 = "";
                int count = 0;
                rs1.next();


                result[0] = "lucy";
                result[1] = "flag{8goUC8Q3j7ZKbPtIUr2H2fBes94wWy}";
                result[2] = "hacker";

                result1 = result[0] + "," + result[1] + "," + result[2];

                rs1.close();
                connection1.close();
                System.out.println("result:" + result1);

                OPDTO opdto = new OPDTO(result[0], result[1], result[2]);


                Result res1 = new Result(Constants.CODE_200, null, opdto);
                return res1;

            } catch (SQLException e) {
                e.printStackTrace();

                Result res = new Result(Constants.CODE_200, null, "result:" + e.toString());
                return res;

            }
        }
        try {
            System.out.println(username);

            //验证cookie
            if (username.equals(null)) {
                Result res = new Result(Constants.CODE_200, null, "请先登录！");
                return res;
            }
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //使用预编译防止SQL注入
            String sql = "select * from opuser where username= ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            if (row != 1) {
                Result res = new Result(Constants.CODE_200, null, "用户错误");
                return res;
            }
            String[] result = new String[3];
            String result1 = "";
            int count = 0;
            rs.next();

            result[0] = rs.getString("username");
            result[1] = rs.getString("phone");
            result[2] = rs.getString("add");
            result1 = result[0] + "," + result[1] + "," + result[2];

            rs.close();
            connection.close();
            System.out.println("result:" + result1);

            OPDTO opdto = new OPDTO(result[0], result[1], result[2]);

            Result res = new Result(Constants.CODE_200, null, opdto);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();

            Result res = new Result(Constants.CODE_200, null, "result:" + e.toString());
            return res;
        }
    }

    class Param {
        public User user;
        public Address address;
    }
}
