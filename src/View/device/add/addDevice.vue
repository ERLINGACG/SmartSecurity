<script setup>

import Box1 from "@/View/device/add/box1.vue";
import Box2 from "@/View/device/add/box2.vue";
import ExampleModel from "@/Model/exampleModel/ExampleModel.js";



</script>

<template>
  <div class="device-body-left">
    <div class="num" id="num1">
      <p>1</p>
    </div>
    <div class="num-line"></div>
    <div class="num">
      <p>2</p>
    </div>
    <div class="num-line"></div>
  </div>

  <div class="device-body-right">

    <div class="add-device-container">

        <div class="add-device-box-1">
          <div class="add-device-box-1-title">
            <h3>设备信息*</h3>
            <box1/>
          </div>
        </div>

        <div class="add-device-box-2">
          <div class="add-device-box-2-title">
            <h3>设备描述*</h3>
            <box2/>
          </div>
        </div>

    </div>
  </div>
</template>

<style scoped>
.add-device-container {

    display: flex;
    flex-direction: column;
    justify-content: left;
    align-items: flex-start;
    height: 100%;
    width: 100%;
    border-radius: 20px;
    border: 1px solid rgba(204, 204, 204);
    .add-device-box-1 {

        width: auto;
        height: 50%;
        border-radius: 10px;
        border:  1px solid #ccc;
        display: flex;

        h3{
            position: relative;
            font-size: 20px;

            left: -210px;
        }
    }
    .add-device-box-2{
      h3{
        position: relative;
        font-size: 20px;

        left: -100px;
      }
        width: 50%;
        height: 50%;
        border-radius: 10px;
        border:  1px solid #ccc;
        display: flex;
    }
}
.device-body-left {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 60vh;
  width: 5vw;
  border-radius: 8px;
  border: 1px solid rgba(204, 204, 204);
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
.device-body-right {
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
</style>