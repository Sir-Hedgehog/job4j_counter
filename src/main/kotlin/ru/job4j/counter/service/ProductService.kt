package ru.job4j.counter.service

import org.springframework.stereotype.Service
import ru.job4j.counter.entity.Product
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.utils.Utils
import kotlin.math.roundToInt

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.2
 * @since 22.02.2023
 */
@Service
class ProductService(private val repository: ProductRepository) {
    /**
     * Метод сохраняет новый продукт
     * @param product - новый продукт
     * @return сохраненный продукт
     */
    fun saveProduct(product: Product): Product {
        product.name = Utils.castToOneType(product.name)
        return repository.save(product)
    }

    /**
     * Метод считает калории, исходя из выбранных продуктов
     * @param products - карта выбранных продуктов с их фактическим весом
     * @return количество калорий
     */
    fun countCalories(products: Map<String, Int>): Int {
        if (products.isEmpty()) {
            return 0
        }
        return products
            .map { repository.getByName(it.key.lowercase()).calories * it.value * 0.01 }
            .reduce { sum, element -> sum + element }
            .roundToInt()
    }

    /**
     * Метод выдает все продукты, сохраненные в базе
     * @return все продукты, сохраненные в базе
     */
    fun getAllProducts(): List<Product> {
        return repository.findAll()
    }

    /**
     * Метод осуществляет поиск в базе продукта по имени
     * @return найден ли продукт в базе
     */
    fun checkProductByName(productName: String): Boolean {
        val normalizedName = Utils.castToOneType(productName)
        val product = repository.findByName(normalizedName)
        return product != null
    }
}