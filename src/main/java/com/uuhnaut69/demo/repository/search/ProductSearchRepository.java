package com.uuhnaut69.demo.repository.search;

import com.uuhnaut69.demo.domain.Product;

import java.util.List;

public interface ProductSearchRepository {

    /**
     * Full text search product document
     *
     * @param searchContent
     * @return List {@link Product}
     */
    List<Product> fullTextSearch(String searchContent);

    /**
     * Autocomplete product name as you type
     *
     * @param searchContent
     * @return List {@link Product}
     */
    List<Product> autocomplete(String searchContent);
}
