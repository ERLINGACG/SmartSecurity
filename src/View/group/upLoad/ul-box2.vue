<script setup>

import memberModel from "@/Model/group/memberModel.js";
import memberService from "@/ViewModel/group/memberService.js";
</script>



<template>
<div class="ul-box2-container">
   <h3>成员描述*</h3>
   <textarea  placeholder="请输入成员描述" v-model="memberModel.addMemberModel.memberDescription"></textarea>
   <div class="ul-box2-btn">
     <input type="checkbox" >
     <p >我已阅读并同意隐私</p>
   </div>
   <div class="ul-box2-btn">
     <button @click="memberService.addMember(memberModel.addMemberModel,memberModel.addFeatureModel)">上传</button>
     <button>更新已有成员</button>
   </div>
</div>
</template>

<style scoped>
.ul-box2-container {
  height: 100%;
  width: 100%;
  padding: 0 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  h3{
    font-size: 18px;
    margin-top: -10px;
  }
  textarea {
    margin-top: 6px;
    margin-bottom: 6px;
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
  .ul-box2-btn{
     display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    color: #a8c5e6;
    button{
      padding: 10px 32px;
      margin-right: 24px;
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