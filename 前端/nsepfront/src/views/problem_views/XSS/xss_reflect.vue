<template>
  <div id="building">

    <div style="margin-top: 1%;
          margin-left: 50%">
      <el-input v-model="xss_reflect"
                style="width: 45%;
                margin-right: 5%;"
                placeholder="payload"></el-input>
      <el-button @click="xss_reflect_fun"
                 type="success"
                 plain>搜索</el-button>
      <el-button @click="problem"
                 type="success"
                 plain>题目要求</el-button>
    </div>
  </div>

  <el-dialog v-model="problemdetail"
             title="题目要求"
             width="30%">
    <p>该页面存在反射型XSS漏洞，能否构造payload，使得页面出现弹窗(弹窗内容为你的学号)？</p>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";
// import router from "@/router";

export default {
  data(){
    return{
      xss_reflect:'',
      myresult:'',
      problemdetail:false,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    xss_reflect_fun(){
      request.get("/xss/reflect",{
        params:{
          xss:this.xss_reflect,
          username:this.user.username
        }
      }).then(res =>{
        this.myresult=res.data;
        console.log(res.code)
        console.log(res.data)

        if(res.code==200)
          document.write(this.myresult);
        else
          location.reload()


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
  background:url("../../../assets/img/bugimg/MOOC.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

/deep/ .el-input__inner {
  background-color: rgba(255, 255, 255, 0.5) !important;
}


</style>