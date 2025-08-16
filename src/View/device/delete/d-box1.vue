<script setup>

import DeviceModel from "@/Model/device/deviceModel.js";
</script>

<template>
  <div class="d-box1">
    <h3>设备信息*</h3>
    <div class="d-info">
      <h4>绑定邮箱</h4>
      <input type="text" placeholder="请输入邮箱" v-model="DeviceModel.deleteModel.email" />
      <h4>设备id</h4>
      <input type="text" placeholder="请输入设备id" v-model="DeviceModel.deleteModel.pid" />
    </div>
    <div class="detail">
      <ul class="consequence-list">
        <li id="c1">删除后设备将解除与系统的所有关联</li>
        <li id="c2">设备配置信息将被永久清除</li>
        <li id="c3">您可以在设备管理页面重新绑定该设备</li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.d-box1 {
  height: 200px;
  width: auto;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  border: 1px solid #ccc;
  border-radius: 4px;

  h3{
    font-size: 20px;

  }
  .d-info {
    margin-top: 10px;
    display: flex;
    flex-direction: row;
    h4 {
      color: #a8c5e6;
      font-size: 14px;
      margin: 0;
    }
    input {
      margin-left: 10px;
      margin-right: 5px;
      padding: 8px 12px;
      border: 1px solid #304156;
      border-radius: 4px;
      background: #2d3a4b;
      color: #fff;
      transition: all 0.3s;

      &:hover {
        border-color: #409eff;
      }
      &:focus {
        border-color: #1890ff;
        box-shadow: 0 0 8px rgba(24, 144, 255, 0.2);
      }
    }

  }
  .detail {

    margin-left: -20px;

    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }

  .consequence-list {
    margin-top: 12px;
    color: #a8c5e6;
    font-size: 14px;
    line-height: 1.8;
  }



}

</style>