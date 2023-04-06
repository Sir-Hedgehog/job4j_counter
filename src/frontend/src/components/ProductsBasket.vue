<template>
  <div class="basket">
    <div v-if="!submitted">
      <div v-if="elect_products.size !== 0">
        <div class="calories_count" v-for="[product] in this.elect_products" :key="product">
          <label class="product_name">{{ product }}&nbsp;</label>
          <input type="number" class="input-group" min="0" max="99999" id="product_calories" :value=0 @input="updateProductMap(product, $event.target.value)" required/>
        </div>
        <button class="button" @click="countCalories()">Посчитать калории</button>
      </div>
      <div v-else>
        <h4 class="empty_basket">Корзина пуста! </h4>
      </div>
    </div>
    <div v-else>
      <h4 class="calorie_count">Количество потребленных калорий: <span class="number">{{ this.result }}</span></h4>
      <h6><router-link class="main" to="/products/all">Вернуться на главную</router-link></h6>
    </div>
  </div>
</template>

<script>
  import AllProducts from "@/components/AllProducts";
  import ProductService from "@/services/ProductService";

  export default {
    name: 'ProductBasket',
    data() {
      return {
        elect_products: AllProducts.data().elect_products,
        submitted: false,
        current_map: new Map,
        result: 0
      }
    },
    methods: {
      countCalories() {
        ProductService.countFromBasket(this.current_map).then((response) => {
          this.elect_products = new Map
          this.result = response.data
          this.submitted = true
        })
      },
      updateProductMap(product, calories) {
        if (calories != null && calories !== "" && calories !== undefined) {
          this.current_map.set(product, calories)
          return this.current_map.get(product)
        }
        return null
      }
    }
  }
</script>

<style>
  .basket {
    margin: auto;
    width: 500px;
  }

  .product_name {
    margin-top: 0;
  }

  .calories_count {
    padding: 3px 0;
    text-align: left;
    width: 300px;
    margin: 0 auto;
  }

  .input-group {
    border-radius: 5px;
    border: 2px solid black;
    padding: 2px 4px;
    margin: 0 auto !important;
  }

  .button {
    border-radius: 10px;
    padding: 5px 67px;
    font-size: medium;
    background-color: white;
    color: #000;
    border: solid #fc9d03;
    margin: 15px 0;
  }

  .button:hover {
    border-radius: 10px;
    color: white;
    background-color: #fc9d03;
  }

  .empty_basket {
    color: black;
  }

  .main {
    display: inline-block;
    padding: 10px 50px;
    font-size: medium;
    text-decoration: none;
    background-color: white;
    color: #000;
    border: solid #fc9d03;
    margin: 12px 30px;
    border-radius: 10px;
  }

  .main:hover {
    border-radius: 10px;
    color: white;
    background-color: #fc9d03;
  }

  .calorie_count {
    color: black;
  }

  .number {
    color: #fc9d03;
  }
</style>