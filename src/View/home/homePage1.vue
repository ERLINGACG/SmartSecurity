<script setup>

import HomeItem from "@/View/home/homeItem.vue";
</script>

<template>
  <div class="home-page-1-container">
    <div class="home-page-1-content">

      <div class="home-page-1-top-bar">
        <div class="home-page-1-top-bar-title">
            <h1>智能安全监控系统</h1>
        </div>

        <div class="home-page-1-top-bar-right">

            <div class="home-page-1-top-bar-right-item">
               <input type="text" placeholder="搜索">
               <button>搜索</button>
              <router-link to="/about" class="top-bar-link">关于我们</router-link>
              <router-link to="/user/PersonalCenter" class="top-bar-link">个人中心</router-link>
              <router-link to="/logout" class="top-bar-link">退出登录</router-link>

            </div>
        </div>
      </div>

      <div class="home-page-1-left-bar">
            <div class="home-page-1-left-bar-item">
              <router-link to="/home/homeItem">
                <i class="el-icon-menu"></i>
                <span>首页</span>
              </router-link>
            </div>
            <div class="home-page-1-left-bar-item">
              <router-link to="/home/devicePage/add">
                <i class="el-icon-menu"></i>
                <span>设备管理</span>
              </router-link>
            </div>
            <div class="home-page-1-left-bar-item">
              <router-link to="/home/page3">
                <i class="el-icon-menu"></i>
                <span>模块导航</span>
              </router-link>
            </div>
            <div class="home-page-1-left-bar-item">
              <router-link to="/home/groupPage/create">
                <i class="el-icon-menu"></i>
                <span>群组管理</span>
              </router-link>
            </div>
            <div class="home-page-1-left-bar-item">
              <router-link to="/home/page5">
                <i class="el-icon-menu"></i>
                <span>系统设置</span>
              </router-link>
            </div>
      </div>

      <div class="home-page-1-main-content">
        <div class="home-page-1-main-content-item">
<!--           <home-item></home-item>-->
          <router-view></router-view>
        </div>
        <div class="home-page-1-footer">
        <p>© 2023 智能安全监控系统. 保留所有权利.</p>
          <p>技术支持：智能安全监控系统团队</p>
        </div>
      </div>

    </div>
  </div>
</template>

<style>
.home-page-1-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.home-page-1-content {
  width: 100%;
  height: 100%;

}
.home-page-1-top-bar {
  width: 100%;
  height: 50px;
  position:  fixed;
  display: flex;
  background-color: #333;
  left: 0;
  align-items: center; /* 新增 */
  right: 20px; /* 调整位置 */
  .home-page-1-top-bar-right{
    position: absolute;
    justify-content: center;
    align-items: center;
    right: 0;
  }
}
.home-page-1-left-bar {
  width: 200px;
  height: 100%;
  position:  fixed;
  background-color: #444;
  left: 0;
  top: 50px;
}
.home-page-1-top-bar-right-item {
  display: flex;
  align-items: center;
  gap: 25px;  /* 元素间距 */
  margin-right:  10px;
  input {
    padding: 8px 12px;
    border-radius: 4px;
    border: 1px solid #555;
    background: #444;
    color: white;
    width: 200px;
    &:focus {
      outline: 2px solid #1890ff;
    }

  }
  .top-bar-link {
    display: flex;
    align-items: center;
    padding: 8px 16px;
    color: #ccc;
    text-decoration: none;
    transition: all 0.3s;
    border-radius: 4px;
    position: relative;

    &:hover {
      background: rgba(255, 255, 255, 0.05);
      color: #fff;

      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: -5px;
        width: 100%;
        height: 2px;
        background: #1890ff;
      }
    }

    &.router-link-active {
      background: #1890ff;
      color: white;
    }
  }
  button {
    padding: 8px 20px;
    background: #1890ff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background 0.3s;

    &:hover {
      background: #096dd9;
    }
  }

  a {
    color: #ccc;
    text-decoration: none;
    transition: color 0.3s;
    position: relative;

    &:hover {
      color: #fff;

      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: -5px;
        width: 100%;
        height: 2px;
        background: #1890ff;
      }
    }
  }
}
.home-page-1-left-bar-item {
  height: 45px;
  margin: 8px 12px;
  border-radius: 4px;
  overflow: hidden;
  transition: all 0.3s;

  &:hover {
    background: rgba(255, 255, 255, 0.05);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  }

  a {
    display: flex;
    align-items: center;
    padding: 0 16px;
    height: 100%;
    color: #ccc;
    text-decoration: none;
    transition: all 0.3s;

    .el-icon-menu {
      font-size: 18px;
      margin-right: 12px;
      color: #666;
    }

    &:hover {
      color: #fff;
      .el-icon-menu {
        color: #1890ff;
      }
    }

    &.router-link-active {
      background: #1890ff;
      color: white;
      .el-icon-menu {
        color: white;
      }
    }
  }
}
.home-page-1-top-bar-title {
  margin-left: 20px;
  h1 {
    margin: 0;
    font-size: 20px;
    font-weight: 500;
    background: linear-gradient(135deg, #1890ff 30%, #40a9ff);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
    letter-spacing: 1px;
    transition: all 0.3s;
    position: relative;

    &::after {
      content: "";
      position: absolute;
      left: 0;
      bottom: -3px;
      width: 100%;
      height: 2px;
      background: #1890ff;
      transform: scaleX(0);
      transition: transform 0.3s;
    }

    &:hover {
      text-shadow: 0 2px 8px rgba(24, 144, 255, 0.3);
      &::after {
        transform: scaleX(1);
      }
    }
  }
}
.home-page-1-main-content {
  margin-top: 50px; /* 避开顶部导航栏 */
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(100vh - 50px); /* 全屏高度减去顶部栏 */
  width:  calc(100vw - 200px);
  overflow-y: auto; /* 垂直滚动 */
  overflow-x: hidden;

  right: 0;
  border-radius: 10px;
  border: 1px solid #ccc;

  .home-page-1-main-content-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    width: 100%;
    height: auto;

    border-radius: 10px;
  }
  .home-page-1-footer {
    padding: 30px;
    margin-top: 20px;
    width: 100%;
    height: calc(100vh - 50px - 20px); /* 全屏高度减去顶部栏和底部栏 */
    background-color: #333;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: #ccc;
    font-size: 14px;
    box-sizing: border-box;
  }

}
</style>