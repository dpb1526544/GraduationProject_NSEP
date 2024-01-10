<template>
  <div class="line"></div>
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-menu-item index="1">
      <h1 style="margin-top: 3px">
        NSEP-OPCENTER
      </h1>
    </el-menu-item>
    <el-menu-item index="5"
                  style="margin-left: 60%">
      <i :class="imgurl"
         style="color: white;
         font-size: 180%"></i>
    </el-menu-item>
    <el-menu-item index="6" style="">
      <el-image :src="imgsrc"
                style="height:95%;
                width:95%;
                margin-left: 15%;
                margin-top: 5%;
                border-radius: 100%;"/>
    </el-menu-item>
  </el-menu>

  <el-row class="tac" style="">
    <el-col :span="4"
            style="height: 100%">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="width: 100%;
          height: 100%;
          margin-right: 2%;"
      >

        <el-menu-item index="1"
                      style="font-size: 100%;
                      margin-right: 10%">
          <i class="el-icon-document"
             style="color: white"></i>
          <span>
            我的订单
          </span>
        </el-menu-item>

        <el-menu-item index="2"
                      style="margin-right: 10%;
                              font-size: 100%">
          <i class="el-icon-timer"
             style="color: white"></i>
          <span>
            我的预约
          </span>
        </el-menu-item>

        <el-menu-item index="3"
                      style="font-size: 100%;
                      margin-right: 10%">
          <i class="el-icon-picture-outline"
             style="color: white"></i>
          <span>
            我的图片
          </span>
        </el-menu-item>

        <el-menu-item index="4"
                      style="font-size: 100%;
                      margin-right: 10%">
          <i class="el-icon-chat-line-round" style="color: white"></i>
          <span>
            我的消息
          </span>
        </el-menu-item>
        <el-menu-item index="5"
                      style="font-size: 100%;
                      margin-right: 10%">
          <i class="el-icon-news"
             style="color: white"></i>
          <span>
            客户服务
          </span>
        </el-menu-item>
        <br><br><br><br><br><br>
        <br><br><br><br><br><br>
        <br><br><br><br><br><br>
        <br><br><br><br><br><br>
        <br><br><br><br><br><br>
      </el-menu>
    </el-col>

    <el-col :span="20" style="margin-right: 0px">
      <el-row>
        <el-col>
          <div style="">
            <el-card class="box-card"
                     style="height: 680px">
              <template #header>
                <div class="card-header">
                  <span
                      style="color: black;
                      font-weight: bold;
                      font-size: 20px;">
                    个人信息中心
                  </span>
                </div>
              </template>

              <el-col style="margin-top: 3%">
                <span
                    style="font-size: 15px;
                    margin-top: 3%">
                  hi {{username1}},欢迎来到个人中心
                </span>
                <el-button @click="getperson_information"
                           style="margin-left: 5%">
                  查看个人信息
                </el-button>

                <el-card
                    style="margin-top: 5%;
                    margin-left: 5%;
                    width: 1100px;
                    height: 300px">
                  <el-descriptions class="margin-top"
                                   title="个人信息"
                                   :column="2"
                                   style="width: 1000px"
                                   border>
                    <template v-slot:extra>
                      <el-button
                          type="primary"
                          size="small">
                        刷新
                      </el-button>
                    </template>
                    <el-descriptions-item>
                      <template v-slot:label>
                        <i class="el-icon-user"></i>
                        用户名
                      </template>
                      {{username}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                      <template v-slot:label>
                        <i class="el-icon-phone-outline"></i>
                        通讯电话
                      </template>
                      <!--          {{creaction_date}}-{{expiration_date}}-->
                      {{phone}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                      <template v-slot:label>
                        <i class="el-icon-office-building"></i>
                        通讯地址
                      </template>
                      {{add}}
                    </el-descriptions-item>

                  </el-descriptions>
                </el-card>
              </el-col>
            </el-card>

          </div>
        </el-col>
      </el-row>

    </el-col>
  </el-row>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      id:'',
      myresult:'',
      myinformation:'',
      username:'',
      username1:'',
      phone:'',
      add:'',
      show:0,
      imgsrc: require('@/assets/img/bugimg/p.jpeg'),
      imgsrc1: require('@/assets/img/bugimg/net.jpeg'),
      imgsrc2: require('@/assets/img/logo3.jpg'),
      imgurl:'el-icon-bell',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    getperson_information(){
      this.show=1;
      request.get("/op1/person_center/information",{
        params:{
          username1:this.user.username
        }
      }).then(res =>{
        this.username=res.data.username;
        this.phone=res.data.phone;
        this.add=res.data.add;
      })
    }
  }
}
</script>