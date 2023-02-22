package ru.job4j.counter.entity

import lombok.Data
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.1
 * @since 22.02.2023
 */
@Entity
@Data
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    var id: Int,

    @Column(name = "name")
    var name: String,

    @Column(name = "calories")
    @NotNull(message = "Введите данных о количестве калорий")
    @Min(value = 0, message = "Количество потребленных калорий не может быть ниже 0")
    var calories: Int,

    @Column(name = "category")
    @NotEmpty
    var category: String
)
