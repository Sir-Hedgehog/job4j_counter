package ru.job4j.counter.entity

import lombok.Data
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.1
 * @since 10.03.2023
 */
@Entity
@Data
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(name = "name")
    @field:NotEmpty(message = "Введите наименование товара")
    var name: String,

    @Column(name = "calories")
    @field:NotNull(message = "Введите данные о количестве калорий")
    @field:Min(value = 0, message = "Количество потребленных калорий не может быть ниже 0")
    var calories: Int,

    @Column(name = "category")
    @field:NotEmpty(message = "Выберите категорию товара")
    var category: String
)