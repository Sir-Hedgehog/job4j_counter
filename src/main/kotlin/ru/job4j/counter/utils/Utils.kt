package ru.job4j.counter.utils

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.03.2023
 */
class Utils {
    /**
     * Метод приводит имя продукта к одному типу
     * @param name имя продукта
     */
    companion object {
        fun castToOneType(name: String): String {
            return name.lowercase().trim().replace("ё", "е")
        }
    }
}