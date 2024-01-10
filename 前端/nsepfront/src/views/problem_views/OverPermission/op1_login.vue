<template>
    <div id='building'>
      <el-card style="width: 30%;
                      margin-left: 35%;
                      margin-top: 15%"
      >
        <div class="bf_form">
          <div class="bf_form_main">
            <el-row style="margin-left: 18%;
                            margin-top: 2%"
            >
              <span style="font-size: 20px;

                          /*color: white*/
                          " class="header blue lighter bigger"
              >
                Please Enter Your Information
              </span>
            </el-row>
            <el-row style="margin-left: 18%;
                            margin-top: 4%"
            >
              <el-input v-model="username"
                        style="width: 250px;
                        margin-right: 50px"
                        placeholder="username"
              >
              </el-input>
            </el-row>
            <el-row style="margin-left: 18%;
                            margin-top: 3%"
            >
              <el-input v-model="password" style="width: 250px;
                        margin-right: 50px"
                        placeholder="password"
              >
              </el-input>

            </el-row>
            <el-row style="margin-left: 40%;
                            margin-top: 5%"
            >
              <el-button @click="login">登录</el-button>
            </el-row>
            <el-row>
              <span style="margin-top: 5%;
              font-size: 6px;
              margin-left: 5%"
              >
                Tips:测试用户:curry,密码:123;尝试使用该用户获取lucy的个人信息
              </span>
            </el-row>
          </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
      </el-card>
    </div>
</template>

<script>
import router from '../../../router/index'
import request from "@/utils/request";

export default {
  data() {
    return {
      username:'',
      password:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods: {
    login() {
      request.get("/op1/login", {
        params: {
          username: this.username,
          password: this.password,
          username1:this.user.username
        }
      }).then(res => {
        if(res.data=="success"){
          router.push("/oppc")
        }
        else if(res.data=="no"){
          alert("该用户未注册")
        }
        else if(res.data=="fail"){
          alert("用户名或密码错误")
        }
        else if(res.data=="op"){
          alert("禁止使用该用户登录")
        }
      })
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  overflow: hidden;
}
#building{
  background:url("../../../assets/img/toolsimg/ip3.jpg");
  width:100%;
  height:120%;
  position:fixed;
  background-size:100% 100%;
}
</style>