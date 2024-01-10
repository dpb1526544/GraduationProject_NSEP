package com.dpb.nsepback.controller.ToolsController.SubDomain;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.DomainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class DomainFindController {
    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/DomainFind")
    public @ResponseBody
    Result domainfind(@RequestParam("urlP") String urlP){
        //cookie
        String cookie = null;
        try {
            Connection connection = dataSource.getConnection();
            // 预编译
            String sql = "select cookie from cookie where tools = ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, "domain");
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为"+row);
            if(row==1){
                rs.next();
                cookie = rs.getString("cookie");
            }
            else {
                Result res=new Result(Constants.CODE_200,null,"无cookie信息");
                return res;
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
            Result res=new Result(Constants.CODE_200,null,"数据库错误");
            return res;
            //return "输入内容错误，数据库查询错误";
        }

        String url = "https://chaziyu.com/"+urlP+"/";
        System.out.println(url);

        String return_data = sendRequest(url,"GET",cookie);
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
        System.out.println("return第一部分："+ret1);
        System.out.println("======================");
        System.out.println(ret[ret.length-1]);

        String[] ret2 = ret[ret.length-1].split("</span></td>");
        System.out.println("ret2[]="+ret2);
        String ret3 = "";
        int index_start2;
        int index_last2;
        for(int i=2;i<ret2.length;i++){
            index_start2 = ret2[i].indexOf("rel=\"nofollow\">");
            index_last2 = ret2[i].indexOf("</a></td>");
            ret3 = ret3+ret2[i].substring(index_start2+15,index_last2)+",";
        }
        System.out.println("return第二部分："+ret3);
        String retdata = ret1 + ret3;
        String[] retdata1 = retdata.split(",");
        DomainDTO[] domainDTOS = new DomainDTO[retdata1.length];
        for (int i=0;i<retdata1.length;i++){
            domainDTOS[i] = new DomainDTO((i+1)+"",retdata1[i]);
        }
        Result res=new Result(Constants.CODE_200,null,domainDTOS);
        return res;
    }

    public String sendRequest(String urlParam,String requestType,String cookie) {

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
            con.setRequestProperty("Cookie",cookie);
            //            con.setRequestProperty("User-Agent","");
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
                    json.append(line);
                }
                return json.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
