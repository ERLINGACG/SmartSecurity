<script setup>

import ExampleModel from "@/Model/exampleModel/ExampleModel.js";
import {computed, onMounted, ref} from "vue";
import DeviceModel from "@/Model/device/deviceModel.js";
import DeviceService from "@/ViewModel/device/deviceService.js";

const total = ref(DeviceModel.DeviceList.value.length);
const totalPages = computed(() => Math.ceil(total.value / pageSize.value));

const currentPage = ref(1); // 当前页
const pageSize = ref(5);  // 每页显示的条数

const paginatedDevices = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  return DeviceModel.DeviceList.value.slice(start, start + pageSize.value);
});

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};
onMounted(async () => {
  DeviceModel.DeviceList.value = await DeviceService.GetDeviceList(localStorage.getItem("nowUser"))
  console.log(DeviceModel.DeviceList.value)
})
</script>

<template>
  <div class="list-device-container">
    <div class="list-device-header">
       <h3>设备列表</h3>
       <div class="list-device-header-search">
          <input type="text" placeholder="按设备名称搜索">
          <button class="search-btn">搜索</button>
          <select>
             <option value="all">搜索设置</option>
          </select>
         <select>
             <option>设备筛选</option>
         </select>

       </div>
       <div class="list-device-header-line"></div>
    </div>
    <div class="list-device-body">
      <div class="list-item-title">
        <h4>设备pid</h4>
        <h4>设备名称</h4>
        <h4>设备类型</h4>
        <h4>设备topic</h4>
        <h4>设备绑定邮箱</h4>
        <h4>更改时间</h4>
      </div>
      <div class="list-item-line"></div>
      <div class="list-item-content">

        <div v-for="(item, index) in paginatedDevices" :key="index" class="list-item">

            <h5>{{item.pid}}</h5>
            <h5>{{item.deviceName}}</h5>
            <h5>{{item.deviceType}}</h5>
            <h5>{{item.deviceTopic}}</h5>
            <h5>{{item.userEmail}}</h5>
            <h5>{{item.date}}</h5>
        </div>

      </div>
      <div class="pagination">
        <button
            @click="prevPage"
            :disabled="currentPage === 1"
            class="page-btn"
        >
          上一页
        </button>

        <span class="page-info">
      第 {{ currentPage }} 页 / 共 {{ totalPages }} 页
    </span>

        <button
            @click="nextPage"
            :disabled="currentPage >= totalPages"
            class="page-btn"
        >
          下一页
        </button>
        <button class="">刷新</button>
      </div>

    </div>
  </div>

</template>

<style scoped>
.list-device-container {
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content:flex-start;
  width: 100%;
  height: 100%;
  .list-device-header {
      display: flex;
    width: 100%;
      flex-direction: column;
      justify-content:flex-start;
      align-items: flex-start;
        h3 {
          font-size: 24px;
          font-weight: bold;
        }
       .list-device-header-search {
          display: flex;
          align-items: flex-start;
          justify-content:flex-start;
           input {
             padding: 8px 12px;
             margin-right: 6px;
             border: 1px solid #304156;
             border-radius: 4px;
             height: 28px;
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
           padding: 0 12px;
           height: 28px;
           display: flex;
           align-items: center;
           justify-content:center;
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
             margin-left: 5px;
             background: #1f2d3d;
             color: #fff;
           }
         }
         .search-btn {
           display: flex;
           align-items: center;
           justify-content:center;
           padding: 10px 32px;
           background: #1890ff;
           color: white;
           border: none;
           height: 28px;
           border-radius: 4px;
           cursor: pointer;
           transition: all 0.3s;

           &:hover {
             background: #40a9ff;
             box-shadow: 0 2px 8px rgba(24, 144, 255, 0.3);
           }
         }


       }
       .list-device-header-line {
          width: 90%;
          height: 2px;
          background-color: #ccc;
          margin-top: 10px;
       }
  }
  .list-device-body {
     display: flex;
     flex-direction: column;
     align-items: flex-start;
     justify-content:flex-start;
     width: 100%;
     height: 100%;
     .list-item-line {
        width: 85%;
        height: 2px;
        background-color: #ccc;
        margin-top: 10px;
      }
     .list-item-title {
        display: grid;
        grid-template-columns: repeat(6, 1fr);
        grid-column-gap: 24px;
        margin-top: 10px;
        h4 {
          min-width: 100px;
          font-size: 16px;
          font-weight: bold;
          text-align: center;
        }
     }
     .list-item{
        display: grid;
        grid-template-columns: repeat(6, 1fr);
        grid-column-gap: 24px;
        margin-top: 10px;
        border-bottom: 1px solid #ccc;
        min-height: 30px;
        max-height: 30px;
        h5{
          font-size: 13px;
          min-width: 100px;
          max-width: 100px;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          padding: 0 8px;
        }
     }
  }
  .pagination {

     display: flex;
     justify-content: center;
     align-items: center;
     margin-top: 20px;
     button {
       margin-right: 10px;
       padding: 8px 16px;
       border: none;
       border-radius: 4px;
       background: #1890ff;
       color: white;
       cursor: pointer;
       transition: all 0.3s;

       &:hover {
         background: #40a9ff;
         box-shadow: 0 2px 8px rgba(24, 144, 255, 0.3);
       }
     }
    .page-info {
       margin-right: 10px;
       font-size: 14px;
       color: #666;
    }
    .refresh-btn {
      display: flex;
      align-items: center;
      justify-content:center;
      padding: 10px 32px;
      background: #1890ff;
      color: white;
      border: none;
      height: 28px;
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