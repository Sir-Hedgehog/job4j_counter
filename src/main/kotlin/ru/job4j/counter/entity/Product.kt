package ru.job4j.counter

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    @field:NotNull @field:NotEmpty var name: String,
    @field:NotNull var calories: Int)