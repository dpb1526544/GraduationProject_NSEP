package com.dpb.nsepback.entity;

import java.util.Random;

// 随机生成flag
public class FlagMake {

    private String flag;

    public FlagMake(){
        flag = newFlag();
    }

    public String newFlag(){
        String data = null;
        String flag1 = null;
        data = randomletterstr(30);
        flag1 = "flag{"+data+"}";
        return flag1;
    }

    public String randomletterstr(int length) {

        //随机产生字母串
        String str = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIGKLMNOPQRSTUVWXYZ" +
                "1234567890";    //可随机产生的字符
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            //可随机产生字符的数量
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    public String getFlag(){
        return flag;
    }
}

