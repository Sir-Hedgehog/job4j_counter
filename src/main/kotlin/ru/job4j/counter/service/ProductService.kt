package ru.job4j.counter.service

import org.springframework.stereotype.Service
import ru.job4j.counter.Product
import ru.job4j.counter.repository.ProductRepository
import kotlin.math.roundToInt

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.1
 * @since 16.03.2022
 */
@Service
class ProductService(private val repository: ProductRepository) {

    /**
     * Метод сохраняет новый продукт
     * @param product - новый продукт
     * @return - сохраненный продукт
     */
    fun saveProduct(product: Product): Product {
        product.name = product.name.lowercase()
        return repository.save(product)
    }

    /**
     * Метод считает калории, исходя из выбранных продуктов
     * @param products - карта выбранных продуктов с их фактическим весом
     * @return - количество калорий
     */
    fun countCalories(products: Map<String, Int>): Int {
        return products
            .map { repository.findByName(it.key.lowercase()).calories * it.value * 0.01 }
            .reduce { sum, element -> sum + element }
            .roundToInt()
    }

    /**
     * Метод выдает все продукты, сохраненные в базе
     * @return - все продукты, сохраненные в базе
     */
    fun getAllProducts(): List<Product> {
        return repository.findAll()
    }
}