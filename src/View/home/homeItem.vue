<script setup>
import {onMounted, ref} from "vue";
import * as echarts from 'echarts'
import HomePageModel from '../../Model/home/HomePageModel'


const card4Ref=ref(null)
const card5Ref=ref(null)
const card6Ref=ref(null)

onMounted(() => {
    const chart4D=echarts.init(card4Ref.value);
    chart4D.setOption(HomePageModel.card4ChartOptions);
    const chart5D=echarts.init(card5Ref.value);
    chart5D.setOption(HomePageModel.card5ChartOptions);
    const chart6D=echarts.init(card6Ref.value);
    chart6D.setOption(HomePageModel.card6RadarOption);
})
</script>

<template>
  <div class="home-item">
    <div class="home-item-title">
      <h3>
        系统总览
      </h3>
    </div>

    <div class="title-divider"></div>

    <div class="home-item-content">

      <div class="home-item-content-card">

        <div class="home-item-content-card-item" id="card1">

          <div class="home-item-content-card-item-title">
             <h4>产品总览</h4>
          </div>
          <div class="home-item-content-card-item-content">
            <div class="product-overview" >
              <p>在线设备数:</p>
              <p id="product-num">12</p>
            </div>

            <div class="line-divider"></div>
            <div class="device-overview">
              <p>绑定设备数</p>
              <p id="product-num">12</p>
            </div>
          </div>

        </div>

        <div class="home-item-content-card-item" id="card2">
          <div class="user-card">
            <div class="heard-icon">
              <img src="/src/assets/user/userex.jpg" alt="">
              <h4>空条承太郎</h4>
            </div>
            <div class="user-line"></div>
            <div class="user-info">
              <div class="user-avatar">
                <p id="user-email">绑定邮箱:</p>
                <p id="user-group">用户ID:</p>
                <p id="user-register-date">注册日期:</p>
              </div>
              <div class="user-info-data">
                <p>email@example.com</p>
                <p>123456789</p>
                <p>2021-01-01</p>
              </div>

            </div>
          </div>
        </div>
        <div class="home-item-content-card-item" id="card3">
          <div class="usually-card">
            <h4>最近访问</h4>
            <div class="usually-card-item">

            </div>
          </div>
          <div class="fast-card">
            <h4>常用模块</h4>
            <div class="fast-card-item">
              <p id="i1">监控模块</p>
              <p id="i2">设备管理</p>
              <p id="i3">消息推送</p>
              <p id="i4">数据分析</p>
              <p id="i5">系统设置</p>
            </div>
          </div>
        </div>
        <div class="home-item-content-card-item" id="card4" ref="card4Ref">

        </div>
        <div class="home-item-content-card-item" id="card5" ref="card5Ref">

        </div>
        <div class="home-item-content-card-item" id="card6" ref="card6Ref">

        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.home-item {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 90%;
}
.home-item-title {

  position: relative;
  font-size: 24px;
  font-weight: bold;
  margin-top: 130px;

  width: 100%; /* 添加宽度限制 */
  text-align: left;
}

.title-divider {
  width:calc(100% - 40px);
  height: 1px;
  background-color: #e8e8e8;
  margin: 10px 0;
  transform: scaleY(0.5);
}
.home-item-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 修改为左对齐 */
  justify-content: center;
  height: 100%;
  width: 100%;
  .home-item-content-card {
    display: grid;
    justify-content: left;
    grid-template-columns: auto;
    grid-template-rows: auto;

    width: 90%;
    height: 100%;
    border-radius: 20px;
    gap:  10px;
    .home-item-content-card-item {

      display: grid;
      grid-template-columns: auto;
      grid-template-rows: auto;
      width: 100px;
      height:100px;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      border-radius: 20px;

      font-size: 18px;

      font-weight: bold;
      color: #333;
      text-align: center;
      h4 {
        margin-top: 20px;
      }
    }
    #card1 {
      width: 44vw;
      height:200px;

      grid-column-start: 1;
      grid-column-end: 6;
      background: linear-gradient(135deg, #8A2BE2 0%, #4B0082 100%);
      justify-content: left;
      .home-item-content-card-item-title {
        width: 100px;
        height: 50px;
        font-weight: bold;
        color: white;
        grid-column-start: 1;
        grid-column-end: 2;
      }
      .home-item-content-card-item-content {
        width: 60vw;
        height: 150px;
        padding: 10px;
        display:  flex;
        grid-column-start: 1;
        grid-column-end: 6;
        grid-row-start: 2;
        grid-row-end: 3;
        border-radius: 20px;

        justify-content: left;
        gap: 120px;
        .product-overview {
          display: flex;
          margin-top: -80px;
          flex-direction: column;

          align-items: center;
          justify-content: center;
          color: white;
          #product-num {
             margin-left: -70px;
          }
        }
        .line-divider {
          width: 1px;

          height: 120px;
          background-color: #e8e8e8;
          margin-top: -10px;
          margin-bottom: 10px;
        }
        .device-overview {
          margin-top: -80px;
          display: flex;
          flex-direction: column;

          align-items: center;
          justify-content: center;
          color: white;
        }
      }
    }
    #card2 {
      width: 26vw;
      height:200px;
      grid-column-start: 6;
      grid-row-start: 1;
      background: rgba(255, 255, 255, 0.05);
      color: white;
      display: flex;
      justify-content: left;
      padding-left: -30px; /* 添加左侧内边距 */
      align-items: center;
      gap:  20px;
      .user-card {
        margin-left: 3vw;
        display: grid;
        grid-template-columns: auto;
        grid-template-rows: auto;
        align-items: center;
        justify-content: left;
        grid-gap: -20px;
        width: 100%;
        height: 100%;
        .heard-icon {
          grid-column-start: 1;
          grid-column-end: 2;
          grid-row-start: 1;
          grid-row-end: 2;
          margin-top: 5px;
          display: flex;
          flex-direction: row;
          align-items: center;
          justify-content:flex-start;
          width: 100%;

          img{
            position: relative;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            left: 0;
            border: 2px solid white;
            margin-right: 10px;
          }
          h4 {
            color: white;
            font-size: 18px;
            font-weight: bold;
            margin-top: 10px;
          }
        }
        .user-info {
          grid-column-start: 1;
          grid-column-end: 10;
          grid-row-start: 2;
          grid-row-end: 5;
          display: grid;
          grid-template-columns: 1fr 1fr 1fr 1fr;
          grid-template-rows: 1fr  ;
          margin-top: -30px;
          grid-gap: 10px;
          .user-avatar {
            width: 100%;
            height: 100%;

          }
        }
        p {
          color: white;
          font-size: 15px;
          font-weight: bold;
        }
        #user-email {
          grid-column-start: 1;
          grid-column-end: 2;
          grid-row-start: 2;
          grid-row-end: 3;
        }
        #user-group {
          grid-column-start: 1;
          grid-column-end: 2;
          grid-row-start: 3;
          grid-row-end: 4;

        }
        #user-register-date {
          grid-column-start: 1;
          grid-column-end: 2;
          grid-row-start: 4;
          grid-row-end: 5;
        }
        .user-line{
          padding-left: 10px;
          width:  250px;
          height: 1px;
          background-color: white;
          margin-top: -20px;
          grid-column-start: 1;
          grid-column-end: 2;
          grid-row-start: 2;
          grid-row-end: 3;

        }
      }

    }
    #card3 {
      margin-top: 0;
      width: 71vw;
      height:20vh;
      display: grid;
      grid-template-columns: auto;
      grid-template-rows: auto;
      grid-column-start: 1;
      grid-column-end: 10;
      grid-row-start: 2;
      grid-row-end: 3;
      grid-gap: 10px;
      .usually-card {
        width: 30vw;
        height: 20vh;
        background: rgba(25,125,230,1); /* 调整透明度 */
        border-radius: 20px;
        border: 1px solid rgba(25,125,230,1); /* 新增边框 */
        grid-column-start: 1;
        grid-column-end: 2;
        grid-row-start: 1;
        grid-row-end: 1;
        display: flex;
        flex-direction: column;
        justify-content: left;
        h4{
          position: relative;
          left:calc(20vw - 20vw*2 + 9vw);
          margin-top: 10px;
          color: white;
          font-size: 18px;
          font-weight: bold;

        }
        .usually-card-item {
          grid-column-start: 1;
          grid-column-end: 5;
          grid-row-start: 2;
          grid-row-end: 3;
          display: grid;
          width: 100%;
          height: 10vh;

        }
      }
      .fast-card {
        width: 40vw;
        height: 20vh;
        background: rgba(25,125,230,1); /* 调整透明度 */
        border-radius: 20px;
        border: 1px solid rgba(255,255,255,0.1); /* 新增边框 */
        grid-column-start: 2;
        grid-column-end: 10;
        grid-row-start: 1;
        grid-row-end: 1;
        h4{
          position: relative;
          left: calc(40vw - 20vw*3 + 4vw);
          margin-top: 10px;
          color: white;
          font-size: 18px;
          font-weight: bold;
        }
        .fast-card-item {

          color: white;
          display: grid;
          grid-template-columns: repeat(3, 1fr);
          gap: 15px;
          padding: 15px;
          width: 100%;
          height: auto;

          p {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100px;
            height: 30px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 8px;
            font-size: 14px;
            font-weight: 500;
            transition: all 0.3s ease;
            cursor: pointer;
            margin: 0;

            &:hover {
              background: rgba(255, 255, 255, 0.2);
              transform: translateY(-2px);
            }
          }
        }
      }
    }
    #card4 {
      width: 20vw;
      height:30vh;
      grid-column-start: 1;
      grid-column-end: 2;
      grid-row-start: 3;
      grid-row-end:11;
      background-color: #1f2d3d;

    }
    #card5 {
      width: 20vw;
      height:30vh;
      grid-column-start: 2;
      grid-column-end: 3;
      grid-row-start: 3;
      grid-row-end:11;
      background-color: white;
    }
    #card6 {
      width: 29vw;
      height:30vh;
      grid-column-start: 3;
      grid-column-end: 10;
      grid-row-start: 3;
      grid-row-end:11;
      background-color: #f5f5f5;
    }
  }
}
</style>