<template>
  <div class="header">
    <h1>&#9878; $четчик калорий &#9878;</h1>
  </div>
  <nav>
    <router-link id="main" class="link" to="/products/all">Главная</router-link>
    <router-link id="new" class="link" to="/products/new">Новый продукт</router-link>
    <router-link id="basket" class="link" to="/products/count">Корзина</router-link>
  </nav>
  <router-view/>
  <div>
    <div class="counter">
      <span class="counter_text">{{ count() }}</span>
    </div>
    <router-link class="basket_counter" to="/products/count">
      <img class="cart" :src="cart" alt="cart">
    </router-link>
  </div>
  <footer>
    &#169; Все права защищены
  </footer>
</template>

<script>
  import cart from './images/cart67.png';
  import AllProducts from "@/components/AllProducts";
  import {reactive} from "vue";

  export default {
    name: 'App',
    components: {},
    data() {
      return {
        cart: cart
      };
    },
    methods: {
      count() {
        return reactive(AllProducts.data().elect_products).value.size
      },
    }
  }
</script>

<style>
  .header {
    padding: 30px 0;
    background: black;
    color: white;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    display: flex;
    flex-direction: column;
    min-height: 100%;
  }

  nav {
    background-color: #fc9d03;
    padding: 5px 0;
  }

  .link {
    text-decoration: none;
    color: white;
  }

  .link:hover {
    border: 2px solid white;
    color: white;
  }

  #main {
    margin-right: 40px;
  }

  #basket {
    margin-left: 40px;
  }

  #main, #new, #basket {
    padding: 7px;
    font-size: 18px;
  }

  .basket_counter {
    position: fixed;
    bottom: 95px;
    right: 60px;
    width: 80px;
    height: 80px;
    background-color: black;
    border-radius: 50px;
    text-decoration: none;
  }

  .cart {
    width: 55px;
    height: 55px;
    margin-top: 13px;
    margin-right: 3px;
  }

  .counter {
    width: 23px;
    height: 23px;
    background-color: red;
    position: fixed;
    right: 60px;
    bottom: 155px;
    z-index: 1;
    border-radius: 12px;
  }

  .counter_text {
    color: white;
    font-weight: 700;
  }

  footer {
    padding-top: 20px;
    padding-bottom: 20px;
    background-color: #fc9d03;
    display: block;
    color: white;
    text-align: center;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 18px;
  }
</style>