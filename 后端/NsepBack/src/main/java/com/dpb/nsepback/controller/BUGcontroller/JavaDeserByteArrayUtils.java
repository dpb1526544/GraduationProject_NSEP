package com.dpb.nsepback.controller.BUGcontroller;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Optional;

public class JavaDeserByteArrayUtils {

    //序列化
    public static <T> Optional<byte[]> objectToBytes(T obj) {
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream sOut;
        try {
            sOut = new ObjectOutputStream(out);
            sOut.writeObject(obj);
            sOut.flush();
            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(bytes);
    }

    //反序列化
    public static <T> Optional<T> bytesToObject(byte[] bytes) {
        T t = null;
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream sIn;
        try {
            sIn = new ObjectInputStream(in);
            t = (T) sIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(t);
    }

    //16进制表示格式的字符串转化为字节数组
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;

        //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
        for (int i = 0; i < byteArray.length; i++) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }

        return byteArray;
    }

    public static String toHexString(byte[] byteArray) {

        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return hexString.toString().toLowerCase();
    }

    public static void main(String[] args) {
        JavaDeserializationFlag Flag = new JavaDeserializationFlag("1234abc");
        System.out.println("被序列化对象：" + Flag.toString());

        Optional<byte[]> bytes = JavaDeserByteArrayUtils.objectToBytes(Flag);//将对象转换为二进制字节数组(序列化)
        System.out.println(bytes);
        byte[] ret = bytes.get();

        String r1 = JavaDeserByteArrayUtils.toHexString(ret);
        System.out.println("序列化后转换字节数组-16进制表示：" + r1);
        byte[] r2 = JavaDeserByteArrayUtils.toByteArray(r1);

        JavaDeserializationFlag Flag1 = (JavaDeserializationFlag) JavaDeserByteArrayUtils.bytesToObject(r2).get();
        System.out.println("反序列化后对象：" + Flag1.toString());
    }
}
