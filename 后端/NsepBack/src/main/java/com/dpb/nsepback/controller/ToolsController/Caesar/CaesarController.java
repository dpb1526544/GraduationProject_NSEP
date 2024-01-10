package com.dpb.nsepback.controller.ToolsController.Caesar;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaesarController {
    @RequestMapping("/tools/Caesar/encrypt")
    public Result encrypt(@RequestParam("plain") String plain , @RequestParam("key") int key)
    {
        String cipher="";
        for(int i=0;i<plain.length();i++) {
            char c=plain.charAt(i);
            if(c>='a'&&c<='z')//如果字符串中的某个字符是小写字母
            {
                c+=key%26;//移动key%26位
                if(c<'a')
                    c+=26;//向左超界
                if(c>'z')
                    c-=26;//向右超界
            }else if(c>='A'&&c<='Z')//如果字符串中的某个字符是大写字母
            {
                c+=key%26;//移动key%26位
                if(c<'A')
                    c+=26;//向左超界
                if(c>'Z')
                    c-=26;//向右超界
            }
            cipher +=c;//将解密后的字符连成字符串
        }
        System.out.println(plain+"加密后为："+cipher);


        Result res=new Result(Constants.CODE_200,null,cipher);
        return res;
    }

    @RequestMapping("/tools/Caesar/decrypt")
    public Result decrypt(@RequestParam() String cipher , @RequestParam("key") int key)
    {
        int k=Integer.parseInt("-"+key);
        String plain="";
        for(int i=0;i<cipher.length();i++) {
            char c=cipher.charAt(i);
            if(c>='a'&&c<='z')//如果字符串中的某个字符是小写字母
            {
                c+=k%26;//移动key%26位
                if(c<'a')
                    c+=26;//向左超界
                if(c>'z')
                    c-=26;//向右超界
            }else if(c>='A'&&c<='Z')//如果字符串中的某个字符是大写字母
            {
                c+=k%26;//移动key%26位
                if(c<'A')
                    c+=26;//向左超界
                if(c>'Z')
                    c-=26;//向右超界
            }
            plain +=c;//将解密后的字符连成字符串
        }
        System.out.println(cipher+"解密后为："+plain);

        Result res=new Result(Constants.CODE_200,null,plain);
        return res;
    }
}
