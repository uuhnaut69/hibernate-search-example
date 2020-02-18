package com.uuhnaut69.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Indexed(index = "catalog")
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field(analyze = Analyze.YES)
    @Column(columnDefinition = "text")
    private String catalogName;

    @ContainedIn
    @JsonIgnore
    @ManyToMany(mappedBy = "catalogs")
    private List<Product> products = new ArrayList<>();

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }
}
