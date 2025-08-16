<script setup>

import DeviceModel from "@/Model/device/deviceModel.js";
import DeviceService from "@/ViewModel/device/deviceService.js";


const updateDevice = () => {
    DeviceService.UpdateDevice(DeviceModel.updateModel.value)
};
const test = () => {
   DeviceService.test(
      DeviceModel.updateModel.value,
   )
}
</script>

<template>
  <div class="u-box2">
    <h3>更改设备信息*</h3>
    <textarea placeholder="更改设备描述（最多200字）" v-model="DeviceModel.updateModel.description"></textarea>
    <div class="submit-section">
      <button class="submit-btn" @click="updateDevice">更改设备信息</button>

    </div>
  </div>

</template>
<style scoped>
.u-box2 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;

  padding: 8px;
  height: auto;
  width: auto;
}
textarea {

  padding: 12px;
  height: 110px;
  width: 300px;
  border: 1px solid #304156;

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
  margin-top: 10px;
  display: flex;
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
</style>