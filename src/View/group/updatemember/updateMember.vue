<script setup>

import ExampleModel from "@/Model/exampleModel/ExampleModel.js";
import {computed, onMounted, ref} from "vue";
import groupService from "@/ViewModel/group/groupService.js";
import groupModel from "@/Model/group/groupModel.js";
import memberService from "@/ViewModel/group/memberService.js";
import memberModel from "@/Model/group/memberModel.js";
import GroupModel from "@/Model/group/groupModel.js";

const put=(index)=>{
   console.log(ExampleModel.m1.value[index]);
}
const total = ref(memberModel.memberList.value.length);
const totalPages = computed(() => Math.ceil(total.value / pageSize.value));

const currentPage = ref(1); // 当前页
const pageSize = ref(5);  // 每页显示的条数
const paginatedMembers = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  return memberModel.memberList.value.slice(start, start + pageSize.value);
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

const index = ref(null);
const sureGroup = async (index) => {
  if (index === null) {
    index = 0;
  }
  console.log(groupModel.groupList.value[index].gid);
  memberModel.memberList.value=await memberService.getMembers(groupModel.groupList.value[index].gid);

}


onMounted(async () => {
  await groupService.getGroupList();
});
</script>

<template>
  <div>
    <div class="update-member-container">
         <div class="header">
            <h3>成员管理</h3>
            <div class="header-line"></div>
         </div>
         <div class="body">
           <div class="search-member">
             <input placeholder="搜索成员">
             <button>搜索</button>
             <select v-model="index">
                 <option  v-for="(item, index) in groupModel.groupList.value" :key="item.gid" :value="index" >
                   {{item.groupName}}
                 </option>
             </select>
             <button style="margin-left: 10px;" @click="sureGroup(index)">确认</button>
           </div>
           <div class="member-list">
             <div class="member-item-title">
               <h4>成员ID</h4>
               <h4>成员姓名</h4>
               <h4>成员邮箱</h4>
               <h4>成员性别</h4>
               <h4>成员身份</h4>
               <h4>更新时间</h4>
             </div>
             <div class="member-item-line"></div>
             <div class="member-item" v-for="(item, index) in paginatedMembers" :key="index">
                  <div class="member-item-box">
                    <input style="width: 70px" v-model="item.mid" readonly/>
                    <input v-model="item.memberName" />
                    <input v-model="item.memberEmail"/>
                    <input v-model="item.memberGender"/>
                    <input v-model="item.memberIdentity"/>
                    <input v-model="item.updateTime"/>
                    <button @click="memberService.updateMember(item)">更新</button>
                    <button @click="memberService.deleteMember(item)">删除</button>
                  </div>
             </div>
             <div class="member-page-btn">
               <button @click="prevPage()">上一页</button>
               <p>第{{currentPage}}页/共{{totalPages}}页</p>
               <button @click="nextPage()">下一页</button>
               <button>刷新</button>
             </div>
           </div>
         </div>
    </div>
  </div>
</template>

<style scoped>
.update-member-container {

    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    padding: 20px;
     .header {
       display: flex;
       flex-direction: column;
       align-items: flex-start;
       justify-content:flex-start;
       height: auto;
       width: 5vw;
       h3{
         font-size: 18px;
         font-weight: bold;
       }
       .header-line {
         width: 40vw;
         height: 1px;
         background-color: #EFEFEF;
         margin-top: 10px;
       }
       margin-bottom: 10px;
     }
   .body {
     height: 100%;
     width:   100%;
     display: flex;
     flex-direction: column;
     align-items: flex-start;
     justify-content:flex-start;
     .search-member {
         display: flex;
         flex-direction: row;
         align-items: center;
         justify-content: space-between;
         width: auto;
         height: 20px;

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
     .member-list{
         width: 100%;
         height: auto;
         display: flex;
         flex-direction: column;
         align-items: flex-start;
         justify-content:flex-start;
         .member-item-line {
           width: 100%;
           height: 1px;
           background-color: #EFEFEF;
            margin-top: 5px;
            margin-bottom: 10px;
         }
         .member-item-box {
           display: flex;
           flex-direction: row;
           align-items: center;
           margin-left: -10px;
         }
         .member-item {
           display: flex;
           flex-direction: row;
           align-items: center;
           justify-content: flex-start;
           padding: 10px;

           input {

             margin-right: 16px;

             height: 24px;
             width: 90px;
             border: 1px solid #304156;
             border-radius: 4px;
             background: #1f2d3d;
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
           width: 100%;
           height: 30px;
           margin-bottom: 10px;
           border-bottom: 1px solid #EFEFEF;
           button {
             margin-right: 10px;
             background: #1890ff;
             height: 28px;
             width: 40px;
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
         .member-item-title {
             display: flex;
             flex-direction: row;
             align-items: center;
             justify-content:center;
             margin-left: -20px;
             height: 50px;
             padding: 10px;
             h4 {
               margin-right: 26px;
               padding: 10px;
               font-size: 16px;
               font-weight: 500;

             }

         }
       }
       .member-page-btn {
           display: flex;
           flex-direction: row;
           align-items: center;
           justify-content: space-between;
           width: auto;
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
           p {
             margin-right: 10px;
             padding: 0;
             font-size: 14px;
             font-weight: 500;
           }
       }
   }
}
</style>