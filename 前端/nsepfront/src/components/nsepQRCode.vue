<template>
  <p style="margin:0px;
      padding:0px;
      margin-bottom:20px;">
    邀请学生扫描此二维码加入课程
  </p>
  <div class="qrcode"
       ref="qrcodeContainer"
       style="width:80%;
       margin:10%;"></div>
<!--  <p>或输入课程暗号加入课程</p> <h3>8ZHA</h3>-->
</template>

<script>
import QRCode from 'qrcodejs2'  // 引入qrcode
export default {
  name : 'test',
  mounted () {
    this.$nextTick(()=> {
      this.qrcode();
    })
  },
  props:['cid'],
  data(){
    return{
      teacher:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      courseid:this.cid,
    }
  },
  methods: {
    qrcode() {
      let current_url=window.location.href.slice(0,-17);
      let href=current_url+'/invitation'+'?id1='+this.teacher.userid+'&id2='+this.courseid;
      // window.open(href);

      let qrcode = new QRCode(this.$refs.qrcodeContainer, {// eslint-disable-line no-unused-vars
        width: 200,
        height: 200,
        text: href, // 二维码对应的地址
        colorDark : "#000",
        colorLight : "#fff",
        correctLevel: QRCode.CorrectLevel.H
      })
    },
  }
}
</script>