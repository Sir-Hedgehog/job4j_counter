<template>
  <label class="category_choice" for="categories">
    <select v-model="selected" class="categories">
      <option selected value>все категории</option>
      <option v-for="category in this.categories" :key="category">
        {{ category }}
      </option>
    </select>
  </label>
  <section class="products">
      <ul>
        <li v-for="product in computed_products" :key="product.id">
          <div class="choice">
            <div class="info">
              <p>{{ product.name }}</p>
              <p>{{ product.calories }} ККал</p>
              <p>{{ product.category }}</p>
            </div>
            <button class="button_to_add" @click="addToBasket(product)"> + </button>
          </div>
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
  .products {
    padding-top: 10px;
    margin-bottom: auto;
  }

  .category_choice {
    padding-top: 8px;
    padding-bottom: 12px;
    background-color: #fc9d03;
    display: block;
  }

  .categories {
    width: 190px;
    height: 30px;
    border: 2px solid black;
    font-size: 16px;
    color: black;
    background-color: white;
    border-radius: 5px;
    font-weight: normal;
  }

  ul {
    padding-left: 0;
  }

  li {
    list-style-type: none;
    display: inline-block;
    width: 158px;
    vertical-align: top;
    margin: 10px;
  }

  .choice {
    height: 140px;
    display: block;
    position: relative;
    background-color: white;
    margin: 5px;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    border: 3px solid #000;
  }

  .info {
    padding: 5px;
  }

  p {
    line-height: 15px;
    margin: 0 !important;
    padding-bottom: 5px;
    font-family: "Lucida Console", "Courier New", monospace;
    font-size: small;
  }

  .button_to_add {
    position: absolute;
    bottom: 0;
    transform: translateX(-50%);
    padding: 5px 66px;
    font-size: larger;
    border: none;
    background-color: white;
    color: #000;
    border-radius: 0 0 10px 10px;
    border-top: solid #fc9d03;
  }

  .button_to_add:hover {
    border-radius: 0 0 7px 7px;
    color: white;
    background-color: #fc9d03;
  }
</style>