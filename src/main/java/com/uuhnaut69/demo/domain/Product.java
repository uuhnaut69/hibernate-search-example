package com.uuhnaut69.demo.domain;

import com.uuhnaut69.demo.config.bridge.SearchStringBridge;
import com.uuhnaut69.demo.config.indexcondition.IndexWhenEnabledInterceptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
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
@Indexed(index = "product", interceptor = IndexWhenEnabledInterceptor.class)
@AnalyzerDef(name = "edgeNgram",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characters by their simpler counterpart (è => e, etc.)
                @TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
                @TokenFilterDef(
                        factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
                        params = {
                                @Parameter(name = "minGramSize", value = "1"),
                                @Parameter(name = "maxGramSize", value = "20")
                        }
                )
        })
@AnalyzerDef(name = "edgeNgramQuery",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characters by their simpler counterpart (è => e, etc.)
                @TokenFilterDef(factory = LowerCaseFilterFactory.class) // Lowercase all characters
        })
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Fields({
            @Field(name = "productName", index = Index.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "edgeNgram")),
            @Field(name = "productNameAutocomplete", index = Index.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "edgeNgramQuery"), bridge = @FieldBridge(impl = SearchStringBridge.class))
    })
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
    @Enumerated(EnumType.STRING)
    private Status status;

    public Product(String productName, String description, List<Catalog> catalogs, Status status) {
        this.productName = productName;
        this.description = description;
        this.catalogs = catalogs;
        this.status = status;
    }
}
