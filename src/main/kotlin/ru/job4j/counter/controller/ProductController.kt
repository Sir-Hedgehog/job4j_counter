package ru.job4j.counter.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.job4j.counter.entity.Product
import ru.job4j.counter.service.ProductService
import javax.validation.Valid

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.2
 * @since 10.03.2023
 */
@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:8081")
class ProductController(private val productService: ProductService) {

    @PostMapping("/new")
    fun saveProduct(@Valid @RequestBody product: Product): ResponseEntity<Product> {
        val savedProduct = productService.saveProduct(product)
        return ResponseEntity.ok(savedProduct)
    }

    @PostMapping("/count")
    fun countCalories(@RequestBody products: Map<String, Int>): ResponseEntity<Int> {
        val calories = productService.countCalories(products)
        return ResponseEntity.ok(calories)
    }

    @GetMapping("/all")
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val allProducts = productService.getAllProducts()
        return ResponseEntity.ok(allProducts)
    }

    @GetMapping("/check")
    fun checkProductByName(@RequestParam productName: String): ResponseEntity<Boolean> {
        val checkResult = productService.checkProductByName(productName)
        return ResponseEntity.ok(checkResult)
    }
}