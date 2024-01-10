<template style="background-color: #000000">
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
        NSEP 在线子域名查询系统
      </h1>
    </el-col>
  </el-row>

  <el-row style="margin-top: 5%">
    <div style="">
      <el-col :span="12">
        <el-input v-model="urlP"
                  style="width: 300%;
                  margin-left: 350%"
                  placeholder="请输入待查询的域名,注意不要带有www">

        </el-input>
      </el-col>

      <el-col :span="12">
        <el-button @click="subdomain"
                   type="primary"
                   style="margin-left: 880%">
          Query
        </el-button>
      </el-col>
    </div>
  </el-row>

  <el-row style="margin-left: 30%;
          margin-top: 2%">
    <span class="tishi"
          style="font-size: 15px;
          color: red;">
      *声明：本站点仅供网络安全实验平台学习使用，严禁用于非法渗透测试，所产生后果与本站点无关！*
    </span>
  </el-row>

  <el-card v-if="show==1"
           style="width: 60%;
           margin-left: 20%;
           margin-top: 5%;
           word-break:break-all">
    <el-table
        :data="myresult"
        border="true"
        style="width: 100%;">
      <el-table-column prop="id" label="序号" />
      <el-table-column prop="subDomain" label="子域名" />
    </el-table>
  </el-card>
</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      imgsrc: require('@/assets/img/toolsimg/domain1.png'),
      urlP:'',
      myresult:''
    }
  },
  methods:{
    subdomain(){
      this.show=1
      request.get("/DomainFind",{
        params:{
          urlP:this.urlP
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })

    },
    //实例创建之前钩子函数--给body添加行内样式
    beforeCreate () {
      this.$nextTick(()=>{
        document.body.setAttribute('style', 'background:#000000')
      })
    }

  }
}
</script>

<style scoped>
.tishi{
  font-weight:bold;
}
</style>