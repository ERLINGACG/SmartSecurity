<script>
import userModel from "@/Model/user/UserModel.js";
import UserService from "@/ViewModel/user/UserService.js";
import {createRouter as $router} from "vue-router";

export default {
  name: "Login",
  data() {
    return {
      userModel
    };
  },
  methods: {
   async login() {
      // 登录逻辑
      console.log(userModel.UserLogin);
      const result = await UserService.Login(this.userModel);
      console.log(result);
     if (result===2000) {
        console.log('登录成功');
        localStorage.setItem("nowUser", this.userModel.UserLogin.email);
        this.$router.push("/home/homeItem");
     }
    }
  }
}
</script>

<template>
  <div class="login-container">
    <div class="login-box">
       <div class="login-left">
          <img src="/src/assets/user/logo.png" alt="logo">
       </div>

       <div class="login-right">

         <div class="login-form">
          <h2>登录</h2>
           <input type="text" placeholder="邮箱" v-model="userModel.UserLogin.email">
           <input type="password" placeholder="密码" v-model="userModel.UserLogin.passwordHash">
           <button @click="login()">登录</button>
           <div class="form-links">
             <a href="#" @click="$router.push('/user/register')">注册账号</a>
             <a href="#" @click.prevent="">忘记密码？</a>
           </div>
        </div>
       </div>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
}
.login-box {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 600px;
  width: 1000px;

  border-radius: 5px;
  box-shadow: 0 0 10px white;
}
.login-left {
  position: fixed;
  left: 0;
  img {
    width: 1000px;
    height: 600px;

  }
}
.login-right {
  position: fixed;
  right: 0;
  height: 600px;
  width: 400px;
  background: rgba(255, 255, 255, 0.3); /* 调整透明度为50% */
  border-radius: 5px;

  /* 优化毛玻璃效果 */
  backdrop-filter: blur(15px) saturate(180%);
  -webkit-backdrop-filter: blur(15px) saturate(180%);

  /* 调整边框和阴影 */
  border: 1px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
  .login-form {
    padding: 40px;
    width: 100%;
    top: 35%;  /* 调整垂直位置 */

    /* 修改为更灵活的布局 */
    display: flex;
    flex-direction: column;
    gap: 20px;

    h2 {
      color: #2c3e50;
      font-size: 24px;
      margin-bottom: 15px;
      padding-bottom: 10px;
      border-bottom: 2px solid rgba(44, 62, 80, 0.1);
    }

    input {
      padding: 12px 20px;
      border: 1px solid rgba(44, 62, 80, 0.3);
      border-radius: 8px;
      background: rgba(255, 255, 255, 0.1);
      transition: all 0.3s ease;
      font-size: 14px;

      &:focus {
        outline: none;
        border-color: #3498db;
        box-shadow: 0 0 8px rgba(52, 152, 219, 0.3);
      }

      &::placeholder {
        color: rgba(44, 62, 80, 0.6);
      }
    }

    button {
      padding: 12px 0;
      background: #3498db;
      color: white;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      transition: all 0.3s ease;
      font-weight: 500;
      letter-spacing: 1px;
      margin-top: 10px; /* 增加按钮上边距 */

      &:hover {
        background: #2980b9;
        transform: translateY(-1px);
      }

      &:active {
        transform: translateY(0);
      }
    }
    .form-links {
      display: flex;
      justify-content: space-between;
      margin-top: 15px;

      a {
        color: #1a5d99;
        font-size: 14px;
        font-weight: 500;
        text-decoration: none;
        transition: color 0.3s ease;

        &:hover {
          color: #2980b9;
          text-decoration: underline;
        }

        &:active {
          transform: translateY(1px);
        }
      }
    }
  }
}
</style>