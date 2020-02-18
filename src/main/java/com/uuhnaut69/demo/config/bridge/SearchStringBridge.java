package com.uuhnaut69.demo.config.bridge;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.MetadataProvidingFieldBridge;
import org.hibernate.search.bridge.spi.FieldMetadataBuilder;
import org.hibernate.search.bridge.spi.FieldType;

public class SearchStringBridge implements MetadataProvidingFieldBridge {

    @Override
    public void configureFieldMetadata(String s, FieldMetadataBuilder fieldMetadataBuilder) {
        fieldMetadataBuilder.field(s, FieldType.STRING);
    }

    @Override
    public void set(String s, Object o, Document document, LuceneOptions luceneOptions) {

    }
}
