const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
}),
module.exports = {
  lintOnSave: false,
  configureWebpack: {
    resolve: { fallback: { fs: false } }
  },
  devServer: {
    host: 'localhost',
    port: 8082,
    proxy: {                 //设置代理，必须填
        '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
            target: 'http://localhost:8081',     //代理的目标地址
            changeOrigin: true,              //是否设置同源，输入是的
            pathRewrite: {                   //路径重写
                '^/api': ''                     //选择忽略拦截器里面的内容
            }
        }
    }
}
}
