<template>
<!--  靶场模块-->
  <div class="honeypot">
    <nsepheader></nsepheader>
    <bug-card></bug-card>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "../components/nsepheader.vue";
import nsepfooter from '../components/nsepfooter.vue';
import BugCard from "../components/BugCard.vue";
import animation from "../assets/js/background_black";
export default {
  components: {
    nsepheader,
    nsepfooter,
    BugCard,
    // nsepsider
  },
  created() {
    animation()
  },
  setup() {},
};
</script>


<style scoped>
.honeypot {
  /*background-image: url("../assets/img/bg4.jpg");*/
  /*background-size:100% 100%;*/

  background-color:rgba(105,105,105,0.5);
  margin: 0px;
  padding: 0px;
  min-height: 100vh;
}
</style>