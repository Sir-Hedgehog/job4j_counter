package ru.job4j.counter.repository

import org.springframework.data.repository.CrudRepository
import ru.job4j.counter.entity.Product

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.1
 * @since 16.03.2022
 */
interface ProductRepository : CrudRepository<Product, Long> {
    fun save(product: Product): Product
    fun getByName(name: String): Product
    fun findByName(name: String): Product?
    override fun findAll(): List<Product>
}