<template>
  <span></span>
</template>

<script>
  export default {
    name: 'AllCategories',
    data() {
      return {
        categories: Categories,
      }
    }
  }
  const Categories = {
    MILK: 'молочные продукты',
    EGGS: 'яйца',
    CORN: 'зерновые продукты',
    BEANS: 'бобовые продукты',
    NUTS_AND_SEEDS: 'орехи и семена',
    VEGETABLES_AND_GREEN: 'овощи и зелень',
    MEAT: 'мясные продукты',
    FRUITS: 'фрукты',
    BERRIES: 'ягоды',
    MUSHROOMS: 'грибы',
    JUICES: 'соки',
    SEAFOOD: 'морепродукты',
    ALCOHOL: 'спиртное'
  };
</script>

<style>

</style>