package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.domain.Catalog;

import java.util.List;

public interface CatalogService {

    /**
     * Generate dummy catalog data
     *
     * @return List {@link Catalog}
     */
    List<Catalog> dummyData();

    /**
     * Get all catalogs
     *
     * @return List {@link Catalog}
     */
    List<Catalog> findAll();

    /**
     * Random update catalog
     */
    void randomUpdate();

}
