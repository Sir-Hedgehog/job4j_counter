const { createApp } = Vue

const app = createApp({
    template: '<product-list :products="products" />',
    data() {
        return {
            products: []
        }
    }
});

app.component('product-list', {
    props: ['products'],
    template: '<div v-for="product in products">{{ product.name }}</div>',
});

app.mount('#app')


