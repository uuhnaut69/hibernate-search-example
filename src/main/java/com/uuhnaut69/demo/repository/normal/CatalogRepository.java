package com.uuhnaut69.demo.repository.normal;

import com.uuhnaut69.demo.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {

    List<Catalog> findByIdIn(List<UUID> uuids);

}
