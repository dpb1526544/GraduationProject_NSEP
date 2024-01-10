<template>
<div id="building">

  <div style="margin-top: 0.5%;">
    <el-button @click="problem"
               style="float: left;
               margin-left: 15%">题目要求</el-button>
    <el-input v-model="xss_store"
              style="width: 25%;
              margin-left:20%;"
              placeholder="payload"></el-input>
    <el-button @click="xss_store_fun"
               style="margin-left: 1%">搜索</el-button>
    <el-button v-if="payload==1"
               @click="show"> 查看 </el-button>
  </div>

</div>

  <el-dialog v-model="problemdetail"
             title="题目要求"
             width="30%">
    <p>
      该页面存在存储型XSS漏洞，能否构造payload，使得在控制台输出你的学号？
    </p>
    <p>
      输入payload，搜索后，点击查看按钮，查看结果。
    </p>
  </el-dialog>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      xss_store:'',
      myresult:'',
      myresult_cookie:'',
      payload:0,
      problemdetail:false,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    xss_store_fun(){
      request.get("/xss/stored/store",{
        params:{
          xss:this.xss_store,
          username:this.user.username
        }
      }).then(res =>{
        // this.myresult=res.data;
        // alert(this.myresult)
        console.log(res)
        this.payload=1
      })
    },
    show(){
      request.get("/xss/stored/show",{
        params:{
          username:this.user.username
        }
      }).then(res =>{

        if(res.code==200){
          alert("答案正确！")
          console.log(res.data)
          document.write(res.msg)
        }
        else if(res.code==500){
          alert("答案错误！")
          document.write(res.msg)
        }
      })
    },
    problem(){
      this.problemdetail=true
    }
  }
}
</script>

<style scoped>
#building{
  background:url("../../../assets/img/bugimg/weibo.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>