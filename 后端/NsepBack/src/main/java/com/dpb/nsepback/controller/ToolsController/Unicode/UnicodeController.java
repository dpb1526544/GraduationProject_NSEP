package com.dpb.nsepback.controller.ToolsController.Unicode;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnicodeController {

    @RequestMapping("/tools/Unicode/encode")
    public @ResponseBody Result encode(@RequestParam("data") String data)
    {
        if(data==null||"".equals(data))
        {
            return Result.error();
        }
        StringBuffer unicode=new StringBuffer();
        for(int i=0;i<data.length();i++)
        {
            char c= data.charAt(i);
            String hex = Integer.toHexString(c);
            if(hex.length()<=2)
            {
                hex="00"+hex;
            }
            unicode.append("\\u"+hex);
            System.out.print(unicode);
        }
        Result res=new Result(Constants.CODE_200,null,unicode.toString());
        return res;
    }

    @RequestMapping("/tools/Unicode/decode")
    public @ResponseBody Result decode(@RequestParam("data") String data)
    {
        if(data==null||"".equals(data))
        {
            return Result.error();
        }
        StringBuffer sb=new StringBuffer();
        int i=-1;
        int pos=0;
        while ((i = data.indexOf("\\u", pos)) != -1) {
            sb.append(data.substring(pos, i));
            if (i + 5 < data.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(data.substring(i + 2, i + 6), 16));
            }
        }
        Result res=new Result(Constants.CODE_200,null,sb.toString());
        System.out.print(sb);
        return res;

    }
}
