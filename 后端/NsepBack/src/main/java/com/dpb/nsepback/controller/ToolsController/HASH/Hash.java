package com.dpb.nsepback.controller.ToolsController.HASH;

import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/HASH")
public class Hash {

    // SHA256加密
    @GetMapping("/SHA256")
    public Result en_bysha256(@RequestParam() String Text) {

        myMd5 md5=new myMd5();
        System.out.println(md5.getMD5(Text));
        return Result.success(md5.getMD5(Text));
    }

    // SHA512加密
    @GetMapping("/SHA512")
    public Result en_bysha512(@RequestParam() String Text) {

        SHA512 sha=new SHA512();
        System.out.println("SHA512加密== " + sha.SHA512(Text));
        return Result.success(sha.SHA512(Text));
    }

    // MD5加密
    @GetMapping("/MD5")
    public Result en_bymd5(@RequestParam() String Text) {

        SHA256 sha256=new SHA256();
        String ans = sha256.getSha256Str(Text);
        System.out.println(ans);
        return Result.success(ans);

    }

    public static void main(String[] args)  {
        myMd5 md5=new myMd5();
        System.out.println(md5.getMD5("2133"));

        SHA256 sha256=new SHA256();
        String ans = sha256.getSha256Str("123456");
        System.out.println(ans);
    }
}
