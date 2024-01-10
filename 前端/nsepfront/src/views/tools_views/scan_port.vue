<template>
  <el-row>
    <el-col
        :span="10"
        style="margin-top: 4%">
      <el-image
          :src="imgsrc"
          style="height: 75%;
          width: 40%;
          margin-left: 45%;
          margin-top: 5%"/>
    </el-col>

    <el-col :span="14" style="">
      <h1
          style=";margin-top: 16%;
          width: 500px;">
        NSEP 在线端口扫描系统
      </h1>
    </el-col>
  </el-row>

  <el-row
      style="margin-left: 15%;
      margin-top: 3%;">
      <el-card
          style="width: 80%;
          height: 250px">
        <div class="bf_form">
          <div class="bf_form_main">
            <el-col
                :span="24"
                style="width: 90%;
                margin-top: 3%">
              <span
                  style="font-size: 20px;
                  color: black;
                  margin-left: 17%;
                  width: 50%">
                Please Enter Ip And Ports(Port1-Ports2)
              </span>
            </el-col>

            <el-col
                :span="24"
                style="margin-left: 20%;
                margin-top: 2%">
              <span class="tishi"
                    style="font-size: 15px;
                    color: red;">
                *声明：本站点仅供网络安全实验平台学习使用，严禁用于非法渗透测试，所产生后果与本站点无关！*
              </span>
            </el-col>

            <el-col
                :span="24"
                style="margin-top: 5%;
                margin-left: 15%">
              <el-input v-model="ips"
                        style="width: 250px;
                        margin-right: 50px"
                        placeholder="ips">

              </el-input>
              <el-input v-model="ports"
                        style="width: 250px;
                        margin-right: 50px"
                        placeholder="ports">

              </el-input>
              <el-button @click="scan_start">
                scan
              </el-button>
            </el-col>

          </div>
        </div>

      </el-card>


      <el-card v-if="show==1"
               style="margin-left: 0%;
               margin-top: 5%;
               width: 80%;
               margin-bottom: 10%;
               word-break:break-all">
        <el-table
            :data="myresult"
            border="true"
            style="width: 100%;">
          <el-table-column prop="ip" label="IP" />
          <el-table-column prop="port" label="端口" />
          <el-table-column prop="service" label="服务" />
          <el-table-column prop="isOpen" label="状态" />
        </el-table>
      </el-card>
  </el-row>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      show:0,
      ips:'',
      ports:'',
      myresult:'',
      imgsrc: require('@/assets/img/toolsimg/scanport.jpeg'),
    }
  },
  methods:{
    scan_start(){
      this.show=1
      request.get("/tools/scan_port",{
        params:{
          ips:this.ips,
          ports:this.ports
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
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


</style>