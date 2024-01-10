package com.dpb.nsepback.controller.ToolsController.Rot13;

import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/Rot13")
public class Rot13 {

    // Rot13加密
    @GetMapping("/encrypto")
    public Result encrypto(@RequestParam() String Text) throws UnsupportedEncodingException {

        System.out.println("明文："+Text);

        Rot13 rot13=new Rot13();
        String encryStr = rot13.cipher(Text);
        System.out.println("加密后得到得字符串："+encryStr);
        return Result.success(encryStr);
    }

    // Rot13解密
    @GetMapping("/decrypto")
    public Result decrypto(@RequestParam() String Text) throws UnsupportedEncodingException {

        System.out.println("密文："+Text);

        Rot13 rot13=new Rot13();
        String decryStr = rot13.cipher(Text);
        System.out.println("解密后得到得字符串："+decryStr);
        return Result.success(decryStr);
    }


    private static final Map map;
    static {
        final char[] lookup1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

        final char[] lookup2 = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm".toCharArray();

        Map m = new HashMap();

        for (int i = 0; i < lookup1.length; i++) {

            m.put(lookup1[i], lookup2[i]);
        }
        map = Collections.unmodifiableMap(m);

    }

    private Rot13() {
    }

    /**
     * ROT-13 算法.
     *
     * @param inStr
     * @return
     */

    public static String cipher(final String inStr) {

        char[] arr = inStr.toCharArray();
        StringBuilder sb = new StringBuilder(arr.length);

        for (char c : arr) {
            Character out = (Character) map.get(c);
            if (out == null) {
                sb.append(c);

            } else {

                sb.append(out);

            }

        }
        return sb.toString();

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Rot13 rot13=new Rot13();
        String encryStr = rot13.cipher("nihao42134sadgsdsx");
        System.out.println("加密后得到得字符串："+encryStr);
        String decryStr = rot13.cipher(encryStr);
        System.out.println("解密后得到得字符串："+decryStr);
    }

}