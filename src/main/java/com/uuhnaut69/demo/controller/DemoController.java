package com.uuhnaut69.demo.controller;

import com.uuhnaut69.demo.domain.Catalog;
import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.service.CatalogService;
import com.uuhnaut69.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final ProductService productService;

    private final CatalogService catalogService;

    @PostMapping("/products")
    public List<Product> dummyProductData(){
        return productService.dummyData();
    }

    @PostMapping("/catalogs")
    public List<Catalog> dummyCatalogData(){
        return catalogService.dummyData();
    }
}
