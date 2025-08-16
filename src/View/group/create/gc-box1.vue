<script setup>

import GroupModel from "@/Model/group/groupModel.js";
</script>

<template>
  <div class="gc-box-1-container">
    <h3>群组信息*</h3>
      <div class="gc-box-1-form">
        <div class="gc-box-1-form-item">
          <h4>群组名称</h4>
          <input type="text" id="name"  placeholder="请输入群组名称" v-model="GroupModel.newGroup.groupName">
          <h4>群组可见性</h4>
          <select v-model="GroupModel.newGroup.groupVisibility">
            <option value="公开">公开</option>
            <option value="私有">私有</option>
          </select>
        </div>
        <div class="gc-box-1-form-item" id="email-container" >
          <h4>绑定邮箱</h4>
          <input type="text" id="email"  placeholder="请输入邮箱" v-model="GroupModel.newGroup.groupEmail">
        </div>
        <div class="gc-box-1-form-item" id="topic-container">
            <h4>绑定设备topic</h4>
            <input type="text" id="topic"  placeholder="请输入设备topic" v-model="GroupModel.newGroup.groupTopic">
        </div>
      </div>
  </div>
</template>

<style scoped>
.gc-box-1-container {
    margin-top: 12px;
    height: auto;width: 100%;
    display: flex;

     flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    .gc-box-1-form {

      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      gap: 10px;
      .gc-box-1-form-item {
        margin-top: 10px;
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;

        h4 {
          color: #a8c5e6;
          font-size: 14px;
          margin: 0;
        }
        input {
          margin-left: 35px;
          margin-right: 10px;
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
}
#email-container {
  margin-top: 0;
}
#topic-container {
  margin-top: -3px;
  input {
    margin-left: 5px;
  }
}
</style>