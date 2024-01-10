<template>
  <el-image
      :src="imgsrc4"
      style="height:30px;
                    width:15%;
                    margin-right: 70%;
                    margin-top: 1%;
                    margin-bottom: 2%;
                    "/>
  <div id='building1'>
    <el-card
        style="width: 25%;
        background-color: rgba(255,255,255,0.7);
        margin-left: 60%;
        margin-top: 10%"
    >
      <div class="bf_form">
        <div class="bf_form_main">
          <el-row style="margin-left: 18%;
                            margin-top: 2%"
          >
              <span style="font-size: 15px;

                          /*color: white*/
                          " class="header blue lighter bigger"
              >
                密码登录
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
            <el-input v-model="password"
                      style="width: 250px;
                        margin-right: 50px"
                      placeholder="password"
            >
            </el-input>

          </el-row>
          <el-row style="margin-left: 40%;
                            margin-top: 5%">
            <el-button @click="login">
              登录
            </el-button>
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
      imgsrc4: require('@/assets/img/bugimg/tianmaologo.png'),

      username:'',
      password:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods: {
    login() {
      request.get("/url/login1", {
        params: {
          username: this.username,
          password: this.password,
          username1:this.user.username
        }
      }).then(res => {
        if(res.code=="401"){
          alert("token认证失败，请重新登录！")
        }
        else{
          alert(res.data)
        }
        router.push("/buy")
      })
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  overflow: hidden;
}

#building1{
  background:url("../../../assets/img/bugimg/tianmao6.jpg");
  width:110%;
  height:90%;
  position:fixed;
  background-size:100% 100%;
}
</style>