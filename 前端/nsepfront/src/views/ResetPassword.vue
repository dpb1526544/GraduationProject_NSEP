<template>
  <div class="resetPassword">
    <div class="container">
      <div
        style="margin: 20px 20px;
          text-align: center;
          font-size: 24px;
          color: #99a9bf;
        "
      >
        <h2 style="color: aqua; font-family: '幼圆'">
          网 络 安 全 实 验 平 台
        </h2>
      </div>
      <el-steps
        :active="active"
        :space="200"
        finish-status="success"
        align-center
      >
        <el-step title="身份验证" icon="edit"></el-step>
        <el-step title="密码重置" icon="promotion"></el-step>
        <el-step title="重置完成" icon="key"></el-step>
      </el-steps>
      <div v-if="active === 1" class="common_div">
        <el-form
          :rules="rules"
          :model="user"
          ref="username"
          class="user-container"
          label-position="left"
          label-width="70px"
          size="medium"
        >
          <el-form-item
            prop="username"
            style="float: right; width: 80%"
            label="学工号"
          >
            <el-input
              type="text"
              size="medium"
              v-model="user.username"
              v-bind:disabled="disabled"
              placeholder="请输入要找回密码的学工号"
              prefix-icon="user"
            >
            </el-input>
          </el-form-item>
          <el-form-item style="float: right; width: 80%" label="验证码">
            <el-input
              style="margin: 5px 0; width: 65%"
              type="text"
              v-model="emailCode"
              ref="code"
              placeholder="请输入邮箱验证码"
              prefix-icon="message"
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
        </el-form>
      </div>
      <div v-if="active === 2" class="common_div">
        <el-form
          :rules="rules"
          :model="user"
          ref="password"
          class="user-container"
          label-position="left"
          label-width="85px"
          size="medium"
        >
          <el-form-item
            prop="password"
            style="float: right; width: 80%"
            label="密码"
          >
            <el-input
              type="text"
              v-model="user.password"
              auto-complete="off"
              show-password
              placeholder="请输入新的密码"
              prefix-icon="promotion"
              spellcheck="false"
            >
            </el-input>
          </el-form-item>
          <el-form-item
            prop="confirmPassword"
            style="float: right; width: 80%"
            label="验证密码"
          >
            <el-input
              type="text"
              show-password
              v-model="user.confirmPassword"
              auto-complete="off"
              placeholder="请确认密码"
              prefix-icon="check"
              spellcheck="false"
            >
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="active === 3" class="common_div">
        <img src="../assets/img/right.png" style="margin-left: 30%" /><br />
        <h3 style="color: goldenrod; font-family: 幼圆; margin-left: 41%">
          重 置 成 功 !
        </h3>
        <br />
        <div style="color: goldenrod; font-family: 幼圆; margin-left: 25%">{{ count }}s 后将自动跳转到登录页，点击下一步立即跳转！</div>
      </div>
      <div class="common_div">
        <el-button @click="next" class="action_button">下一步</el-button>
        <el-button @click="$router.push('/login')" class="back_button">返回登录</el-button>
      </div>
    </div>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import request from "../utils/request";
import router from "@/router";
import animation from "../assets/js/background_black";
import nsepfooter from "../components/nsepfooter.vue";

export default {
  name: "ResetPassword",
  components: { nsepfooter },
  data() {
    return {
      active: 1,
      emailCode: "",
      realemailcode: "",
      user: {},
      disabled: false,
      count: "", //倒计时
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
      },
    };
  },
  created() {
    animation();
  },
  methods: {
    getCode() {
      if (this.active === 1) {
        this.$refs.username.validate((valid) => {
          //表单验证失败
          if (!valid) {
            this.$message("请检查学工号是否符合要求");
            return false;
          }
          if (
            this.user.username === "" ||
            typeof this.user.username === "undefined"
          ) {
            this.$message({
              message: "请输入要找回密码的学工号",
              type: "warning",
            });
            return false;
          }
          request
            .get("/Mail/verifyMailTwo" + "?username=" + this.user.username)
            .then((res) => {
              if (res.code === "200") {
                this.realemailcode = res.data;
                this.$message.success(
                  "系统已将验证码发送到您绑定的邮箱，请注意查收！"
                );
                this.disabled = true;
              } else if (res.code === "500") {
                this.$message.error("系统未查询到您的学工号，请检查输入");
              } else {
                this.$message.error("系统出现了某些问题，请联系管理员");
              }
            });
        });
      }
    },

    threeGo() {
      const TIME_COUNT = 3;
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.show = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.show = true;
            clearInterval(this.timer);
            this.timer = null;
            this.$router.push({
              path: "/login",
            });
          }
        }, 1000);
      }
    },

    next() {
      if (this.active === 1) {
        if (this.disabled === false) {
          this.$message.error("请输入要找回密码的学工号并获取验证码");
        } else {
          if (this.emailCode === "") {
            this.$message.error("请输入验证码");
          } else {
            if (this.realemailcode === this.emailCode) {
              this.$message.success("验证通过!");
              this.active++;
              this.disabled = false;
            } else {
              this.$message.error("验证码不正确，请检查");
            }
          }
        }
      }
      if (this.active === 2) {
        this.$refs.password.validate((valid) => {
          //表单验证失败
          if (!valid) {
            this.$message("请检查密码输入是否符合要求");
            return false;
          }
          if (this.user.password != this.user.confirmPassword) {
            this.$message.error("两次输入的密码不一致，请检查");
          } else {
            request
              .get(
                "/user/resetpwd" +
                  "?username=" +
                  this.user.username +
                  "&password=" +
                  this.user.password
              )
              .then((res) => {
                if (res.code === "200") {
                  this.realemailcode = res.data;
                  this.$message.success("修改密码成功");
                  this.threeGo();
                  this.active++;
                } else {
                  this.$message.error(
                    "修改失败，系统出现了某些问题，请联系管理员"
                  );
                }
              });
          }
        });
      }
      if (this.active === 3) {
        router.push("/login");
      }
    },
  },
};
</script>

<style scoped>
.resetPassword {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 10% auto;
  width: 30%;
  padding: 25px 30px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  opacity: 0.7;
}
.common_div {
  margin-top: 5%;
}
.user-container {
  width: 80%;
  background: #fff;
}
.action_button {
  width: 20%;
  margin-top: 3%;
  margin-left: 30%;
  text-align: center;
}
.back_button{
  width: 20%;
  margin-top: 3%;
  margin-left: 10%;
  text-align: center;
}
</style>

