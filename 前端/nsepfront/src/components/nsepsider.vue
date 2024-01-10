<template>
  <el-col :span="12">
<!--    <h5 class="mb-2">Custom colors</h5>-->
    <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        style="width: 200px;
        height: 100vh"
        :router="true"
    >
      <el-menu-item index="/login">
        SQL-inject
      </el-menu-item>
      <el-menu-item>
        XSS
      </el-menu-item>
      <el-menu-item>
        RCE
      </el-menu-item>
      <el-menu-item>
        Over Permisson
      </el-menu-item>
      <el-menu-item>
        openssl心脏滴血
      </el-menu-item>
      <el-menu-item>
        弱口令破解
      </el-menu-item>
      <el-menu-item>
        Unsafe file uploads
      </el-menu-item>
    </el-menu>
  </el-col>
</template>