<script setup>

import DBox1 from "@/View/device/delete/d-box1.vue";
import DBox2 from "@/View/device/delete/d-box2.vue";
</script>

<template>
  <div class="delete-device-container">
     <div class="delete-device-left">
       <div class="num">
         <p>1</p>
       </div>
       <div class="line"></div>
       <div class="num">
         <p>2</p>
       </div>
       <div class="line"></div>
     </div>
     <div class="delete-device-right">
       <div class="box1">
         <d-box1/>
       </div>
       <div class="box2">
          <d-box2/>
       </div>
     </div>
  </div>
</template>

<style scoped>
.delete-device-container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  height: 100%;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #ccc;
}
.delete-device-left {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 5vw;
  height: 100%;
  border-radius: 5px;
  border: 1px solid #ccc;

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
.delete-device-right {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 20px;
  height: 60vh;
  width: 55vw;
  border-radius: 5px;
  border: 1px solid #ccc;
}
</style>