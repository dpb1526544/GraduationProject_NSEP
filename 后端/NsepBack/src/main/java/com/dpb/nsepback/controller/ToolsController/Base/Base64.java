package com.dpb.nsepback.controller.ToolsController.Base;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

@RestController
public class Base64 {
    @RequestMapping("tools/Base64/encode")
    public @ResponseBody
    Result encode(@RequestParam("data") String data){
        System.out.println(data);
        String encodeData = null;
        encodeData = setEncryptionBase64(data);
        System.out.println(encodeData);
        Result res=new Result(Constants.CODE_200,null,encodeData);
        return res;
    }

    @RequestMapping("tools/Base64/decode")
    public @ResponseBody
    Result decode(@RequestParam("data") String data){
        String decodeData = null;
        decodeData = getDecodeBase64(data);
        System.out.println(decodeData);
        Result res=new Result(Constants.CODE_200,null,decodeData);
        return res;
    }

    //文件转字符串
    public static String file2String(File file){
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String temp;
            while((temp = buffer.readLine()) !=null ){
                sb.append(temp);

            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    //加密
    public static String setEncryptionBase64(String str){
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if( b != null){
            s = new BASE64Encoder().encode(b);
        }
        return s;

    }

    //解密
    public static String getDecodeBase64(String str){
        byte[] b = null;
        String result = null;
        if(str != null){
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
