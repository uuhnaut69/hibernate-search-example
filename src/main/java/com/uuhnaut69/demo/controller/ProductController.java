package com.uuhnaut69.demo.controller;

import com.uuhnaut69.demo.domain.model.Product;
import com.uuhnaut69.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.query.facet.Facet;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Full text query product
     *
     * @param text
     * @return List {@link Product}
     */
    @GetMapping
    public List<Product> fullTextQuery(@RequestParam(name = "text", defaultValue = "") String text) {
        return productService.fullTextSearch(text);
    }

    /**
     * Autocomplete product name
     *
     * @param text
     * @return List {@link Product}
     */
    @GetMapping("/autocomplete")
    public List<Product> autocomplete(@RequestParam(name = "text", defaultValue = "") String text) {
        return productService.autocomplete(text);
    }

    /**
     * Aggregation product material
     *
     * @return List {@link Facet}
     */
    @GetMapping("/group-material")
    public List<Facet> getFacetingProductMaterial() {
        return productService.facetingProductMaterial();
    }

    /**
     * Aggregation product price
     *
     * @param fromPrice
     * @param toPrice
     * @return List {@link Facet}
     */
    @GetMapping("/group-price")
    public List<Facet> getFacetingProductPrice(@RequestParam(name = "fromPrice", defaultValue = "0") double fromPrice,
                                               @RequestParam(name = "toPrice", defaultValue = "100") double toPrice) {
        return productService.facetingProductPrice(fromPrice, toPrice);
    }

    /**
     * Aggregation product catalog
     *
     * @return List {@link Facet}
     */
    @GetMapping("/group-catalog")
    public List<Facet> getFacetingProductCatalog() {
        return productService.facetingProductCatalog();
    }

    /**
     * Generate dummy product data
     *
     * @return List {@link Product}
     */
    @PostMapping
    public List<Product> dummyProductData() {
        return productService.dummyData();
    }

    /**
     * Toggle status product
     *
     * @param id
     * @throws NotFoundException
     */
    @PatchMapping("/{id}/toggle-status")
    public void toggleStatusProduct(@PathVariable UUID id) {
        productService.toggleStatusProduct(id);
    }

    /**
     * Delete product
     *
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
    }

}
