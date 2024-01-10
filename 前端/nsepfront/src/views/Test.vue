<template>
  <el-button size="mini" type="success" @click="downloadHandle(fileName, fileLocation)">下载</el-button>
</template>

<script>

import request from "@/utils/request";

export default {
  data(){
    return{
      fileName:'cipherText1',
      fileLocation: 'cipherTex1',
    }
  },
  methods:{
    // fileName下载设置名称，fileLocation文件存储名称
    downloadHandle(fileName,fileLocation) {
      let prome = {
        fileLocation: fileLocation
      }
      request.post(
          '/download/zip',
          prome,
          {
            responseType: 'blob',
            timeout: 60000
          }
      ).then(response => {
        if (!response.size) {
          this.$message({
            message: '没有可下载文件',
            type: 'warning'
          })
          return
        }
        const url = window.URL.createObjectURL(new Blob([response]))
        const link = window.document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', fileName+'.zip')
        document.body.appendChild(link)
        link.click()
      }).catch((data) => {
        console.log(data)
      })
    },

  }
}
</script>


<style scoped>
.honeypot {
  /*background-image: url("../assets/img/bg4.jpg");*/
  /*background-size:100% 100%;*/
  background-size: 100% 100%;
  margin: 0px;
  padding: 0px;
  min-height: 100vh;
}
</style>