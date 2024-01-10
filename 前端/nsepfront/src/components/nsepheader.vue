<template>
  <div class="menu">
    <el-row>
      <!-- logo部分 -->
      <el-col :span="4" >
        <el-image :src="imgsrc"
                  @click="goto_home"
                  style="height:60px;
                  text-align: center;"/>
<!--        <h3  style="margin-left: 40%;">SDUCSRP</h3>-->
      </el-col>
      <!-- 按钮菜单部分 -->
      <el-col :span="13">
        <el-menu
            :default-active="mypath"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#000"
            text-color="#fff"
            active-text-color="#FF9912"
            @select="handleSelect"
        >
          <el-menu-item v-if="user.role !=3" index="/honeypot" @click="goto_honeypot"> 实验资源 </el-menu-item>
          <el-menu-item v-if="user.role !=3" index="/nstools" @click="goto_nstools">网安工具</el-menu-item>
          <el-menu-item v-if="user.role !=3" index="/forum" @click="goto_forum">writeup</el-menu-item>
          <el-menu-item v-if="user.role !=3" index="/navigation" @click="goto_navigation">安全导航</el-menu-item>
          <!--当用户权限为教师时才会出现(2)-->
          <el-menu-item v-if="user.role ==2" index="/coursemanagement" @click="goto_coursemanagement">课程管理</el-menu-item>
          <!--当用户权限为管理员时才会出现(3)-->
          <el-menu-item v-if="user.role ==3" index="/usermanagement" @click="goto_usermanagement">用户管理</el-menu-item>

          <el-menu-item v-if="user.role ==3" index="/message" @click="goto_message">信息发布</el-menu-item>
        </el-menu>
      </el-col>

      <!-- 用户登录部分 -->
      <el-col :span="7">
        <div class="header-right">
          <div class="header-user-con">

            <!--登陆后-->
            <div class="btn-bell" v-if="user.role!=null">
              <el-badge :is-dot="messagenum"
                        style="margin-right: 30px;">
                <router-link to="/message">
                  <el-icon style="color: white"><Bell /></el-icon>
                </router-link>
              </el-badge>
              <el-dropdown trigger="click">
                <span style="color: white; ">
                  <i class="el-icon-s-custom"></i>
                  {{user.realname}}
                </span>
                <template #dropdown>
                  <el-dropdown-menu style="background-color: black; ">
                    <el-dropdown-item @click="goto_center" style="color: white">个人中心</el-dropdown-item>
                    <el-dropdown-item @click="Logout" style="color: white">退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>

            <!--登陆前-->
            <div v-if="user.role==null">
              <el-button type="info"
                         style="background-color: black;
                         border-color: black;
                         padding:0px;
                         float: right"
                         @click="goto_register">
                注册
              </el-button>
              <el-button type="info"
                         style="background-color: black;
                         border-color: black;
                         padding:0px;
                         float: right"
                         @click="goto_login">
                <i class="el-icon-finished"
                   style="padding: 0px;
                   margin: 0px"></i>  登录
              </el-button>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-divider style="background-color: black"/>
  </div>
</template>

<script>
import router from '../router/index.js'
import request from "@/utils/request";

export default {

  data() {
    return {
      mypath:this.$route.path,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      messagenum: false,
      imgsrc:require('@/assets/img/logo3.jpg'),
    }
  },
  created() {
    this.getmessage()
  },
  methods: {
    goto_home(){
      router.push('/');
    },
    goto_login(){
      router.push('/login');
    },
    goto_register(){
      router.push('/register');
    },
    goto_honeypot(){
      router.push('/honeypot')
    },
    goto_nstools(){
      router.push('/nstools')
    },
    goto_navigation(){
      router.push('/navigation')
    },
    goto_forum(){
      router.push('/forum')
    },
    goto_coursemanagement(){
      router.push('/coursemanagement')
    },
    goto_usermanagement(){
      router.push('/usermanagement')
    },
    goto_center(){
      router.push('/personalcenter')
    },
    goto_message(){
      router.push('/message')
    },
    Logout(){
      localStorage.clear();
      router.push('/login');
    },
    getmessage(){
      request.get("/message/checknew",{
        params:{
          userid:this.user.userid
        }
      }).then(res =>{
        //存在未读消息
        if (res.data>0){
          this.messagenum=true
        }else{
          this.messagenum=false
        }
      })
    },
  }
}
</script>

<style scoped>
.el-row {
  margin: 0px;
  padding: 0px;
  display: flex;
  flex-wrap: wrap;
  color: white;
  background-color: black;
}
.el-col{
  height: 60px;
}
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 60px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 60px;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
  padding-left: 20px;
}
.somebutton {
  float: left;
  line-height: 60px;
}
.el-button {
  color: #fff;
  margin-left: 35px; /**左边间距 */
  /* background-color: #1E90FF; */
  /* border-color: #1E90FF; */
  height: 60px;
  /* line-height: 34px; */
  padding: 0px;
}
.header-right {
  /*float: right;*/
  text-align: center;
  margin-left: 50%;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 60px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell{
  position: relative;
  /*width: 30px;*/
  text-align: center;
  cursor: pointer;
}
.btn-fullscreen {
  position: relative;
  /*width: 30px;*/
  text-align: center;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}

.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}

.el-divider {
  margin: 0;
  position: relative;
  vertical-align: middle;
}
</style>