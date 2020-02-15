package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.domain.Catalog;

import java.util.List;
import java.util.UUID;

public interface CatalogService {

    /**
     * Get set catalog that has id in list id param
     *
     * @param uuids
     * @return List {@link Catalog}
     */
    List<Catalog> findByIdIn(List<UUID> uuids);

    /**
     * Generate dummy data
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

}
