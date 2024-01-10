<template>
  <div class="tools">
    <div>
      <h1 style="text-align: left;
          color: white;
          padding-top: 35px;
          margin-top: 0px;
          margin-bottom:25px;
          font-size: 35px">
        网络安全工具库
        <i class="el-icon-d-arrow-right"></i>
      </h1>
    </div>
    <div>
      <el-row>
        <el-col  v-for="(tool, index) in toolsinfo"
                 :key="index"
                 :span="6">
          <el-card shadow="hover"
                   style=" margin:10px;
                   height:200px;
                   cursor:pointer;
                   background-color: rgba(0,0,0,0.5);
                   color: white;
                   border-style: none">
            <el-row>
              <el-col :span="6">
                <!--<el-image :src="tool.imgurl" style="float: left; width: 85%;heigth:85%;margin-right: 15%;" />-->
                <i :class="tool.imgurl"
                   style="font-size: 60px;"></i>
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="16">
                <h4 style="margin-top:5px;
                    text-align: left;
                    font-size: 15px">{{ tool.toolname}}</h4>
                <el-divider style="margin: 0px;
                            padding: 0px;
                            background-color: rgba(255,255,255,0.5)"/>
                <p style="text-align: left;
                    font-size: 10px;
                    height: 10px">{{ tool.toolcontent}}</p>
              </el-col>
            </el-row>
            <el-divider />
            <el-button @click="gotopage(tool.toolurl)"
                       style="width: 100%;
                       background-color: rgba(138,43,226,0.2);
                       color: white"> 查看 </el-button>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script>
import animation from "../assets/js/background_black";

export default {
  data(){
    return{
      toolsinfo:[
        {
          toolname:'子域名查询',
          toolcontent:'在线子域名查询工具',
          toolurl:'/domainfindtool',
          imgurl:'el-icon-monitor',
        },
        {
          toolname:'IP查询',
          toolcontent:'在线IP信息查询工具',
          toolurl:'/ipinfotool',
          imgurl:'el-icon-delete-location',
        },
        {
          toolname:'Port Scan',
          toolcontent:'在线端口扫描工具',
          toolurl:'/scanporttool',
          imgurl:'el-icon-search',
        },
        {
          toolname:'base64编码',
          toolcontent:'在线Base64编码解码工具',
          toolurl:'/base64tool',
          imgurl:'el-icon-sort',
        },
        {
          toolname:'URL编码',
          toolcontent:'在线URL编码转换',
          toolurl:'/urlencode',
          imgurl:'el-icon-refresh',
        },
        {
          toolname:'凯撒密码加解密',
          toolcontent:'在线凯撒密码翻译器',
          toolurl:'/CaesarCrypt',
          imgurl:'el-icon-s-operation',
        },
        {
          toolname:'unicode编码',
          toolcontent:'在线unicode编码',
          toolurl:'/unicode',
          imgurl:'el-icon-help',
        },
        {
          toolname:'Rot13加解密',
          toolcontent:'在线Rot13加解密',
          toolurl:'/rot13',
          imgurl:'el-icon-money',
        },
        {
          toolname:'HASH计算',
          toolcontent:'支持多种HASH加密',
          toolurl:'/hash',
          imgurl:'el-icon-guide',
        },
        {
          toolname:'DES加解密',
          toolcontent:'对称密码-DES在线加解密',
          toolurl:'/des',
          imgurl:'el-icon-cpu',
        },
        {
          toolname:'RC4密码',
          toolcontent:'流密码-在线RC4加解密',
          toolurl:'/rc4',
          imgurl:'el-icon-lock',
        },
      ]
    }
  },
  created() {
    animation()
  },
  methods: {
    gotopage(url){
      let current_url=window.location.href.slice(0,-8);
      let new_url=current_url+url;
      window.open(new_url);
    },
  }
};
</script>


<style scoped>
.search{
  height:200px;
  /*background-color: #475669;*/
  margin-left:10%;
  margin-right:10%;
  /*margin-top:2%;*/
}
.tools{
  height: 800px;
  /*background-color: #f56c6c;*/
  margin-left:8%;
  margin-right:8%;
  /*margin-top:2%;*/
}
.dv-border{
  /*margin:10px;*/
  height:250px;
  cursor:pointer;
  background-color: #f56c6c;
}
</style>