package ru.job4j.counter.entity

import lombok.Data
import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.2
 * @since 13.09.2023
 */
@Entity
@Data
@Table(name = "products")
@Indexed
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Field
    @Column(name = "name")
    @field:NotEmpty(message = "Введите наименование товара")
    var name: String,

    @Column(name = "calories")
    @field:NotNull(message = "Введите данные о количестве калорий")
    @field:Min(value = 0, message = "Количество потребленных калорий не может быть ниже 0")
    var calories: Int,

    @Column(name = "category")
    @field:NotEmpty(message = "Выберите категорию товара")
    var category: String) {

    data class Builder(
        var id: Int? = null,
        var name: String? = null,
        var calories: Int? = null,
        var category: String? = null) {

        fun id(value: Int) = apply { id = value }
        fun name(value: String) = apply { name = value }
        fun calories(value: Int) = apply { calories = value }
        fun category(value: String) = apply { category = value }

        fun build(): Product = Product(id ?: 0, name ?: "название", calories ?: 0, category ?: "категория")
    }
}