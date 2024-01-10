<template>
  <br><br>
  <el-card shadow="always"
           style="text-align: center;
           margin-right: 100px;
           margin-left: 100px;
           height: 600px">
    <el-row>
      <el-col :span="8">
      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          NSEP unicode在线编码解码工具
        </div>
        <el-button style="text-align: right" size="small" icon="el-icon-thumb">
          <el-link href="https://blog.csdn.net/hyongilfmmm/article/details/112037596"
                   target="_blank"
                   style="font-size: 20px;color: darkgreen"

          >Unicode简介
          </el-link>
        </el-button>


      </el-col>
      <el-col :span="8">
      </el-col>
    </el-row>

    <br>
    <div class="el-common-layout">
      <div class="text-area" >
        <el-input
            v-model="textdata"
            type="textarea"
            placeholder="请输入待加密或待解密的内容"
            style="width: 700px;font-size: 20px"
            :rows=5
        />
      </div>
    </div>



    <el-button @click="encode" type="success">EnCode</el-button>
    <el-button @click="decode" type="info">DeCode</el-button>

    <el-card style="width: 40%;height: 200px;margin-left: 30%;margin-top: 20px;">
      <p style="text-align: left">编码/解码结果:</p>
      <p>{{ myresult }}</p>
    </el-card>
  </el-card>


</template>

<script>
import request from "@/utils/request";
export default {
  data(){
    return {
      textdata: '',
      myresult: '',
    }
  },
  methods:{
    encode() {
      request.get("tools/Unicode/encode",{
        params:{
          data:this.textdata
        }
      }).then(res=>{
        this.myresult=res.data
      })
    },

    decode() {
      request.get("tools/Unicode/decode",{
        params:{
          data:this.textdata
        }
      }).then(res=>{
        this.myresult=res.data
      })
    }
  }

}
</script>

<style scoped>
/*文本域*/
.text-area {
  width: 100%;
  /*border-top: 1px solid gainsboro;*/
  /*border-bottom: 1px solid gainsboro;*/

}

.text-area textarea {
  width: 50%;
  height: 100%;
  /*margin: 0.75rem  0.75rem;*/
  /*border: none;*/
  /*!*outline: none;*!*/
  /*padding-left: 1.125rem;*/
  height: 10.5rem;
}

.text-area textarea::-webkit-input-placeholder {
  color: #9E9E9E;
}

#app{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
.body{
  margin: 0;
  border: 0;
  padding: 0;
}

#building1{
  position: fixed;
  width: 2500px;
  height: 2500px;
  background-size: 100%,100%;
}

</style>