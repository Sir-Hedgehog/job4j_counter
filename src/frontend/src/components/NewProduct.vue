<template>
  <div class="data">
    <div v-if="!submitted">
      <Form @submit="saveProduct" :validation-schema="schema" v-slot="{ errors }">
        <span v-if="!checkProduct">Данный продукт уже есть в базе! Вы можете его найти в главной странице приложения.</span>
        <div class="form-group">
          <Field name="name" type="text" class="input-group" id="name" :class="{ 'is-invalid': errors.name }" placeholder="Наименование"/>
          <div class="error">{{ errors.name }}</div>
        </div>
        <div class="form-group">
          <Field name="calories" type="number" class="input-group" id="calories" :class="{ 'is-invalid': errors.calories }" placeholder="Калорийность в 100г продукта"
                 onkeydown="return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" />
          <div class="error">{{ errors.calories }}</div>
        </div>
        <div class="form-group">
          <Field name="categories" as="select" id="categories" :class="{ 'is-invalid': errors.categories }">
            <option id="default_category" disabled selected value>Категория</option>
            <option :value="category" v-for="category in this.categoryEnum" :key="category">
              {{ category }}
            </option>
          </Field>
          <div class="error">{{ errors.categories }}</div>
        </div>
        <button class="button" type="submit">Создать</button>
      </Form>
    </div>
    <div v-else>
      <h4>Продукт "{{ this.product_name.toLowerCase() }}" успешно создан!</h4>
      <h6>Теперь вы можете добавить его в корзину калорий!</h6>
    </div>
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
            .min(2, 'Введите не менее 2 символов')
            .max(30, 'Введите не более 40 символов')
            .matches(/^([а-яёА-ЯЁ\s\\-])+$/, "Доступны к использованию буквы кириллицей, дефис и пробел!"),
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
  .data {
    margin: auto;
  }

  .error {
    text-align: center;
    font-size: small;
    color: #f55a1d;
  }

  .form-group {
    padding: 6px 0;
    text-align: left;
    width: 400px;
  }

  .input-group {
    border-radius: 5px;
    border: 2px solid black;
    padding: 2px 4px;
  }

  .input-group::placeholder {
    opacity: 0.5;
  }

  #categories {
    width: 400px;
    height: 30px;
    border: 2px solid black;
    font-size: 16px;
    background-color: white;
    border-radius: 5px;
  }

  #default_category {
    color: rgba(0, 0, 0, 0.2);
  }

  #default_category::placeholder {
    opacity: 0.5;
  }

  .button {
    border-radius: 10px;
    padding: 5px 67px;
    font-size: medium;
    background-color: white;
    color: #000;
    border: solid #fc9d03;
    margin: 12px 0;
  }

  .button:hover {
    border-radius: 10px;
    color: white;
    background-color: #fc9d03;
  }

  h4 {
    color: #fc9d03;
  }

  h6 {
    color: black;
  }
</style>