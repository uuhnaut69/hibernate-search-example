package com.uuhnaut69.demo.repository.search;

import com.uuhnaut69.demo.domain.model.Product;
import org.hibernate.search.query.facet.Facet;

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


    /**
     * Faceting product material
     *
     * @return List {@link Facet}
     */
    List<Facet> facetingProductMaterial();

    /**
     * Faceting product price
     *
     * @param fromPrice
     * @param toPrice
     * @return List {@link Facet}
     */
    List<Facet> facetingProductPrice(double fromPrice, double toPrice);

}
