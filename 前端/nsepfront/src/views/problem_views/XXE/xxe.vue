<template>
<div class="building">
  <div style="margin-top: 5%;margin-bottom: 2%">
    <h1 style="color: white">XXE漏洞靶场</h1>
    <h1 style="color: white">这是一个XXE漏洞环境，试着构造payload读取/test/XXE/XXE.txt</h1>
  </div>

  <div style="margin-top: 2%">
    <el-input v-model="param.xxe" style="width: 250px;margin-right: 50px" placeholder="payload"></el-input>
    <el-button @click="xxefun" type="info" plain>submit</el-button>
  </div>

  <el-card style="width: 50%;height: 50%;margin-left: 25%;margin-top: 5%;word-break:break-all;background-color: rgba(135,135,135,0.8)">
    <!--    style=word-break:break-all-->
    <h1 style="color: white;">返回结果:</h1>
    <h3 style="color: white;"> {{ myresult }}</h3>

  </el-card>
</div>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      param:{
        xxe:''
      },

      myresult:'',

    }
  },
  methods:{
    xxefun(){
      request.post("/xxe/payload",this.param).then(res =>{
        this.myresult=res.data
      })
    }
  }
}
</script>

<style scoped>
.building{
  background:url("../../../assets/img/bugimg/xxe.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
</style>
