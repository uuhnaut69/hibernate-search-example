package com.uuhnaut69.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uuhnaut69.demo.domain.constant.DomainConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DomainConstant.CATALOG)
@Indexed(index = DomainConstant.CATALOG)
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Facet
    @Field(analyze = Analyze.NO)
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
