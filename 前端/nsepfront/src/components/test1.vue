<template>

  <el-drawer v-model="drawer1" direction="ttb" :with-header="false" size="100%">
    <div class="part1" @click="drawer1=false">
      <!--      <el-card id="intro">-->
      <div style="color: white;margin-bottom:5%;padding-bottom: 0px;position: absolute;bottom: 5%">
        <div style="margin-left: 5%">
          <h1 style="text-align: left;font-family:宋体;font-size: 65px;">靶场简介</h1>
          <el-divider   style="margin-bottom: 0px;width: 60%"/>
          <p style="text-align: left;font-size: 17px;width: 60%;line-height: 30px">本靶场含有众多的靶机,它们场景各异、所含漏洞不尽相同。<br/>点击左下方按钮，可以显示该漏洞的简介，
            下边是若干靶机，每个靶机都集成了该漏洞。<br/>你需要阅读提示，利用靶机环境中存在的漏洞，破解它并找出隐藏在其中的一串随机字符串，我们称之
            为FLAG。当你找到了FLAG,别忘记将它提交，此时你便成功、完整的征服了该靶机！</p>
        </div>
      </div>
      <!--      </el-card>-->
    </div>
  </el-drawer>

<!--  板块一-->
  <div style="height: 800px;
        background-color: rgba(211,211,211,0.8)">
    <el-button
        style="height: 80px;
        /*background-color: #475669;*/
        border-style: none;
        background-color: rgba(0,0,0,0);
        margin-top: 10px;
        display: inline;
        text-align: left;
        border-style: none;"
        @click="drawer1=true"
    >
      <i  style="position:relative;
            color: #475669;
            font-size:60px;
            margin-bottom: 0px;
            padding-bottom: 0px"
          class="el-icon-arrow-up"></i>
    </el-button>
    <el-row style="height: 500px;">

      <!--右侧漏洞详解-->
      <el-col :span="11" style="background-color: rgba(0,0,0,0)">

        <el-card class="card1" style="background-color:rgba(128,128,128,0.2);margin-left: 5%" >
          <h1 style="color: #2c3e50;font-size: 30px;"> 漏洞概述 </h1>
          <el-carousel height="450px" autoplay="false" arrow="always">
            <el-carousel-item v-if="introimg!=''">
              <el-image :src="introimg" style="height:100%;width:100%;"/>
            </el-carousel-item >
            <el-carousel-item >
              <p style="margin-right: 10px;margin-left:10px;margin-top:0;color: black;font-size: 20px;text-align: left;">
                {{introduction}}
              </p>
            </el-carousel-item>
          </el-carousel>
        </el-card>

      </el-col>


<!--分割线-->
      <el-col :span="2">
        <el-divider direction="vertical"
                    style="height: 90%;
                    margin-top: 20px;"></el-divider>
      </el-col>

      <!--      左侧九宫格漏洞选择按钮-->
      <el-col :span="11">
        <div style="margin-top: 10%;">
          <div class="select" >
            <el-button class="btn-group" type="info" @click="getprobleminfo('force')"> 暴力破解</el-button>
            <el-button class="btn-group" type="info" @click="getprobleminfo('rce')"> RCE</el-button>
            <el-button class="btn-group" type="info" @click="getprobleminfo('filedownload')" style="width: 25%"> 文件下载漏洞</el-button>
            <el-button class="btn-group" type="info" @click="getprobleminfo('fileupload')" style="width: 25%"> 文件上传漏洞</el-button>
          </div>
          <div class="select" >
            <el-button class="btn-group"  type="info" @click="getprobleminfo('sqli')"> sql注入</el-button>
            <el-button class="btn-group"  type="info" @click="getprobleminfo('javadeser')" style="width: 30%"> JAVA反序列化漏洞</el-button>
            <el-button class="btn-group"  type="info" @click="getprobleminfo('xss')"> XSS</el-button>
            <el-button class="btn-group"  type="info" @click="getprobleminfo('xxe')"> XXE</el-button>
          </div>
          <div class="select" >
            <el-button class="btn-group"  type="info" @click="getprobleminfo('urlredirect')" style="width: 20%"> 重定向漏洞</el-button>
            <el-button class="btn-group"  type="info" @click="getprobleminfo('BAC')"> 逻辑越权</el-button>
            <el-button class="btn-group"  type="info" @click="getprobleminfo('pathtravel')"> 目录遍历</el-button>
          </div>
        </div>

      </el-col>

    </el-row>
  </div>

  <el-drawer v-model="drawer"  :with-header="false" size="52%">
    <div style="width: 100%;height: 100%;background-color: rgba(0,0,0,0.7)">
<!--      <div class="problem">-->
        <el-row style="height: 100%;">
          <!--靶机-->
          <el-col :span="24" style="">
<!--            <div style="height: 100%;width: 100%;margin-top: 5%;">-->
              <el-card class="card2">
                <div :style="{ height: windowHeight + 'px' }">
                  <el-scrollbar >
                    <el-card style="margin-bottom: 5%;margin-left: 10%;margin-right: 5%" class="problem-card" v-for="(problem, problemid) in problemlist" :key="problemid" @click="gotopro(problem.problemid)">
                      <i style="float:left;font-size: 60px;color: #8cc5ff;" :class="iconurl"></i>
                      <h2 style="color: white;margin: 50px;font-size: 25px;"> 靶机代号：{{problem.problemname}}</h2>
                    </el-card>
                  </el-scrollbar>
                </div>
              </el-card>
<!--            </div>-->
          </el-col>

        </el-row>

<!--      </div>-->
    </div>

  </el-drawer>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      windowWidth: document.documentElement.clientWidth,  //实时屏幕宽度
      windowHeight: document.documentElement.clientHeight,   //实时屏幕高度
      drawer:false,
      drawer1:false,
      problemlist:[],
      iconurllist:{
        sqli:'el-icon-key',
        rce:'el-icon-s-promotion',
        xss:'el-icon-eleme',
        xxe:'el-icon-document-delete',
        javadeser:'el-icon-edit',
        filedownload:'el-icon-tickets',
        fileupload:'el-icon-document-copy',
        urlredirect:'el-icon-refresh',
        BAC:'el-icon-s-custom',
        pathtravel:'el-icon-tickets'
      },
      iconurl:'',
      introductionlist:{
        sqli:" Sql 注入攻击是通过将恶意的 Sql 查询或添加语句插入到应用的输入参数中，再在后台 Sql 服务器上解析执行进行的攻击，它目前黑客对数据库" +
            "进行攻击的最常用手段之一。当我们访问动态网页时, Web 服务器会向数据访问层发起 Sql 查询请求，如果权限验证通过就会执行 Sql 语句。\n" +
            " 这种网站内部直接发送的Sql请求一般不会有危险，但实际情况是很多时候需要结合用户的输入数据动态构造 Sql 语句，如果用户输入的数据被构造" +
            "成恶意 Sql 代码，Web 应用又未对动态构造的 Sql 语句使用的参数进行审查，则会带来意想不到的危险。Sql 注入带来的威胁主要有如下几点猜解" +
            "后台数据库，这是利用最多的方式，盗取网站的敏感信息。绕过认证，列如绕过验证登录网站后台。注入可以借助数据库的存储过程进行提权等操作。",

        rce:"RCE漏洞，可以让攻击者直接向后台服务器远程注入操作系统命令或者代码，从而控制后台系统。\n" +
            "原理:一般出现这种漏洞，是因为应用系统从设计上需要给用户提供指定的远程命令操作的接口。比如我们常见的路由器、防火墙、入侵检测等设备的web" +
            "管理界面上。一般会给用户提供一个ping操作的web界面，用户从web界面输入目标IP，提交后，后台会对该IP地址进行一次ping测试，并返回测试结" +
            "果。 如果，设计者在完成该功能时，没有做严格的安全控制，则可能会导致攻击者通过该接口提交“意想不到”的命令，从而让后台进行执行，从而控制" +
            "整个后台服务器。 现在很多的企业都开始实施自动化运维,大量的系统操作会通过\"自动化运维平台\"进行操作。在这种平台上往往会出现远程系统命" +
            "令执行的漏洞。 远程代码执行 同样的道理,因为需求设计,后台有时候也会把用户的输入作为代码的一部分进行执行,也就造成了远程代码执行漏洞。 " +
            "不管是使用了代码执行的函数,还是使用了不安全的反序列化等等。",

        xss:"跨站脚本攻击(Cross Site Scripting)，恶意攻击者往Web页面里插入恶意Script代码，当用户浏览该页之时，嵌入其中Web里面的Script代码" +
            "会被执行，从而达到恶意攻击用户的目的。一共有三种类型的XSS：1.反射型XSS又称非持久性XSS，这种攻击往往具有一次性。攻击者通过邮件等形式" +
            "将包含XSS代码的链接发送给正常用户，当用户点击时，服务器接受该用户的请求并进行处理，然后把带有XSS的代码发送给用户。用户浏览器解析执行" +
            "代码，触发XSS漏洞。2. 存储型 XSS攻击脚本存储在目标服务器的数据库中，具有更强的隐蔽性。攻击者在论坛、博客、留言板中，发帖的过程中嵌入" +
            "XSS攻击代码，帖子被目标服务器存储在数据库中。当用户进行正常访问时，触发XSS代码。3. DOM型 XSSDOM型XSS全称Document Object Mode" +
            "l，使用DOM动态访问更新文档的内容、结构及样式。HTML标签都是节点，节点组成了节点树。通过HTML　DOM 可以对树上的所有节点进行修改。\n" +
            "服务器响应不会处理攻击者脚本，而是用户浏览器处理这个响应时，DOM对象就会处理XSS代码，触发XSS漏洞。",

        xxe:"任何可以上传XML文件的位置都有可能存在XXE漏洞。若没有对上传的XML文件进行过滤，可导致上传恶意文件。Web应用的脚本代码没有限制XML引入外" +
            "部实体，从而导致测试者可以创建一个包含外部实体的XML，使得其中的内容会被服务器端执行当允许引用外部实体时，通过构造恶意内容，就可能导致" +
            "任意文件读取、系统命令执行、内网端口探测、攻击内网网站等危害。",

        javadeser:"Java反序列化是指把字节序列恢复为Java对象的过程；2015 年年底，由公共依赖库 Apache Common Collections 引起的 Java 任意" +
            "命令执行漏洞的严重安全问题，使得 Java 反序列化漏洞逐渐进入了安全研究人员的视野（在此之前存在，但并未被重视），而任意命令执行的成因正" +
            "是反序列化操作的 ObejctInputStream 类的 readObject 方法触发的。该漏洞的危害在于：如果Java应用对用户输入，即不可信数据做了反序" +
            "列化处理，那么攻击者可以通过构造恶意输入，让反序列化产生非预期的对象，非预期的对象在产生过程中就有可能带来任意代码执行。",

        filedownload:"文件下载漏洞是指文件下载功能没有对下载的文件类型、目录做合理严谨的过滤，导致用户可以下载服务器的任意文件。用户可以任意下载" +
            "文件，例如脚本、代码服务器配置文件等。利用得到的代码进行审计分析有可能得到其它漏洞来进行攻击",

        fileupload: "大部分的网站和应用系统都有上传功能，而程序员在开发文件上传功能时，并未考虑文件格式后缀的合法性校验或者是否只在前端通过js进行" +
            "后缀检验。这时攻击者可以上传一个与网站脚本语言相对应的恶意代码动态脚本，例如(jsp、asp、php、aspx文件后缀)到服务器上，从而访问这些恶" +
            "意脚本中包含的恶意代码，进行动态解析最终达到执行恶意代码的效果，进一步影响服务器安全。文件上传漏洞是指用户上传了一个可执行的脚本文件，" +
            "并通过此脚本文件获得了执行服务器端命令的能力。一般都是指“上传Web脚本能够被服务器解析”的问题。\n",

        urlredirect:"URL重定向（URLredirection）漏洞，又称跳转漏洞。指的是网络应用程序接受用户可控的输入作为到外部站点的链接，然后在重定向中" +
            "使用该链接。可以认为是SSRF一种。漏洞原理：http参数可能包含URL值，并且可能导致Web应用程序将请求重定向到指定的URL。通过将URL值修改为" +
            "恶意站点，攻击者可以成功启动网络钓鱼诈骗并窃取用户凭据。",

        BAC:"逻辑越权漏洞属于漏洞分类中的服务端漏洞，属于权限控制类漏洞越权漏洞是比较常见的漏洞类型，通常分为两种类型，垂直和水平越权，水平越权：也" +
            "叫做访问控制攻击。web应用程序在接受用户发送过来的操作请求时，没有判断这条请求的数据操作所属人，攻击者使A用户具有操作B账号的权力。垂直" +
            "越权：也叫做权限提升攻击。使用低权限的账号，发送高权限账号才能有的请求，获得其高权限的操作。漏洞原理：通常用户访问一个应用的大致流程是" +
            "：登陆—验证权限—数据库查询—数据返回，如果\"验证权限\"这个阶段出现验证权限不足或没有验证，那么就会导致越权，用户A可以操作其他用户的内容。",

        pathtravel:"目录遍历（也称为文件路径遍历）是一个Web安全漏洞，它使攻击者可以读取运行应用程序的服务器上的任意文件。" +
            "这可能包括应用程序代码和数据，后端系统的凭据以及敏感的操作系统文件。" +
            "在某些情况下，攻击者可能能够写入服务器上的任意文件，从而允许他们修改应用程序数据或行为，并最终完全控制服务器。\n"
      },
      introduction:'',
      introimglist:{
        sqli:require('@/assets/img/sqli.png'),
        rce:require('@/assets/img/rce.webp'),
        xss:require('@/assets/img/xss1.webp'),
        xxe:require('@/assets/img/xxe.jpg'),
        javadeser:require('@/assets/img/javadeser.png'),
        filedownload:require('@/assets/img/filedownload.jpg'),
        // fileupload:require('@/assets/img/fileupload.webp'),
        fileupload:require('@/assets/img/fileupload.jpg'),
        urlredirect:require('@/assets/img/urlredirect.jpg'),
        BAC:'',
        pathtravel:'',
      },
      introimg:'',
    }
  },
  created() {
    this.getprobleminfo('force')
    this.drawer=false
  },
  methods:{
    // 获取题目信息
    getprobleminfo(type){
      this.drawer=true
      request.get("/problem/getinfobytype",{
        params:{
          problemtype:type
        }
      }).then(res =>{
        this.problemlist={};
        this.problemlist=res.data;
      })
      this.introduction=this.introductionlist[type];
      this.iconurl=this.iconurllist[type];
      this.introimg='';
      this.introimg=this.introimglist[type];
    },

    // 加载题目页面
    gotopro(problemid){
      this.$router.push({path:'/bugpage',query: {id:problemid}})
    },

  },

}
</script>

<style scoped>
.select{
  padding-bottom: 40px;
  margin-left: 5%;
  text-align: left;
  /*padding-right: 0px;*/
  /*margin-right: 0px;*/
  /*background-color: rgba(255,255,255,0.1);*/
}
.btn-group{
  height: 100px;
  /*width: 25%;*/
  width: 20%;
  background-color: #475669;
  color: white;
  /*border-color: #8cc5ff;*/
  border-style: none;
  border-radius: 0;

}
p{
  font-family: "LiSu";
  line-height:30px;
}
.problem-card{
  background-color:rgba(128,138,135,0.3);
  /*background-color:#606266;*/
  height: 30%;
  margin: 10px;
  margin-right: 20px;
  cursor:pointer;
  border-width: 5px;
  border-color:#808A87;
}

.card2{
  /*height:550px;*/
  /*margin: 20px;*/
  position: relative;
  background-color: rgba(0,0,0,0.9);
  border-color: #475669;
  /*padding: 0px;*/
  /*border-style: none;*/
}
.part1{
  height: 800px;
  background-color:rgba(255,255,255,1);
  background:url("../assets/img/carousel_2.jpg");
  background-size: 100% 100%;
  padding-top: 5%;
}
</style>