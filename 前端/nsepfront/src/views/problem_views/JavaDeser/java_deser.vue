<template>
  <div class="building">
    <title>java反序列化</title>

    <div style="margin-top: 5%;margin-bottom: 2%">
      <h1 style="color: white">java反序列化漏洞（源码分析），部分序列化代码通过“下载部分源码”按钮获取</h1>
    </div>

    <el-button type="info" @click="download" plain> 下载部分源码</el-button>
    <el-button type="info" @click="ser" plain> 点击执行java序列化代码 </el-button>

    <el-card style="width: 50%;height: 50%;margin-left: 25%;margin-top: 5%;word-break:break-all;background-color: rgba(135,135,135,0.8)">

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
      der_data:'',
      myresult:'',
      flag:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      file:{
        name:'SourceCode.java',
      }
    }
  },
  methods:{
    ser(){
      request.get("/java_deser/start",{
        params:{
          username:this.user.username
        }
      }).then(res =>{
        //alert(res.data)
        this.myresult = res.data;
      })
    },
    der(){
      request.post("/java_deser_answer",this.der_data).then(res =>{
        //alert(res.data)
        this.flag = res.data;
      })
    },
    download(){
      request.get("/java_deser/Download",{
        params:{
          filename:'SourceCode.java',
        }
      }).then(res =>{
        alert(res.data)
        this.downloadFile('SourceCode.java',res.data)
      })
    },
    downloadFile(fileName, data) {
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));
      let link = document.createElement('a');
      link.style.display = 'none';
      link.href = url;
      link.setAttribute('download', fileName);
      document.body.appendChild(link);
      link.click();
    }
  }
}
</script>

<style scoped>
.building{
  background:url("../../../assets/img/bugimg/javadeser.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
</style>