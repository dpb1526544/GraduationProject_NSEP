module.exports = {
    plugins: {
        'postcss-px2rem-exclude': {
            remUnit: 192,//PC端 宽度 / 10即可 移动端 750 / 10 , 75 / 2 =37.5
            exclude: /node_modules|folder_name/i, //忽略第三方插件
        },
    },
}
