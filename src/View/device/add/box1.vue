<script setup>
import ExampleModel from "@/Model/exampleModel/ExampleModel.js";
import DeviceModel from "@/Model/device/deviceModel.js";

const box1Model = ExampleModel.box1;
</script>

<template>
  <div class="box1">
     <div class="box1-item">
       <h4>绑定账户</h4>
       <input type="text" placeholder="邮箱/手机号" id="input1" v-model="DeviceModel.device.userEmail">
       <h4>设备名称</h4>
       <input type="text" placeholder="请输入设备名称" id="input1" v-model="DeviceModel.device.deviceName">
     </div>

     <div class="box1-item">
       <h4>设备pid</h4>
       <input type="text" placeholder="请输入设备pid" id="input2" v-model="DeviceModel.device.pid">
       <h4>设备类型</h4>
       <select id="input3" v-model="DeviceModel.device.deviceType">
          <option value="camera">摄像头</option>
          <option value="switch">智能开关</option>
          <option value="socket">智能插座</option>
       </select>
     </div>
    <div class="box1-item">
      <h4>设备topic</h4>
      <input type="text" placeholder="请输入设备topic" id="input3" v-model="DeviceModel.device.deviceTopic">
    </div>

  </div>
</template>

<style scoped>
.box1 {
  height: auto;
  width: auto;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-items: flex-start;
  gap: 12px;
  .box1-item {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    h4 {
      color: #a8c5e6;
      font-size: 14px;
      margin: 0;
    }
    input {
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
    select {
      margin-left: 5px;
      padding: 8px 12px;
      border: 1px solid #304156;
      border-radius: 4px;
      background: #2d3a4b url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%2390a3bf'%3E%3Cpath d='M7 10l5 5 5-5z'/%3E%3C/svg%3E") no-repeat right 12px center;
      color: #fff;
      appearance: none;
      transition: all 0.3s;
      width: 100px;

      &:hover {
        border-color: #409eff;
        cursor: pointer;
      }

      &:focus {
        border-color: #1890ff;
        box-shadow: 0 0 8px rgba(24, 144, 255, 0.2);
        outline: none;
      }

      option {
        background: #1f2d3d;
        color: #fff;
      }
    }

  }

}
#input1 {
  margin-left: 5px;
  margin-right: 5px;
}
#input2 {
  margin-left: 14px;
  margin-right: 6px;
}
#input3 {
  margin-left: 4px;
  margin-right: 6px;
}
</style>