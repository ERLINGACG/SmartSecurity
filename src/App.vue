<script setup>

</script>

<template>
  <main>
    <router-view></router-view>
  </main>
</template>

<style>
/* 添加全局暗色主题 */
:root {
  --bg-color: #1a1a1a;
  --text-color: #ffffff;
}

html, body {
  background-color: var(--bg-color);
  color: var(--text-color);
  height: 100%;
  margin: 0;
  padding: 0;
}

#app {
  background-color: var(--bg-color);
  color: var(--text-color);
  min-height: 100vh;
}
</style>
