<template>
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

    <el-menu-item index="1" style="font-size: 20px; margin-left: 3%">
      首页
    </el-menu-item>

    <el-menu-item index="2" style="font-size: 20px"> 学习资料 </el-menu-item>

    <el-menu-item index="3" style="font-size: 20px"> 精品软件 </el-menu-item>

    <el-menu-item index="4" style="font-size: 20px"> 优站精选 </el-menu-item>

    <el-menu-item index="5" style="font-size: 20px"> 电子书籍 </el-menu-item>

    <el-menu-item index="6" style="font-size: 20px"> 模板资源 </el-menu-item>

    <el-menu-item index="7" style="font-size: 20px"> VIP解析 </el-menu-item>
  </el-menu>

  <el-row>
    <el-col :span="16" style="margin-top: 3%">
      <el-row>
        <div class="block" style="margin-left: 14%; width: 100%">
          <span class="demonstration"></span>
          <!-- 走马灯 -->
          <el-carousel indicator-position="outside">
            <el-carousel-item v-for="item in imgList" :key="item.name">
              <img :src="item.src" style="height: 100%; width: 100%" />
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-row>

      <el-row>
        <el-col style="margin-top: 3%">
          <div style="margin-left: 14%; width: 86%">
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 25px"
                  >
                    置顶文章
                  </span>
                </div>
              </template>

              <el-row>
                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc3"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        热情能使人笑对困难。苏轼仕途受挫,把自己的热情倾注于山水之间,享受大自然带给自己的乐趣,驱散心中的乌云。
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc4"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        海,真的海,同北方高原那片苍茫的土地一样,凝聚着一种无法言说的神秘的生命力,给人一种超越自然的深刻。
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc5"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        这陡峻的岩石耸立着，仿佛在无声地叙述着什么，又仿佛在期待着什么，沉思着什么。
                      </span>
                    </div>
                  </el-row>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </el-col>
      </el-row>

      <el-row>
        <el-col style="margin-top: 3%">
          <div style="margin-left: 14%; width: 86%">
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 25px"
                  >
                    学习资料
                  </span>
                </div>
              </template>
              <el-row>
                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc8"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        王策VLOG个人短视频经验分享课
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc9"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>
                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        助你轻松玩转小红书爆粉特训班5.0
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left">
                      <el-image
                        :src="imgsrc10"
                        style="
                          height: 100%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 6%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        0基础掌握二次剪辑和混剪技术
                      </span>
                    </div>
                  </el-row>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </el-col>
      </el-row>

      <el-row>
        <el-col style="margin-top: 3%">
          <div style="margin-left: 14%; width: 86%">
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 25px"
                  >
                    电子书籍
                  </span>
                </div>
              </template>

              <el-row>
                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left; height: 50%">
                      <el-image
                        :src="imgsrc11"
                        style="
                          height: 120%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        margin-top: 10%;
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        从零开始做运营2：运营人的进化
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left; height: 50%">
                      <el-image
                        :src="imgsrc12"
                        style="
                          height: 80%;
                          width: 80%;
                          margin-left: 4%;
                          margin-top: 8%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row>
                    <div
                      style="
                        text-align: left;
                        width: 90%;
                        margin-left: 3%;
                        margin-top: 9%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        奔跑吧，程序员 – 从零开始打造产品、技术和团队
                      </span>
                    </div>
                  </el-row>
                </el-col>

                <el-col :span="8">
                  <el-row>
                    <div style="text-align: left; height: 50%">
                      <el-image
                        :src="imgsrc13"
                        style="
                          height: 80%;
                          width: 90%;
                          margin-left: 4%;
                          margin-top: 2%;
                        "
                      />
                    </div>
                  </el-row>

                  <el-row style="">
                    <div
                      style="
                        text-align: left;
                        width: 80%;
                        margin-left: 3%;
                        margin-top: 8%;
                      "
                    >
                      <span
                        style="
                          color: #000000;
                          font-weight: bold;
                          font-size: 10px;
                          margin-left: 3%;
                        "
                      >
                        成为数据分析师：6步练就数据思维
                      </span>
                    </div>
                  </el-row>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </el-col>

    <el-col :span="8" style="margin-top: 3%">
      <el-row>
        <div style="margin-left: 0%">
          <el-card class="box-card" style="width: 95%; margin-left: 10%">
            <template #header>
              <div class="card-header">
                <span
                  style="color: #42b983; font-weight: bold; font-size: 25px"
                >
                  资源分享
                </span>
              </div>
            </template>
            <div class="blog">
              <div class="upload">
                <input
                  style="margin-left: 20%"
                  type="file"
                  @change="fileChoose($event)"
                />
                <button style="margin-top: 5%" @click="submit">
                  <span> 提交 </span>
                </button>
              </div>
            </div>
            <!--            <el-form-item label="图片：" prop="tempImagePath">-->
            <!--              <el-upload-->
            <!--                  class="upload"-->
            <!--                  accept="image/jpeg"-->
            <!--                  :show-file-list="false"-->
            <!--                  list-type="picture-card"-->
            <!--                  :headers="{ token: token}"-->
            <!--                  :action="actionUrl"-->
            <!--                  :before-upload="beforeAvatarUpload"-->
            <!--                  :auto-upload="false"-->
            <!--                  :on-success="handleAvatarSuccess">-->
            <!--                <div class="el-upload__tip">-->
            <!--                  只能上传.gif/.jpeg/.png文件且小于500K-->
            <!--                </div>-->
            <!--              </el-upload>-->
            <!--            </el-form-item>-->
          </el-card>
        </div>
      </el-row>
      <el-row>
        <div style="margin-left: 0%">
          <el-card
            class="box-card"
            style="width: 65%; margin-left: 7%; margin-top: 10%"
          >
            <template #header>
              <div class="card-header">
                <span
                  style="color: #42b983; font-weight: bold; font-size: 25px"
                >
                  最新分享
                </span>
              </div>
            </template>
            <el-row>
              <el-col span="4">
                <div style="text-align: left">
                  <el-image :src="imgsrc14" style="" />
                </div>
              </el-col>
              <el-col span="4">
                <div style="margin-top: 5%">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 15px"
                    >北鱼写作工坊文笔视频课程
                  </span>
                </div>
              </el-col>
            </el-row>

            <el-row style="margin-top: 20%">
              <el-col span="4">
                <div style="text-align: left">
                  <el-image :src="imgsrc15" style="" />
                </div>
              </el-col>
              <el-col span="4">
                <div style="margin-top: 5%">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 15px"
                    >PPTist—在线ppt制作
                  </span>
                </div>
              </el-col>
            </el-row>

            <el-row style="margin-top: 20%">
              <el-col span="4">
                <div style="text-align: left">
                  <el-image :src="imgsrc16" style="" />
                </div>
              </el-col>
              <el-col span="4">
                <div style="margin-top: 5%">
                  <span
                    style="color: #000000; font-weight: bold; font-size: 15px"
                    >视频特效，教程及工程文件下载站
                  </span>
                </div>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-row>
    </el-col>
  </el-row>

  <el-divider></el-divider>
</template>

<script>
import request from "@/utils/request";
import axios from "axios";

export default {
  data() {
    return {
      imgsrc: require("@/assets/img/bugimg/moviePC.png"),
      imgsrc1: require("@/assets/img/bugimg/cutmovie.png"),
      imgsrc2: require("@/assets/img/bugimg/fileuploadlogo.jpeg"),
      imgsrc3: require("@/assets/img/bugimg/fileupload2.jpg"),
      imgsrc4: require("@/assets/img/bugimg/fileupload3.jpg"),
      imgsrc5: require("@/assets/img/bugimg/fileupload4.jpg"),
      imgsrc6: require("@/assets/img/bugimg/fileupload5.jpg"),
      imgsrc7: require("@/assets/img/bugimg/fileupload6.jpg"),
      imgsrc8: require("@/assets/img/bugimg/fileupload8.png"),
      imgsrc9: require("@/assets/img/bugimg/fileupload9.png"),
      imgsrc10: require("@/assets/img/bugimg/fileupload10.png"),
      imgsrc11: require("@/assets/img/bugimg/fileupload11.jpg"),
      imgsrc12: require("@/assets/img/bugimg/fileupload12.jpg"),
      imgsrc13: require("@/assets/img/bugimg/fileupload13.jpg"),
      imgsrc14: require("@/assets/img/bugimg/fileupload15.png"),
      imgsrc15: require("@/assets/img/bugimg/fileupload16.jpg"),
      imgsrc16: require("@/assets/img/bugimg/fileupload17.jpg"),
      imgsrc17: require("@/assets/img/bugimg/fileupload0.png"),
      imgurl: "el-icon-download",

      imgList: [
        {
          name: "1",
          src: require("@/assets/img/bugimg/fileupload.jpg"),
        },
        {
          name: "2",
          src: require("@/assets/img/bugimg/jianying.png"),
        },
        {
          name: "3",
          src: require("@/assets/img/bugimg/fileupload1.jpg"),
        },
        {
          name: "4",
          src: require("@/assets/img/bugimg/kugou1.jpg"),
        },
      ],
      file: "",
      formData: "",
      actionUrl: "http://43.143.195.149:8081/upload/file",
      token: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user")).token
        : null,
      fileLists: [],
    };
  },
  methods: {
    fileChoose(event) {
      this.file = event.target.files[0]; //
      console.log(event);
    },

    submit() {
      let file = new FormData();

      file.append("file", this.file);
      let fileName = file.get("file").name;
      let index = fileName.lastIndexOf(".");
      // 黑名单方式，且没加全部转为小写
      // let fileType = ['jsp','php','asp','aspx']
      // 白名单，前端验证可绕过
      let fileType = [
        "jpg",
        "png",
        "gif",
        "doc",
        "pdf",
        "exe",
        "docx",
        "html",
        "jpeg",
        "mp4",
      ];
      if (index != -1) {
        let suffix = fileName.substr(index + 1);
        // .toLowerCase();
        if (fileType.includes(suffix)) {
          axios
            .post("http://43.143.195.149:8081/upload/file", file, {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            })
            .then((res) => {
              this.$message.success("⽂件上传成功");
              alert(res.data);
            })
            .catch((res) => {
              this.$message.error("⽂件上传失败");
              alert(res.data);
            });
        } else {
          this.$message.error(
            "文件格式错误！请选择'jpg','png','gif','doc','pdf','exe','docx','html','jpeg','mp4'格式的文件"
          );
        }
      }
    },

    upload() {
      request.post("/upload/file", this.formData).then((res) => {
        if (res.code == "200") {
          console.log("新到文件名", res.data);
          this.$message.success("文件上传成功");
        } else {
          alert("ai");
        }
      });
    },

    getFile(event) {
      let file = event.target.files[0];
      let fileName = file.name;
      let index = fileName.lastIndexOf(".");
      let fileType = ["png", "jpeg", "jpg", "jif", "doc", "pdf", "xls"];
      if (index != -1) {
        let suffix = fileName.substr(index + 1).toLowerCase();
        if (fileType.includes(suffix)) {
          this.formData.append("file", file);
        } else {
          this.$message.error(
            "文件格式错误！请选择'png','jpeg','jpg','jif','doc','pdf','xls'格式的文件"
          );
        }
      }
    },

    beforeAvatarUpload(file) {
      const imgType = file.type === "image/jpeg" || file.type === "image/png";
      const isLt500k = file.size / 1024 / 1024 < 0.48;
      if (!imgType) {
        this.$message.error("上传图片只能是 JPG和png 格式!");
        return false;
      }
      if (!isLt500k) {
        this.$message.error("上传图片大小不能超过 500k!");
        return false;
      }
    },

    handleRemove(file, fileList) {
      console.log(file, fileList);
    },

    handlePreview(file) {
      console.log(file);
    },

    beforeRemove(file) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    handleChange(file) {
      this.fileLists.push(file);
    },

    submitUpload() {
      const formData = new FormData();
      // 因为要传一个文件数组过去，所以要循环append
      this.fileLists.forEach((file) => {
        formData.append("files", file.raw);
      });
      this.uploadRequest(formData);
    },

    uploadRequest(formData) {
      let that = this;
      axios
        .post("http://43.143.195.149:8081/upload", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(function (res) {
          console.log(res);
          if (res.data == "ok") {
            that.$message.success("上传成功!");
          }
        })
        .catch(function () {
          that.$message.error("网络请求异常!");
        });
    },
  },
};
</script>