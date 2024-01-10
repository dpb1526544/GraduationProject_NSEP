<template>
  <div class="management"
       style="padding-bottom: 5px;background-color: rgba(255,255,255,0.6)">

    <nsepheader></nsepheader>

    <!--    搜索框-->
    <div style="margin-top: 20px">
      <el-input
          style="width: 500px; "
          placeholder="请输入学工号查询"
          suffix-icon="el-icon-search"
          v-model="search" />
      <el-button type="primary"
                 plain
                 style="margin: 10px"
                 @click="getstuinfo">查询</el-button>
      <el-button type="primary"
                 plain
                 style="margin: 10px"
                 @click="dialogVisible=true;user={}">新建用户</el-button>
    </div>

    <el-card  style="width:80%;
              margin-top:2%;
              margin-left:10%;">

      <!--      卡片表格-->
      <div class="table-wrapper" >
        <el-table :data="tableData"
                  border="true"
                  style="width: 100%;">
          <el-table-column prop="username" label="学号" />
          <el-table-column prop="password" label="密码" />
          <el-table-column prop="realname" label="姓名" />
          <el-table-column prop="email" label="邮箱" />
          <el-table-column label="权限">
            <template #default="scope">
              <p v-if="scope.row.role==1">学生</p>
              <p v-if="scope.row.role==2">教师</p>
              <p v-if="scope.row.role==3">管理员</p>
            </template>
          </el-table-column>

          <el-table-column  label="操作" width="180">
            <template #default="scope">
              <el-button type="success"
                         plain
                         @click="edit(scope.row)" 
                         v-if="scope.row.role != 3">编辑</el-button>
              <el-button type="danger"
                         plain
                         @click="deletethis(scope.row.username)"
                         v-if="scope.row.role != 3">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            v-model:currentPage="currentPage"
            :page-size="pageSize"
            :disabled="disabled"
            :background="background"
            layout="total, prev, pager, next"
            :total="totalpage"
            @size-change="handleSizeChange"
            @current-change="getstuinfo(currentcourse)"
            style="padding-top: 20px"
        />
      </div>
    </el-card>

    <!--弹窗-->
    <el-dialog title="用户信息"
               v-model="dialogVisible"
               width="30%">
      <el-form :model="user"
               label-width="20%">
        <el-form-item label="账户">
          <el-input v-model="user.username"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="user.realname"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="user.email"
                    placeholder="选填"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="用户身份">
          <el-radio-group v-model="user.role">
            <el-radio label="2">教师</el-radio>
            <el-radio label="1">学生</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary"
                 @click="savenew">确 定</el-button>
      </span>
    </el-dialog>


    <!--弹窗-->
    <el-dialog title="用户信息"
               v-model="dialogVisible2"
               width="30%">
      <el-form :model="user"
               label-width="20%">
        <el-form-item label="账户">
          <el-input v-model="user.username"
                    disabled></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="user.realname"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="user.email"
                    placeholder="选填"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="用户身份">
          <el-radio-group v-model="user.role">
            <el-radio :label="2">教师</el-radio>
            <el-radio :label="1">学生</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="dialogVisible2 = false">取 消</el-button>
      <el-button type="primary" @click="saveedit()">确 定</el-button>
      </span>
    </el-dialog>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "@/components/nsepheader";
import nsepfooter from '../components/nsepfooter.vue';
import animation from "../assets/js/background_white";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  components: {
    nsepheader,
    nsepfooter
  },
  data(){
    return{
      tableData:[],
      currentPage:1,
      pageSize:5,
      search:'',
      totalpage:'',
      currentusername:'',
      dialogVisible:false,
      dialogVisible2:false,
      user:{},
    }
  },
  created(){
    animation()
    this.getstuinfo()
  },
  methods: {
    //加载表格用户信息
    getstuinfo(){
      request.get("/user/page",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search,
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.Pagecontent
        this.totalpage=res.data.PageNum
        console.log(this.totalpage)
      })
    },
    //删除用户信息
    deletethis(a){
      this.$confirm('确认删除该用户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.get("/user/delete", {
          params: {
            username: a
          }
        }).then(res => {
          console.log(res)
          ElMessage({
            message: "删除成功",
            center: true,
            type: 'success',
          })

          location.reload()//刷新页面
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    //新增用户信息
    savenew(){
      // if(this.user.role=="教师"){
      //   this.user.role=2
      // }else this.user.role=1;

      console.log(this.user)
      request.get("/user/save", {
        params:{
          username:this.user.username,
          password:this.user.password,
          realname:this.user.realname,
          email:this.user.email,
          role:this.user.role,
        }
      }).then(res =>{
        if (res.data=="success"){
          ElMessage({
            message: "添加成功",
            center: true,
            type: 'success',
          })

          this.dialogVisible2=false
          location.reload()//刷新页面
        }else{
          ElMessage({
            message: "添加失败，学工号重复",
            center: true,
            type: 'error',
          })
          this.dialogVisible2=false
          location.reload()//刷新页面
        }
      })
    },
    //编辑用户信息
    edit(a){
      this.user=a;
      this.currentusername=a.username;
      // if(a.role==2){
      //   this.user.role="教师"
      //   a.role=2;
      // }else{
      //   this.user.role="学生";
      //   a.role=1;
      // } 
      this.dialogVisible2=true;
    },
    saveedit() {
      // if(this.user.role=="教师"){
      //   this.user.role=2
      // }else this.user.role=1;
      this.dialogVisible2=false;
      console.log(this.user)
      request.get("/user/update", {
        params:{
          username:this.user.username,
          password:this.user.password,
          realname:this.user.realname,
          email:this.user.email,
          role:this.user.role,
        }
      }).then(res =>{
        if (res.data=="success"){
          ElMessage({
            message: "修改成功",
            center: true,
            type: 'success',
          })
          // location.reload()//刷新页面
        }else{
          ElMessage({
            message: "修改失败",
            center: true,
            type: 'failure',
          })
        }
      })
    }
  }
}

</script>

<style scoped>
.btngroup{
  float: left;
}
.management{
  margin: 0px;
  /*background-image: linear-gradient(to bottom right, #000000, 	#483D8B);*/
  position: relative;
  width:100%;
  min-height: 100vh;
}

</style>

