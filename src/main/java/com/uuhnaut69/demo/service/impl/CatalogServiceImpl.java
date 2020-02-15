package com.uuhnaut69.demo.service.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.demo.domain.Catalog;
import com.uuhnaut69.demo.repository.CatalogRepository;
import com.uuhnaut69.demo.service.CatalogService;
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
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;


    @Override
    public List<Catalog> findByIdIn(List<UUID> uuids) {
        return catalogRepository.findByIdIn(uuids);
    }

    @Override
    public List<Catalog> dummyData() {
        Faker faker = new Faker();
        List<Catalog> catalogs = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> catalogs.add(new Catalog(faker.commerce().department())));
        catalogRepository.saveAll(catalogs);
        return catalogs;
    }

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }
}
