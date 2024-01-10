<template>
  <br>
  <br>
  <el-card shadow="always" style="text-align: center; margin-right: 100px;margin-left: 100px;height: 700px">
    <!--    标题-->
    <el-row>
      <el-col :span="8">
      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          NSEP Rot13在线加解密
        </div>
        <el-button style="text-align: right" size="small" icon="el-icon-thumb">
          <el-link href="https://blog.csdn.net/qq_17046291/article/details/80306580?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165459051316782184695260%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165459051316782184695260&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-80306580-null-null.142^v11^pc_search_result_control_group,157^v13^new_style1&utm_term=Rot13&spm=1018.2226.3001.4187"
                   target="_blank"
                   style="font-size: 20px;color: darkgreen"

          >什么是Rot13？
          </el-link>
        </el-button>
      </el-col>
      <el-col :span="8">
      </el-col>
    </el-row>

    <!--    输入框-->
    <div class="el-common-layout">
      <div class="text-area" style="margin: 10px">
        <!--    <textarea v-model="textdata" placeholder="请输入编码字符串或待解码字符串">-->
        <!--    </textarea>-->
        <el-input
            v-model="textdata"
            type="textarea"
            placeholder="请输入待加密或待解密的内容"
            style="width: 600px;font-size: 20px;margin: 10px;"
            :rows=5
        />
      </div>
    </div>

    <!--    加解密按钮-->
    <el-button @click="encode(textdata)" type="info">EnCrypto</el-button>
    <el-button @click="decode(textdata)" type="info">DeCrypto</el-button>

    <!--    结果区域-->
    <el-card style="width: 40%;height: 20%;margin-left: 30%;margin-top: 3%">
      <h2 style="text-align: left">加密/解密结果:</h2>
      <el-input
          v-model="myresult"
          type="textarea"
          placeholder=""
          style="font-size: 20px"
          disabled
          :rows=5
      />
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
    encode(text) {
      // alert(this.textdata)
      request.get("/Rot13/encrypto", {
        params: {
          Text: text
        }
      }).then(res => {
        //alert(res.data);
        this.myresult = res.data
      })
    },
    decode(text) {
      request.get("/Rot13/decrypto", {
        params: {
          Text: text,
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

</style>