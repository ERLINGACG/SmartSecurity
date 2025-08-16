<script setup>
import {computed, onMounted, ref} from "vue";
import GroupModel from "@/Model/group/groupModel.js";
import groupService from "@/ViewModel/group/groupService.js";
import DeviceModel from "@/Model/device/deviceModel.js";
const total = ref(GroupModel.groupList.value.length);
const totalPages = computed(() => {
  return Math.ceil(GroupModel.groupList.value.length / pageSize.value)
})

const currentPage = ref(1); // 当前页
const pageSize = ref(5);  // 每页显示的条数

const paginatedGroups = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  return GroupModel.groupList.value.slice(start, start + pageSize.value);
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
onMounted(async ()=>{
  await groupService.getGroupList()
  console.log("GroupModel.groupList",GroupModel.groupList)
})
</script>

<template>
<div class="list-group-container">
  <div class="list-group-header">
    <h3>群组列表</h3>
    <div class="line"></div>
  </div>
  <div class="list-group-body">
    <div class="list-group-item-box">
       <div class="list-group-item-title">
           <h4>群组gid</h4>
           <h4>群组名称</h4>
           <h4>绑定邮箱</h4>
           <h4>群组可见性</h4>
           <h4>绑定设备topic</h4>

       </div>
      <div class="title-line">
      </div>
      <div class="list-group-item" v-for="item in paginatedGroups" :key="item.groupId">
        <div class="item">
          <h5>{{item.gid}}</h5>
          <h5>{{item.groupName}}</h5>
          <h5>{{item.groupEmail}}</h5>
          <h5>{{item.groupVisibility}}</h5>
          <h5>{{item.groupTopic}}</h5>

        </div>
        <div class="line">
        </div>
      </div>
      <div class="list-group-btn-box">
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
            :disabled="currentPage === totalPages"
            class="page-btn"
        >
          下一页
        </button>
      </div>
    </div>

  </div>


</div>
</template>

<style scoped>
.list-group-container {
  height: 100%;
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;

}
.list-group-header{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  width: 100%;
  height: auto;
  margin-bottom:  10px;
  h3 {
    font-size: 18px;
    font-weight: bold;
  }
  .line {
     height: 1px;
     width: 90%;
     background-color: #EFEFEF;
   }
}
.list-group-body{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  .list-group-item-box{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    width: 100%;
    height: auto;
    margin-bottom: 10px;
    .list-group-item-title{
      margin-left: -10px;
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
    .title-line {
      height: 1px;
      width: 90%;
      background-color: #EFEFEF;
    }
  }
  .list-group-item{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    width: 100%;
    height: auto;
    margin-bottom: 10px;
    .item{
      margin-left: -10px;
      display: grid;
      grid-template-columns: repeat(6, 1fr);
      grid-column-gap: 24px;
      margin-top: 10px;
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
    .line {
      height: 1px;
      width: 90%;
      background-color: #EFEFEF;
    }
  }
}
</style>