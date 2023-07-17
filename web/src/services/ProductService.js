import axios from 'axios'

const PRODUCT_API_BASE_URL = 'http://localhost:8090/products'

class ProductService {
    getAllProducts() {
        return axios.get(PRODUCT_API_BASE_URL + "/all")
    }
    addNewProduct(product) {
        return axios.post(PRODUCT_API_BASE_URL + "/new", product)
    }
    countFromBasket(elect_products) {
        return axios.post(PRODUCT_API_BASE_URL + "/count", Object.fromEntries(elect_products))
    }
    checkProductByName(productName) {
        let result = axios.get(PRODUCT_API_BASE_URL + "/check", {
            params: {
                productName: productName
            }
        })
        console.log("axios result " + result)
        return result
    }
}

export default new ProductService()