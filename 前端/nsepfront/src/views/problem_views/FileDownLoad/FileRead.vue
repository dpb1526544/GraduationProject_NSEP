<template>
  <div class="menu">
    <el-row>
      <!-- 按钮菜单部分 -->
      <el-col :span="24">
        <el-menu
            :default-active="mypath"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#fff"
            text-color="#000"
            active-text-color="#FF9912"
            @select="handleSelect"
        >
          <el-menu-item index="/honeypot">  </el-menu-item>
          <el-menu-item index="/abilitypromotion"></el-menu-item>
          <el-menu-item index="/forum" ></el-menu-item>
          <el-menu-item index="/navigation" >玄幻</el-menu-item>
          <el-menu-item index="/coursemanagement" >都市</el-menu-item>
          <el-menu-item index="/usermanagement" >仙侠</el-menu-item>
          <el-menu-item index="/usermanagement" >历史</el-menu-item>
          <el-menu-item index="/usermanagement" >游戏</el-menu-item>
          <el-menu-item index="/usermanagement" >排行</el-menu-item>

        </el-menu>
      </el-col>
    </el-row>
  </div>

  <div class="building" style="width: 50%;margin-left: 25%">
      <el-row>
        <el-col :span="8"
                style="text-align: left">

          <el-card class="card" id="img1" @click="read1">
          </el-card>
        </el-col>

        <el-col :span="8"
                style="text-align: left">

          <el-card class="card" id="img2" @click="read2">
          </el-card>
        </el-col>

        <el-col :span="8"
                style="text-align: left">

          <el-card class="card" id="img3" @click="read3">
          </el-card>
        </el-col>
      </el-row>

      <el-row style="margin-top: 5%">
        <el-col :span="8"
                style="text-align: left">
          <el-card class="card" id="img4" @click="read">
          </el-card>

        </el-col>

        <el-col :span="8"
                style="text-align: left">

          <el-card class="card" id="img5" @click="read5">
          </el-card>
        </el-col>

        <el-col :span="8"
                style="text-align: left">

          <el-card class="card" id="img6" @click="read6">
          </el-card>
        </el-col>
      </el-row>


  </div>

  <el-dialog v-model="show"
             title="书籍详情"
             width="30%">
    <p>{{content}}</p>
  </el-dialog>

</template>

<script>

import request from "@/utils/request";

export default {

  data(){
    return{
      show:false,
      content:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{

    read(){
      request.get("/Read",{
        params:{
          filename:'flag.txt',
          username:this.user.username
        }
      }).then(res =>{
        console.log(res)
        this.content=res.data
        this.show=true
      })
    },

    read1(){
      this.content='文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……'
      this.show=true
    },
    read2(){
      this.content='《明朝那些事儿》这篇文主要讲述的是从1344年到1644年这三百年间关于明朝的一些事情，以史料为基础，以年代和具体人物为主线，并加入了小说的笔法，对明朝十七帝和其他王公权贵和小人物的命运进行全景展示，尤其对官场政治、战争、帝王心术着墨最多，并加入对当时政治经济制度、人伦道德的演义。'
      this.show=true
    },
    read3(){
      this.content='《我们仨》是钱钟书夫人杨绛撰写的家庭生活回忆录。1998年，钱钟书逝世，而他和杨绛唯一的女儿钱瑗已于此前（1997年）先他们而去。在人生的伴侣离去四年后，杨绛在92岁高龄的时候用心记述了他们这个特殊家庭63年的风风雨雨、点点滴滴，结成回忆录《我们仨》。'
      this.show=true
    },
    read5(){
      this.content='现代人内心流失的东西，这家杂货店能帮你找回——\n' +
          '\n' +
          '僻静的街道旁有一家杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。\n' +
          '\n' +
          '因男友身患绝症，年轻女孩静子在爱情与梦想间徘徊；克郎为了音乐梦想离家漂泊，却在现实中寸步难行；少年浩介面临家庭巨变，挣扎在亲情与未来的迷茫中……\n' +
          '\n' +
          '他们将困惑写成信投进杂货店，随即奇妙的事情竟不断发生。\n' +
          '\n' +
          '生命中的一次偶然交会，将如何演绎出截然不同的人生？'
      this.show=true
    },
    read6(){
      this.content='12岁的阿富汗富家少爷阿米尔与仆人哈桑情同手足。然而，在一场风筝比赛后，发生了一件悲惨不堪的事，阿米尔为自己的懦弱感到自责和痛苦，逼走了哈桑，不久，自己也跟随父亲逃往美国。\n' +
          '\n' +
          '成年后的阿米尔始终无法原谅自己当年对哈桑的背叛。为了赎罪，阿米尔再度踏上暌违二十多年的故乡，希望能为不幸的好友尽最后一点心力，却发现一个惊天谎言，儿时的噩梦再度重演，阿米尔该如何抉择？'
      this.show=true
    },
  }
}
</script>


<style scoped>

.card{
  height:300px;
  margin: 5%;
  border-style: none;
  position: relative;
  cursor:pointer;
}
#img1{
  background:url("../../../assets/img/bugimg/fileread/img1.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img2{
  background:url("../../../assets/img/bugimg/fileread/img2.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img3{
  background:url("../../../assets/img/bugimg/fileread/img3.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img4{
  background:url("../../../assets/img/bugimg/fileread/img4.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img5{
  background:url("../../../assets/img/bugimg/fileread/img5.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
#img6{
  background:url("../../../assets/img/bugimg/fileread/img6.jpg");
  background-repeat: no-repeat;
  background-size:100% 100%;
}
</style>