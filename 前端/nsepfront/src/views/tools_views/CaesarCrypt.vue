<template>
  <br>
  <br>
  <el-card shadow="always" style="text-align: center; margin-right: 100px;margin-left: 100px;height: 1600px">
    <el-row>
      <el-col :span="8">

      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          NSEP 凯撒密码在线加解密工具
        </div>
        <el-button style="text-align: right" size="small" icon="el-icon-thumb">
          <el-link href="https://blog.csdn.net/dlfls0710/article/details/117831145"
                   target="_blank"
                   style="font-size: 20px;color: darkgreen"
          >凯撒密码简介
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
    <el-button @click="encrypt" type="info" style="margin: 20px">EnCode</el-button>
    <el-button @click="decrypt" type="info" style="margin: 20px">DeCode</el-button>

    <el-input
        v-model="inputKey"
        type="textarea"
        placeholder="请输入偏移量"
        style="width: 100px;font-size: 12px;margin: 20px"
        :rows=1
    />

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
      myresult: '',
      inputKey:'',
    }

  },
  methods: {
    encrypt() {
      request.get("/tools/Caesar/encrypt", {
        params: {
          plain: this.textdata,
          key: this.inputKey,
        }
      }).then(res => {
        this.myresult = res.data;
      })
    },
    decrypt() {
      request.get("/tools/Caesar/decrypt", {
        params: {
          cipher: this.textdata,
          key: this.inputKey,
        }
      }).then(res => {
        this.myresult = res.data
      })
    }

  }
}
</script>

<style scoped>
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