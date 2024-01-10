<template>
  <div class="invitation">
    <nsepheader></nsepheader>
    <el-dialog
      v-model="visable"
      title="邀请确认"
      width="30%"
      :before-close="handleClose"
    >
      <span
        >你将加入{{ teacher.teachername }}的{{ course.coursename }}课程</span
      >
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="saveinfo">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import nsepheader from "../components/nsepheader.vue";
import animation from "../assets/js/background_black";
import router from "../router/index.js";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

export default {
  name: "Home",
  components: {
    nsepheader,
  },
  data() {
    return {
      visable: true,
      teacher: {
        teacherid: this.$route.query.id1,
        teachername: "",
      },
      course: {
        courseid: this.$route.query.id2,
        coursename: "",
      },
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
      url: router.currentRoute._value.fullPath,
    };
  },
  created() {
    animation();
    this.loadinfo(this.course.courseid, this.teacher.teacherid);
  },
  methods: {
    loadinfo(id1, id2) {
      console.log(this.user.userid);
      if (this.user.userid == null) {
        ElMessage({
          message: "用户未登录",
          center: true,
          type: "error",
        });
        // setTimeout(()=>{
        //跳转到登录页，并附带上当前Url信息
        let Base64 = require("js-base64").Base64;
        console.log("aaa" + this.url);
        this.url = Base64.encode(this.url);
        console.log("aaa" + this.url);
        router.push({ path: "/login", query: { url: this.url } });
        //延迟时间：1秒
        // },1000)
      }

      // 获取课程名
      request
        .get("/course/getcoursename", {
          params: {
            courseid: id1,
          },
        })
        .then((res) => {
          this.course.coursename = res.data;
        });

      //获取教师名
      request
        .get("/user/getname", {
          params: {
            userid: id2,
          },
        })
        .then((res) => {
          this.teacher.teachername = res.data;
        });
    },

    saveinfo() {
      request
        .get("/StuCourse/saveinfo", {
          params: {
            userid: this.user.userid,
            courseid: this.course.courseid,
          },
        })
        .then((res) => {
          if (res.code == "200") {
            ElMessage({
              message: "确认成功！",
              center: true,
              type: "success",
            });
            setTimeout(() => {
              //2秒后延迟跳转到首页
              router.push("/");
            }, 2000);
          } else {
            ElMessage({
              message: "数据库异常",
              center: true,
              type: "error",
            });
          }
        });
    },
  },
};
</script>

<style scoped>
</style>