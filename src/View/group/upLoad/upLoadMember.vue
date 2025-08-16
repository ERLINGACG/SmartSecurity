<script setup>

import UlBox1 from "@/View/group/upLoad/ul-box1.vue";
import UlBox2 from "@/View/group/upLoad/ul-box2.vue";
</script>

<template>
  <div class="upload-member-container">
    <div class="upload-member-left">

      <div class="num" id="num1">
        <p>1</p>
      </div>
      <div class="num-line"></div>
      <div class="num">
        <p>2</p>
      </div>
      <div class="num-line"></div>
    </div>
    <div class="upload-member-right">
       <ul-box1/>
       <ul-box2/>
    </div>
  </div>
</template>

<style scoped>
.upload-member-container {
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  height: 100%;
  width: 100%;
  .upload-member-left {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 5vw;
    .num {
      font-size: 12px;
      font-weight: bold;
      color: #ffffff;
      background: #1f2d3d;
      border-radius: 50%;
      height: 30px;
      width: 30px;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .num-line {
      height: 170px;
      width: 2px;
      background-color: #1f2d3d;
    }
  }
  .upload-member-right {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}
</style>