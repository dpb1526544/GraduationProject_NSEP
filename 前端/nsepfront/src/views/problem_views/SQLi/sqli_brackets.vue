<template>
  <div id="building">
    <div style="margin-top: 5%;">
<!--      <h1 style="color: white">FL4G做了一个查询用户信息的界面，输入ID(如：1)来试着查询信息吧~</h1>-->
<!--      <h1 style="color: white">注意括号！flag就藏在sqlifl4g表中哦~</h1>-->
    </div>
    <div style="margin-top: 5%">
      <el-input v-model="id" style="width: 40%;margin-right: 50px;color: white" placeholder="请输入学号ID"></el-input>
      <el-button @click="pingfun" type="primary">查找</el-button>
    </div>
<!--    <el-card v-if="show==1" style="width: 60%;margin-left: 20%;margin-top: 5%;word-break:break-all">-->
    <el-card class="card"
             style="width: 60%;
             margin-left: 20%;
             margin-top: 5%;
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
      request.get("/sqli/brackets",{
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
  background:url("../../../assets/img/bugimg/hacker3.jpg");
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
  background-color: rgba(255,255,255,0.6);
  border-style: none;
  color: white;
}
</style>