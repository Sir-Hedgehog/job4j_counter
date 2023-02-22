<template>
  <div>
    <h1 class="text-center">Новый продукт</h1>
  </div>
  <div v-if="!submitted">
    <div class="form-group">
      <label for="product_name">Наименование</label>
      <input type="text" class="" id="product_name" v-model="product.name"/>
    </div>
    <div class="form-group">
      <label for="product_calories">Калорийность в 100г продукта</label>
      <input type="text" class="" id="product_calories" v-model="product.calories"/>
    </div>
    <div class="form-group">
      <label for="categories">Категория продукта
        <select v-model="product.categories" id="categories">
          <option disabled selected value>---</option>
          <option v-for="category in this.categoryEnum" :key="category">
            {{ category }}
          </option>
        </select>
      </label>
    </div>
    <button @click="saveProduct" class="">Создать</button>
  </div>
  <div v-else>
    <h4>Продукт "{{ this.product.name.toLowerCase() }}" успешно создан!</h4>
    <h6>Теперь вы можете добавить его в корзину калорий!</h6>
  </div>
</template>

<script>
  import AllCategories from "@/components/AllCategories";
  import ProductService from "@/services/ProductService";

  export default {
    name: 'NewProduct',
    data() {
      return {
        categoryEnum: AllCategories.data().categories,
        product: {},
        submitted: false
      }
    },
    methods: {
      saveProduct() {
        let current = {
          id: null,
          name: this.product.name,
          calories: this.product.calories,
          category: this.product.categories
        };
        ProductService.addNewProduct(current).then(response => {
            this.product.id = response.data.id;
            this.submitted = true;
        })
      },
      getSavedProduct() {
        this.submitted = false;
        this.product = {};
      }
    }
  }
</script>

<style>

</style>