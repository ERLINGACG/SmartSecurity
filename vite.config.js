import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  base: '/vue/',
  server: {
    host: '0.0.0.0',    // 监听所有网络接口
    allowedHosts: [      // 添加允许访问的域名
      'frp-dad.com',     // 您需要放行的外部域名
      'localhost',        // 保留本地访问
      'api.smartsecurity.local'
    ]
  },


})
