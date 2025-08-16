<script setup>

</script>

<template>
  <div class="device-container">
    <div class="device-header">
       <div class="device-title">
          <h3>设备管理</h3>
       </div>


    </div>
    <div class="device-search-bar">
      <router-link to="/home/devicePage/add" class="bar-item">添加设备</router-link>
      <router-link to="/home/devicePage/delete" class="bar-item">删除设备</router-link>
      <router-link to="/home/devicePage/list" class="bar-item">设备列表</router-link>
      <router-link to="/home/devicePage/update" class="bar-item">修改设备</router-link>
    </div>
    <div class="device-title-line"></div>
    <div class="device-body">
      <div class="device-body-box">




          <router-view/>


      </div>
    </div>


  </div>
</template>

<style scoped>
.device-container {
  position: relative;
  margin-left: 200px;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
}
.device-header {
  display: flex;
  justify-content: left;
  flex-direction: column;
  align-items: flex-start;
  height: 60px;
  .device-title {
    line-height: 60px;
    font-size: 24px;
    font-weight: bold;
   }

}
.device-search-bar {
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
.device-title-line {
   height: 2px;
   width: 60vw;
   background-color: #ffffff;
   justify-self: center;
 }
 .device-body {
   margin-top: 20px;
   display: flex;
   justify-content: space-between;
   flex-direction: column;
   height: 100%;

 }
 .device-body-box {
    display: flex;
    justify-content: space-between;
    height: 60vh;
    width: 60vw;
    border-radius: 8px;
    border: 1px solid rgba(204, 204, 204);


 }

</style>