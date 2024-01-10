package com.dpb.nsepback.controller.ToolsController.IPInfo;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.IPDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class IPInfoController {
    @RequestMapping("tools/IPInfo")
    public @ResponseBody
    Result getIPInfo(@RequestParam("ip1") String ip1){
        String url = "https://ip.useragentinfo.com/jsonp?ip="+ip1;
        String return_json = sendRequest(url,"GET");
        String[] info1 = return_json.split(",");

        String country = info1[0];
        int indexs = country.indexOf(":")+3;
        int indexe = country.length()-1;
        country = country.substring(indexs,indexe);

        String country_code = info1[1];
        indexs = country_code.indexOf(":")+3;
        indexe = country_code.length()-1;
        country_code = country_code.substring(indexs,indexe);

        String province = info1[2];
        indexs = province.indexOf(":")+3;
        indexe = province.length()-1;
        province = province.substring(indexs,indexe);

        String city = info1[3];
        indexs = city.indexOf(":")+3;
        indexe = city.length()-1;
        city = city.substring(indexs,indexe);

        String distinct = info1[4];
        indexs = distinct.indexOf(":")+3;
        indexe = distinct.length()-1;
        distinct = distinct.substring(indexs,indexe);

        String isp = info1[5];
        indexs = isp.indexOf(":")+3;
        indexe = isp.length()-1;
        isp = isp.substring(indexs,indexe);

        String operator = info1[6];
        indexs = operator.indexOf(":")+3;
        indexe = operator.length()-1;
        operator = operator.substring(indexs,indexe);

        String ip = info1[7];
        indexs = ip.indexOf(":")+3;
        indexe = ip.length()-1;
        ip = ip.substring(indexs,indexe);

        String lon = null;

        String lat = null;

        String net_str = null;

        IPDTO ipdto = new IPDTO();
        ipdto.setIp(ip);
        ipdto.setCountry(country);
        ipdto.setCountry_code(country_code);
        ipdto.setProvince(province);
        ipdto.setCity(city);
        ipdto.setDistinct(distinct);
        ipdto.setIsp(isp);
        ipdto.setOperator(operator);
        ipdto.setLon(lon);
        ipdto.setLat(lat);
        ipdto.setNet_str(net_str);

        Result res=new Result(Constants.CODE_200,null,ipdto);
        return res;
    }

    public static String sendRequest(String urlParam, String requestType) {
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
                System.out.println(json.toString());
                return json.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "{\"count\":0,\"data\":\"fail\n";
    }
}
