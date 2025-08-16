<script setup>

import ExampleModel from "@/Model/exampleModel/ExampleModel.js";
import DeviceService from "@/ViewModel/device/deviceService.js";
import DeviceModel from "@/Model/device/deviceModel.js";
</script>

<template>
  <div class="box2">
    <div class="box2-item">
      <textarea placeholder="请输入设备描述（最多200字）" v-model="DeviceModel.device.deviceDescribe"></textarea>
      <div class="submit-section">
        <button class="submit-btn" @click="DeviceService.PostAddDevices(DeviceModel.device)">提交设备信息</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.box2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: auto;
  width: 100%;
  gap: 20px;
  textarea {
    margin-left: 10px;
    padding: 12px;
    height: 110px;
    width: 300px;
    border: 1px solid #304156;
    border-radius: 4px;
    background: #2d3a4b;
    color: #fff;
    resize: vertical;
    transition: all 0.3s;

    &:hover {
      border-color: #409eff;
    }

    &:focus {
      border-color: #1890ff;
      box-shadow: 0 0 8px rgba(24, 144, 255, 0.2);
    }
  }
  .submit-section {
    display: flex;
    margin-left: 10px;
    justify-content: flex-start;

    .submit-btn {
      padding: 10px 32px;
      background: #1890ff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: all 0.3s;

      &:hover {
        background: #40a9ff;
        box-shadow: 0 2px 8px rgba(24, 144, 255, 0.3);
      }
    }
  }
}
</style>