<template>
  <div id="nav">
    <router-link to="/products/all">Главная</router-link> |
    <router-link to="/products/new">Новый продукт</router-link> |
    <router-link to="/products/count">Корзина</router-link>
  </div>
  <router-view/>
</template>

<script>
  export default {
    name: 'App',
    components: {}
  }
</script>

<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
