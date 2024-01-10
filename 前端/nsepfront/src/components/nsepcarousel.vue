<template>
  <!-- 走马灯 -->
  <el-carousel indicator-position="outside" height="700px">
    <el-carousel-item v-for="item in imgList" :key="item.name">
      <img :src="item.src"
           style="height: 100%;
           width: 100%" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  setup() {
    return {
      imgList: [
        {
          name: "1",
          src: require("../assets/img/carousel_1.jpg"),
        },
        {
          name: "2",
          src: require("../assets/img/carousel_2.jpg"),
        },
        {
          name: "3",
          src: require("../assets/img/carousel_3.jpg"),
        },
        {
          name: "4",
          src: require("../assets/img/carousel_4.jpg"),
        },
        {
          name: "5",
          src: require("../assets/img/carousel_5.jpg"),
        },
      ],
    };
  },
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 600px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>

