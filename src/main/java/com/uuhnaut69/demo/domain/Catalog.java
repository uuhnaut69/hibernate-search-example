package com.uuhnaut69.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Indexed
@NoArgsConstructor
@AllArgsConstructor
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field
    @Column(columnDefinition = "text")
    private String catalogName;

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }
}
