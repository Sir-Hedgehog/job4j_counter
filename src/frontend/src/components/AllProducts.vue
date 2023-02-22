<template>
  <div>
    <h1 class="text-center">Список продуктов</h1>
  </div>
  <nav>
    <label for="categories">
      <select v-model="selected" id="categories">
        <option selected value>все категории</option>
        <option v-for="category in this.categories" :key="category">
          {{ category }}
        </option>
      </select>
    </label>
  </nav>
  <section class="products">
      <ul>
        <li v-for="product in computed_products" :key="product.id">
          <span>{{ product.name }}</span>
          <span>{{ product.calories }}</span>
          <span>{{ product.category }}</span>
          <button @click="addToBasket(product)" class=""> + </button>
        </li>
      </ul>
  </section>
</template>

<script>
import ProductService from "@/services/ProductService";
import AllCategories from "@/components/AllCategories";
import {ref} from "vue";

const elect_products_map = ref(new Map)

export default {
  name: 'AllProducts',
  data() {
    return {
      products : [],
      selected: '',
      categories: AllCategories.data().categories,
      elect_products: elect_products_map
    }
  },
  methods: {
    getProducts() {
      ProductService.getAllProducts().then((response) => {
        this.products = response.data
      })
    },
    addToBasket(product) {
      this.elect_products.set(product.name, 0)
      console.log(this.elect_products)
    }
  },
  computed: {
    computed_products: function() {
      let filter = this.selected
      return this.products.filter(function(product) {
        let filtered = true
        if (filter === "все категории") {
          return true
        }
        if (filter && filter.length > 0) {
          filtered = product.category === filter
        }
        return filtered
      })
    }
  },
  created() {
    this.getProducts()
  }
}
</script>

<style>

</style>