<template>
  <div id="building">
<!--  <p>SQLi-数字型</p>-->
    <div style="margin-top: 5%;">
    </div>
    <div style="margin-top: 5%">
      <el-input v-model="id" style="width: 40%;margin-right: 50px" placeholder="请输入学号ID"></el-input>
      <el-button @click="pingfun" type="info" plain>查找</el-button>
    </div>
    <el-card class="card"
             style="width: 60%;
             margin-left: 20%;
             margin-top: 4%;
             word-break:break-all">
      <el-table :data="myresult"
                border="true"
                style="width: 100%;
                background-color: rgba(255,255,255,0.5)">
        <el-table-column prop="id" label="学号" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="level" label="权限" />
      </el-table>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      id:'',
      myresult:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created(){
    this.loaddata()
  },
  methods:{
    pingfun(){
      this.show=1
      request.get("/sqli/num",{
        params:{
          id:this.id,
          username:this.user.username
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    },

    loaddata(){
      request.get("/sqli/load").then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    },

  }
}
</script>

<style scoped>
#building{
  background:url("../../../assets/img/bugimg/hacker2.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
/deep/ .el-input__inner {
  background-color: rgba(255, 255, 255, 0.9) !important;
}
/* 表格内背景颜色 */
.el-table th, .el-table tr,.el-table td{
  border: 0;
  background-color: transparent;
}

.card{
  margin-top: 10px;
  background-color: rgba(135,135,135,0.6);
  border-style: none;
  color: white;
}
</style>
