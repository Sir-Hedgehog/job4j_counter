<template>
  <div>
    <h1 class="text-center">Новый продукт</h1>
  </div>
  <div v-if="!submitted">
    <Form @submit="saveProduct" :validation-schema="schema" v-slot="{ errors }">
      <span v-if="!checkProduct">Данный продукт уже есть в базе! Вы можете его найти в главной странице приложения.</span>
      <div class="form-group">
        <label for="name">Наименование</label>
        <Field name="name" type="text" class="" id="name" :class="{ 'is-invalid': errors.name }"/>
        <div class="">{{ errors.name }}</div>
      </div>
      <div class="form-group">
        <label for="calories">Калорийность в 100г продукта</label>
        <Field name="calories" type="number" class="" id="calories" :class="{ 'is-invalid': errors.calories }"
               onkeydown="return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" />
        <div class="">{{ errors.calories }}</div>
      </div>
      <div class="form-group">
        <label for="categories">Категория продукта</label>
        <Field name="categories" id="categories" as="select" class="" :class="{ 'is-invalid': errors.categories }">
          <option disabled selected value>---</option>
          <option :value="category" v-for="category in this.categoryEnum" :key="category">
            {{ category }}
          </option>
        </Field>
        <div class="">{{ errors.categories }}</div>
      </div>
      <button type="submit" class="">Создать</button>
    </Form>
  </div>
  <div v-else>
    <h4>Продукт "{{ this.product_name.toLowerCase() }}" успешно создан!</h4>
    <h6>Теперь вы можете добавить его в корзину калорий!</h6>
  </div>
</template>

<script>
  import AllCategories from "@/components/AllCategories"
  import ProductService from "@/services/ProductService"
  import { Form, Field } from 'vee-validate'
  import * as yup from 'yup'

  export default {
    name: 'NewProduct',
    components: {
      Form,
      Field
    },
    data() {
      const schema = yup.object().shape({
        name: yup.string()
            .required('Введите имя продукта!')
            .matches(/^([а-яёА-ЯЁ\s\\-])+$/, "Доступно к использованию буквы, дефис и пробел!"),
        calories: yup.number()
            .required('Введите калории числовым значением!')
            .min(1, "Минимальное количество символов: 1")
            .max(9999999, "Максимальное количество символов: 7"),
        categories: yup.string()
            .required("Выберите категорию продукта!")
      });
      return {
        categoryEnum: Object.values(AllCategories.data().categories),
        submitted: false,
        product_name: "",
        checkProduct: true,
        schema
      }
    },
    methods: {
      saveProduct(values) {
        this.product_name = values.name
        let current = {
          id: null,
          name: values.name,
          calories: values.calories,
          category: values.categories
        };
        ProductService.checkProductByName(this.product_name).then(response => {
          if (!response.data) {
            ProductService.addNewProduct(current).then(response => {
              current.id = response.data.id
              this.submitted = true
            })
          } else {
            this.checkProduct = false
          }
        })
      },
      getSavedProduct() {
        this.checkProduct = true
        this.submitted = false
      }
    }
  }
</script>

<style>

</style>