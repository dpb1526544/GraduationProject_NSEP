<template>
  <div class="forum">
    <nsepheader></nsepheader>
    <el-row>
      <el-col :span="18">
        <forum-card></forum-card>
      </el-col>
      <el-col :span="6">
        <el-card style="background-color: rgba(255,255,255,0.2);
        border-style: none;
        border-radius: 0;
        rgba(255,255,255,0.2);
        margin-right:10%;
        margin-top:3%;
        width: 90%;
        height: 300px;">
          <el-button type="primary" plain style="width:80%;margin-left:5%;margin-top:10%" @click="gotopersonalcenter()">
            已发布的writeup
          </el-button>
          <el-button type="primary" plain style="width:80%;margin-left:5%;margin-top:10%" @click="gotohoneypot()">
            回到实验资源
          </el-button>
        </el-card>
      </el-col>
    </el-row>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "../components/nsepheader.vue";
import nsepfooter from '../components/nsepfooter.vue';
import ForumCard from "../components/ForumCard.vue";
import animation from "../assets/js/background_black";
import router from "../router";
export default {
  components: {
    nsepheader,
    nsepfooter,
    ForumCard
  },
  created() {
    animation()
  },
  methods:{
    gotohoneypot(){
      router.push("/honeypot")
    },
    gotopersonalcenter(){
      router.push({
        path:'/personalcenter',
      })
    }
  }
};
</script>


<style scoped>
.forum{
  margin: 0px;
  /*background-image: linear-gradient(to bottom right, #000000, 	#483D8B);*/
  background-color: rgba(255,255,255,0);
  position: relative;
  width:100%;
  min-height: 100vh;
}

</style>