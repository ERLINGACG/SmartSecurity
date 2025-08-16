<script>
import userModel from "@/Model/user/UserModel.js";
import UserService from "@/ViewModel/user/UserService.js";

export default {
  data(){
    return {
      userModel,
      code:'',
      nextPassword:'',
      codeImgUrl:'http://localhost:8080/user/api/getCodeImage'

    }
  },
  methods: {
    async register() {
      if(this.userModel.UserRegister.passwordHash!== this.nextPassword){
        alert('两次输入的密码不一致，请重新输入！');
        return;
      }
      console.log(this.code);
      await UserService.Register(this.userModel, this.code)
    },
    async getCode(){
      this.codeImgUrl = `http://localhost:8080/user/api/getCodeImage?${new Date().getTime()}`
    }
  },
  mounted(){
    this.getCode();
  }
}
</script>

<template>
  <div class="register-container">
    <div class="register-box">
      <div class="register-left">
        <img src="/src/assets/user/logo.png" alt="logo">
      </div>

      <div class="register-right">

        <div class="register-form">
          <h2>注册</h2>
          <input type="text" placeholder="邮箱" v-model="userModel.UserRegister.email">
          <input type="password" placeholder="密码" v-model="userModel.UserRegister.passwordHash">
          <input type="password" placeholder="确认密码" v-model="nextPassword">
          <div class="form-group">
            <input type="text" placeholder="验证码" v-model="code">
            <img
                :src="codeImgUrl"
                @click="getCode"
                alt="captcha"
                style="cursor: pointer"
                width="90px"
                height="40px"
            >
          </div>
          <button @click="register">注册</button>
          <div class="form-links">
            <a href="#" @click.prevent="$router.push('/user/login')">已有账号？登录</a>
            <a href="#" @click.prevent="">忘记密码？</a>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
}
.register-box {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 600px;
  width: 1000px;

  border-radius: 5px;
  box-shadow: 0 0 10px white;
}
.register-left {
  position: fixed;
  left: 0;
  img {
    width: 1000px;
    height: 600px;

  }
}
.register-right {
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
  .register-form {
    padding: 40px;
    width: 100%;
    top: 35%;  /* 调整垂直位置 */

    /* 修改为更灵活的布局 */
    display: flex;
    flex-direction: column;
    gap: 20px;
    .form-group {
      display: flex;
      align-items: center;
      gap: 10px;
    }
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
      background: rgba(255, 255, 255, 0.8);
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