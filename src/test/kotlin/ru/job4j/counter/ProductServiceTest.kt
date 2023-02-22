package ru.job4j.counter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import ru.job4j.counter.entity.Product
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.service.ProductService

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.1
 * @since 22.02.2023
 */
@ExtendWith(MockitoExtension::class)
class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @InjectMocks
    private lateinit var productService: ProductService

    @Test
    fun `Count calories`() {
        val products = mapOf("Слива" to 200, "Перец" to 50, "Хурма" to 150)

        val first = Product(1, "слива", 49, "фрукты")
        val second = Product(2, "перец", 26, "овощи и зелень")
        val third = Product(3, "хурма", 67, "фрукты")

        given(productRepository.findByName("слива")).willReturn(first)
        given(productRepository.findByName("перец")).willReturn(second)
        given(productRepository.findByName("хурма")).willReturn(third)

        val expected = 212
        assertEquals(expected, productService.countCalories(products))
    }
}