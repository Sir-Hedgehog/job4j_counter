package ru.job4j.counter.service

import org.springframework.stereotype.Service
import ru.job4j.counter.entity.Product
import ru.job4j.counter.repository.ProductRepository
import kotlin.math.roundToInt

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.3
 * @since 21.07.2023
 */
@Service
class ProductService(private val repository: ProductRepository) {
    /**
     * Метод сохраняет новый продукт
     * @param product - новый продукт
     * @return сохраненный продукт
     */
    fun saveProduct(product: Product): Product {
        product.name = product.name.castToOneType()
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
        val normalizedName = productName.castToOneType()
        val words: List<String> = normalizedName.split(" ")
        return findByNameUsingPermutations(words)
    }

    /**
     * Метод совершает поиск продукта по базе с учетом перестановки введенных слов
     * @param words список введенных слов
     * @return найден ли продукт в базе
     */
    fun findByNameUsingPermutations(words: List<String>): Boolean {
        var result = false
        val factorials = IntArray(words.size + 1)
        factorials[0] = 1
        for (i in 1..words.size) {
            factorials[i] = factorials[i - 1] * i
        }
        for (i in 0 until factorials[words.size]) {
            var currentPermutation = ""
            var temp = words
            var positionCode = i
            for (position in words.size downTo 1) {
                val selected = positionCode / factorials[position - 1]
                currentPermutation += temp[selected]
                if (position > 1) {
                    currentPermutation += " "
                }
                positionCode %= factorials[position - 1]
                temp = temp.subList(0, selected) + temp.subList(selected + 1, temp.size)
            }
            val product = repository.findByName(currentPermutation)
            if (product != null) {
                result = true
                break
            }
        }
        return result
    }

    /**
     * Метод приводит имя продукта к одному типу
     */
    fun String.castToOneType(): String {
        return this.lowercase().trim().replace("ё", "е")
    }
}