package ru.job4j.counter.repository

import org.apache.lucene.search.Query
import org.hibernate.search.jpa.FullTextQuery
import org.hibernate.search.jpa.Search
import org.springframework.stereotype.Repository
import ru.job4j.counter.entity.Product
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.09.2023
 */
@Repository
@Transactional
class ProductCheckRepository(@PersistenceContext val entityManager: EntityManager) {
    infix fun searchProducts(normalizedName: String): List<Product> {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        val queryBuilder = fullTextEntityManager.searchFactory.buildQueryBuilder().forEntity(Product::class.java).get()

        val queryList: MutableList<Query?> = ArrayList<Query?>()
        var query: Query? = null
        for (keyword in normalizedName.split(" ")) {
            query = queryBuilder.keyword().onField("name").matching(keyword).createQuery()
            queryList += query
        }

        val fullTextQuery: FullTextQuery = fullTextEntityManager.createFullTextQuery(query, Product::class.java)
        return fullTextQuery.resultList.map { result -> result as Product }.filter { result -> result.name.length == normalizedName.length }.toList()
    }
}