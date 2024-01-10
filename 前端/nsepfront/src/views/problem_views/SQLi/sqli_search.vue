<template>
    <div id="building">
        <div style="margin-top: 100px;">
        </div>
        <div style="margin-top: 100px">
            <el-input v-model="id" style="width: 40%;margin-right: 50px" placeholder="请输入学号ID"></el-input>
            <el-button @click="pingfun" type="primary">查找</el-button>
        </div>

        <el-card style="width: 50%;margin-left: 25%;margin-top: 5%;word-break:break-all">
            <el-table :data="myresult" border="true" style="width: 100%;">
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
      request.get("/sqli/search",{
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
        background:url("../../../assets/img/bugimg/hacker1.jpg");
        width:100%;
        height:100%;
        position:fixed;
        background-size:100% 100%;
    }

</style>