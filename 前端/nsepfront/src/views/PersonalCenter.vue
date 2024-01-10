<template>
  <div class="pc">
    <nsepheader></nsepheader>

    <div class="common-layout" >
      <el-container style="margin-top: 2%;width: 80%;margin-left: 10%;">
        <el-aside width="20%" style="text-align: right;background-color:rgba(128,138,135,0)">
          <el-card style="background-color: rgba(0,0,0,0);margin-top: 10%">
<!--            <el-scrollbar style="width: 100%">-->
              <el-menu style="border-style: none" background-color="rgba(128,138,135,0.2)" text-color="#F5F5F5" active-text-color="#FFF8DC" >
                <el-menu-item index="1" @click="myinfo">个人信息</el-menu-item>
                <el-menu-item  v-if="user.role == 1"  index="2" @click="myclass">参与课程</el-menu-item>
                <el-menu-item index="3" @click="mywriteup">My writeup</el-menu-item>
              </el-menu>
<!--            </el-scrollbar>-->
          </el-card>
        </el-aside>
        <el-main style="background-color:rgba(128,138,135,0);width: 60%;">
          <el-card  v-if="selfinfo == 1"  v-model="user" class="box-card" style="text-align: left;background-color:rgba(128,138,135,0.5)" shadow="never">
              <template #header>
                <div class="card-header">
                  <span style="color:#F5F5F5;font-size:15px;" >个人信息</span>
                </div>
              </template>
              <el-form :label-position="labelPosition" label-width="100px" v-model="user" style="color: white">
                <el-form-item label="姓名" prop="pass" style="width: 400px;" class="item">
                  <el-input  disabled v-model="user.realname"/>
                </el-form-item>
                <el-form-item label="学/工号" prop="checkPass" style="width: 400px;" class="item">
                  <el-input disabled v-model="user.username"/>
                </el-form-item>
                <el-form-item label="邮箱" prop="checkPass" style="width: 400px;" class="item">
                  <el-input disabled v-model="user.email"/>
                </el-form-item>
                <el-form-item style="alignment: center;width: 400px">
                  <el-button   @click="repwd" type="success" plain>修改密码</el-button>
                </el-form-item>
              </el-form>
            </el-card>

          <div v-if="selfcourse == 1" direction="vertical" style="text-align: center;margin-top: 5px">
            <el-card class="box-card" style="margin-top: 5px;" shadow="hover">
              <el-table :data="courseData" border="true" style="width: 100%;" >
                <el-table-column prop="coursename" label="课程名" />
                <el-table-column prop="realname" label="教师" />
              </el-table>
            </el-card>
          </div>

          <div v-if="selfwriteup == 1" direction="vertical" style="text-align: left;">
            <el-card v-for="(forum,i) in forumlist" :key="i" class="box-card" style="width: 100%;margin-top: 5px;background-color:rgba(128,138,135,0.5);color:white" shadow="hover">
              <template #header>
                <div class="card-header">
                  <span style="font-weight:bold">{{ forum.title }}</span>
                </div>
              </template>
              <div class="text item">
                {{ forum.content }}
              </div>
              <div class="text item" style="margin-top: 20px">
                {{ forum.username }}
              </div>
              <div class="text item" style="text-align: right">
                {{ forum.time }}
              </div>
              <div class="text item" style="text-align: right;margin-top: 2%;">
                <el-button type="info" @click="delwup(forum)" plain>删除</el-button>
              </div>
            </el-card>
          </div>

          <el-card  v-if="nodata == 1"  v-model="user" class="box-card" style="text-align: left;background-color:rgba(128,138,135,0.5)" shadow="never">
            <el-empty description="No Data" />
          </el-card>

        </el-main>
      </el-container>
    </div>

    <el-dialog title="修改密码" v-model="dialogVisible" width="30%">
      <el-form :model="userpw" label-width="15%" :rules="rules">
        <el-form-item label="原密码">
          <el-input v-model="userpw.password"></el-input>
        </el-form-item>
        <el-form-item prop="newpassword" label="新密码">
          <el-input v-model="userpw.newpassword"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" plain @click="savepw">确 定</el-button>
      </span>
    </el-dialog>
  </div>
  <nsepfooter></nsepfooter>
</template>

<script>
import nsepheader from "@/components/nsepheader";
import nsepfooter from '../components/nsepfooter.vue';
import request from "@/utils/request";
import router from "@/router";
import animation from "../assets/js/background_black";
import {ElMessage} from "element-plus";

export default {
  components: {nsepheader,nsepfooter},
  data(){
    return{
      selfinfo:1,
      selfwriteup:0,
      selfcourse:0,
      nodata:0,
      dialogVisible:false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      userpw:{},
      forumlist:[],
      courseData:[],
      rules: {
          newpassword: [
            {required: true, message: '请输入新密码', trigger: 'blur'},
            {min: 6, max: 20, message: '密码长度为 6 到 20 个字符', trigger: 'blur'}
          ]
        }
    }

  },
  created() {
    animation()
    this.myinfo()
  },
  methods:{
    repwd(){
      this.dialogVisible=true;
      this.userpw={};
    },
    savepw(){
      this.userpw.username=this.user.username
      if (this.userpw.password.length == 0||this.userpw.newpassword.length == 0) {
        this.$message.error("输入不能为空")
        return false
      }
      if (this.userpw.password!=this.user.password) {
        this.$message.error("原密码错误")
        return false
      }
      request.post("/user/repw", this.userpw).then(res => {
        console.log(res)
        if(res.code === '200') {
          this.$message.success("修改成功,请重新登录")
          router.push("/login");
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    myinfo(){
      this.selfinfo=1
      this.selfwriteup=0
      this.selfcourse=0
      this.nodata=0
      request.post("/user/selbyun",this.user).then(res =>{
        console.log(res)
        this.user=res.data
      })
    },
    myclass(){
      this.selfinfo=0
      this.selfwriteup=0
      request.get("/StuCourse/mycourse",{
        params:{
          userid:this.user.userid,
        }
      }).then(res =>{
        console.log(res)
        this.courseData=res.data
        if(this.courseData.length==0){
          this.selfcourse=0
          this.nodata=1
        }else{
          this.selfcourse=1
          this.nodata=0
        }
      })
    },
    mywriteup(){
      this.selfinfo=0
      this.selfcourse=0
      request.get("/forum/mywriteup",{
        params:{
          username:this.user.username,
        }
      }).then(res =>{
        console.log(res)
        this.forumlist=res.data
        if(this.forumlist.length==0){
          this.selfwriteup=0
          this.nodata=1
        }else{
          this.selfwriteup=1
          this.nodata=0
        }
      })
    },
    delwup(forum){
      console.log(forum.forumid)
      this.$confirm('确认删除该writeup？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.get("/forum/delete",{
          params:{
            forumid:forum.forumid,
          }
        }).then(res =>{
          console.log(res);
          ElMessage({
            message: "已删除",
            center: true,
            type: 'success',
          })
          this.mywriteup()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // cellStyle(){
    //     return 'background:#808A87'
    // }

  },

}
</script>

<!-- Add "scoped" 让css只对当前组件生效 -->
<style scoped>
.pc{
  margin: 0px;
  /*background-image: linear-gradient(to bottom right, #000000, 	#483D8B);*/
  background-color: rgba(135,135,135,0.5);
  position: relative;
  width:100%;
  min-height: 100vh;

}
.box-card{
  width: 80%;
}
.item /deep/ .el-form-item__label{
  color:whitesmoke;
  /*font-weight:bold;*/
  /*font-size:20px*/
}

</style>
