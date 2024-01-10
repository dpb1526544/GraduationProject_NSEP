//JavaDeserController.java
JavaDeserializationFlag Flag = new JavaDeserializationFlag(flag);
Optional<byte[]> bytes = JavaDeserByteArrayUtils.objectToBytes(Flag);
byte[] ret = bytes.get();
String r1 = JavaDeserByteArrayUtils.toHexString(ret);
System.out.println("Serialized converted byte array - hex representation:"+r1);

Result res=new Result(Constants.CODE_200,null,"flag class converts byte arrays (hex) after serialization:"+r1);
return res;


//JavaDeserializationFlag.java
import java.io.Serializable;
public class JavaDeserializationFlag implements Serializable {
    public String flag;
    public JavaDeserializationFlag(String s) {
        this.flag = s;
    }
    @Override
    public String toString() {
        return this.flag;
    }
}


//JavaDeserByteArrayUtils.java
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Optional;

public class JavaDeserByteArrayUtils {
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
    
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
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
            if ((byteArray[i] & 0xff) < 0x10)
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }
}
