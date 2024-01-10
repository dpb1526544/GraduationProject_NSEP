<template>
  <!--  <p>任意文件下载漏洞</p>-->
  <div class="line"></div>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <el-menu-item index="0">
      <el-image
        :src="imgsrc2"
        style="
          height: 90%;
          width: 105%;
          margin-left: 20%;
          margin-top: 5%;
          border-radius: 30%;
        "
      />
    </el-menu-item>
    <el-menu-item index="1" style="font-size: 100%; margin-left: 3%"
      >首页</el-menu-item
    >
    <el-submenu index="2" style="font-size: 100%">
      <template v-slot:title>软件下载</template>
      <el-menu-item index="2-1">聊天软件</el-menu-item>
      <el-menu-item index="2-2">影音软件</el-menu-item>
      <el-menu-item index="2-3">游戏软件</el-menu-item>
      <el-submenu index="2-4">
        <template v-slot:title>安全软件</template>
        <el-menu-item index="2-4-1">杀毒软件</el-menu-item>
        <el-menu-item index="2-4-2">安全卫士</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-menu-item index="3" style="font-size: 100%">驱动下载</el-menu-item>
    <el-menu-item index="4" style="font-size: 100%">软件推荐</el-menu-item>

    <el-menu-item index="5" style="margin-left: 50%">
      <i :class="imgurl0" style="color: white; font-size: 180%"></i>
    </el-menu-item>
    <el-menu-item index="6" style="">
      <el-image
        :src="imgsrc0"
        style="
          height: 95%;
          width: 95%;
          margin-left: 15%;
          margin-top: 5%;
          border-radius: 100%;
        "
      />
    </el-menu-item>
  </el-menu>
  <div class="block">
    <span class="demonstration"></span>
    <!-- 走马灯 -->
    <el-carousel indicator-position="outside" height="500px">
      <el-carousel-item v-for="item in imgList" :key="item.name">
        <img :src="item.src" style="height: 100%; width: 100%" />
      </el-carousel-item>
    </el-carousel>
  </div>

  <el-divider></el-divider>

  <el-row>
    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                影视大全pc版 1.0.0.1351 最新版
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrc"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：40.3M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：影音软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2021-05-11
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="70"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="30"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="download"
              style="
                width: 50%;
                height: 20%;
                margin-left: 24%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>
    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                2022剪映windows版 v2.9.5.8278 官方最新版
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrc1"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：350.5M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：影音软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2022-04-01
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="81"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="19"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="noflag"
              style="
                width: 50%;
                height: 20%;
                margin-left: 24%;
                margin-top: 0%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>
    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                酷狗音乐播放器最新版 v10.0.53 官方电脑版
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrc3"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：90.3M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：影音软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2022-03-21
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="88"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="12"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="noflag"
              style="
                width: 50%;
                height: 10%;
                margin-left: 24%;
                margin-top: 0%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>

    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                虾米音乐播放器免费软件
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrcxiami"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：83.3M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：影音软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2021-12-19
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="85"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="15"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="download"
              style="
                width: 50%;
                height: 20%;
                margin-left: 24%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>
    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                腾讯电脑管家pc版安装包 v15.3 最新版
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrctengxun"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：556.5M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：安全软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2022-05-31
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="60"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="40"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="noflag"
              style="
                width: 50%;
                height: 20%;
                margin-left: 24%;
                margin-top: 0%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>
    <el-col :span="8">
      <div style="margin: 30px; width: 100%; height: 180%">
        <el-card class="box-card" style="width: 90%">
          <template #header>
            <div class="card-header">
              <span style="color: #42b983; font-weight: bold">
                全民仙战37游戏 v3.0.0.0 官方最新版
              </span>
            </div>
          </template>

          <el-row>
            <el-col :span="4">
              <div style="text-align: left">
                <el-image
                  :src="imgsrcgame"
                  style="
                    height: 120px;
                    width: 120px;
                    margin-left: 25%;
                    margin-top: 30%;
                  "
                />
              </div>
            </el-col>
            <el-col :span="15" style="margin-top: 1%">
              <p style="text-align: left; margin-left: 30%">软件大小：963.7M</p>
              <p style="text-align: left; margin-left: 30%">
                软件类型：游戏软件
              </p>
              <p style="text-align: left; margin-left: 30%">软件语言：中文</p>
              <p style="text-align: left; margin-left: 30%">
                更新时间：2022-01-31
              </p>
            </el-col>
          </el-row>
          <el-row>
            <div class="demo-progress">
              <el-col :span="5" style="margin-left: 5%">
                <p>Like:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="100"
                  style="margin-left: 20%; margin-top: 20%; stroke-width: 4"
                  color="#67C23A"
                />
              </el-col>
              <el-col :span="5" style="margin-left: 25%">
                <p>Dislike:</p>
              </el-col>
              <el-col :span="5">
                <el-progress
                  :text-inside="true"
                  :stroke-width="20"
                  :percentage="0"
                  style="margin-left: 20%; margin-top: 20%"
                  color="#409EFF"
                />
              </el-col>
            </div>
          </el-row>
          <el-row class="mb-4" style="margin-top: 3%">
            <el-button
              @click="noflag"
              style="
                width: 50%;
                height: 10%;
                margin-left: 24%;
                margin-top: 0%;
                background-color: #42b983;
              "
            >
              <el-col>
                <i
                  :class="imgurl"
                  style="color: white; font-size: 40px; margin-left: 50%"
                >
                </i>
              </el-col>
              <el-col>
                <p style="color: white; margin-left: 70%; font-size: 120%">
                  点击下载
                </p>
              </el-col>
            </el-button>
          </el-row>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 0px;
  margin-top: 10px;
}

.box-card {
  width: 50%;
  height: 50%;
}

//head样式
.el-card ::v-deep .el-card__header {
  padding: 4px 10px;
  //background-color: palegoldenrod;
}

//body样式
.el-card ::v-deep .el-card__body {
  padding: 0px;
  //background-color: powderblue;
}

//进度条
.demo-progress .el-progress--line {
  margin-bottom: 8px;
  width: 130px;
}

//zoumadeng
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      filename: "kugou.exe",
      imgsrc: require("@/assets/img/bugimg/moviePC.png"),
      imgsrc1: require("@/assets/img/bugimg/cutmovie.png"),
      imgsrc2: require("@/assets/img/bugimg/software.jpeg"),
      imgsrc3: require("@/assets/img/bugimg/kugou.png"),
      imgsrc17: require("@/assets/img/bugimg/fileupload0.png"),
      imgsrcxiami: require("@/assets/img/bugimg/xiami.jpg"),
      imgsrctengxun: require("@/assets/img/bugimg/tengxun.png"),
      imgsrcgame: require("@/assets/img/bugimg/game.png"),
      imgurl: "el-icon-download",

      imgsrc0: require("@/assets/img/bugimg/p.jpeg"),
      imgsrc11: require("@/assets/img/bugimg/net.jpeg"),
      imgsrc12: require("@/assets/img/logo3.jpg"),
      imgurl0: "el-icon-bell",

      activeIndex: "1",
      activeIndex2: "1",
      name: "DevSort",
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},

      file: {
        name: "kugou.exe",
      },

      imgList: [
        {
          name: "1",
          src: require("@/assets/img/bugimg/moviepc1.jpeg"),
        },
        {
          name: "2",
          src: require("@/assets/img/bugimg/jianying.png"),
        },
        {
          name: "3",
          src: require("@/assets/img/bugimg/kugou.jpg"),
        },
        {
          name: "4",
          src: require("@/assets/img/bugimg/kugou1.jpg"),
        },
      ],
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },

    download() {
      request
        .get("/Download", {
          params: {
            filename: "kugou.exe",
            username: this.user.username,
          },
        })
        .then((res) => {
          // alert(res)
          this.downloadFile(this.filename, res.data);
        });
    },

    downloadFile(fileName, data) {
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));
      let link = document.createElement("a");
      link.style.display = "none";
      link.href = url;
      link.setAttribute("download", fileName);
      document.body.appendChild(link);
      link.click();
    },

    noflag() {
      alert("Flag is not here!");
    },
  },
};
//2130
</script>
