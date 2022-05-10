package ru.job4j.counter.entity

import lombok.Data
import ru.job4j.counter.enum.Category
import javax.persistence.*
import javax.validation.constraints.NotEmpty

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.03.2022
 */
@Entity
@Data
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = 0,

    @NotEmpty
    @Column(name = "name")
    var name: String = "",

    @Column(name = "calories")
    var calories: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    var category: Category = Category.MILK
)
