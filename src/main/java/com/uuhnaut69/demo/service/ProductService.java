package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.domain.Product;

import java.util.List;

public interface ProductService {

    /**
     * Generate dummy product data
     *
     * @return List {@link Product
     */
    List<Product> dummyData();

    /**
     * Full text search product
     *
     * @param text
     * @return List {@link Product}
     */
    List<Product> fullTextSearch(String text);

    /**
     * Autocomplete product name
     *
     * @param text
     * @return List {@link Product
     */
    List<Product> autocomplete(String text);

}
