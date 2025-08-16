<script setup>

import deviceModel from "@/Model/device/deviceModel.js";
import DeviceService from "@/ViewModel/device/deviceService.js";
</script>

<template>
  <div class="d-box2">
    <div class="title">
            <h3>删除设备*</h3>
    </div>
      <div class="delete-confirm">
        <label>
          <input type="checkbox" v-model="deviceModel.deleteModel.isdelete">
          我已知晓删除设备的后果
        </label>
      </div>
      <div class="button-box">
        <button class="submit-btn" @click="DeviceService.DeleteDevice(
            deviceModel.deleteModel.isdelete,
            deviceModel.deleteModel.pid,
            deviceModel.deleteModel.email
        )">删除设备</button>
        <button class="submit-btn">取消</button>
      </div>


  </div>
</template>

<style scoped>
.d-box2 {
  height: auto;
  width: auto;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: flex-start;
  border: 1px solid #ccc;
  border-radius: 4px;
  .button-box {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    margin-top: 16px;
  }
  .submit-btn {
    margin-right: 5px;
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
  .delete-confirm {
    margin-top: 15px;
    display: flex;
    align-items: center;
    gap: 8px;
    label {
      display: flex;
      align-items: center;
      color: #1890ff;
      font-size: 14px;
      cursor: pointer;
    }
  }
}
</style>