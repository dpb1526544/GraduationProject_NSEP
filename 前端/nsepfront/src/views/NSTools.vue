<template>
  <div class="cryptoTool">
    <nsepheader></nsepheader>
    <!--    安全工具-->
    <div style="background-color: rgba(8,46,84,0.8);
          margin-top:0px;
          padding: 0px;">
      <toolscard></toolscard>
    </div>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "../components/nsepheader.vue";
import nsepfooter from '../components/nsepfooter.vue';
import toolscard from "../components/ToolsCard.vue";
import animation from "../assets/js/background_black";

export default {
  components: {
    nsepheader,
    nsepfooter,
    toolscard,
  },
  created() {
    animation()
  }
};
</script>

<style scoped>
</style>