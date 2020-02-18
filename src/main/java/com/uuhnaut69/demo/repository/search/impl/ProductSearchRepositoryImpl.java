package com.uuhnaut69.demo.repository.search.impl;

import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.repository.search.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    private static final String PRODUCT_NAME = "productName";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> fullTextSearch(String searchContent) {
        Query query = getQueryBuilder().bool()
                .must(
                        getQueryBuilder().keyword()
                                .onField("published")
                                .matching(true)
                                .createQuery()
                )
                .must(
                        getQueryBuilder().simpleQueryString().onFields(PRODUCT_NAME, "description", "catalogs.catalogName")
                                .withAndAsDefaultOperator().matching(searchContent).createQuery()
                ).createQuery();
        return getFullTextQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> autocomplete(String searchContent) {
        Query query = getAutocompleteQueryBuilder().bool().must(getQueryBuilder().keyword().onField("published").matching(true).createQuery()).must(getQueryBuilder().simpleQueryString().onField(PRODUCT_NAME).withAndAsDefaultOperator().matching(searchContent).createQuery()).createQuery();
        return getFullTextQuery(query).getResultList();
    }


    /**
     * Get full text query
     *
     * @param query
     * @return FullTextQuery
     */
    private FullTextQuery getFullTextQuery(Query query) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(query, Product.class);
    }


    /**
     * Get query builder
     *
     * @return QueryBuilder
     */
    private QueryBuilder getQueryBuilder() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();
    }

    /**
     * Get autocomplete query builder
     *
     * @return QueryBuilder
     */
    private QueryBuilder getAutocompleteQueryBuilder() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class).overridesForField(PRODUCT_NAME, "edgeNgramQuery")
                .get();
    }
}
