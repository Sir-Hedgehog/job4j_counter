package ru.job4j.counter.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.job4j.counter.entity.Product

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.2
 * @since 12.09.2023
 */

interface ProductRepository : JpaRepository<Product, Long> {
    infix fun save(product: Product): Product
    infix fun getByName(name: String): Product
    override fun findAll(): List<Product>
}