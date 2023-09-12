package ru.job4j.counter.service

import org.hibernate.search.jpa.Search
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.09.2023
 */
@Component
@Transactional
class BuildSearchService(@PersistenceContext val entityManager: EntityManager) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        try {
            val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
            fullTextEntityManager.createIndexer().startAndWait()
        } catch (e: InterruptedException) {
            println("Error occurred trying to build the search index: $e")
        }
    }
}