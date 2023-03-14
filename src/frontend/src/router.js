import { createWebHistory, createRouter } from "vue-router"

const routes =  [
    {
        path: "/",
        name: "start",
        component: () => import("./components/AllProducts")
    },
    {
        path: "/products/all",
        name: "products",
        component: () => import("./components/AllProducts")
    },
    {
        path: "/products/new",
        name: "new",
        component: () => import("./components/NewProduct")
    },
    {
        path: "/products/count",
        name: "count",
        component: () => import("./components/ProductsBasket")
    },
    {
        path: "/products/check",
        name: "check",
        component: () => import("./components/NewProduct")
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
