<template>
  <h1>留言板</h1>
  <el-input v-model="content" style="width: 50%;" ></el-input>
  <el-button @click="submit" >提交</el-button>

  <el-input v-model="content" style="width: 50%;" disabled></el-input>

  <el-button >删除</el-button>
</template>

<script>
import request from "@/utils/request";
import router from "../../../router";

export default {
  data(){
    return{
      content:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    submit(){
      request.get("sqli/delete",{
        params:{
          messahe:this.content,
        }
      }).then(res =>{
        console.log(res)

      })
    },

    delete(){
      request.get("sqli/delete",{
        params:{
          messahe:this.content,
        }
      }).then(res =>{
        console.log(res)
        if(res=='delete'){
          this.content=''
        }else{
          router.push('/delete1');
        }
      })
    },

  }
}
</script>

