const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    open: true,
    proxy: {
      '/tongsuo': {
        target: 'http://192.168.100.27:18085',   //代理接口
        changeOrigin: true,
      }
    }
  }
})
