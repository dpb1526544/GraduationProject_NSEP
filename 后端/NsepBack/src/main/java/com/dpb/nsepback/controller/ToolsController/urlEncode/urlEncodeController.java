package com.dpb.nsepback.controller.ToolsController.urlEncode;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class urlEncodeController {

    @RequestMapping("tools/URL/encode")
    public @ResponseBody
    Result encode(@RequestParam("data") String data) {
        System.out.println(data);
        String encodeData = null;
        encodeData = getURLEncoderString(data);
        System.out.println(encodeData);
        Result res = new Result(Constants.CODE_200, null, encodeData);
        return res;
    }

    @RequestMapping("tools/URL/decode")
    public @ResponseBody
    Result decode(@RequestParam("encodeData") String encodeData) {
        System.out.println(encodeData);
        String data = null;
        data = URLDecoderString(encodeData);
        Result res = new Result(Constants.CODE_200, null, data);
        return res;
    }

    //URL编码
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //URL解码
    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
