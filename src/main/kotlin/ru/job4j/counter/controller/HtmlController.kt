package ru.job4j.counter.controller

import org.springframework.web.bind.annotation.*
import ru.job4j.counter.Product
import ru.job4j.counter.service.ProductService
import java.util.logging.Logger
import javax.validation.Valid

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.03.2022
 */
@RestController
@RequestMapping("/counter")
class HtmlController(private val service: ProductService) {
    val log = Logger.getLogger(HtmlController::class.java.name)

    @PostMapping("/new_product")
    fun saveNewProduct(@Valid @RequestBody product: Product): Product {
        return service.saveProduct(product)
    }

    @PostMapping("/sum")
    fun countCalories(@RequestBody products: Map<String, Int>): Int {
        return service.countCalories(products)
    }
}