package com.dpb.nsepback.controller.BUGcontroller;

import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.sql.DataSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

// 目录遍历漏洞
@RestController
public class PathTravelController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/pathtravel")
    public Result pathtravel(@RequestParam("username") String username)
            throws IOException {

        String problemid = "pro026";
        String flag = null;
        String flagMD5 = null;
        String flagBase64 = null;
        String flagPath = "../../../../../../../../../vue/src/assets/img/bugimg/pathtravel/flag";

        try {
            String sql = "";
            Connection connection;
            PreparedStatement st;
            ResultSet rs;
            int row;

            connection = dataSource.getConnection();

            // 预编译
            sql = "select flag from flag where username = ? and problemid = ?";
            st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            System.out.println(sql);
            rs = st.executeQuery();

            rs.last();
            row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为" + row);

            if (row == 1) {
                rs.next();
                flag = rs.getString("flag");
                System.out.println("flag已存在:" + flag);
            } else {
                FlagMake flagMake = new FlagMake();
                flag = flagMake.getFlag();
                System.out.println("生成flag:" + flag);

                flagMD5 = MD5.getMD5String(flag);
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + username +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);

                BufferedImage image = createImage(flag);
                File file = new File("vue/src/assets/img/bugimg/pathtravel");
                String fileName = "flag.jpg";
                File jpgFile = new File(file, fileName);
                if (!jpgFile.exists()) {
                    jpgFile.createNewFile();
                }

                ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(jpgFile);
                ImageIO.write(image, "jpg", imageOutputStream);
                imageOutputStream.close();
                System.out.println("图片写入完成，请查看！");
            }

            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误 in flag");
            System.out.println(e.toString());
        }


        return Result.success();
    }

    public static BufferedImage createImage(String str) {
        // 设置背景宽高
        int width = 1000, height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文对象
        Graphics graphics = image.getGraphics();
        // 填充
        graphics.fillRect(0, 0, width, height);
        // 设定字体大小及样式
        graphics.setFont(new Font("宋体", Font.BOLD, 34));
        // 字体颜色
        graphics.setColor(Color.BLUE);
        // 描绘字符串
        graphics.drawString(str, 100, 100);
        graphics.dispose();
        return image;
    }

}
