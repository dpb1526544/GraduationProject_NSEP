package com.dpb.nsepback.controller.BUGcontroller;


import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.common.Result;
import com.dpb.nsepback.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;

@RestController
public class FileUploadController {

    private static final String UPLOADED_FOLDER = "/test/fileupload";
    @Autowired
    DataSource dataSource;
    public String sql;

    @PostMapping("/upload/file")
    public @ResponseBody
    Result uploadPic(@RequestParam("file") MultipartFile file) {
        String result = "";
        //win服务器部署时
//        String filePath = "D://test/fileupload";
        String filePath = "/test/fileupload/";
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String houzhui = fileName.substring(index + 1);
        System.out.println(houzhui);

        String problemid = "pro008";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();
            // 预编译
            String sql = "select flag from flag where problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, problemid);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

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
                flag = "flag{k9tFIos3TvAPM6f026tK8U50xBRbj4}";
                System.out.println("生成flag:" + flag);
                //将flag MD5加密后存入数据库
                flagMD5 = MD5.getMD5String(flag);
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('" + "public" +
                        "', '" + problemid + "', '" + flag + "');";
                System.out.println(sql);

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
        }

        //转换为list，然后利用List的contains方法，方法参数类型为Object类型
        Object[] whiteList = {"jpg", "png", "gif", "doc", "pdf", "exe", "docx", "html", "jpeg", "mp4"};
//        'jpg','png','gif','doc','pdf','exe','docx','html','jpeg','mp4'
        boolean ifContain3 = Arrays.asList(whiteList).contains(houzhui);
        System.out.println(ifContain3);

        File dest = new File(filePath + file.getOriginalFilename());

        try {
            file.transferTo(dest);
            if (!ifContain3) {
                result = "文件上传成功!" + flag;
            } else {
                result = "文件上传成功!";
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            result = "文件上传失败";
        }
        Result res = new Result(Constants.CODE_200, null, result);
        return res;
    }

    @GetMapping("/any")
    public String index() {
        return "upload"; // return upload.html page
    }

    @GetMapping("/pic")
    public String uploadPic() {
        return "uploadPic"; // return uploadPic.html page
    }

    @PostMapping("/upload22")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/file/status";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + UPLOADED_FOLDER + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "upload failed");
            System.out.println(e.toString());
        }

        return "redirect:/file/status";
    }

    @GetMapping("/status")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/upload22/picture")
    @ResponseBody
    public String uploadPicture(@RequestParam("file") MultipartFile multifile) throws Exception {
        if (multifile.isEmpty()) {
            return "Please select a file to upload";
        }

        String fileName = multifile.getOriginalFilename();
        String Suffix = fileName.substring(fileName.lastIndexOf(".")); // 获取文件后缀名
        String mimeType = multifile.getContentType(); // 获取MIME类型
        String filePath = UPLOADED_FOLDER + fileName;
        File excelFile = convert(multifile);


        // 判断文件后缀名是否在白名单内  校验1
        String[] picSuffixList = {".jpg", ".png", ".jpeg", ".gif", ".bmp", ".ico"};
        boolean suffixFlag = false;
        for (String white_suffix : picSuffixList) {
            if (Suffix.toLowerCase().equals(white_suffix)) {
                suffixFlag = true;
                break;
            }
        }
        if (!suffixFlag) {
            System.out.println("[-] Suffix error: " + Suffix);
            deleteFile(filePath);
            return "Upload failed. Illeagl picture.";
        }


        // 判断MIME类型是否在黑名单内 校验2
        String[] mimeTypeBlackList = {
                "text/html",
                "text/javascript",
                "application/javascript",
                "application/ecmascript",
                "text/xml",
                "application/xml"
        };
        for (String blackMimeType : mimeTypeBlackList) {
            System.out.println("[-] Mime type error: " + mimeType);
            deleteFile(filePath);
            return "Upload failed. Illeagl picture.";
        }

        // 判断文件内容是否是图片 校验3
        boolean isImageFlag = isImage(excelFile);
        String randomFilePath = "";
        deleteFile(randomFilePath);

        if (!isImageFlag) {
            System.out.println("[-] File is not Image");
            deleteFile(filePath);
            return "Upload failed. Illeagl picture.";
        }


        try {
            byte[] bytes = multifile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multifile.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(e.toString());
            deleteFile(filePath);
            return "Upload failed";
        }

        System.out.println("[+] Safe file. Suffix: {}, MIME: {}" + Suffix + mimeType);
        System.out.println("[+] Successfully uploaded {}" + filePath);
        return String.format("You successfully uploaded '%s'", filePath);
    }

    private void deleteFile(String filePath) {
        File delFile = new File(filePath);
        if (delFile.isFile() && delFile.exists()) {
            if (delFile.delete()) {
                System.out.println("[+] " + filePath + " delete successfully!");
                return;
            }
        }
        System.out.println(filePath + " delete failed!");
    }

    private File convert(MultipartFile multiFile) throws Exception {
        Object Generators = null;
        String uuid = null;
        String fileName = multiFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));


        String randomFilePath = UPLOADED_FOLDER + uuid + suffix;
        File convFile = new File(randomFilePath);
        boolean ret = convFile.createNewFile();
        if (!ret) {
            return null;
        }
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multiFile.getBytes());
        fos.close();
        return convFile;
    }

    private static boolean isImage(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        return bi != null;
    }
}


