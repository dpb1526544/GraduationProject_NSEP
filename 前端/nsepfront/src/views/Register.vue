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
        <b>学 生 注 册</b>
      </div>

      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username" style="width: 70%; margin-left: 15%">
          <el-input
            placeholder="请输入学号"
            size="medium"
            style="margin: 5px 0"
            prefix-icon="user"
            v-model="user.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password" style="width: 70%; margin-left: 15%">
          <el-input
            placeholder="请输入密码"
            size="medium"
            style="margin: 5px 0"
            prefix-icon="lock"
            show-password
            v-model="user.password"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="confirmPassword"
          style="width: 70%; margin-left: 15%"
        >
          <el-input
            placeholder="请确认密码"
            size="medium"
            style="margin: 5px 0"
            prefix-icon="lock"
            show-password
            v-model="user.confirmPassword"
          ></el-input>
        </el-form-item>
        <el-form-item prop="realname" style="width: 70%; margin-left: 15%">
          <el-input
            placeholder="请输入姓名"
            size="medium"
            style="margin: 5px 0"
            prefix-icon="edit"
            v-model="user.realname"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email" style="width: 70%; margin-left: 15%">
          <el-input
            placeholder="请输入邮箱"
            v-bind:disabled="disabled"
            size="medium"
            style="margin: 5px 0"
            prefix-icon="connection"
            v-model="user.email"
          ></el-input>
        </el-form-item>
        <el-form-item prop="emailcode" style="width: 70%; margin-left: 15%">
          <el-input
            placeholder="请输入邮箱验证码"
            size="medium"
            style="margin: 5px 0; width: 65%"
            prefix-icon="edit"
            v-model="emailcode"
          >
          </el-input>
          <el-button
            type="info"
            plain
            style="margin-left: 5%; width: 30%"
            autocomplete="off"
            v-bind:disabled="disabled"
            @click="getCode"
          >
            获取验证码
          </el-button>
        </el-form-item>
        <el-form-item
          style="
            margin: 5px 0;
            text-align: center;
            width: 70%;
            margin-left: 15%;
          "
        >
          <el-button
            type="success"
            plain
            style="width: 60%"
            autocomplete="off"
            @click="register"
          >
            注册
          </el-button>
          <el-button
            type="warning"
            plain
            size="small"
            autocomplete="off"
            @click="$router.push('/login')"
            style="width: 36%"
          >
            返回登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <nsepfooter></nsepfooter>
  </div>
</template>
  
  <script>
import request from "../utils/request";
import router from "@/router";
import animation from "../assets/js/background_black";
import nsepfooter from "../components/nsepfooter.vue";
export default {
  name: "Login",
  components: { nsepfooter },
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: "请输入学号", trigger: "blur" },
          {
            min: 6,
            max: 12,
            message: "学号长度为 6 到 12 个字符",
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
        confirmPassword: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 20,
            message: "密码长度为 6 到 20 个字符",
            trigger: "blur",
          },
        ],
        realname: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            min: 2,
            max: 10,
            message: "姓名长度为 2 到 10 个字符",
            trigger: "blur",
          },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            min: 5,
            max: 30,
            message: "邮箱长度为 5 到 30 个字符",
            trigger: "blur",
          },
        ],
      },
      disabled: false,
      emailcode: "",
      realemailcode: "",
    };
  },
  created() {
    animation();
  },
  methods: {
    register() {
      //进行表单验证
      this.$refs.userForm.validate((valid) => {
        //表单验证失败
        if (!valid) {
          this.$message("请检查页面输入是否符合要求");
          return false;
        }
        if (this.user.password != this.user.confirmPassword) {
          this.$message.error("两次输入的密码不一致");
          return false;
        }
        if (this.realemailcode != this.emailcode) {
          this.$message.error("您的邮箱验证码不正确，请重新输入");
          return false;
        }
        request.post("/user/register", this.user).then((res) => {
          if (res.code === "200") {
            this.$message.success("注册成功");
            router.push("/login");
          } else {
            this.$message.error(res.msg);
          }
        });
      });
    },
    getCode() {
      //邮箱正则验证
      const data = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
      if (!data.test(this.user.email)) {
        this.$message.error("您输入的邮箱格式有误");
        return false;
      }
      this.disabled = true;
      request
        .get("/Mail/verifyMail" + "?email=" + this.user.email)
        .then((res) => {
          if (res.code === "200") {
            this.realemailcode = res.data;
            this.$message.success("发送成功，请注意查收！");
          } else {
            this.$message.error("发送失败，请检查您的邮箱输入是否正确");
          }
        });
    },
  },
};
</script>
  
  <style scoped>
.wrapper {
  height: 100vh;
  background: rgba(255, 255, 255, 0.2);
}
.card {
  margin: 100px auto;
  background-color: #fff;
  width: 500px;
  height: 620px;
  padding: 20px;
  background-color: rgba(155, 155, 155, 0.5);
  border-style: none;
}
.el-input >>> .el-input__inner {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
}
</style>