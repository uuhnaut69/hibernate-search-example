package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.domain.Product;

import java.util.List;

public interface ProductService {

    /**
     * Generate dummy data
     *
     * @return List {@link Product
     */
    List<Product> dummyData();

    /**
     * Full text search demo
     *
     * @param text
     * @return List {@link Product}
     */
    List<Product> fullTextSearch(String text);

    /**
     * Autocomplete demo
     *
     * @param text
     * @return List {@link Product
     */
    List<Product> autocomplete(String text);

}
