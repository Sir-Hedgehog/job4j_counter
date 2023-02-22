<template>
  <div>
    <h1 class="text-center">Корзина</h1>
  </div>
  <div v-if="!submitted">
      <div v-for="[product] in this.elect_products" :key="product">
        <label>{{ product }}</label> :
        <input type="number" min="0" id="product_calories" :value=0 @input="updateProductMap(product, $event.target.value)" required/>
      </div>
      <button @click="countCalories()" class="">Посчитать калории</button>
  </div>
  <div v-else>
    <h4>Количество калорий за сегодня: {{ this.result }}</h4>
    <h6><router-link to="/products/all">Вернуться на главную</router-link></h6>
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
        if (calories != null && calories !== "" && calories !== undefined && calories !== 0) {
          this.current_map.set(product, calories)
          return this.current_map.get(product)
        }
        return null
      }
    }
  }
</script>

<style>

</style>