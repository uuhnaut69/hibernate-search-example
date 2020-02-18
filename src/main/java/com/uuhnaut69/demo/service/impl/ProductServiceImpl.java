package com.uuhnaut69.demo.service.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.demo.domain.Catalog;
import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.domain.Status;
import com.uuhnaut69.demo.repository.normal.ProductRepository;
import com.uuhnaut69.demo.service.CatalogService;
import com.uuhnaut69.demo.service.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        IntStream.range(0, 30).forEach(i -> products.add(new Product(faker.commerce().productName(), faker.commerce().material(), catalogs, Status.ENABLED)));
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

    @Override
    public void toggleStatusProduct(UUID id) throws NotFoundException {
        Product product = findById(id);
        if (product.getStatus() == Status.ENABLED) {
            product.setStatus(Status.DISABLED);
        } else {
            product.setStatus(Status.ENABLED);
        }
    }

    @Override
    public void delete(UUID id) throws NotFoundException {
        Product product = findById(id);
        productRepository.delete(product);
    }

    /**
     * Find product by id
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    private Product findById(UUID id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found product"));
    }
}
