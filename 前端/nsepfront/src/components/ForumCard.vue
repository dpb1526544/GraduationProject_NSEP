<template>
  <div class="main">
    <!--    搜索发布部分-->
    <el-card
      style="
        background-color: rgba(255, 255, 255, 0.2);
        border-style: none;
        border-radius: 0;
        margin-bottom: 2%;
        padding-right: 0%;
        padding-left: 0%;
      "
    >
      <el-row>
        <el-col :span="2"></el-col>
        <el-col :span="16">
          <div>
            <el-input
              style="width: 100%; text-align: right; margin-right: 0"
              placeholder="请输入关键词"
              suffix-icon="el-icon-search"
              v-model="search"
            />
          </div>
        </el-col>
        <el-col :span="2">
          <div style="text-align: left; margin-left: 10px">
            <el-button class="btn-group" @click="load" style="width: 90%"
              >搜索</el-button
            >
          </div>
        </el-col>
        <el-col :span="2">
          <div style="text-align: left">
            <el-button class="btn-group" @click="writeup" style="width: 90%"
              >发布</el-button
            >
          </div>
        </el-col>
        <el-col :span="4"><div></div></el-col>
      </el-row>
    </el-card>

    <el-card
      style="
        background-color: rgba(255, 255, 255, 0.2);
        border-style: none;
        border-radius: 0;
        margin-bottom: 30px;
      "
    >
      <div
        direction="vertical"
        style="text-align: left; margin-left: 5%; margin-right: 5%"
      >
        <el-card
          v-for="(forum, i) in forumlist"
          :key="i"
          class="box-card"
          shadow="hover"
        >
          <template #header>
            <div class="card-header">
              <span style="font-weight: bold">
                <h2 style="margin: 0px; padding: 0px">{{ forum.title }}</h2>
              </span>
            </div>
          </template>
          <div class="text item">
            <p style="font-size: 15px; line-height: 30px">
              {{ forum.content }}
            </p>
          </div>
          <div class="text item" style="text-align: right">
            {{ forum.time }}
          </div>
        </el-card>
      </div>

      <!--分页组件-->
      <el-pagination
        v-model:currentPage="currentPage"
        background
        :page-size="pageSize"
        :disabled="disabled"
        layout="total, prev, pager, next"
        :total="totalpage"
        @size-change="handleSizeChange"
        @current-change="load"
        style="padding-top: 20px"
      />
    </el-card>
  </div>

  <!--  发布writeup弹窗-->
  <el-dialog title="发布" v-model="dialogVisible" width="30%">
    <el-form :model="form" label-width="60px">
      <el-form-item label="题目">
        <el-input v-model="title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="content" type="textarea" :rows="5"></el-input>
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="savewrite">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";
import { ElMessage } from "element-plus";

export default {
  data() {
    const formatTooltip = (val) => {
      return val / 100;
    };

    return {
      title:'',
      content:'',
      formatTooltip,
      dialogVisible: false,
      search: "",
      currentPage: 1,
      pageSize: 4,
      totalpage: 0,
      forumlist: [],
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
      form: {},
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/forum/page", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          this.forumlist = res.data.Pagecontent;
          this.totalpage = res.data.PageNum;
        });
    },

    writeup() {
      this.dialogVisible = true;
      this.form = {};
    },
    savewrite() {
      if (this.title === '') {
        ElMessage({
          message: "题解名不能为空",
          center: true,
          type: "error",
        });
        return false;
      } else if (this.content === '') {
        ElMessage({
          message: "题解内容不能为空",
          center: true,
          type: "error",
        });
        return false;
      } else {
        this.form.username = this.user.username;
        this.form.title = this.title;
        this.form.content = this.content;
        request.post("/forum/save", this.form).then((res) => {
          console.log(res);
        });
        ElMessage({
          message: "发布成功！",
          center: true,
          type: "success",
        });
        location.reload();
        this.dialogVisible = false;
      }
    },
  },
};
</script>


<style scoped>
.main {
  margin-left: 50px; /**外边距 */
  margin-right: 10px; /**外边距 */
  margin-top: 0px;
  padding: 10px; /**内边距 */
}
.el-row {
  margin-top: 10px;
  height: 60px;
}
.btn-group {
  height: 30px;
  background-color: #303133;
  color: #99a9bf;
  border-color: #8cc5ff;
}
/*.el-input >>> .el-input__inner {*/
/*  background-color: rgba(0, 0, 0, 0.2);*/
/*  color: white;*/
/*}*/
.box-card {
  margin-top: 10px;
  background-color: rgba(135, 135, 135, 0.6);
  border-style: none;
  color: white;
  border-radius: 0;
}
</style>