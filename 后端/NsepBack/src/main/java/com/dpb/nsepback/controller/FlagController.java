package com.dpb.nsepback.controller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.BUGcontroller.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RestController
public class FlagController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/FlagConfirm")
    public Result java_derser_start(@RequestParam(value = "username",required = false) String username
                                    ,@RequestParam(value = "problemid",required = false) String problemid
                                    ,@RequestParam(value = "flag",required = false) String flag){
        String result = null;
        String flagMD5 = null;
        System.out.println(username);
        System.out.println(problemid);
        System.out.println(flag);
        flagMD5 = MD5.getMD5String(flag);
        System.out.println(flagMD5);

        if(problemid.equals("pro008")){
            username="public";
        }
        if(problemid.equals("pro013")){
            username="xxe";
        }
        if(problemid.equals("pro024")){
            username="op";
        }

        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select * from flag where username = ? and problemid = ? and flag = ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            st.setString(3, flag);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为"+row);
            if(row==1){
                System.out.println("flag验证成功"+ flag);
                result = "true";
            }
            else {
                System.out.println("flag验证失败"+ flag);
                result = "false";
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
            result = "输入内容错误，数据库查询错误";
        }

        Result res=new Result(Constants.CODE_200,null,result);
        return res;
    }



}
