<template>
  <br>
  <br>
  <el-card shadow="always" style="text-align: center; margin-right: 100px;margin-left: 100px;height: 600px">
    <el-row>
      <el-col :span="8">

      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          NSEP BASE64在线编码解码工具
        </div>
        <el-button style="text-align: right" size="small" icon="el-icon-thumb">
          <el-link href="https://blog.csdn.net/local_752/article/details/121970823"
                   target="_blank"
                   style="font-size: 20px;color: darkgreen"

          >什么是BASE64？
          </el-link>
        </el-button>
      </el-col>
      <el-col :span="8">

      </el-col>
    </el-row>


    <div class="el-common-layout">
      <div class="text-area" style="margin: 10px">
        <el-input
            v-model="textdata"
            type="textarea"
            placeholder="请输入待加密或待解密的内容"
            style="width: 700px;font-size: 20px"
            :rows=5
        />
      </div>

    </div>

    <el-button @click="encode" type="info" style="margin: 20px">EnCode</el-button>
    <el-button @click="decode" type="info">DeCode</el-button>

    <el-card style="width: 40%;height: 100px;margin-left: 30%;margin-top: 5%">
      <p style="text-align: left">编码/解码结果:</p>
      <p>{{ myresult }}</p>
    </el-card>
  </el-card>


</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      textdata: '',
      myresult: ''
    }

  },
  methods: {
    encode() {
      request.get("/tools/Base64/encode", {
        params: {
          data: this.textdata
        }
      }).then(res => {
        this.myresult = res.data
      })
    },
    decode() {
      request.get("/tools/Base64/decode", {
        params: {
          data: this.textdata,
        }
      }).then(res => {
        this.myresult = res.data
      })
    }

  }
}
</script>

<style scoped>
/*文本域*/
.text-area {
  width: 100%;

}

.text-area textarea {
  width: 50%;
  height: 100%;
  height: 10.5rem;
}

.text-area textarea::-webkit-input-placeholder {
  color: #9E9E9E;

}

</style>