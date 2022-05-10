package ru.job4j.counter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import ru.job4j.counter.entity.Product
import ru.job4j.counter.enum.Category
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.service.ProductService

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.03.2022
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

        val first = Product(1, "слива", 49, Category.FRUITS)
        val second = Product(2, "перец", 26, Category.VEGETABLES_AND_GREEN)
        val third = Product(3, "хурма", 67, Category.FRUITS)

        given(productRepository.findByName("слива")).willReturn(first)
        given(productRepository.findByName("перец")).willReturn(second)
        given(productRepository.findByName("хурма")).willReturn(third)

        val expected = 212
        assertEquals(expected, productService.countCalories(products))
    }
}