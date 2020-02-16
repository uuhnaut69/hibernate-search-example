package com.uuhnaut69.demo.service.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.demo.domain.Catalog;
import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.repository.ProductRepository;
import com.uuhnaut69.demo.service.CatalogService;
import com.uuhnaut69.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final EntityManager entityManager;

    private final ProductRepository productRepository;

    private final CatalogService catalogService;

    @Override
    public List<Product> dummyData() {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        List<Catalog> catalogs = catalogService.findAll();
        IntStream.range(0, 5).forEach(i -> products.add(new Product(faker.commerce().productName(), faker.commerce().material(), catalogs, true)));
        return productRepository.saveAll(products);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> fullTextSearch(String text) {
        Query query = getQueryBuilder().bool()
                .must(
                        getQueryBuilder().keyword()
                                .onField("published")
                                .matching(true)
                                .createQuery()
                )
                .must(
                        getQueryBuilder().simpleQueryString().onFields(text, "productName", "description", "catalogs.catalogName")
                                .matching(text).createQuery()
                ).createQuery();
        return getFullTextQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> autocomplete(String text) {
        Query query = getQueryBuilder().bool().must(getQueryBuilder().keyword().onField("published").matching(true).createQuery()).must(getQueryBuilder().keyword().onField("productName").matching(text.toLowerCase()).createQuery()).createQuery();
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
}
