<template>
  <div>
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
            <el-menu-item index="/usermanagement" >人物</el-menu-item>
            <el-menu-item index="/usermanagement" >建筑</el-menu-item>
            <el-menu-item index="/usermanagement" >排行</el-menu-item>

          </el-menu>
        </el-col>
      </el-row>
    </div>
    
    <div>
      <img :src="url">
    </div>

  </div>
</template>

<script>


export default {
  data(){
    return{
      url:'',
      image:this.$route.query.img,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      let Base64 = require('js-base64').Base64
      this.image=Base64.decode(this.image)
      this.url=require("../../../assets/img/bugimg/pathtravel/"+this.image);
      console.log(this.url)
      console.log(this.image)
    },

  }
}
</script>

<style scoped>

</style>