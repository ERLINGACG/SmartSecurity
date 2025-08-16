<script setup>

import GcBox1 from "@/View/group/create/gc-box1.vue";
import GcBox2 from "@/View/group/create/gc-box2.vue";
</script>

<template>
  <div class="create-group-container">
    <div class="create-group-left">

      <div class="num" id="num1">
        <p>1</p>
      </div>
      <div class="num-line"></div>
      <div class="num">
        <p>2</p>
      </div>
      <div class="num-line"></div>
    </div>
    <div class="create-group-right">
         <gc-box1/>
         <div class="create-group-line"></div>
         <gc-box2/>
    </div>


  </div>
</template>

<style scoped>
.create-group-container {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  height: 100%;
  width: 100%;
  .create-group-left{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 5vw;
    border-radius: 3px;
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
    .num-line {
      height: 170px;
      width: 2px;
      background-color: #1f2d3d;
    }
  }
  .create-group-line{
    margin-top: 10px;
    margin-bottom: 20px;
    height: 1px;
    width: 80%;
    background-color: #ccc;
  }
  .create-group-right{
    display: flex;
    flex-direction: column;
    justify-content:left;
    align-items: flex-start;
    height: 60vh;
    width: 55vw;
    border-radius: 8px;
    border: 1px solid rgba(204, 204, 204);
    padding: 20px;
  }
}
#num1{
  margin-top: 20px;
}
</style>