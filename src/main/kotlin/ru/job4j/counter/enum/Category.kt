package ru.job4j.counter.enum

enum class Category(private val category: String) {

    MILK("молочные продукты"),
    EGGS("яйца"),
    CORN("зерновые продукты"),
    BEANS("бобовые продукты"),
    NUTS_AND_SEEDS("орехи и семена"),
    VEGETABLES_AND_GREEN("овощи и зелень"),
    MEAT("мясные продукты"),
    FRUITS("фрукты"),
    BERRIES("ягоды"),
    MUSHROOMS("грибы"),
    JUICES("соки"),
    SEAFOOD("морепродукты");

    override fun toString(): String {
        return "Category(category='$category')"
    }

    companion object {
        fun getEnum(value: String): Category {
            return values().find { category -> category.category == value }!!
        }

        fun getContains(): List<String> {
            return values().map { category -> category.category }
        }
    }
}