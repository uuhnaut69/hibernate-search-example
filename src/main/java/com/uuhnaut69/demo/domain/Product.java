package com.uuhnaut69.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

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
@AnalyzerDef(name = "ngram",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = StopFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class,
                        params = {
                                @Parameter(name = "minGramSize", value = "3"),
                                @Parameter(name = "maxGramSize", value = "3")})
        }
)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = "ngram"))
    @Column(columnDefinition = "text")
    private String productName;

    @Field(analyze = Analyze.YES)
    @Column(columnDefinition = "text")
    private String description;

    @IndexedEmbedded(includeEmbeddedObjectId = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_catalog", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "catalog_id"))
    private List<Catalog> catalogs = new ArrayList<>();

    @Field(analyze = Analyze.NO)
    private boolean published;

    public Product(String productName, String description, List<Catalog> catalogs, boolean published) {
        this.productName = productName;
        this.description = description;
        this.catalogs = catalogs;
        this.published = published;
    }
}
