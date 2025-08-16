<script setup>
import groupService from "@/ViewModel/group/groupService.js";
import GroupModel from "@/Model/group/groupModel.js";

const submitGroup=async()=>{

    // groupService.testCatModel(GroupModel.newGroup)
    await groupService.addGroup(GroupModel.newGroup);
}
</script>

<template>
  <div class="gc-box2">
     <h3>创建群组*</h3>
     <textarea  placeholder="群组描述" v-model="GroupModel.newGroup.groupDescription"></textarea>

     <button class="submit-btn" @click="submitGroup">创建</button>

  </div>
</template>

<style scoped>
.gc-box2 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  textarea {
    margin-top: 12px;
    margin-bottom: 12px;
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