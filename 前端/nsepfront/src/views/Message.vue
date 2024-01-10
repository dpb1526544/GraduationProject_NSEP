<template>
  <div class="message"
       style="padding-bottom: 5px;background-color: rgba(135,135,135,0.6)" :style="{ height: windowHeight + 'px' }">
    <nsepheader></nsepheader>
<!--    <el-empty :image-size="200" />-->
    <el-button v-if="user.role ==3"
               @click="dialogVisible=true"
               style="width: 150px;
               margin-top: 4%;
               background-color: #303133;
               color: #99a9bf;
               border-color: #8cc5ff;">发布通知</el-button>

    <el-card v-for="(message,i) in messagelist" :key="i" class="box-card" >
<!--      标题-->
      <template #header>
        <div class="card-header">
          <span style="font-weight:bold">
            <h1>{{ message.messagetitle }}</h1>
          </span>
        </div>
      </template>
<!--      内容-->
      <div class="text item" style="height: 50px;">
        <p style="font-size: 15px; line-height: 30px">{{ message.messagecontent }}</p>
      </div>
<!--      日期-->
      <div class="text item" style="text-align: right">
        <p style="font-size: 15px; line-height: 25px" >{{ message.date }}</p>
      </div>
    </el-card>

    <el-dialog title="发布"
               v-model="dialogVisible"
               width="30%">
      <el-form :model="message"
               label-width="60px">
        <el-form-item label="标题">
          <el-input v-model="message.title"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="message.content"
                    type="textarea"
                    :rows=5>
          </el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="savemessage">确 定</el-button>
      </span>
    </el-dialog>

    <el-empty v-if="nodata==1" description="暂无消息" :image-size="250" />

  </div>
</template>

<script>
import nsepheader from "../components/nsepheader.vue";
import animation from "../assets/js/background_black";
import request from "../utils/request";
import {ElMessage} from "element-plus";

export default {
  components: {
    nsepheader,
  },
  data(){
    return{
      windowWidth: document.documentElement.clientWidth,  //实时屏幕宽度
      windowHeight: document.documentElement.clientHeight,   //实时屏幕高度
      nodata:0,
      messagelist:[],
      dialogVisible:false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      message:{},
    }
  },
  created() {
    animation()
    this.loadmessage()
    this.readmessage()
    // this.isnodata()
  },
  methods: {
    loadmessage(){
      console.log(this.user.userid)
      request.get("/message/search", {
        params:{
          userid:this.user.userid
        }
      }).then(res => {
        console.log(res)
        if(res.data.length==0)
          this.nodata=1

        this.messagelist=res.data;
      })

      // console.log(this.messagelist)
      // console.log("nodata"+this.nodata)
    },
    readmessage(){
      request.get("/message/read", {
        params:{
          userid:this.user.userid
        }
      }).then(res => {
        console.log(res)
      })
    },
    savemessage(){
      request.get("/message/save", {
        params:{
          title:this.message.title,
          content:this.message.content
        }
      }).then(res =>{
        console.log(res);
      })
      ElMessage({
        message: '发布成功！',
        center: true,
        type: 'success',
      })
      location.reload();
      this.dialogVisible=false;
    },
    isnodata(){
      // if(this.messagelist.length==0)
      //   this.nodata=1
      // console.log(this.messagelist.length)
      // console.log("nodata"+this.nodata)
    }

  }
}
</script>

<style scoped>
.box-card{
  width: 60%;
  margin-top: 20px;
  margin-left: 20%;
  text-align: left;
  background-color: rgba(135,135,135,0.7);
  border-style: none;
  color: white;
}
</style>