package com.uuhnaut69.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Indexed
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field
    @Column(columnDefinition = "text")
    private String productName;

    @Field
    @Column(columnDefinition = "text")
    private String description;

    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_catalog", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "catalog_id"))
    private List<Catalog> catalogs = new ArrayList<>();

    @Field
    private boolean published;

    public Product(String productName, String description, List<Catalog> catalogs, boolean published) {
        this.productName = productName;
        this.description = description;
        this.catalogs = catalogs;
        this.published = published;
    }
}
