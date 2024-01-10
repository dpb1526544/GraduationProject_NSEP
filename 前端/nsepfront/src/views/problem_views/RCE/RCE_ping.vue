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
      <h1 style="margin-top: 3px">NSEP-LINK</h1>
    </el-menu-item>
    <el-menu-item index="5" style="margin-left: 70%">
      <i :class="imgurl" style="color: white;font-size: 180%"></i>
    </el-menu-item>
    <el-menu-item index="6" style="">
      <el-image :src="imgsrc" style="height:95%;width:95%;margin-left: 15%;margin-top: 5%;border-radius: 100%;"/>
    </el-menu-item>
  </el-menu>

  <el-row class="tac" style="height: 1500px">
    <el-col :span="4" style="height: 100%">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="width: 100%;height: 100%;margin-right: 2%;">
        <el-menu-item index="1" style="font-size: 100%;margin-right: 10%">
          <i class="el-icon-data-line" style="color: white"></i>
          <span>运行状态</span>
        </el-menu-item>
        <el-submenu index="1" style="margin-left: 0%;margin-right: 17%">
          <template v-slot:title style="font-size: 100%">
            <i class="el-icon-help" style="color: white"></i>
            <span>AP管理</span>
          </template>
          <el-menu-item index="1-4">
            <template v-slot:title>无线网络设置</template>
          </el-menu-item>
          <el-menu-item index="1-4">
            <template v-slot:title>漫游设置</template>
          </el-menu-item>
          <el-menu-item index="1-4">
            <template v-slot:title>射频调优</template>
          </el-menu-item>
          <el-menu-item index="1-4">
            <template v-slot:title>基本设置</template>
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="3" style="margin-right: 17%;font-size: 100%">
          <i class="el-icon-odometer" style="color: white"></i>
          <span>仪表盘 </span>
        </el-menu-item>
        <el-menu-item index="4" style="font-size: 100%;margin-right: 10%">
          <i class="el-icon-setting" style="color: white"></i>
          <span>基本设置</span>
        </el-menu-item>
        <el-menu-item index="5" style="font-size: 100%;margin-right: 10%">
          <i class="el-icon-warning-outline" style="color: white"></i>
          <span>安全事件</span>
        </el-menu-item>
        <el-menu-item index="5" style="font-size: 100%;margin-right: 10%">
          <i class="el-icon-lock" style="color: white"></i>
          <span>认证管理</span>
        </el-menu-item>
        <el-menu-item index="5" style="font-size: 100%;margin-right: 10%">
          <i class="el-icon-s-operation" style="color: white"></i>
          <span>高级功能</span>
        </el-menu-item>
        <br><br><br><br><br><br><br><br>
        <br><br><br><br><br><br><br>
        <br><br><br><br><br><br><br><br>
      </el-menu>
    </el-col>

    <el-col :span="20" style="margin-right: 0px">
      <el-row>
        <el-col>
          <div style="width: 100%">
            <el-card class="box-card" style="">
              <template #header>
                <div class="card-header">
                  <span style="color: black;font-weight: bold;font-size: 20px;margin-right: 90%">
                    运行状态
                  </span>
                </div>
              </template>
              <div>
                <el-col :span="8" style="margin-left: 30%;margin-top: 1%">
                  <h4>测试连接状态</h4>
                </el-col>
                <el-col :span="8" style="margin-left: 2%;margin-top: 1%">
                  <el-input v-model="ip" placeholder="请输入测试路由IP"></el-input>
                </el-col>
                <el-col :span="8" style="margin-left: 3%;margin-top: 1%">
                  <el-button @click="pingfun" type="primary">Ping</el-button>
                </el-col>
              </div>

              <el-col style="margin-top: 2%;margin-left: 10%">
                <el-card style="width: 60%;margin-left: 20%">
                  <template #header>
                    <div class="card-header">
                  <span style="color: black;font-weight: bold;">
                    版本信息
                  </span>
                    </div>
                  </template>
                  <el-row>
                    <el-col :span="24">
                      当前软件版本：4.3.1 Build 110818 Rel.36674s
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      当前硬件版本：R4148v3
                    </el-col>
                  </el-row>
                </el-card>
                <br>
                <br>
                <el-card style="width: 60%;margin-left: 20%">
                  <template #header>
                    <div class="card-header">
                  <span style="color: black;font-weight: bold;">
                    LAN口状态
                  </span>
                    </div>
                  </template>
                  <el-row>
                    <el-col :span="24">
                      MAC地址: 38-83-45-AA-39-3A
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      IP地址: 182.168.1.1
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      子网掩码: 255.255.255.O
                    </el-col>
                  </el-row>
                </el-card>
                <br>
                <br>
                <el-card style="width: 60%;margin-left: 20%">
                  <template #header>
                    <div class="card-header">
                  <span style="color: black;font-weight: bold;">
                    WAN口状态
                  </span>
                    </div>
                  </template>
                  <el-row>
                    <el-col :span="24">
                      WAN口: 线路正常
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      MAC地址: 28-8A-40-9A-39-3A
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      IP地址: 182.168.3.3
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      子网掩码: 255.255.255.252
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      网关: 180.166.232.89
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      DNS服务器: 202.96.209.5 , 116.228.111.118
                    </el-col>
                  </el-row>
                </el-card>
                <br>
                <br>
                <el-card style="width: 60%;margin-left: 20%">
                  <template #header>
                    <div class="card-header">
                  <span style="color: black;font-weight: bold;">
                    WAN口流量统计
                  </span>
                    </div>
                  </template>
                  <el-row>
                    <el-col :span="24">
                      接收字节数: 487037.801MB
                    </el-col>
                    <el-col :span="24" style="margin-top: 5%">
                      发送字节数: 643675.58MB
                    </el-col>
                  </el-row>
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
      chartData: {
        columns: ['日期', '销售额'],
        rows: [
          { '日期': '1月1日', '销售额': 123 },
          { '日期': '1月2日', '销售额': 1223 },
          { '日期': '1月3日', '销售额': 2123 },
          { '日期': '1月4日', '销售额': 4123 },
          { '日期': '1月5日', '销售额': 3123 },
          { '日期': '1月6日', '销售额': 7123 }
        ]
      },
      imgsrc: require('@/assets/img/bugimg/p.jpeg'),
      imgsrc1: require('@/assets/img/bugimg/net.jpeg'),
      imgsrc2: require('@/assets/img/logo3.jpg'),
      imgurl:'el-icon-bell',
      ip:'',
      myresult:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    pingfun(){
      request.get("/RCE/ping",{
        params:{
          ip:this.ip,
          username:this.user.username
        }
      }).then(res =>{
        alert(res.data);
        this.$alert(res.data, 'Ping', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      })
    }

  }
}

</script>