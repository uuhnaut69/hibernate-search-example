package com.uuhnaut69.demo.controller;

import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
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
    public void toggleStatusProduct(@PathVariable UUID id) throws NotFoundException {
        productService.toggleStatusProduct(id);
    }

    /**
     * Delete product
     *
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) throws NotFoundException {
        productService.delete(id);
    }

}
