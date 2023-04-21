<template>
  <div class="basket">
    <div v-if="!submitted">
      <div v-if="elect_products.size !== 0">
        <Form @submit="countCalories" :initial-values="initialData" :validation-schema="schema">
          <FieldArray name="products">
            <div class="grams" v-for="([name], index) in this.elect_products" :key="name">
              <label :for="name" class="product_name">{{name}} (в граммах)</label>
              <Field type="number" :name="`products[${index}].weight`" class="input-group" :id="name" :value=0 @input="updateProductMap(name, $event.target.value)"
                     onkeydown="return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" required/>
              <ErrorMessage class="error" :name="`products[${index}].weight`" />
            </div>
            <button class="button" type="submit">Посчитать калории</button>
          </FieldArray>
        </Form>
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
  import AllProducts from "@/components/AllProducts"
  import ProductService from "@/services/ProductService"
  import { Form, Field, FieldArray, ErrorMessage } from 'vee-validate'
  import * as yup from 'yup'

  export default {
    name: 'ProductBasket',
    components: {
      Form,
      Field,
      FieldArray,
      ErrorMessage
    },
    data() {
      const initialData = {
        products: [
          {
            weight: 0,
          },
        ],
      };
      const schema = yup.object().shape({
        products: yup.array().of(
          yup.object().shape({
            weight: yup.number()
                  .transform((value) => isNaN(value) ? undefined : value)
                  .required('Введите калории числовым значением!')
                  .min(0, "Минимальное значение: 0")
                  .max(100000, "Максимальное значение: 100000")
            })
        )
     });
      return {
        elect_products: AllProducts.data().elect_products,
        submitted: false,
        current_map: new Map,
        result: 0,
        schema,
        initialData
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
      updateProductMap(product, grams) {
        if (grams != null && grams !== "" && grams !== undefined) {
          this.current_map.set(product, grams)
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

  .grams {
    padding: 3px;
    text-align: left;
    width: 450px;
    margin: 0 auto;
  }

  .input-group {
    border-radius: 5px;
    border: 2px solid black;
    padding: 2px 4px;
    margin: 0 auto !important;
  }

  .error {
    text-align: center;
    font-size: small;
    color: #f55a1d;
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