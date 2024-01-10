package com.dpb.nsepback.controller.ToolsController.SubDomain;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class domain {
    public static String sendRequest(String urlParam,String requestType) {

        HttpURLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            StringBuilder json = new StringBuilder();
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            //con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32");
            con.setRequestProperty("Cookie","Hm_lvt_7d51be3b7524d35798ad1304e725bd2c=1653190253,1653827802,1653871299; Hm_lpvt_7d51be3b7524d35798ad1304e725bd2c=1653871340");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);

            //得到响应码
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                while ((line = buffer.readLine()) != null) {
                    //resultBuffer.append(line);
                    json.append(line);
                }
                return json.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
    public static void main(String[] args) {

        //String url ="http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=120.79.75.96";
        String url = "https://chaziyu.com/"+"baidu.com"+"/";
        //String url = "https://domainhouse.buzz/query.php?token=9378409013576a0cb7c6fac863d5dfe03f0d288a&domain=baidu.com";
        String return_data = sendRequest(url,"GET");
        System.out.println(return_data);
        int index_start = return_data.indexOf("<th><span>子域名</span></th>");
        int index_last = return_data.lastIndexOf("最新子域名查询");
        String return_data1 = return_data.substring(index_start,index_last);
        System.out.println(return_data1);

        String[] ret = return_data1.split("<tr class=\"J_link\">");
        String ret1 = "";
        int index_start1;
        int index_last1;
        for(int i=1;i<ret.length;i++){
            index_start1 = ret[i].indexOf("target=\"_blank\">");
            index_last1 = ret[i].indexOf("</a></td>");
            ret1 = ret1+ret[i].substring(index_start1+16,index_last1)+",";
        }
        System.out.println(ret1);
        System.out.println("======================");
        System.out.println(ret[ret.length-1]);

        String[] ret2 = ret[ret.length-1].split("</span></td>");
        System.out.println("ret2[]="+ret2);
        String ret3 = "";
        int index_start2;
        int index_last2;
        for(int i=2;i<ret2.length;i++){
//            System.out.println(ret2[i]);
            index_start2 = ret2[i].indexOf("rel=\"nofollow\">");
            index_last2 = ret2[i].indexOf("</a></td>");
            ret3 = ret3+ret2[i].substring(index_start2+15,index_last2)+",";
        }
    }
    //使用URLConnection获取指定网页返回的json数据
    public static String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    //JSON与对象转换
    public static void getJson(){
        Res person = new Res("张三","百家姓中出现较多的名字");
        //java对象转JSON
        String gPersonString = JSON.toJSONString(person);
        System.out.println(gPersonString);
        Res person1 = new Res();
        person1 = JSON.parseObject(gPersonString,Res.class);
    }
}

