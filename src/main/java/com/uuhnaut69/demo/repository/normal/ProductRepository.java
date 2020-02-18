package com.uuhnaut69.demo.repository.normal;

import com.uuhnaut69.demo.domain.Product;
import com.uuhnaut69.demo.repository.search.ProductSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, ProductSearchRepository {

}
