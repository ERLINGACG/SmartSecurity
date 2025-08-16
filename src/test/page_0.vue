<script>
import axios from "axios";

export default {
  data(){
    return {}
  },
  methods:{
    t1(){
      console.log('t1')
    },
    async t2(){
      const url='http://localhost:8080/user/test/hello3'
      const response = await axios.get(url);
      console.log(response.data);
    }
  }
}
</script>

<template>
  <div>
    <h1>Page 0</h1>
    <button @click="t2">t1</button>
  </div>
</template>

<style scoped>

</style>