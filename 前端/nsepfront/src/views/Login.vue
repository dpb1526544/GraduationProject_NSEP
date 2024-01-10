<template>
  <div class="wrapper">
    <el-card class="card">
      <div
        style="
          margin: 20px 20px;
          text-align: center;
          font-size: 24px;
          color: #99a9bf;
        "
      >
        <h2 style="color: aqua; font-family: '幼圆'">
          网 络 安 全 实 验 平 台
        </h2>
      </div>
      <div
        style="
          margin: 20px 0;
          text-align: center;
          font-size: 24px;
          color: #99a9bf;
        "
      >
        <b>登 录</b>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username" style="width: 70%; margin-left: 15%">
          <el-input
            size="medium"
            style="margin: 10px 0"
            prefix-icon="user"
            v-model="user.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password" style="width: 70%; margin-left: 15%">
          <el-input
            size="medium"
            style="margin: 10px 0"
            prefix-icon="lock"
            show-password
            v-model="user.password"
          >
          </el-input>
        </el-form-item>
        <el-form-item style="width: 70%; margin-left: 70%; margin-top: -5%">
          <el-link type="success" @click="$router.push('/resetPassword')">忘记密码?</el-link>
        </el-form-item>
        <el-form-item
          style="
            margin-top: 10px;
            width: 70%;
            margin-left: 15%;
            text-align: center;
          "
        >
          <el-button
            type="success"
            plain
            autocomplete="off"
            @click="login"
            style="width: 65%"
          >
            现在进入
          </el-button>
          <el-button
            type="warning"
            plain
            size="small"
            autocomplete="off"
            @click="$router.push('/register')"
            style="width: 31%"
          >
            注册
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <nsepfooter></nsepfooter>
  </div>
</template>
  
  <script>
import router from "../router/index.js";
import request from "../utils/request";
import animation from "../assets/js/background_black";
import nsepfooter from "../components/nsepfooter.vue";
export default {
  name: "Login",
  components: {
    nsepfooter,
  },
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 6,
            max: 12,
            message: "用户名长度为 6 到 12 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 20,
            message: "密码长度为 6 到 20 个字符",
            trigger: "blur",
          },
        ],
      },
      url: this.$route.query.url,
    };
  },
  created() {
    animation();
  },
  methods: {
    login() {
      //进行表单验证
      this.$refs.userForm.validate((valid) => {
        //表单验证失败
        if (!valid) {
          //提示语
          this.$message("请检查是否满足输入要求");
          return false;
        }
        request.post("/user/login", this.user).then((res) => {
          if (res.code === "200") {
            localStorage.setItem("user", JSON.stringify(res.data)); // 存储用户信息到浏览器

            //决定登陆后去哪个界面
            if (this.url != null) {
              let Base64 = require("js-base64").Base64;
              this.url = Base64.decode(this.url);

              router.push(this.url);
            } else router.push("/");
          } else {
            this.$message.error(res.msg);
          }
        });
      });
    },
    forgetPwd() {},
  },
};
</script>
  
  <style scoped>
.wrapper {
  height: 100vh;
  background: rgba(255, 255, 255, 0.2);
}
.card {
  margin: 200px auto;
  /*background-color: #fff;*/
  width: 550px;
  height: 400px;
  padding: 20px;
  background-color: rgba(155, 155, 155, 0.5);
  border-style: none;
  /*border-radius: 10px;*/
}
.el-input >>> .el-input__inner {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
}
</style>