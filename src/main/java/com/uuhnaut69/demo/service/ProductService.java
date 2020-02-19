package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.domain.model.Product;
import javassist.NotFoundException;
import org.hibernate.search.query.facet.Facet;

import java.util.List;
import java.util.UUID;

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

    /**
     * Toggle status of product
     *
     * @param id
     */
    void toggleStatusProduct(UUID id) throws NotFoundException;

    /**
     * Delete product
     *
     * @param id
     * @throws NotFoundException
     */
    void delete(UUID id) throws NotFoundException;

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
