package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class OpGetuserController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/op1/person_center/getusername")
    public @ResponseBody Result login(@CookieValue("username") String username) throws SQLException {
        System.out.println(username);
        Result res=new Result(Constants.CODE_200,null,username);
        return res;
    }
}
