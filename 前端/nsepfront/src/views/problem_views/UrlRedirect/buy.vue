<template>
  <el-row style="">
    <el-image
        :src="imgsrc1"
        style="height:320px;
                    width:100%;

                    "/>
  </el-row>
  <el-row>
    <el-col>
            <el-image
                :src="imgsrc2"
                style="height:80%;
                          width:70%;
                          margin-left: 0%;
                          margin-top: 5%"/>
    </el-col>
    <el-col>
      <el-row>
        <el-col :span="12">
          <el-button @click="login"
                     style="width:20%;
                   height: 40%;
                   margin-left:110%;
                   background-color: #FFEDED;">
              <span
                  style="color: red;
                margin-left: 10%;
                margin-bottom: 5px;
                font-size: 120%;">
                立即购买
              </span>
<!--            </el-col>-->
          </el-button>
        </el-col>
        <el-col :span="12">
          <el-button @click="login"
                     style="width:20%;
                   height: 40%;
                   margin-left:0%;
                   background-color: #FE0036;">
            <el-col :span="8" style="margin-bottom: 3px">
              <i :class="imgurl"
                 style="color: white;
                 font-size: 20px;
                 margin-bottom: 10px;
                 margin-left: 0%">
              </i>
            </el-col>
            <el-col :span="8">
                <span
                    style="color: white;
                  margin-left: 0%;
                  font-size: 120%;">
                  加入购物车
                </span>
            </el-col>
          </el-button>
        </el-col>
      </el-row>

    </el-col>

  </el-row>

  <el-row>
    <el-image
        :src="imgsrc4"
        style="height:350px;
                    width:90%;
                    margin-left: 3%;
                    margin-top: 10%;
                    "/>
  </el-row>
</template>

<script>

import request from "@/utils/request";
// import router from "@/router";

export default {
  name: "buy",
  data(){
    return{
      imgsrc: require('@/assets/img/bugimg/huawei.jpg'),
      imgsrc1: require('@/assets/img/bugimg/tianmao2.png'),
      imgsrc2: require('@/assets/img/bugimg/huawei2.png'),
      imgsrc4: require('@/assets/img/bugimg/tianmao5.png'),
      imgsrc17: require('@/assets/img/bugimg/fileupload0.png'),

      imgurl:'el-icon-shopping-cart-2',
    }
  },
  methods:{
    login(){
      alert("请先登录")
      request.get("/url_redirect", {
        params: {
          url:"http://43.143.195.149:8081/urllogin"
        }
      }).then(res => {
        window.location.href=res.data;
      })

      // router.push("/urlLogin")
    }
  }
}
</script>

<style scoped>

</style>