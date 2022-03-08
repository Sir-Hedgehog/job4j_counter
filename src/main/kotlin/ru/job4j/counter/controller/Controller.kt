package ru.job4j.counter.controller

import org.springframework.web.bind.annotation.*
import ru.job4j.counter.Product
import ru.job4j.counter.service.ProductService
import java.util.logging.Logger
import javax.validation.Valid

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.03.2022
 */
@RestController
@RequestMapping("/products")
class Controller(private val service: ProductService) {
    val log = Logger.getLogger(Controller::class.java.name)

    @PostMapping("/new")
    fun saveNewProduct(@Valid @RequestBody product: Product): Product {
        return service.saveProduct(product)
    }

    @PostMapping("/count")
    fun countCalories(@RequestBody products: Map<String, Int>): Int {
        return service.countCalories(products)
    }

    @PostMapping("/all")
    fun countCalories(): List<Product> {
        return service.getAllProducts()
    }
}