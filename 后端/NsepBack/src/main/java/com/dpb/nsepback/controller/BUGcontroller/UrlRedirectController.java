package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UrlRedirectController {
    @RequestMapping("/url_redirect")
    public @ResponseBody Result
    sendRedirect(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(url+":ok");
        Result res=new Result(Constants.CODE_200,null,url);
        return res;
    }
}
