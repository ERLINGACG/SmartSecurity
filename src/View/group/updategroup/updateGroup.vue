<script setup>

import {onMounted, ref} from "vue";
import groupService from "@/ViewModel/group/groupService.js";
import GroupModel from "@/Model/group/groupModel.js";
import DeviceModel from "@/Model/device/deviceModel.js";
import DeviceService from "@/ViewModel/device/deviceService.js";
import GroupService from "@/ViewModel/group/groupService.js";

onMounted(async ()=>{
   await groupService.getGroupList()

})
const selectInfo=''
const selectGroup = (title)=>{
  GroupModel.updateGroupList.value = Object.assign({},
      GroupModel.groupList.value.find(item=>item.groupName===title))

  console.log("selectGroup",title)
}
const pid = ref(0)
const sureSelect = async () => {
  console.log("sureSelect PID:",pid.value)
  GroupModel.updateGroupList.value = Object.assign({},
      await groupService.getGroup(GroupModel.groupList.value[pid.value].groupName))
  console.log(GroupModel.updateGroupList.value)
}


</script>

<template>
  <div class="update-group-container">
    <div class="update-group-header">
       <h3>管理群组*</h3>
       <div class="update-group-header-line"></div>
    </div>

     <div class="update-group-body">
       <div class="update-group-search-bar">
           <input type="text" placeholder="搜索群组" v-model="selectInfo">
           <button @click="selectGroup(selectInfo)">搜索</button>
          <select v-model="pid">
            <option v-for="(item, index) in GroupModel.groupList.value" :value="index">{{item.groupName}}</option>
          </select>
          <button class="confirm-select" @click="sureSelect()">确认选择</button>
       </div>
       <div class="update-group-line"></div>
       <div class="update-group-item">
          <div class="update-group-item-1" id="i1">
             <h4>群组名称</h4>
             <input type="text" placeholder="请输入群组名称" v-model="GroupModel.updateGroupList.value.groupName">
             <h4>群组可见性</h4>
             <select v-model="GroupModel.updateGroupList.value.groupVisibility">
               <option value="公开">公开</option>
               <option value="私有">私有</option>
             </select>
          </div>
         <div class="update-group-item-1" id="i2">
             <h4>群组绑定topic</h4>
             <input type="text" placeholder="请输入群组绑定的topic" v-model="GroupModel.updateGroupList.value.groupTopic">
         </div>
         <div class="update-group-item-1" id="i3">
             <h4>群组描述</h4>
             <textarea placeholder="请输入群组描述" v-model="GroupModel.updateGroupList.value.groupDescription"></textarea>
         </div>
         <div class="update-group-item-1" id="i4">
           <button @click="groupService.updateGroup(GroupModel.updateGroupList.value)">更新</button>
           <button @click="groupService.deleteGroup(GroupModel.updateGroupList.value.gid)">删除该群组</button>
         </div>
       </div>
     </div>
  </div>
</template>

<style scoped>
#i2 {
  margin-top: 10px;
}
#i1 {
  input {
     margin-left: 42px;
  }
}
#i3{
  margin-top: 10px;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  width: 100%;
  height: auto;
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

}
.update-group-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 20px;
  height: 100%;
  width: 100%;
  .update-group-header {
     display: flex;
     flex-direction: column;
     justify-content: flex-start;
     align-items: flex-start;
     height: auto;
     margin-bottom:  10px;
     width: 100%;
     h3 {
       font-size: 18px;
       font-weight: bold;
     }.update-group-header-line {
        height: 1px;
        width: 80%;
        background-color: #EFEFEF;
      }

  }
  .update-group-body {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    .update-group-search-bar {
      display: flex;
      flex-direction:row;
      justify-content: flex-start;
      align-items: flex-start;
      height:auto;
      width: auto;
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
        height: 30px;
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
      button {
        padding: 10px;
        height: 30px;
        width: 80px;
        display: flex;
        align-items: center;
        justify-content:center;
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
    .update-group-line {
      height: 1px;
      width: 100%;
      background-color: #EFEFEF;
      margin-top: 10px;
      margin-bottom: 10px;
    }
    .update-group-item {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      width: 100%;
      height: auto;
      .update-group-item-1 {
         display: flex;
         flex-direction: row;
         justify-content: flex-start;
         align-items: center;
         width: 100%;
         height: auto;
          input {
            margin-left: 10px;
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
         h4 {
           color: #a8c5e6;
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
        button {
          margin-right: 10px;
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
  }
}
.confirm-select {
  margin-left: 10px;
}
</style>