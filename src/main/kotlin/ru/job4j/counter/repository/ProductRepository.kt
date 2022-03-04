package ru.job4j.counter.repository

import org.springframework.data.repository.CrudRepository
import ru.job4j.counter.Product

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.03.2022
 */
interface ProductRepository : CrudRepository<Product, Long> {
    fun save(product: Product): Product
    fun findByName(name: String): Product
}