<template>
    <div>
      <nsepheader></nsepheader>
      <el-row>
        <el-col
            :span="10"
            style="margin-top: 4%">
          <el-image
              :src="imgsrc"
              style="height: 80%;
              width: 10%;
              margin-left: 85%;
              border-radius: 30%;"/>
        </el-col>
        <el-col :span="14" style="">
          <p class="title"
             style="font-size: 20px;
             margin-top: 8%;
             color: black;
             margin-right: 60%">
            NSEP 在线IP信息查询系统
          </p>
        </el-col>
      </el-row>

      <el-row style="margin-top: 2%">
        <div style="">
          <el-col :span="12">
            <el-input v-model="ip1"
                      style="width: 240%;
                      margin-left: 350%;"
                      placeholder="请输入要查询的IP地址">

            </el-input>
          </el-col>
          <el-col :span="12">
            <el-button @click="getIPInfo"
                       type="primary"
                       style="margin-left: 880%">
              Query
            </el-button>
          </el-col>
        </div>
      </el-row>

      <el-row
          style="margin-left: 30%;
          margin-top: 2%">
          <span class="tishi"
                style="font-size: 15px;">
            *声明：本网站仅供渗透测试学习使用，严禁用于非法渗透测试，所产生后果与本网站无关！*
          </span>
      </el-row>

      <el-row
          style="margin-left: 20%;
          margin-top: 4%;
          width: 1000px;">
          <el-col>
            <el-descriptions class="margin-top"
                             title="IP信息"
                             :column="2"
                             style="width: 900px;
                             color: white;" border>
              <template v-slot:extra>
                <el-button type="primary"
                           size="small"
                           @click="getIPInfo">
                  刷新
                </el-button>
              </template>
              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-user"></i>
                  IP地址
                </template>
                {{ip}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-mobile-phone"></i>
                  国家
                </template>
                {{country}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-cpu"></i>
                  省份
                </template>
                {{province}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-tickets"></i>
                  城市
                </template>
                {{city}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-office-building"></i>
                  区域
                </template>
                {{distinct}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-place"></i>
                  网络提供商
                </template>
                {{isp}}
              </el-descriptions-item>
            </el-descriptions>
          </el-col>
          <el-col style="margin-top: 5%">
          </el-col>
      </el-row>
    </div>
<!--  </div>-->

</template>

<style scoped>
#building{
  background:url("../../assets/img/toolsimg/ip3.jpg");
  width:100%;
  height:120%;
  position:fixed;
  background-size:100% 100%;
}
 .tishi{
   font-weight:bold;
 }

.title{
  font-weight:bold;
}

</style>


<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      show1:0,
      imgsrc: require('@/assets/img/toolsimg/ipinfo.png'),
      ip1:'',
      ip:'',
      country:'',
      country_code:'',
      province:'',
      city:'',
      distinct:'',
      isp:'',
      operator:'',
      lon:'',
      lat:'',
      net_str:''
    }
  },
  methods:{
    getIPInfo(){
      this.show=1
      request.get("/tools/IPInfo",{
        params:{
          ip1:this.ip1
        }
      }).then(res =>{
        this.ip=res.data.ip;
        this.country=res.data.country;
        this.country_code=res.data.country_code;
        this.province=res.data.province;
        this.city=res.data.city;
        this.distinct=res.data.distinct;
        this.isp=res.data.isp;
        this.operator=res.data.operator;
        this.lon=res.data.lon;
        this.lat=res.data.lat;
        this.net_str=res.data.net_str;
      })
    }

  }
}
</script>