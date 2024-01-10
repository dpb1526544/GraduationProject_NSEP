/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云docker
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 43.143.195.149:3307
 Source Schema         : nsep

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 10/05/2023 15:47:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cookie
-- ----------------------------
DROP TABLE IF EXISTS `cookie`;
CREATE TABLE `cookie`  (
  `cookie` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `tools` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cookie
-- ----------------------------
INSERT INTO `cookie` VALUES ('Hm_lvt_7d51be3b7524d35798ad1304e725bd2c=1653190253,1653827802,1653871299,1653956560; Hm_lpvt_7d51be3b7524d35798ad1304e725bd2c=1653956560', 'domain');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coursename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名',
  `teacherid` int(11) NULL DEFAULT NULL COMMENT '教师ID',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`courseid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '2023春季网络安全技术', 2, '2023-04-21 08:06:34');
INSERT INTO `course` VALUES (2, '2023春季密码学', 2, '2023-02-24 10:56:00');

-- ----------------------------
-- Table structure for flag
-- ----------------------------
DROP TABLE IF EXISTS `flag`;
CREATE TABLE `flag`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `problemid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`, `problemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flag
-- ----------------------------
INSERT INTO `flag` VALUES ('12019242000', 'pro002', 'flag{sbsCwo8Xu2NQaassgGI8TncdUU44Nn}');
INSERT INTO `flag` VALUES ('12019242305', 'pro002', 'flag{eEh5A237ZNY7ZV0GiHkaBLLGmS4cBR}');
INSERT INTO `flag` VALUES ('12019242305', 'pro007', 'flag{VXkdcRSvEyV1wDVWohmRM9Iq5LDk97}');
INSERT INTO `flag` VALUES ('12019242305', 'pro009', 'flag{D5SluYl3B1X298fAluj5fNeSTvnNIh}');
INSERT INTO `flag` VALUES ('12019242305', 'pro011', 'flag{GxPorU82MircSuMG1ojanA3QgH9I1L}');
INSERT INTO `flag` VALUES ('12019242305', 'pro026', 'flag{4ukRsYo0iLtbwVcBrrccCod4nF6b5c}');
INSERT INTO `flag` VALUES ('12019242306', 'pro002', 'flag{AWYX3GgrAFSfqAdY0dRRMrde0iCy84}');
INSERT INTO `flag` VALUES ('12019242307', 'pro002', 'flag{xrWbo1KYgv2XqH7X8qoCQ0FOkEjaCw}');
INSERT INTO `flag` VALUES ('12019242307', 'pro007', 'flag{upjGUXQOOAyjYNjh4igEQBolxHcptg}');
INSERT INTO `flag` VALUES ('12019242307', 'pro009', 'flag{pgD03MzBF2RL4xsu7lj75CZEsc8KdA}');
INSERT INTO `flag` VALUES ('12019242307', 'pro011', 'flag{S6gO6HRlhA38svf2BBcjVFFNZ3x499}');
INSERT INTO `flag` VALUES ('12019242307', 'pro012', 'flag{pGFd7zSYH5kvwWlrqqltgjfqcAZ9Y9}');
INSERT INTO `flag` VALUES ('12019242315', 'pro002', 'flag{QWnuiZ9vGzXzMApGajYVxKkEyQenFm}');
INSERT INTO `flag` VALUES ('20230507001', 'pro002', 'flag{uw7XMuGwpmvYrEEShxiNH2DZLhQg3z}');
INSERT INTO `flag` VALUES ('20230507001', 'pro007', 'flag{dVcOKf76uNOo3vfAReF0Zx5SxRBEwn}');
INSERT INTO `flag` VALUES ('20230507001', 'pro025', 'flag{d6ScnCBHqxGfw5kltSpXhND7SxAlWT}');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `forumid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `username` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(1) NULL DEFAULT 1 COMMENT '0：未通过审核；1：通过审核',
  PRIMARY KEY (`forumid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (1, '如来十三掌', '题目：夜哆悉諳多苦奢陀奢諦冥神哆盧穆皤三侄三即諸諳即冥迦冥隸數顛耶迦奢若吉怯陀諳怖奢智侄諸若奢數菩奢集遠俱老竟寫明奢若梵等盧皤豆蒙密離怯婆皤礙他哆提哆多缽以南哆心曰姪罰蒙呐神。舍切真怯勝呐得俱沙罰娑是怯遠得呐數罰輸哆遠薩得槃漫夢盧皤亦醯呐娑皤瑟輸諳尼摩罰薩冥大倒參夢侄阿心罰等奢大度地冥殿皤沙蘇輸奢恐豆侄得罰提哆伽諳沙楞缽三死怯摩大蘇者數一遮……解法：首先需要找到“与佛论禅”对文字进行解码，很多时候就直接卡在这一步了，然后要根据题目名称联想到rot13，还要观察出是经过base64编码，让后还能将这两个组合起来，才能得到flag。', '12019242305', '2023-02-21 19:05:21', 1);
INSERT INTO `forum` VALUES (2, 'buckup', '打开题目连接，发现提示信息“index.php的备份文件名”；\r\n百度后可得知，一些编辑器再编辑后会生产一个 .bak后缀的文件，用于紧急恢复；\r\n例如：index.php→ index.php.bak；不同的编译器可能会有不同的后缀；同样也有可能是编写者主动备份；\r\n逐一尝试后，发现存在index.php.bak文件，notepad(或者其他编辑器)打开后即可得到flag。', '12019242317', '2023-02-21 16:11:38', 1);
INSERT INTO `forum` VALUES (3, 'give_you_flag', '下载附件后查看，发现是gif格式图片，并且有一张二维码一闪而过，猜测是图片的某一帧中插入了二维码；使用PS或者GIF制作器打开，发现缺少三个小方块的二维码，而这些小方块被称为定位图案，用于标记二维码矩形的大小，用三个定位图案可以标识并确定一个二维码矩形的位置和方向。二维码随然具有很强的纠错功能，能够在30%面积被遮盖的情况下成功识别，但前提是定位图案不被遮盖，因此接下来只需把三个定位图案P上就行了。', '12019242353', '2023-02-21 16:12:06', 1);
INSERT INTO `forum` VALUES (4, 'disabled_button', '打开题目链接，发现有一按钮，但是无法按下；\r\n查看源码发现，是“disabled”在捣鬼，我们不能通过点击按钮提交表单；\r\n不过我们能通过linux中的curl命令来post数据；\r\n在终端里输入命令：curl -d \"auth=flag\" \"http://111.198.29.45:49554/\"；\r\n\"auth=flag\"及是我们需要提交的数据；', '12019242315', '2023-02-21 16:12:58', 1);
INSERT INTO `forum` VALUES (5, 'xff_referer', '根据题目的提示，了解到本题需要我们伪造xff和referer；\r\n解题过程比较简单，主要是抓包并进行修改。\r\n使用fiddler抓包，然后添加上根据要求伪造的xff和referer即可。', '12019242322', '2023-02-21 16:13:34', 1);
INSERT INTO `forum` VALUES (6, 'gif', '下载附件并解压，发现是一堆黑色和白色的图片，按某种规律排列，猜想flag就应该隐藏在黑白图片的排列顺序里。\r\n联想到黑白两种状态可能对应二进制的‘0’和‘1’，而这些黑白图片的顺序可以改写成一串二进制序列，然后发现图片总数为104，是8的倍数，自然联想到一个字节8位二进制码，可以尝试对得到的序列按8位分组。推测出，白色对应0，黑色对应1，得到的8位二进制数应该对应ascii的字符编码。再把二进制数转为字符，即为flag。', '12019242370', '2023-02-21 18:59:22', 1);
INSERT INTO `forum` VALUES (7, '想蹭网先解开密码', '下载附件，为.cap文件，这种文件是数据包探测程序包含收集的数据包文件格式，结合题目名称，此文件应该是wifi密码爆破过程中抓包得到的数据，因此可以使用工具进行爆破密码，可以使用工具有airepaly-ng， WIFIPR等，此处采用WIFIPR进行破解。', '12019242328', '2023-02-21 19:12:55', 1);
INSERT INTO `forum` VALUES (8, '旋转跳跃', '此题需要用到隐写工具MP3stego，下载附件，使用MP3stego工具提取，输入指令：\r\nDecode.exe -X -P syclovergeek sycgeek-mp3.mp3；\r\n其中syclovergeek为题目提示的key值，等待提取结束后，获得flag:SYC{Mp3_B15b1uBiu_W0W}', '12019242355', '2023-02-21 19:14:34', 1);
INSERT INTO `forum` VALUES (9, 'convert', '打开题目连接，发现是一堆01数字；分析其结构比较符合8位二进制ask码，同时结合题目名称convert（转换）猜测是需要进行进制转换。打开转换后的文件，发现文件头部存在“RAR”的标识，因此将后缀名改为.rar，解压，得到图片key.jpg，在其属性中发现base64码，解码得到flag。', '12019242400', '2023-02-21 19:15:01', 1);
INSERT INTO `forum` VALUES (10, '求getshell', '打开连接，是一个文件上传页面，结合题目名称，应该是需要上传shell；写一个简单shell，上传并抓包。\r\n将后缀改为.php5，这样不会被过滤；将Content-Type: application/octet-stream改为Content-Type: image/jpeg，躲避文件识别；\r\n将Content-Type: multipart/form-data;其中的一个字母换成大写，越过服务器端检查。', '12019242331', '2023-02-21 19:23:28', 1);
INSERT INTO `forum` VALUES (12, 'xss反射型注入', '在页面输入框中使用<script></scritpt>标签以及alert函数即可完成对自己学工号的反射型xss注入，注入成功后会弹窗flag，快去试试吧', '12019242305', '2023-04-18 17:07:44', 1);
INSERT INTO `forum` VALUES (14, 'xss存储型', '页面输入框存在xss存储型漏洞，在页面输入框内使用<script>标签及console.log方法打印自己的学号，即可拿到flag，快试试吧！！', '12019242353', '2023-04-18 17:13:02', 1);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `messagetitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `messagecontent` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '新版本更新了！', '当前系统已经更新到1.1版本，请各位老师同学及时检查数据和功能完备，联系方式：1941379720@qq.com', '2023-02-03 15:53:39');
INSERT INTO `message` VALUES (3, '新系统上线', '新系统nsep1.3 beta发布了', '2023-04-18 17:52:45');

-- ----------------------------
-- Table structure for opuser
-- ----------------------------
DROP TABLE IF EXISTS `opuser`;
CREATE TABLE `opuser`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `add` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `username1` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of opuser
-- ----------------------------
INSERT INTO `opuser` VALUES ('curry', '123', '12312312312', '宁夏银川', NULL);
INSERT INTO `opuser` VALUES ('lucy', '123=+kshf:?dkjsidfo', '12312312311', '宁夏银川', NULL);
INSERT INTO `opuser` VALUES ('lucy', NULL, 'flag{l6PjReo949GCMYjaHicvcZZTPC6fn5}', NULL, '100001');

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `problemid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID',
  `problemname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `problemratio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `problemcontent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  PRIMARY KEY (`problemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES ('pro001', 'RCE-ping?', 'rce', '4', '最近刚学会ping指令，你知道它也是TCP/IP协议的一部分。正好你的舍友刚搭建了一个路由器管理平台，不妨去搞个破坏实践一下。');
INSERT INTO `problem` VALUES ('pro002', 'SQLi-brackets', 'sqli', '5', '这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里');
INSERT INTO `problem` VALUES ('pro003', 'SQLi-Char', 'sqli', '4', '这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里');
INSERT INTO `problem` VALUES ('pro004', 'SQLi-Num', 'sqli', '3', '这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里');
INSERT INTO `problem` VALUES ('pro007', '文件下载', 'filedownload', '3', '任意文件下载漏洞，看看能不能下载flag文件？给你点提示，flag在flag.txt里哦！不用感谢');
INSERT INTO `problem` VALUES ('pro008', 'upload_mime', 'fileupload', '3', '去了解一下MIME');
INSERT INTO `problem` VALUES ('pro009', 'Java Deseialization', 'javadeser', '4', '根据提供的flag类的序列化后的数据，阅读序列化源码，写出反序列化代码，尝试获取flag');
INSERT INTO `problem` VALUES ('pro010', 'URL-Redirect', 'urlredirect', '3', 'url重定向');
INSERT INTO `problem` VALUES ('pro011', 'XSS-Store', 'xss', '3', '存储型XSS漏洞');
INSERT INTO `problem` VALUES ('pro012', 'XSS-Reflect', 'xss', '4', '反射型XSS漏洞');
INSERT INTO `problem` VALUES ('pro013', 'XXE', 'xxe', '4', 'XXE漏洞，flag存储在/test/XXE/XXE.txt,can you get it?');
INSERT INTO `problem` VALUES ('pro022', 'SQL 删除型', 'sqli', '4', '试试用报错函数');
INSERT INTO `problem` VALUES ('pro023', 'SQL 搜索型', 'sqli', '3', '注意正确的闭合');
INSERT INTO `problem` VALUES ('pro024', 'op_login', 'BAC', '4', '尝试获取lucy用户的个人信息');
INSERT INTO `problem` VALUES ('pro025', 'file_read', 'filedownload', '3', '文件下载漏洞');
INSERT INTO `problem` VALUES ('pro026', 'path_travel', 'pathtravel', '3', '目录遍历');
INSERT INTO `problem` VALUES ('pro027', 'unsafe_filter1', 'sqli', '4', '不安全过滤1');
INSERT INTO `problem` VALUES ('pro028', 'unsafe_filter2', 'sqli', '4', '不安全过滤2');

-- ----------------------------
-- Table structure for sqlifl4g
-- ----------------------------
DROP TABLE IF EXISTS `sqlifl4g`;
CREATE TABLE `sqlifl4g`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `problemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `fl4g` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sqlifl4g
-- ----------------------------
INSERT INTO `sqlifl4g` VALUES ('error data!1', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!2', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!3', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!4', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!5', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!6', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!7', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!8', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!9', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!10', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!11', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!12', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!13', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!14', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!15', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!16', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!17', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('error data!18', 'pro002', 'error data!');
INSERT INTO `sqlifl4g` VALUES ('12019242306', 'pro002', 'flag{AWYX3GgrAFSfqAdY0dRRMrde0iCy84}');
INSERT INTO `sqlifl4g` VALUES ('12019242307', 'pro002', 'flag{xrWbo1KYgv2XqH7X8qoCQ0FOkEjaCw}');
INSERT INTO `sqlifl4g` VALUES ('12019242307', 'pro011', 'flag{S6gO6HRlhA38svf2BBcjVFFNZ3x499}');
INSERT INTO `sqlifl4g` VALUES ('12019242305', 'pro002', 'flag{eEh5A237ZNY7ZV0GiHkaBLLGmS4cBR}');
INSERT INTO `sqlifl4g` VALUES ('12019242315', 'pro002', 'flag{QWnuiZ9vGzXzMApGajYVxKkEyQenFm}');
INSERT INTO `sqlifl4g` VALUES ('20230507001', 'pro002', 'flag{uw7XMuGwpmvYrEEShxiNH2DZLhQg3z}');
INSERT INTO `sqlifl4g` VALUES ('12019242000', 'pro002', 'flag{sbsCwo8Xu2NQaassgGI8TncdUU44Nn}');
INSERT INTO `sqlifl4g` VALUES ('12019242305', 'pro011', 'flag{GxPorU82MircSuMG1ojanA3QgH9I1L}');

-- ----------------------------
-- Table structure for sqliuser
-- ----------------------------
DROP TABLE IF EXISTS `sqliuser`;
CREATE TABLE `sqliuser`  (
  `id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sqliuser
-- ----------------------------
INSERT INTO `sqliuser` VALUES ('1', 'James', '123', '1');
INSERT INTO `sqliuser` VALUES ('2', 'Curry', '123', '3');
INSERT INTO `sqliuser` VALUES ('3', 'Harden', '1', '1');
INSERT INTO `sqliuser` VALUES ('pro004', 'aaa', '1', '1');

-- ----------------------------
-- Table structure for stucourse
-- ----------------------------
DROP TABLE IF EXISTS `stucourse`;
CREATE TABLE `stucourse`  (
  `courseid` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  PRIMARY KEY (`courseid`, `userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stucourse
-- ----------------------------
INSERT INTO `stucourse` VALUES (1, 3);
INSERT INTO `stucourse` VALUES (1, 4);
INSERT INTO `stucourse` VALUES (1, 5);
INSERT INTO `stucourse` VALUES (1, 6);
INSERT INTO `stucourse` VALUES (1, 7);
INSERT INTO `stucourse` VALUES (1, 8);
INSERT INTO `stucourse` VALUES (1, 9);
INSERT INTO `stucourse` VALUES (1, 10);
INSERT INTO `stucourse` VALUES (1, 11);
INSERT INTO `stucourse` VALUES (1, 12);
INSERT INTO `stucourse` VALUES (1, 13);
INSERT INTO `stucourse` VALUES (1, 14);
INSERT INTO `stucourse` VALUES (1, 15);
INSERT INTO `stucourse` VALUES (1, 16);
INSERT INTO `stucourse` VALUES (1, 17);
INSERT INTO `stucourse` VALUES (1, 18);
INSERT INTO `stucourse` VALUES (1, 19);
INSERT INTO `stucourse` VALUES (1, 20);
INSERT INTO `stucourse` VALUES (1, 21);
INSERT INTO `stucourse` VALUES (1, 22);
INSERT INTO `stucourse` VALUES (1, 23);
INSERT INTO `stucourse` VALUES (1, 24);
INSERT INTO `stucourse` VALUES (1, 25);
INSERT INTO `stucourse` VALUES (1, 26);
INSERT INTO `stucourse` VALUES (1, 27);
INSERT INTO `stucourse` VALUES (1, 28);
INSERT INTO `stucourse` VALUES (1, 29);
INSERT INTO `stucourse` VALUES (1, 43);
INSERT INTO `stucourse` VALUES (2, 3);
INSERT INTO `stucourse` VALUES (2, 5);
INSERT INTO `stucourse` VALUES (2, 6);
INSERT INTO `stucourse` VALUES (2, 7);
INSERT INTO `stucourse` VALUES (2, 8);
INSERT INTO `stucourse` VALUES (2, 9);
INSERT INTO `stucourse` VALUES (2, 10);
INSERT INTO `stucourse` VALUES (2, 30);
INSERT INTO `stucourse` VALUES (2, 31);
INSERT INTO `stucourse` VALUES (2, 32);
INSERT INTO `stucourse` VALUES (2, 33);
INSERT INTO `stucourse` VALUES (2, 34);
INSERT INTO `stucourse` VALUES (2, 35);
INSERT INTO `stucourse` VALUES (2, 36);

-- ----------------------------
-- Table structure for stuproblem
-- ----------------------------
DROP TABLE IF EXISTS `stuproblem`;
CREATE TABLE `stuproblem`  (
  `userid` int(10) NOT NULL,
  `problemid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `finishtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`, `problemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stuproblem
-- ----------------------------
INSERT INTO `stuproblem` VALUES (2, 'pro007', '2023-05-03 04:53:37');
INSERT INTO `stuproblem` VALUES (3, 'pro001', '2023-02-21 11:09:48');
INSERT INTO `stuproblem` VALUES (3, 'pro002', '2023-02-21 14:55:49');
INSERT INTO `stuproblem` VALUES (3, 'pro003', '2023-02-21 12:09:46');
INSERT INTO `stuproblem` VALUES (3, 'pro004', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (3, 'pro007', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (3, 'pro008', '2023-03-16 10:04:41');
INSERT INTO `stuproblem` VALUES (3, 'pro009', '2023-02-21 11:10:20');
INSERT INTO `stuproblem` VALUES (3, 'pro010', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (3, 'pro011', '2023-04-11 02:36:01');
INSERT INTO `stuproblem` VALUES (3, 'pro012', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (3, 'pro013', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (3, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (3, 'pro023', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (3, 'pro024', '2023-02-21 11:10:41');
INSERT INTO `stuproblem` VALUES (3, 'pro025', '2023-03-16 23:56:28');
INSERT INTO `stuproblem` VALUES (3, 'pro026', '2023-03-17 00:00:39');
INSERT INTO `stuproblem` VALUES (3, 'pro027', '2023-04-18 13:02:48');
INSERT INTO `stuproblem` VALUES (3, 'pro028', '2023-03-25 13:41:37');
INSERT INTO `stuproblem` VALUES (4, 'pro001', '2023-02-21 11:09:48');
INSERT INTO `stuproblem` VALUES (4, 'pro002', '2023-02-21 14:55:49');
INSERT INTO `stuproblem` VALUES (4, 'pro003', '2023-02-21 12:09:46');
INSERT INTO `stuproblem` VALUES (4, 'pro004', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (4, 'pro007', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (4, 'pro008', '2023-03-16 10:04:41');
INSERT INTO `stuproblem` VALUES (4, 'pro009', '2023-02-21 11:10:20');
INSERT INTO `stuproblem` VALUES (4, 'pro010', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (4, 'pro011', '2023-04-11 02:36:01');
INSERT INTO `stuproblem` VALUES (4, 'pro012', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (4, 'pro013', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (4, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (4, 'pro023', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (4, 'pro024', '2023-02-21 11:10:41');
INSERT INTO `stuproblem` VALUES (4, 'pro025', '2023-03-16 23:56:28');
INSERT INTO `stuproblem` VALUES (4, 'pro026', '2023-03-17 00:00:39');
INSERT INTO `stuproblem` VALUES (4, 'pro027', '2023-04-18 13:02:48');
INSERT INTO `stuproblem` VALUES (4, 'pro028', '2023-03-25 13:41:37');
INSERT INTO `stuproblem` VALUES (5, 'pro001', '2023-02-21 11:09:48');
INSERT INTO `stuproblem` VALUES (5, 'pro002', '2023-02-21 14:55:49');
INSERT INTO `stuproblem` VALUES (5, 'pro003', '2023-02-21 12:09:46');
INSERT INTO `stuproblem` VALUES (5, 'pro004', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (5, 'pro007', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (5, 'pro008', '2023-03-16 10:04:41');
INSERT INTO `stuproblem` VALUES (5, 'pro009', '2023-02-21 11:10:20');
INSERT INTO `stuproblem` VALUES (5, 'pro010', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (5, 'pro011', '2023-04-11 02:36:01');
INSERT INTO `stuproblem` VALUES (5, 'pro012', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (5, 'pro013', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (5, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (5, 'pro023', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (6, 'pro002', '2023-05-06 12:08:38');
INSERT INTO `stuproblem` VALUES (6, 'pro008', '2023-04-11 02:36:01');
INSERT INTO `stuproblem` VALUES (6, 'pro009', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (6, 'pro010', '2023-02-21 15:54:43');
INSERT INTO `stuproblem` VALUES (6, 'pro011', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (6, 'pro012', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (6, 'pro013', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (6, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (6, 'pro023', '2023-03-16 10:04:41');
INSERT INTO `stuproblem` VALUES (7, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (7, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (7, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (8, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (8, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (8, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (9, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (9, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (9, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (10, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (10, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (10, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (11, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (11, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (11, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (12, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (12, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (12, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (13, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (13, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (13, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (14, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (14, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (14, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (15, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (15, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (15, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (16, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (16, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (16, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (17, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (17, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (17, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (18, 'pro011', '2023-03-16 23:55:45');
INSERT INTO `stuproblem` VALUES (18, 'pro012', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (18, 'pro022', '2023-02-21 11:10:21');
INSERT INTO `stuproblem` VALUES (41, 'pro002', '2023-04-27 08:22:25');
INSERT INTO `stuproblem` VALUES (43, 'pro002', '2023-04-27 08:20:33');
INSERT INTO `stuproblem` VALUES (47, 'pro002', '2023-05-07 01:09:56');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学工号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` int(1) NULL DEFAULT 1 COMMENT '学生=1；教师=2；管理员=3',
  `finished` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sysadmin', 'sysadmin', '系统管理员', '15265441806@163.com', 3, '0');
INSERT INTO `user` VALUES (2, '20230507001', 'dpb123456', '李老师', '15265441806@163.com', 2, '1');
INSERT INTO `user` VALUES (3, '12019242305', 'dpb123456', '董培彬', '15265441806@163.com', 1, '18');
INSERT INTO `user` VALUES (4, '12019242317', '123456', '尹佳佳', '15295666273@163.com', 1, '18');
INSERT INTO `user` VALUES (5, '12019242353', '123456', '李陇', '13209509915@163.com', 1, '13');
INSERT INTO `user` VALUES (6, '12019242315', '123456', '袁夏莲', '18709598821@163.com', 1, '9');
INSERT INTO `user` VALUES (7, '12019242322', '123456', '张郡', '18895005620@163.com', 1, '3');
INSERT INTO `user` VALUES (8, '12019242370', '123456', '何海亮', '15349503680@163.com', 1, '3');
INSERT INTO `user` VALUES (9, '12019242328', '123456', '范嘉妮', '15593271895@163.com', 1, '3');
INSERT INTO `user` VALUES (10, '12019242355', '123456', '马波', '13895575130@163.com', 1, '3');
INSERT INTO `user` VALUES (11, '12019242400', '123456', '马学峰', '17398462461@163.com', 1, '3');
INSERT INTO `user` VALUES (12, '12019242331', '123456', '葛洋', '13325737454@163.com', 1, '3');
INSERT INTO `user` VALUES (13, '12019242457', '123456', '马尖', '13895559901@163.com', 1, '3');
INSERT INTO `user` VALUES (14, '12019242408', '123456', '晁博荣', '18295462081@163.com', 1, '3');
INSERT INTO `user` VALUES (15, '12019242452', '123456', '姚永刚', '17398488721@163.com', 1, '3');
INSERT INTO `user` VALUES (16, '12019242407', '123456', '杨涛', '13007969525@163.com', 1, '3');
INSERT INTO `user` VALUES (17, '12019242410', '123456', '赵天云', '15226225940@163.com', 1, '3');
INSERT INTO `user` VALUES (18, '12019242468', '123456', '刘雅宁', '15598427256@163.com', 1, '3');
INSERT INTO `user` VALUES (19, '12019242349', '123456', '李佳晨', '13037994022@163.com', 1, '0');
INSERT INTO `user` VALUES (20, '12019242368', '123456', '李寒', '15296995769@163.com', 1, '0');
INSERT INTO `user` VALUES (21, '12019242453', '123456', '杨金贵', '18809586399@163.com', 1, '0');
INSERT INTO `user` VALUES (22, '12019242380', '123456', '王军', '18795352258@163.com', 1, '0');
INSERT INTO `user` VALUES (23, '12019242411', '123456', '王玲娟', '15109612627@163.com', 1, '0');
INSERT INTO `user` VALUES (24, '12019242455', '123456', '马小峰', '19995286335@163.com', 1, '0');
INSERT INTO `user` VALUES (25, '12019242367', '123456', '冯波', '15509625352@163.com', 1, '0');
INSERT INTO `user` VALUES (26, '12019242396', '123456', '谢亚楠', '18895076065@163.com', 1, '0');
INSERT INTO `user` VALUES (27, '12019242426', '123456', '禹鹏飞', '13369586411@163.com', 1, '0');
INSERT INTO `user` VALUES (28, '12019242431', '123456', '杨建勇', '18309691617@163.com', 1, '0');
INSERT INTO `user` VALUES (29, '12019242360', '123456', '田雨欣', '17809551411@163.com', 1, '0');
INSERT INTO `user` VALUES (30, '12019242420', '123456', '陈智会', '17795155031@163.com', 1, '0');
INSERT INTO `user` VALUES (31, '12019242365', '123456', '王一帆', '18009533010@163.com', 1, '0');
INSERT INTO `user` VALUES (32, '12019242432', '123456', '马雪', '18395005851@163.com', 1, '0');
INSERT INTO `user` VALUES (33, '12019242429', '123456', '冯龙', '18295477606@163.com', 1, '0');
INSERT INTO `user` VALUES (34, '12019242336', '123456', '汪杰', '18695123531@163.com', 1, '0');
INSERT INTO `user` VALUES (35, '12019242447', '123456', '李小东', '18169165067@163.com', 1, '0');
INSERT INTO `user` VALUES (36, '12019242417', '123456', '苏福明', '17395483344@163.com', 1, '0');
INSERT INTO `user` VALUES (37, '12019242456', '123456', '马文凯', '15378922063@163.com', 1, '0');
INSERT INTO `user` VALUES (38, '12019242415', '123456', '陈子缘', '15609547155@163.com', 1, '0');
INSERT INTO `user` VALUES (39, '12019242386', '123456', '金娜', '18095566559@163.com', 1, '0');
INSERT INTO `user` VALUES (40, '12019242376', '123456', '杨琳琳', '18095112197@163.com', 1, '0');
INSERT INTO `user` VALUES (41, '12019242306', '123456', '夏立杰', 'www.1941379720@qq.com', 1, '1');
INSERT INTO `user` VALUES (43, '12019242307', '123456', '董培彬', 'www.1941379720@qq.com', 1, '1');
INSERT INTO `user` VALUES (45, '12019242412', '123456', '董培彬', '15265441806@163.com', 1, '0');
INSERT INTO `user` VALUES (46, '12019241305', '123456', '董培彬', 'www.1941379720@qq.com', 1, '0');
INSERT INTO `user` VALUES (47, '12019242000', 'dpb123456', '董某', 'www.1941379720@qq.com', 1, '1');

-- ----------------------------
-- Table structure for usermessage
-- ----------------------------
DROP TABLE IF EXISTS `usermessage`;
CREATE TABLE `usermessage`  (
  `userid` int(10) NOT NULL,
  `messageid` int(11) NOT NULL,
  `state` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`userid`, `messageid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of usermessage
-- ----------------------------
INSERT INTO `usermessage` VALUES (1, 1, 1);
INSERT INTO `usermessage` VALUES (1, 3, 1);
INSERT INTO `usermessage` VALUES (2, 1, 1);
INSERT INTO `usermessage` VALUES (2, 3, 1);
INSERT INTO `usermessage` VALUES (3, 1, 1);
INSERT INTO `usermessage` VALUES (3, 3, 1);
INSERT INTO `usermessage` VALUES (4, 1, 0);
INSERT INTO `usermessage` VALUES (4, 3, 0);
INSERT INTO `usermessage` VALUES (5, 1, 0);
INSERT INTO `usermessage` VALUES (5, 3, 0);
INSERT INTO `usermessage` VALUES (6, 1, 1);
INSERT INTO `usermessage` VALUES (6, 3, 1);
INSERT INTO `usermessage` VALUES (7, 1, 0);
INSERT INTO `usermessage` VALUES (7, 3, 0);
INSERT INTO `usermessage` VALUES (8, 1, 0);
INSERT INTO `usermessage` VALUES (8, 3, 0);
INSERT INTO `usermessage` VALUES (9, 1, 0);
INSERT INTO `usermessage` VALUES (9, 3, 0);
INSERT INTO `usermessage` VALUES (10, 1, 0);
INSERT INTO `usermessage` VALUES (10, 3, 0);
INSERT INTO `usermessage` VALUES (11, 1, 0);
INSERT INTO `usermessage` VALUES (11, 3, 0);
INSERT INTO `usermessage` VALUES (12, 1, 0);
INSERT INTO `usermessage` VALUES (12, 3, 0);
INSERT INTO `usermessage` VALUES (13, 1, 0);
INSERT INTO `usermessage` VALUES (13, 3, 0);
INSERT INTO `usermessage` VALUES (14, 1, 0);
INSERT INTO `usermessage` VALUES (14, 3, 0);
INSERT INTO `usermessage` VALUES (15, 1, 0);
INSERT INTO `usermessage` VALUES (15, 3, 0);
INSERT INTO `usermessage` VALUES (16, 1, 0);
INSERT INTO `usermessage` VALUES (16, 3, 0);
INSERT INTO `usermessage` VALUES (17, 1, 0);
INSERT INTO `usermessage` VALUES (17, 3, 0);
INSERT INTO `usermessage` VALUES (18, 1, 0);
INSERT INTO `usermessage` VALUES (18, 3, 0);
INSERT INTO `usermessage` VALUES (19, 1, 0);
INSERT INTO `usermessage` VALUES (19, 3, 0);
INSERT INTO `usermessage` VALUES (20, 1, 0);
INSERT INTO `usermessage` VALUES (20, 3, 0);
INSERT INTO `usermessage` VALUES (21, 1, 0);
INSERT INTO `usermessage` VALUES (21, 3, 0);
INSERT INTO `usermessage` VALUES (22, 1, 0);
INSERT INTO `usermessage` VALUES (22, 3, 0);
INSERT INTO `usermessage` VALUES (23, 1, 0);
INSERT INTO `usermessage` VALUES (23, 3, 0);
INSERT INTO `usermessage` VALUES (24, 1, 0);
INSERT INTO `usermessage` VALUES (24, 3, 0);
INSERT INTO `usermessage` VALUES (25, 1, 0);
INSERT INTO `usermessage` VALUES (25, 3, 0);
INSERT INTO `usermessage` VALUES (26, 1, 0);
INSERT INTO `usermessage` VALUES (26, 3, 0);
INSERT INTO `usermessage` VALUES (27, 1, 0);
INSERT INTO `usermessage` VALUES (27, 3, 0);
INSERT INTO `usermessage` VALUES (28, 1, 0);
INSERT INTO `usermessage` VALUES (28, 3, 0);
INSERT INTO `usermessage` VALUES (29, 1, 0);
INSERT INTO `usermessage` VALUES (29, 3, 0);
INSERT INTO `usermessage` VALUES (30, 1, 0);
INSERT INTO `usermessage` VALUES (30, 3, 0);
INSERT INTO `usermessage` VALUES (31, 1, 0);
INSERT INTO `usermessage` VALUES (31, 3, 0);
INSERT INTO `usermessage` VALUES (32, 1, 0);
INSERT INTO `usermessage` VALUES (32, 3, 0);
INSERT INTO `usermessage` VALUES (33, 1, 0);
INSERT INTO `usermessage` VALUES (33, 3, 0);
INSERT INTO `usermessage` VALUES (34, 1, 0);
INSERT INTO `usermessage` VALUES (34, 3, 0);
INSERT INTO `usermessage` VALUES (35, 1, 0);
INSERT INTO `usermessage` VALUES (35, 3, 0);
INSERT INTO `usermessage` VALUES (36, 1, 0);
INSERT INTO `usermessage` VALUES (36, 3, 0);
INSERT INTO `usermessage` VALUES (37, 1, 0);
INSERT INTO `usermessage` VALUES (37, 3, 0);
INSERT INTO `usermessage` VALUES (38, 1, 0);
INSERT INTO `usermessage` VALUES (38, 3, 0);
INSERT INTO `usermessage` VALUES (39, 1, 0);
INSERT INTO `usermessage` VALUES (39, 3, 0);
INSERT INTO `usermessage` VALUES (40, 1, 0);
INSERT INTO `usermessage` VALUES (40, 3, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(5) NOT NULL,
  `username` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `level` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '123', 500);
INSERT INTO `users` VALUES (2, 'pikachu', '456', 2);
INSERT INTO `users` VALUES (3, 'test', '789', 3);

-- ----------------------------
-- Table structure for usersqli
-- ----------------------------
DROP TABLE IF EXISTS `usersqli`;
CREATE TABLE `usersqli`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of usersqli
-- ----------------------------
INSERT INTO `usersqli` VALUES (1, 'lc', '123', '1');
INSERT INTO `usersqli` VALUES (2, '123', '123', '3');

-- ----------------------------
-- Table structure for xssstore
-- ----------------------------
DROP TABLE IF EXISTS `xssstore`;
CREATE TABLE `xssstore`  (
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `xsscontent` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xssstore
-- ----------------------------
INSERT INTO `xssstore` VALUES ('dpb123', '<script>console.log(\"dpb123\")</sCript>');
INSERT INTO `xssstore` VALUES ('100001', '<script>console.log(\"100001\")</script>');
INSERT INTO `xssstore` VALUES ('12019242307', '<script>console.log(\"12019242307\")</script>');
INSERT INTO `xssstore` VALUES ('12019242305', '<script>console.log(\"12019242305\")</script>');

SET FOREIGN_KEY_CHECKS = 1;
