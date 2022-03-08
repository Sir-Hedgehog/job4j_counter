package ru.job4j.counter

import ru.job4j.counter.entity.Category
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.03.2022
 */
@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    @field:NotNull @field:NotEmpty var name: String,
    @field:NotNull var calories: Int,
    @ManyToOne @field:NotNull var category: Category)
