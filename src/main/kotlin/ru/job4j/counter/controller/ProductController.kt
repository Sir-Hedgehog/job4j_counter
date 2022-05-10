package ru.job4j.counter.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import ru.job4j.counter.service.ProductService
import java.util.logging.Logger
import javax.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import ru.job4j.counter.entity.Product
import ru.job4j.counter.enum.Category

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.2
 * @since 18.03.2022
 */
@Controller
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    val log = Logger.getLogger(ProductController::class.java.name)

    @GetMapping("/new")
    fun showToSaveProduct(model: Model): String? {

        model["new"] = true
        model["product"] = Product()
        model["categories"] = Category.getContains()

        return "newProduct"
    }

    @PostMapping("/new")
    fun saveProduct(@Valid @ModelAttribute("product") product: Product, @Valid @ModelAttribute("categories") categoryValue: String): String {

        product.category = Category.getEnum(categoryValue)
        productService.saveProduct(product)

        return "redirect:/products/all";
    }

    @PostMapping("/count")
    fun countCalories(products: Map<String, Int>): Int {

        return productService.countCalories(products)
    }

    @GetMapping("/all")
    fun getAllProducts(model: Model): String {

        model["products"] = productService.getAllProducts()
        model["categories"] = Category.getContains()

        return "allProducts"
    }
}