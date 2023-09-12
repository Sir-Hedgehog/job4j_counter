package ru.job4j.counter.service

import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import ru.job4j.counter.entity.Product
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.repository.ProductCheckRepository
import javax.transaction.Transactional
import kotlin.math.roundToInt

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.4
 * @since 12.09.2023
 */
@Service
@Transactional
class ProductService(private val repository: ProductRepository, private val productCheckRepository: ProductCheckRepository) {
    /**
     * Метод сохраняет новый продукт
     * @param product новый продукт
     * @return сохраненный продукт
     */
    fun saveProduct(product: Product): Product {
        product.name = product.name.castToOneType()
        return repository.save(product)
    }

    /**
     * Метод считает калории, исходя из выбранных продуктов
     * @param products карта выбранных продуктов с их фактическим весом
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
        val products: List<Product> = productCheckRepository searchProducts productName.castToOneType()
        if (CollectionUtils.isEmpty(products)) {
            return false
        }
        return true
    }

    /**
     * Метод приводит имя продукта к одному типу
     */
    fun String.castToOneType(): String {
        return this.lowercase().trim().replace("ё", "е")
    }
}