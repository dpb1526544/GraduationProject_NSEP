<template>
  <!--  做题页面-->
  <div class="BugPage">
    <nsepheader></nsepheader>
    <div style="float: left;margin:30px; margin-left: 150px">
      <el-button style="background-color:#292421;color: white;border-color: #8cc5ff;"  @click="goback">返回</el-button>
    </div>
    <!--    卡片-->
    <div>
      <el-card class="Pagecard" style="border-style: none">
        <!--靶机信息-->
        <div style="padding-left: 40px;">
          <el-row>
            <el-col :span="20">
              <h1 style="color: white;text-align: left;font-size: 30px;">{{probleminfo.problemname}}</h1>
            </el-col>
            <el-col :span="4">
              <el-button style="width: 80px;height:20px;margin:20px;text-align: center" type="success" plain @click="gotowp">
                <i class="el-icon-tickets" ></i>WP
              </el-button>
            </el-col>
          </el-row>
          <div style="text-align: left;">
            <h2 style="display:inline-block; color: white;">难度系数：</h2>
            <el-rate  style="display:inline-block;font-size: 20px" v-model="probleminfo.problemratio" disabled/>
          </div>
          <div style="text-align: left;">
            <h2 style="color: white;display:inline-block;">场景描述：</h2>
            <p style="display:inline-block;color: #DCDCDC;font-size: 15px">{{probleminfo.problemcontent}}</p>
          </div>
          <div style="text-align: left;">
            <h2 style="color: white;display:inline-block;">题目附件：</h2>
            <p style="display:inline-block;color: #DCDCDC;font-size: 15px">暂无</p>
          </div>
          <div v-if="dialogVisible==false" style="text-align: left;height:150px;margin-top: 20px;">
            <el-button v-loading="loading" style="background-color: #292421;color: white;width: 200px;border-color: #8cc5ff;" @click="openenviorment()">
              <h3>点击创建靶机环境</h3>
            </el-button>
          </div>
          <div v-if="dialogVisible==true" class="btn2" >
            <el-progress class="progress" v-if="link==0" :text-inside="true" :stroke-width="20" :percentage="percentage" :color="customColorMethod(percentage)" />
            <p @click="gotopage" v-if="link==1" style="cursor: pointer;display: block;color: white;padding-top:10px;text-align: left;font-size: 20px;height:35px">
              <i class="el-icon-s-platform" style="margin-right: 10px;margin-left: 10px;"></i>
              点击此链接进入环境
            </p>
            <el-button class="closebtn" @click="closeenviorment">
              关闭当前靶机环境
            </el-button>
          </div>
        </div>


        <!--提交按钮-->
        <div style="">
          <el-input v-model="input" placeholder="flag{}" style="width:60%;display:block;margin-left: 20%" clearable />
          <el-button  v-if="problemstate==0" @click="submit" style="width: 150px;margin-top: 20px; background-color: #303133;color: #99a9bf;border-color: #8cc5ff;">提 交</el-button>
          <el-button  v-if="problemstate==1"  style="width: 150px;margin-top: 20px;" type="success" plain disabled>已完成</el-button>
        </div>
      </el-card>
    </div>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "@/components/nsepheader";
import nsepfooter from '../components/nsepfooter.vue';
import router from "@/router";
import request from "@/utils/request";
import animation from "../assets/js/background_black";
import { ElMessage } from 'element-plus'
export default {
  components: {nsepheader,nsepfooter},
  data(){
    return{
      problemstate:'',
      input:'',
      probleminfo:{
        problemid:this.$route.query.id,
        problemname:'',
        problemcontent:'',
        problemratio:4,
      },
      dialogVisible:false,
      loading:false,
      percentage:0,
      link:0,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dockerurl:''
    }
  },
  created() {
    animation()
    this.getprobleminfo()
    this.getproblemstate()
  },
  methods:{
    getproblemstate(){
      request.get("/stuproblem/getinfo",{
        params:{
          userid:this.user.userid,
          problemid:this.probleminfo.problemid
        }
      }).then(res =>{
        if (res.data!=null){
          this.problemstate=1
        }
        else this.problemstate=0 //标识未完成
      })
    },
    goback(){
      router.push('/honeypot');
    },
    getprobleminfo(){
      request.get("/problem/getinfobyid",{
        params:{
          problemid:this.probleminfo.problemid
        }
      }).then(res =>{
        this.probleminfo=res.data;
      })
    },
    submit(){
      request.get("/FlagConfirm",{
        params:{
          username:this.user.username,
          problemid:this.probleminfo.problemid,
          flag:this.input
        }
      }).then(res =>{
        if (res.data=="true"){
          ElMessage({
            message: 'Congrats！flag正确',
            center: true,
            type: 'success',
          })
          this.record()
        }else{
          ElMessage({
            message: '错误的flag',
            center: true,
            type: 'error',
          })
        }
        // alert(res.data)
        console.log(res.data)
      })
    },
    record(){
      request.get("/stuproblem/recordinfo", {
        params:{
          userid:this.user.userid,
          problemid:this.probleminfo.problemid,
        }
      }).then(res => {
        if (res.code === '200') {
          // alert('存储成功')
          location.reload();
        } else {
          this.$message.error('数据库连接失败')
        }
      })
    },
    gotowp(){
      router.push('/forum')
    },
    openenviorment(){
      this.loading=true;
      var that=this;
      setTimeout(function(){
        that.loading=false;
        that.dialogVisible=true;
        },1000)

      if (this.probleminfo.url=='3'){
        //动态创建docker
        this.createdocker();

        setTimeout(function(){that.percentage=that.percentage+3;},2000)
        setTimeout(function(){that.percentage=that.percentage+7;},3000)
        setTimeout(function(){that.percentage=that.percentage+15;},5000)
        setTimeout(function(){that.percentage=that.percentage+25;},7000)
        setTimeout(function(){that.percentage=that.percentage+40;},9000)
        setTimeout(function(){that.percentage=that.percentage+10;},12000)
        setTimeout(function(){
          that.link=1;
        },14000)
      }else{
        setTimeout(function(){
          that.percentage=that.percentage+100;
        },1000)
        setTimeout(function(){
          that.link=1;
        },2000)
      }
    },
    gotopage(){
      if (this.probleminfo.url=='3'){
        window.open(this.dockerurl)
      }else {//题号即路由
        let current_url=window.location.href.slice(0,-17);
        // console.log(current_url)
        let href=current_url+this.probleminfo.problemid;
        // console.log(href)
        window.open(href);
      }
    },
    closeenviorment(){
      this.dialogVisible=false;
      this.link=0;
      this.percentage=0;
    },
    customColorMethod (percentage) {
      if (percentage < 30) {
        return '#909399';
      }
      if (percentage < 70) {
        return '#e6a23c'
      }
      return '#67c23a';
    },
  }
}

</script>


<style scoped>
.BugPage{
  margin: 0px;
  /*background-image: linear-gradient(to bottom right, #000000, 	#483D8B);*/
  position: relative;
  width:100%;
  min-height: 100vh;
}
.Pagecard{
  width: 80%;
  height:65%;
  margin: 150px;
  margin-top:110px;
  background-color: rgba(155,155,155,0.6);
  border-color: #808A87;
}
.btn2{
  text-align: left;
  height:120px;
  margin-top: 20px;
  width:30%;
  margin-bottom:30px;
  background-color: rgba(0,0,0,0.1);
}
.closebtn{
  background-color: #292421;
  color: white;
  width: 200px;
  height:50px;
  margin-left:5%;
  text-align: center;
  border-color: #8cc5ff;
  margin-bottom: 0px;
}
.el-input >>> .el-input__inner {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
}
.progress{
  display: block;
  padding-top:20px;
  height:40px;
  width: 70%;
  margin-left:5%;
}
</style>