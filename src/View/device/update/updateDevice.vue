<script setup>

import UBox1 from "@/View/device/update/u-box1.vue";
import UBox2 from "@/View/device/update/u-box2.vue";
</script>

<template>
  <div class="update-device-container">
    <div class="update-device-left">
      <div class="num">
        <p>1</p>
      </div>
      <div class="line"></div>
      <div class="num">
        <p>2</p>
      </div>
      <div class="line"></div>
    </div>
    <div class="update-device-right">
      <div class="u-box-1">
        <u-box1/>
      </div>
      <div class="u-box-2">
        <u-box2/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.update-device-container {
  display: flex;
  justify-content:flex-start;
  align-items: flex-start;
  height: 100%;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 4px;
  .update-device-left {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 5vw;
    border: 1px solid #ccc;
    border-radius: 4px;
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
    .line {
      height: 170px;
      width: 2px;
      background-color: #1f2d3d;
    }
  }
  .update-device-right {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px;
    height: 60vh;
    width: 55vw;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

}
</style>