package ru.job4j.counter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import ru.job4j.counter.entity.Product
import ru.job4j.counter.repository.ProductFullTextRepository
import ru.job4j.counter.repository.ProductRepository
import ru.job4j.counter.service.ProductService

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.3
 * @since 13.09.2023
 */
@ExtendWith(MockitoExtension::class)
class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var productFullTextRepository: ProductFullTextRepository

    @InjectMocks
    private lateinit var productService: ProductService

    @Test
    fun `Count calories`() {
        val products = mapOf("Слива" to 200, "Перец" to 50, "Хурма" to 150)

        val first = Product.Builder().id(1).name("слива").calories(49).category("фрукты").build()
        val second = Product.Builder().id(2).name("перец").calories(26).category("овощи и зелень").build()
        val third = Product.Builder().id(3).name("хурма").calories(67).category("фрукты").build()

        given(productRepository getByName "слива").willReturn(first)
        given(productRepository getByName "перец").willReturn(second)
        given(productRepository getByName "хурма").willReturn(third)

        val expected = 212
        assertEquals(expected, productService.countCalories(products))
    }
}