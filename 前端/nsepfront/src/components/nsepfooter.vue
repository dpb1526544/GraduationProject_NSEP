<template>
    <div class='footer'>
        <span class="scroll"></span>
        <el-divider></el-divider>
        <a
        target="_blank"
        style="color: #ffffff"
        href="https://beian.miit.gov.cn/">鲁ICP备2022010441号-3</a>
      &nbsp; @2022 - 2023 All rights reserved
        <br/>
        <el-drawer></el-drawer>
    </div>
  </template>
  <style>
    .el-divider{
      background-color: rgb(219, 218, 158);
    }
  </style>
  <style scoped>
      .footer{
          height: 250px;
          text-align: center;
          font-size: 16px;
          padding: 0px 100px;
          position: relative;
          color: #ffffff;
      }
      .footer{
        cursor: pointer;
      }
      .copyright{
        font-size: 12px;
      }
      .info{
        font-size: 14px;
        color: #72767b;
        line-height: 25px;
      }
      .footer .scroll{
        display: inline-block;
        width: 20px;
        height: 20px;
        border-radius: 5px;
        border:1px solid #0f70df;
        background-color: rgb(0, 255, 200);
        position: absolute;
        left: 5%;
        top: -25px;
        z-index: 10;
        animation: scrollA infinite 20s linear alternate;
      }
      @keyframes scrollA {
        0% {left: 5%;transform: rotate(180deg);};
        10% {left: 5%;transform: rotate(270deg);}
        20% {left: 5%;transform: rotate(450deg);}
        25% {left: 10%;transform: rotate(540deg);}
        30% {left: 20%;transform: rotate(720deg);}
        35% {left: 30%;transform: rotate(900deg);}
        40% {left: 40%;transform: rotate(1080deg);}
        45% {left: 50%;transform: rotate(1260deg);}
        50% {left: 60%;transform: rotate(1440deg);}
        55% {left: 70%;transform: rotate(1620deg);}
        60% {left: 80%;transform: rotate(1800deg);}
        80% {left: 90%;transform: rotate(2610deg);}
        90% {left: 90%;transform: rotate(2340deg);}
        100% {left: 90%;transform: rotate(2520deg);}
      }
  </style>
  <script>
  export default {
    name: 'Footer',
    data () {
      return {
        drawer: false,
        direction: 'btt'
      }
    },
    methods: {
      handleClose (done) {
        done()
      }
    }
  }
  </script>