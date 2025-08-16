<script setup>

</script>

<template>
  <div class="group-page-container">
    <div class="group-page-header">
      <div class="group-page-header-title">
        <h3>群组管理</h3>
      </div>
      <div class="group-page-header-bar">
        <router-link to="/home/groupPage/create" class="bar-item">
          创建群组
        </router-link>
        <router-link to="/home/groupPage/upload" class="bar-item">
          上传成员信息
        </router-link>
        <router-link to="/home/groupPage/update-m" class="bar-item">
          管理成员
        </router-link>
        <router-link to="/home/groupPage/update-g" class="bar-item">
          管理群组
        </router-link>
        <router-link to="/home/groupPage/list-p" class="bar-item">
          群组列表
        </router-link>
      </div>
      <div class="group-page-line"/>

    </div>
    <div class="group-page-body">
      <router-view></router-view>
    </div>
  </div>

</template>

<style scoped>
.group-page-container {
  position: relative;
  margin-left: 200px;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
}
.group-page-header {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content:flex-start;
  width: 100%;
  height: 100px;
  .group-page-header-title {
    line-height: 60px;
    font-size: 24px;
    font-weight: bold;
  }
  .group-page-line {
    height: 2px;
    width: 60vw;
    background-color: #ffffff;
    justify-self: center;
  }
  .group-page-header-bar {
    display: flex;
    justify-content: left;
    height: 30px;
    gap: 10px;
    .bar-item {
      color: #ffffff;
      font-size: 14px;
      font-weight: bold;
      text-decoration: none;
      cursor: pointer;
      transition: all 0.3s ease;
      border-radius: 5px;
      &:hover {
        color: #409eff;
      }
      &.router-link-active {
        background: #1890ff;
        color: white;
      }

    }
  }

}
.group-page-body {
  display: flex;
  justify-content: flex-start;
  height: 60vh;
  width: 60vw;
  border-radius: 8px;
  border: 1px solid rgba(204, 204, 204);
}
</style>