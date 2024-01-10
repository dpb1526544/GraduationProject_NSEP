<template>
  <el-row :gutter="10">
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
      <div class="grid-content bg-purple">
        <p>WEBSITE</p>
        <a class="aaa" href="http://blog.dongpeibin.cn/" target="_blank">BLOG</a><br>
        <a class="aaa" href="http://nsep.dongpeibin.cn/" target="_blank">NSEP</a><br>
      </div>
    </el-col>
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple">
        <el-divider direction="vertical" style="color: gray"></el-divider>
      </div>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
      <div class="grid-content bg-purple">
        <p>CONTACT THE DEVELOPER</p>
        <a class="aaa" href="http://blog.dongpeibin.cn/" target="_blank">Dong Peibin</a><br>
        <a class="aaa" href="https://gitee.com/dong-peibin" target="_blank">gitee</a><br>
        <a class="aaa" href="https://github.com/dpb1526544" target="_blank">github</a>
      </div>
    </el-col>
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
  </el-row>
</template>

<style  scoped>
.el-row {
  color: azure;
  height: 500px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple {
  margin-top: 40%;
  text-align: center;
  font-size: 25px;
  /* background-color: antiquewhite; */
  height: 300px;
}
.bg-purple .aaa {
  text-align: center;
  font-size: 20px;
  text-decoration:none;
  color: gray;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-divider {
  position: relative;
  vertical-align: middle;
  height: 20ex;
}
</style>