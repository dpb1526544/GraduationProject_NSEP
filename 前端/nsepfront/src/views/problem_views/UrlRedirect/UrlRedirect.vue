<template>
  <title>URL-Redirect</title>
  <p>应用场景一：
    为购物网站界面，用户想加入一件商品到购物车，但是没有登录， 此时系统便会提示登录，会自动跳转到登录界面，
    为了保证用户体验，在用户登录结束后，会再次跳转回商品界面。
    两次跳转均可作为url redirect漏洞测试点。</p>
  <br>
  <p>应用场景二：
    诱导用户访问http://www.aaa.com?returnUrl=http://www.evil.com，借助www.aaa.com让用户访问www.evil.com，
    这种漏洞被利用了对用户和公司都是一种损失。
    对于安全意识较低的用户，当他们从聊天工具、论坛回帖等地方遇到上述链接时，根据链接的开头部分用户会认为打开之后是aaa公司的业务，
    尤其当www.aaa.com是耳熟能详的大型互联网公司时，用户的防备心理会更小，打开之后无论怎么跳转，用户都可能认为是值得信任的，
    当www.evil.com是钓鱼页面时，用户也可能毫不犹豫地输入用户名和密码登录网站。</p>
  <br>
  <el-button @click="redirect"> 点击跳转到登录界面 </el-button>

  </template>

  <script>
  import request from "@/utils/request";

  export default {
    data() {
      return {
        file: {
          url: 'www.baidu.com'
        }
      }
    },
    methods: {
      redirect() {
        request.get("/url_redirect",{
          params:{
            url:'www.baidu.com'
          }
        }).then(res => {
          alert(res.data)
          window.location.href='http://nsep.dongpeibin.cn/pro001';
          //this.downloadFile('flag.txt', res)
        })
      }
    }
  }
  </script>