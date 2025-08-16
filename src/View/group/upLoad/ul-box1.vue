<script setup>
// 头像相关状态
import {onMounted, ref} from "vue";
import memberModel from "@/Model/group/memberModel.js";
import groupModel from "@/Model/group/groupModel.js";
import groupService from "@/ViewModel/group/groupService.js";

const avatarFile = ref(null)
const avatarPreview = ref(null)

// 处理头像选择
const handleAvatarChange = (e) => {
  const file = e.target.files[0]
  if (file) {
    if (!/\.(jpe?g|png|gif)$/i.test(file.name)) {
      alert('仅支持JPG/PNG/GIF格式')
      return
    }
    avatarFile.value = file
    memberModel.addFeatureModel=file
    const reader = new FileReader()
    reader.onload = (e) => {
      avatarPreview.value = e.target.result
    }
    reader.readAsDataURL(file)
  }
}
// 上传头像
const uploadAvatar = async () => {
  if (!avatarFile.value) {
    alert('请先选择头像')
    return
  }

  const formData = new FormData()
  formData.append('avatar', avatarFile.value)

  try {
    // const response = await uploadMemberAvatar(formData)
    // alert(response.message)
    // // 上传成功后重置状态
    avatarFile.value = null
    avatarPreview.value = null  // 新增：清空预览

  } catch (error) {
    console.error('上传失败:', error)
    alert('上传失败，请重试')
  }
}
onMounted(async () => {
  await groupService.getGroupList()
})
</script>

<template>
  <div class="ul-box1">
    <h3>成员信息*</h3>
    <div class="ul-box1-item-1">
      <div class="member-head">
        <img v-if="avatarPreview" :src="avatarPreview" class="avatar-preview" alt="">
        <input
            type="file"
            accept="image/*"
            @change="handleAvatarChange"
            style="display: none;"
            ref="fileInput"
        >
      </div>
      <div class="member-info">
        <div class="member-info-item">
          <h4>成员姓名</h4>
          <input type="text" placeholder="请输入姓名" v-model="memberModel.addMemberModel.memberName">
          <h4>绑定群组</h4>
          <select v-model="memberModel.addMemberModel.groupId">
            <option v-for="item in groupModel.groupList.value" :value="item.gid">{{item.groupName}}</option>
          </select>
        </div>
        <div class="member-info-item">
          <h4>成员邮箱</h4>
          <input type="text" placeholder="请输入邮箱" v-model="memberModel.addMemberModel.memberEmail">
          <h4>成员性别</h4>
          <select v-model="memberModel.addMemberModel.memberGender">
            <option value="male">男</option>
            <option value="female">女</option>
          </select>

        </div>
        <div class="member-info-item">
          <h4>成员身份</h4>
          <input type="text" placeholder="请输入身份" v-model="memberModel.addMemberModel.memberIdentity">
        </div>
        <div class="member-info-item">
          <button @click="$refs.fileInput.click()">上传照片</button>
          <button @click="uploadAvatar">重新上传</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ul-box1 {
  width: auto;
  height: auto;
  display: flex;
  padding: 20px;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  .ul-box1-item-1 {
      display: flex;
     flex-direction: row;
     .member-head {
        width: 150px;
        height: 160px;
        border-radius: 5px;
        margin-right: 20px;
        border:  1px solid #ccc;
       .avatar-preview {
         width: 100%;
         height: 100%;
         object-fit: cover;
       }
     }
  }
  .member-info {
      h4{
        color: #a8c5e6;
      }
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      .member-info-item {
        margin-top: 5px;
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: flex-start;
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
        select {
          margin-left: 10px;
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
          padding: 10px 32px;
          margin-right: 10px;
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
</style>