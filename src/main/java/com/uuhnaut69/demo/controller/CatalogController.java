package com.uuhnaut69.demo.controller;

import com.uuhnaut69.demo.domain.model.Catalog;
import com.uuhnaut69.demo.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    /**
     * Find all catalogs
     *
     * @return List {@link Catalog}
     */
    @GetMapping
    public List<Catalog> getCatalogs() {
        return catalogService.findAll();
    }

    /**
     * Generate dummy catalog data
     *
     * @return
     */
    @PostMapping
    public List<Catalog> dummyCatalogData() {
        return catalogService.dummyData();
    }

    /**
     * Random update some catalog
     */
    @PutMapping
    public void dummyUpdateCatalogData() {
        catalogService.randomUpdate();
    }
}
