package com.dpb.nsepback.controller.BUGcontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class java_linux {

    //(方式一)适合Java调用本地的Liunx无须任何依赖包

    /***
     * 第一种方法，仅仅执行命令无须关注返回结果
     * @throws Exception
     */
    public static void exeCmd() throws Exception{
        Runtime r = Runtime.getRuntime();
        //执行linux命令，不关心返回结果，此处，可以执行一个shell脚本，或者python脚本
        Process p = r.exec("ping ");
        p.waitFor();
    }

    /**
     * 第二种方法，需要执行命令完后的返回结果
     * @return result
     * @throws Exception
     */
    public static String getCodeResult(String commend){
        try{
            //exeCmd();
            //执行一个命令需要展示返回结果的
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(commend);
            p.waitFor();

            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            StringBuffer sb=new StringBuffer();
            while ((line = b.readLine()) != null) {
                sb.append(line).append("\n");
            }

            System.out.println("result： "+sb.toString());
            b.close();
            return sb.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
