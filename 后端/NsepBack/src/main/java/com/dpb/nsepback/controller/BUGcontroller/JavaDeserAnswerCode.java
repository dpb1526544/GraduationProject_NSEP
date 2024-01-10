package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaDeserAnswerCode {

    @PostMapping("/java_deser_answer")
    public @ResponseBody Result answer_mathod(@RequestBody String r1){

        System.out.println("ok");
        byte[] r2;
        JavaDeserializationFlag Flag1;
        Result res;

        r2 = JavaDeserByteArrayUtils.toByteArray(r1);
        Flag1 = (JavaDeserializationFlag) JavaDeserByteArrayUtils.bytesToObject(r2).get();
        System.out.println("反序列化后对象："+Flag1.toString());

        res = new Result(Constants.CODE_200,null,"反序列化后对象："+Flag1.toString());
        return res;
    }
}
