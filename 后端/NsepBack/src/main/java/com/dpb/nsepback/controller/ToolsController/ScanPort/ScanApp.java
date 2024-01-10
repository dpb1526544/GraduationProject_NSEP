package com.dpb.nsepback.controller.ToolsController.ScanPort;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.controller.dto.PortDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 扫描器启动类
 */
@RestController
@RequestMapping("/tools/")
public class ScanApp {
//    {ip='127.0.0.1', port=445, service='null', isOpen=true, banner='null'}
    public static String result;
    public int count = 0;

    @RequestMapping("/scan_port")
    public @ResponseBody Result scan(@RequestParam(value = "ips") String ips,
                                     @RequestParam(value = "ports") String ports,
                                     HttpServletResponse response){
        result="";
        String[] results;
        int count;
        PortDTO[] portDTOS;

        // 待扫描的ip地址或ip地址组
        System.out.println(ips+" "+ports);
        Scanner.start(ips,ports);
        System.out.println("from ScanApp:"+result);

        results = result.split(";");
        count = results.length;
        portDTOS = new PortDTO[count];

        for(int i = 0;i<count;i++){
            String[] ret;

            System.out.println(i);
            portDTOS[i] = new PortDTO();
            ret = results[i].split(",");
            System.out.println(ret[0]);
            portDTOS[i].setIp(ret[0]);
            if(ret.length!=1){
                portDTOS[i].setPort(ret[1]);
                if(ret[2].equals("null")){
                    portDTOS[i].setService("未知");
                }else{
                    portDTOS[i].setService(ret[2]);
                }

                if(ret[3].equals("true")){
                    ret[3] = "open";
                }
                else {
                    ret[3] = "close";
                }
                portDTOS[i].setIsOpen(ret[3]);
            }
        }

        Result res=new Result(Constants.CODE_200,null,portDTOS);
        return res;
    }
}
