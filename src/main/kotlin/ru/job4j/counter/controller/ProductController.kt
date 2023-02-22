package ru.job4j.counter.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.job4j.counter.entity.Product
import ru.job4j.counter.service.ProductService
import java.util.logging.Logger

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.2
 * @since 22.02.2023
 */
@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:8081")
class ProductController(private val productService: ProductService) {
    val log = Logger.getLogger(ProductController::class.java.name)

    @PostMapping("/new")
    fun saveProduct(/*@Valid*/ @RequestBody product: Product): ResponseEntity<Product> {
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
}