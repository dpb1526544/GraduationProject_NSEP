<template>
  <div class="building">
    <div class="menu">
      <el-row>
        <!-- 按钮菜单部分 -->
        <el-col :span="24">
          <el-menu
              :default-active="mypath"
              class="el-menu-demo"
              mode="horizontal"
              background-color="#fff"
              text-color="#000"
              active-text-color="#FF9912"
              @select="handleSelect"
          >
            <el-menu-item index="/honeypot">  </el-menu-item>
            <el-menu-item index="/abilitypromotion"></el-menu-item>
            <el-menu-item index="/forum" ></el-menu-item>
            <el-menu-item index="/navigation" >风景</el-menu-item>
            <el-menu-item index="/coursemanagement" >美食</el-menu-item>
            <el-menu-item index="/usermanagement" >动物</el-menu-item>
            <el-menu-item index="/usermanagement2" >人物</el-menu-item>
            <el-menu-item index="/usermanagement3" >建筑</el-menu-item>
            <el-menu-item index="/usermanagement4" >排行</el-menu-item>

          </el-menu>
        </el-col>
      </el-row>
    </div>

    <div style="">
      <el-row style="">
        <el-col :span="8">
          <el-card class="card" id="img1" @click="goto('img1.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="card" id="img2" @click="goto('img2.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="card" id="img3" @click="goto('img3.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div style="">
      <el-row style="">
        <el-col :span="8">
          <el-card class="card" id="img4" @click="goto('img4.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="card" id="img5" @click="goto('img5.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="card" id="img6" @click="goto('img6.jpg')">
            <p style="font-size: 25px;text-align: right;background-color:rgba(255,255,255,0.5);width:150px;margin-left: 250px;margin-top: 280px;">
              查看详情
              <i class="el-icon-right" style=""></i>
            </p>
          </el-card>
        </el-col>
      </el-row>
    </div>


  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  data(){
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/pathtravel",{
        params:{
          username:this.user.username,
        }
      }).then(res =>{
        console.log(res)
      })
    },
    goto(index){
      let Base64 = require('js-base64').Base64
      index=Base64.encode(index)
      // console.log(index)
      this.$router.push({path:'/imageshow',query: {img:index}})
    },

  }
}
</script>

<style scoped>
.building{
  background-color: rgba(135,135,135,0.2);
}
.card{
  height:350px;
  margin: 5%;
  border-style: none;
  position: relative;
  cursor:pointer;
}
#img1{
  background:url("../../../assets/img/bugimg/pathtravel/img1.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img2{
  background:url("../../../assets/img/bugimg/pathtravel/img2.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img3{
  background:url("../../../assets/img/bugimg/pathtravel/img3.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img4{
  background:url("../../../assets/img/bugimg/pathtravel/img4.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img5{
  background:url("../../../assets/img/bugimg/pathtravel/img5.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img6{
  background:url("../../../assets/img/bugimg/pathtravel/img6.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
</style>