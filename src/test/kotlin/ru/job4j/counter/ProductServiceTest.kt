package ru.job4j.counter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.service.ProductService

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.03.2022
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

        given(productRepository.findByName("слива")).willReturn(Product(1, "слива", 49))
        given(productRepository.findByName("перец")).willReturn(Product(2, "перец", 26))
        given(productRepository.findByName("хурма")).willReturn(Product(3, "хурма", 67))

        val expected = 212
        assertEquals(expected, productService.countCalories(products))
    }
}