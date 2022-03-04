package ru.job4j.counter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 04.03.2022
 */
@SpringBootApplication
class ProductApplication

/**
 * Метод запуска приложения по считыванию калорий
 */
fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}