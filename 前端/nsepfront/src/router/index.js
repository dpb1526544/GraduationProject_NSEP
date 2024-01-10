import { createRouter,createWebHistory } from 'vue-router'
import login from '../views/Login.vue'
import register from '../views/Register.vue'
import resetPassword from '../views/ResetPassword.vue'
import home from '../views/Home.vue'
import navigation from "../views/navigation.vue"
import personalcenter from "../views/PersonalCenter.vue"
import nstools from '../views/NSTools.vue'
import invitation from "../views/invitation.vue"
import courseManagement from '../views/CourseManagement.vue'
import message from "../views/Message.vue"
import forum from '../views/Forum.vue'
import userManagement from '../views/UserManagement.vue'
import Test from "../views/Test.vue"
import BugPage from "../views/BugPage.vue"
import honeypot from '../views/Honeypot.vue'


//工具
import base64tool from '../views/tools_views/base64.vue'
import domainfindtool from '../views/tools_views/domain_find.vue'
import ipinfotool from '../views/tools_views/IP_info.vue'
import scanporttool from "../views/tools_views/scan_port.vue"
import urlencode from "../views/tools_views/URLencode.vue"
import caesar from "../views/tools_views/CaesarCrypt.vue"
import rc4 from "../views/tools_views/RC4.vue"
import des from "../views/tools_views/DES.vue"
import hash from "../views/tools_views/HASH.vue"
import rot13 from "../views/tools_views/Rot13.vue"
import unicode from "../views/tools_views/unicode.vue"



// 漏洞页面
import Rceping from '../views/problem_views/RCE/RCE_ping'
import sqlinum from '../views/problem_views/SQLi/sqli_num'
import sqlibrackets from '../views/problem_views/SQLi/sqli_brackets'
import sqlichar from '../views/problem_views/SQLi/sqli_char'
import sqlidelete from '../views/problem_views/SQLi/sqli_delete'
import sqlidelete1 from '../views/problem_views/SQLi/sqli_delete1'
import sqlisearch from '../views/problem_views/SQLi/sqli_search'
import sqli_unsafe from '../views/problem_views/SQLi/sqli_unsafe'
import sqli_unsafe2 from '../views/problem_views/SQLi/sqli_unsafe2'

import filedownload from '../views/problem_views/FileDownLoad/FileDownload'
import fileread from '../views/problem_views/FileDownLoad/FileRead'
import fileupload from '../views/problem_views/FileUpLoad/fileupload'
import java_deser from "../views/problem_views/JavaDeser/java_deser";

import urlredirect from "../views/problem_views/UrlRedirect/UrlRedirect"
import buy from "../views/problem_views/UrlRedirect/buy"
import urllogin from "../views/problem_views/UrlRedirect/urlLogin"
import urllogin1 from "../views/problem_views/UrlRedirect/urlLogin1"

import xss_reflect from "../views/problem_views/XSS/xss_reflect";
import xss_store from "../views/problem_views/XSS/xss_store";
import xxe from "../views/problem_views/XXE/xxe";
import oplogin from "../views/problem_views/OverPermission/op1_login";
import oppc from "../views/problem_views/OverPermission/op1_person_center";
import pathtravel from "../views/problem_views/PathTravel/PathTravel";
import imageshow from "../views/problem_views/PathTravel/imageshow";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: home
  },
  {
    path: '/login',
    name: 'Login',
    component: login
  },
  {
    path: '/register',
    name: 'Register',
    component: register
  },
  {
    path: '/resetPassword',
    name: 'ResetPassword',
    component: resetPassword
  },
  {
    path: '/navigation',
    name: 'navigation',
    component: navigation,
  },
  {
    path: '/personalcenter',
    name: 'personalcenter',
    component: personalcenter,
  },
  {
    path: '/nstools',
    name: 'nstools',
    component: nstools
  },
  {
    path: '/coursemanagement',
    name: 'courseManagement',
    component: courseManagement,
  },
  {
    path: '/invitation',
    name: 'invitation',
    component: invitation,
  },
  {
    path: '/usermanagement',
    name: 'userManagement',
    component: userManagement,
  },
  {
    path: '/message',
    name: 'message',
    component: message,
  },
  {
    path: '/forum',
    name: 'forum',
    component: forum,
  },
  {
    path: '/honeypot',
    name: 'Honeypot',
    component: honeypot,
  },
  {
    path: '/bugpage',
    name: 'BugPage',
    component: BugPage,
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
  },  
  {
    path: '/oppc',
    name: 'oppc',
    component: oppc,
  },

  //工具
  {
    path: '/base64tool',
    name: 'base64tool',
    component: base64tool
  },
  {
    path: '/domainfindtool',
    name: 'domainfindtool',
    component: domainfindtool
  },
  {
    path: '/ipinfotool',
    name: 'ipinfotool',
    component: ipinfotool
  },
  {
    path: '/scanporttool',
    name: 'scanporttool',
    component: scanporttool
  },
  {
    path: '/urlencode',
    name: 'urlencode',
    component: urlencode
  },
  {
    path: '/rc4',
    name: 'rc4',
    component: rc4
  },
  {
    path: '/rot13',
    name: 'rot13',
    component: rot13
  },
  {
    path: '/des',
    name: 'des',
    component: des
  },
  {
    path: '/hash',
    name: 'hash',
    component: hash
  },
  {
    path: '/CaesarCrypt',
    name: 'caesar',
    component: caesar
  },
  {
    path: '/unicode',
    name: 'unicode',
    component: unicode
  },

  // 漏洞页面
  {
    path: '/pro001',
    name: 'Rceping',
    component: Rceping
  },
  {
    path: '/pro002',
    name: 'sqlibrackets',
    component: sqlibrackets
  },
  {
    path: '/pro003',
    name: 'sqlichar',
    component: sqlichar
  },
  {
    path: '/pro004',
    name: 'sqlinum',
    component: sqlinum
  },
  {
    path: '/pro007',
    name: 'filedownload',
    component: filedownload
  },
  {
    path: '/pro008',
    name: 'fileupload',
    component: fileupload
  },
  {
    path: '/pro009',
    name: 'java_deser',
    component: java_deser
  },
  {
    path: '/pro010',
    name: 'buy',
    component: buy
  },
  {
    path: '/urllogin',
    name: 'urllogin',
    component: urllogin
  },
  {
    path: '/urllogin1',
    name: 'urllogin1',
    component: urllogin1
  },
  {
    path: '/urlredirect',
    name: 'urlredirect',
    component: urlredirect
  },
  {
    path: '/pro011',
    name: 'xss_store',
    component: xss_store
  },
  {
    path: '/pro012',
    name: 'xss_reflect',
    component: xss_reflect
  },
  {
    path: '/pro013',
    name: 'xxe',
    component: xxe
  },
  {
    path: '/pro022',
    name: 'sqlidelete',
    component: sqlidelete
  },
  {
    path: '/pro022success',
    name: 'sqlidelete1',
    component: sqlidelete1
  },
  {
    path: '/pro023',
    name: 'sqlisearch',
    component: sqlisearch
  },
  {
    path: '/pro024',
    name: 'oplogin',
    component: oplogin
  },
  {
    path: '/pro025',
    name: 'fileread',
    component: fileread
  },
  {
    path: '/pro026',
    name: 'pathtravel',
    component: pathtravel
  },
  {
    path: '/imageshow',
    name: 'imageshow',
    component: imageshow
  },
  {
    path: '/pro027',
    name: 'sqli_unsafe',
    component: sqli_unsafe
  },
  {
    path: '/pro028',
    name: 'sqli_unsafe2',
    component: sqli_unsafe2
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes

})

export default router
