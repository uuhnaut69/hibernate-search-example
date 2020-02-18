package com.uuhnaut69.demo.service.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.demo.domain.Catalog;
import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.repository.normal.ProductRepository;
import com.uuhnaut69.demo.service.CatalogService;
import com.uuhnaut69.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CatalogService catalogService;

    @Override
    public List<Product> dummyData() {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        List<Catalog> catalogs = catalogService.findAll();
        IntStream.range(0, 30).forEach(i -> products.add(new Product(faker.commerce().productName(), faker.commerce().material(), catalogs, true)));
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> fullTextSearch(String text) {
        return productRepository.fullTextSearch(text);
    }

    @Override
    public List<Product> autocomplete(String text) {
        return productRepository.autocomplete(text);
    }

}
