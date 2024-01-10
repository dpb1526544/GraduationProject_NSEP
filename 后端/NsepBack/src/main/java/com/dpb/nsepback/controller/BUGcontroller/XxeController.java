package com.dpb.nsepback.controller.BUGcontroller;

import com.alibaba.fastjson.JSONObject;
import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;


@RestController
@RequestMapping("/xxe")
public class XxeController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @PostMapping("/payload")
    public @ResponseBody
    Result xxe(@RequestBody String xxe
    ) throws ParserConfigurationException, IOException, SAXException {
        JSONObject jsonObject = JSONObject.parseObject(xxe);
        xxe = (String) jsonObject.get("xxe");
        String problemid = "pro013";
        String flag = null;
        String flagMD5 = null;
        String flagBase64 = null;
        String flagPath = "/test/XXE/XXE.txt";

        String username = "xxe";
        System.out.println(xxe);
        System.out.println(username);

        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);
            if (row == 1) {
                rs.next();
                flag = rs.getString("flag");
                System.out.println("flag已存在:" + flag);
            } else {
                FlagMake flagMake = new FlagMake();
                flag = flagMake.getFlag();
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误 in flag");
            System.out.println(e.toString());
        }
        //将flag base64编码后写入文件
        flagBase64 = setEncryptionBase64(flag);
        System.out.println("将flagBase64写入文件：" + flagBase64);
        saveAsFileWriter(flagBase64, flagPath);
        /**
         *创建DOM解释器解析XML文件
         */
        // 把要解析的 XML 文档转化为输入流，以便 DOM 解析器解析它
        String anyString = xxe;
        InputStream is = new ByteArrayInputStream(anyString.getBytes(StandardCharsets.UTF_8));

        //InputStream is = new FileInputStream("D:\\note2.xml");
        //创建DOM模式的解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //调用工厂的方法得到DOM解析器对象
        DocumentBuilder builder = factory.newDocumentBuilder();
        //调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象
        Document doc = builder.parse(is);
        //根据元素名称获取元素值
        NodeList nodeList = doc.getElementsByTagName("from");
        Result res;
        if (nodeList.item(0).getTextContent().equals("/test/XXE/XXE.txt")) {
            res = new Result(Constants.CODE_200, null, "result:" + flagBase64);
        } else {
            res = new Result(Constants.CODE_200, null, "result:" + nodeList.item(0).getTextContent());
        }
        return res;
    }

    public static void saveAsFileWriter(String content, String filePath)
            throws IOException {
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(filePath);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //加密
    public static String setEncryptionBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;

    }

    //解密
    public static String getDecodeBase64(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
