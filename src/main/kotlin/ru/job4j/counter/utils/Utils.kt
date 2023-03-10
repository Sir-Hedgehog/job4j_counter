package ru.job4j.counter.utils

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