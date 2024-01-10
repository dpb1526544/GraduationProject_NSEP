<template>
  <div class="management" style="padding-bottom: 5px;background-color: rgba(255,255,255,0.8)" :style="{ height: windowHeight + 'px' }">
    <nsepheader></nsepheader>
    <!--    搜索框-->
    <div style="margin-top: 20px">
      <el-input
          style="width: 500px; "
          placeholder="请输入学号"
          suffix-icon="el-icon-search"
          v-model="search" />
      <el-button type="primary" plain style="margin: 10px" @click="getstuinfo(currentcourse)">查询</el-button>
      <el-button type="primary" plain style="margin: 10px" @click="addcourse">新建课程</el-button>
    </div>

    <el-divider   style="margin-bottom: 0px;"/>

    <el-row v-if="currentcourse!=''">
      <!--      侧边栏-->
      <el-col :span="6" >
        <el-aside style="text-align: right;">
          <el-scrollbar >
            <el-menu   active-text-color="#000000" text-color="#ffd04b">
              <el-menu-item v-for="(course, index) in courselist" :key="index" @click="changecourse(course.courseid,course.coursename)">
                {{course.coursename}}
              </el-menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>
      </el-col>
      <!--      表格-->
      <el-col :span="18">
        <el-card  style="width:90%;margin-top:3%;margin-right:10%;">
          <!--      按钮-->
          <div class="btngroup" style="width: 100%;">
            <h2 style="float: left">
              {{ currentcoursename }}
            </h2>
            <span style="float: right; margin-bottom:10px">
              <el-button type="success" plain @click="showQRcode=true">邀请二维码</el-button>
              <el-button type="success" plain @click="download">导出详情</el-button>
              <el-button type="success" plain @click="Dissolve">解散课程</el-button>
            </span>
          </div>
          <!--      卡片表格-->
          <div class="table-wrapper" >
            <el-table :data="tableData" border="true" style="width: 100%;">
              <el-table-column prop="username" label="学号" width="180" />
              <el-table-column prop="realname" label="姓名" width="180" />
              <el-table-column prop="email" label="邮箱" />
              <el-table-column label="实验完成度" prop="finished">
                <template #default="scope">
                  <el-progress v-if="scope.row.finished<1" style="margin-left: 10px" :percentage="scope.row.finished/18*100" status="warning"/>
                  <el-progress v-if="scope.row.finished>=1" style="margin-left: 10px" :percentage="scope.row.finished/18*100" status="success"/>
                </template>
              </el-table-column>
              <el-table-column  label="操作" width="180">
                <template #default="scope">
                  <el-button type="info" plain @click="studetails(scope.row.username)" v-if="scope.row.role != 3">详情</el-button>
                  <el-button type="info" plain @click="deletethis(scope.row.username)" v-if="scope.row.role != 3">删除</el-button>
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
      </el-col>
    </el-row>

    <el-empty :image-size="200" v-if="currentcourse==''" />

    <!--弹窗-->
    <el-dialog title="用户信息" v-model="dialogVisible" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="学号">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="form.realname"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog v-model="showQRcode" width="300px">
      <nsepqrcode :key="currentcourse" :cid="currentcourse"></nsepqrcode>
    </el-dialog>

    <el-dialog v-model="course" width="30%">
      <el-form :model="form" >
        <el-form-item label="">
          <el-input v-model="coursename" placeholder="课程名（不可修改）"/>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
      <el-button @click="course = false">取 消</el-button>
      <el-button type="primary" @click="savecourse">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="题目完成详情" v-model="details" width="60%">
      <el-table :data="detailsData" border="true" style="width: 100%;">
        <el-table-column prop="username" label="学号" />
        <el-table-column prop="realname" label="姓名" />
        <el-table-column prop="problemname" label="题目" />
        <el-table-column prop="finishtime" label="完成时间" />
      </el-table>
    </el-dialog>
    <nsepfooter></nsepfooter>
  </div>
</template>

<script>
import nsepheader from "@/components/nsepheader";
import nsepfooter from '../components/nsepfooter.vue';
import request from "@/utils/request";
import nsepqrcode from '../components/nsepQRCode'
import animation from "../assets/js/background_white";
import {ElMessage} from "element-plus";

export default {
  components: {
    nsepheader,
    nsepfooter,
    nsepqrcode
  },
  data(){
    return{
      teacher:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dialogVisible:false,
      showQRcode:false,
      course:false,
      details:false,
      coursename:'',
      search:'',
      currentPage:1,
      pageSize:5,
      totalpage:'',
      form:{},
      courselist:[],
      tableData:'',
      currentcourse:'',
      currentcoursename:'',
      downloadData:{},
      detailsData:{},
      windowWidth: document.documentElement.clientWidth,  //实时屏幕宽度
      windowHeight: document.documentElement.clientHeight,   //实时屏幕高度
    }
  },
  created(){
    animation()
    this.loadcourse()
  },
  methods: {
    loadcourse(){
      request.get("/course/getcourselist",{
        params:{
          teacherid:this.teacher.userid
        }
      }).then(res =>{
        console.log(res)
        this.courselist=res.data
        this.currentcourse=res.data[0].courseid
        this.currentcoursename=res.data[0].coursename
        this.getstuinfo(this.currentcourse)
      })
    },
    getstuinfo(cid){
      request.get("/StuCourse/page",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search,
          courseid:cid
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.Pagecontent
        this.totalpage=res.data.PageNum
        console.log(this.totalpage)
      })
    },
    add(){
      this.dialogVisible=true;
      this.form={};
    },
    changecourse(id,name){
      this.currentcourse=id;
      this.currentcoursename=name;
      this.getstuinfo(id)
    },
    Dissolve(){
      this.$confirm('确认解散该课程？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.get("/course/delete",{
          params:{
            courseid:this.currentcourse
          }
        }).then(res =>{
          console.log(res);
          ElMessage({
            message: this.currentcoursename+"已解散",
            center: true,
            type: 'success',
          })
          this.loadcourse()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    save(){
      request.post("/user/save",this.form).then(res =>{
        console.log(res);
      })
      alert("save");
      this.loadcourse();
    },
    download(){
      console.log(this.currentcourse)
      request.get("/download/load",{
        params:{
          courseid:this.currentcourse,
        }
      }).then(res =>{
        console.log(res.data)
        this.Export2Excel(res.data)
      })
    },
    Export2Excel(dataList) {
      import('../excel/Export2Excel').then(excel => {
        const tHeader = ['学号', '姓名', '题目','完成时间']
        // const title = ['', '', '','']; // 这个title和上面数组的数量必须是一致的但是可以是空的
        // 表头对应字段
        const filterVal =['username', 'realname', 'problemname','finishtime'];
        const list = dataList
        const data = this.formatJson(filterVal, list)
        data.map(item => {
          // console.log(item)
          item.map((i, index) => {
            if (!i) {
              item[index] = ''
            }
          })
        })
        // const merges = ['A1:D1'] //合并单元格
        excel.export_json_to_excel({
          // title: title,
          header: tHeader,
          data,
          // merges,
          filename: this.currentcoursename,
          autoWidth: true,
          bookType: 'xlsx'
        })
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) => filterVal.map((j) => v[j]));
    },
    addcourse(){
      this.course=true;
      this.coursename='';
    },
    savecourse(){
      if(this.coursename===''){
        ElMessage({
            message: "课程名不能为空",
            center: true,
            type: 'error',
          })
          return false;
      }
      request.get("/course/save",{
        params:{
          coursename:this.coursename,
          teacherid:this.teacher.userid,
        }
      }).then(res =>{
        console.log(res);
        if (res.code === '200'){
          ElMessage({
            message: this.coursename+"创建成功",
            center: true,
            type: 'success',
          })
          this.loadcourse()
          this.course=false
        }else{
          alert("创建失败");
        }
      })
    },
    studetails(username){
      request.get("/stuproblem/details",{
        params:{
          courseid:this.currentcourse,
          username:username
        }
      }).then(res =>{
        console.log(res)
        this.detailsData=''
        this.detailsData=res.data
        this.details=true
      })
    },
    deletethis(username){
      this.$confirm('确认删除该同学？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.get("/StuCourse/delete",{
          params:{
            courseid:this.currentcourse,
            username:username
          }
        }).then(res =>{
          console.log(res);
          ElMessage({
            message: username+"已从"+this.currentcoursename+"中删除",
            center: true,
            type: 'success',
          })
          this.loadcourse()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  }
}

</script>

<style scoped>
.btngroup{
  float: left;
}
.management{
  margin: 0px;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  position: relative;
  width:100%;
  min-height: 100vh;
}

</style>

