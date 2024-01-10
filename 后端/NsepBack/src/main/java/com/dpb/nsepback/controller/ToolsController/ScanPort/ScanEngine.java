package com.dpb.nsepback.controller.ToolsController.ScanPort;

import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * 扫描器引擎
 * 实现具体的扫描逻辑，提供扫描能力（TCP全连接和半连接扫描）
 */
public class ScanEngine {
    private static Logger logger = Logger.getLogger("TCPFullConnectScan");
    public static final String TCP_FULL_CONNECT_SCAN = "TCP_FULL_CONNECT_SCAN";
    public static final String TCP_HALF_CONNECT_SCAN = "TCP_HALF_CONNECT_SCAN";

    public static ScanObject scan(ScanObject object, String scanEngine){
        switch (scanEngine){
            case TCP_FULL_CONNECT_SCAN:
                return tcpFullConnectScan(object);
            case TCP_HALF_CONNECT_SCAN:
                return tcpHalfConnectScan(object);
        }
        return tcpFullConnectScan(object);
    }

    /**
     * tcp全连接扫描
     * @param object
     * @return
     */
    private static ScanObject tcpFullConnectScan(ScanObject object){
        try{
            // 全连接扫描，发现可用服务
            Socket socket = new Socket(object.getIp(),object.getPort());
            socket.setSoTimeout(100);
            object.setOpen(true);
            object.setService();
            logger.info("[-] Find service :"+ object);
            System.out.println("[-] Find service :"+ object);
            ScanApp.result=ScanApp.result+object;
            socket.close();
        } catch (ConnectException e) {
        } catch (Exception e){
            // 出现其他异常
            logger.info("[-] " + object.toString() + "end with unexecepted exeception:" + e.getMessage());
            System.out.println("[-] " + object.toString() + "end with unexecepted exeception:" + e.getMessage());
            ScanApp.result=ScanApp.result+object;
        }
        return object;

    }

    /**
     * TPC 半连接扫描
     * @param object
     * @return
     */
    private static ScanObject tcpHalfConnectScan(ScanObject object){
        // TODO 待实现tcp半连接扫描
        return object;
    }
}
