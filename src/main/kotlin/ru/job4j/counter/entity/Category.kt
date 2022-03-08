package ru.job4j.counter.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 08.03.2022
 */
@Entity
@Table(name = "categories")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    @field:NotNull var category: String)