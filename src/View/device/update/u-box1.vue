<script setup>

import DeviceModel from "@/Model/device/deviceModel.js";
import {onMounted, ref} from "vue";
import DeviceService from "@/ViewModel/device/deviceService.js";


let list= ref([])
let pid = ref(0)
const truncateText = (str, len) => {
  if (str.length > len) {
    return str.slice(0, len) + "...";
  }else {
    return str;
  }
}

onMounted(async () => {
  list.value = await DeviceService.GetDeviceList(localStorage.getItem("nowUser"))
  DeviceModel.updateConfig.item = list.value
  console.log(DeviceModel.updateModel)
})

const testM = ref({
  deviceName: '',
  deviceType: '',
  deviceTopic: ''
})
const test = async () => {
  console.log(pid.value, "test");
  DeviceModel.updateModel.value = Object.assign({},
      await DeviceService.GetDeviceInfo(pid.value,
          localStorage.getItem("nowUser")))
  console.log(DeviceModel.updateModel.value)
}

</script>
<template>
  <div class="u-box1">
    <div class="u-box1-header">
      <h3>设备信息*</h3>
    </div>
    <div class="u-box1-form" id="u-box1-form1">
      <h4>选择设备</h4>
      <select id="input1" v-model="pid">
          <option v-for="(item, index) in list" :value="item.pid"
             :title="item.deviceName"
          >
              {{truncateText(item.deviceName, 10)+ " : " + truncateText(item.pid, 10)}}
          </option>
      </select>
      <button @click="test">测试</button>
    </div>

    <div class="u-box1-form" id="u-box1-form2">
      <h4>设备名称</h4>
      <input type="text" placeholder="请输入设备名称" id="input2" v-model="DeviceModel.updateModel.value.deviceName">
      <h4>设备类型</h4>
      <select id="input4" v-model="DeviceModel.updateModel.value.deviceType">
        <option value="camera">摄像头</option>
        <option value="switch">智能开关</option>
        <option value="socket">智能插座</option>
      </select>
    </div>
    <div class="u-box1-form" id="u-box1-form3">
      <h4>设备topic</h4>
      <input type="text" placeholder="请输入设备topic" id="input3" v-model="DeviceModel.updateModel.value.deviceTopic">
    </div>
    <div class="u-box1-line"/>
  </div>
</template>

<style scoped>

.u-box1 {
  height: 200px;
  width: auto;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  h3{
    margin-left: 6px;
  }

  .u-box1-header {
    h4{
      font-size: 3vh;
      font-weight: bold;
    }
  }
  .u-box1-form {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    padding: 8px;
    h4 {
      color: #a8c5e6;
      font-size: 14px;
      margin: 0;
    }
    input {
      margin-left: 12px;
      margin-right: 6px;
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
  .u-box1-line {
    height: 2px;
    width: 100%;
    background: #ffffff;
    margin-top: 30px;
    margin-bottom: 12px;
  }
}
#input1{
  margin-left: 22px;
}
#input2{
  margin-left: 22px;
}
#input3{
  margin-left: 20px;
}
#u-box1-form1{
  select{
    width: 200px;
  }
}
</style>