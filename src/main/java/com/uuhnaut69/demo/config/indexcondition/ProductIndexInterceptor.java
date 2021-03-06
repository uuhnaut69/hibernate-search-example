package com.uuhnaut69.demo.config.indexcondition;

import com.uuhnaut69.demo.domain.enums.Status;
import com.uuhnaut69.demo.domain.model.Product;
import org.hibernate.search.indexes.interceptor.EntityIndexingInterceptor;
import org.hibernate.search.indexes.interceptor.IndexingOverride;

public class ProductIndexInterceptor implements EntityIndexingInterceptor<Product> {

    @Override
    public IndexingOverride onAdd(Product product) {
        if (product.getStatus() == Status.ENABLED) {
            return IndexingOverride.APPLY_DEFAULT;
        }
        return IndexingOverride.SKIP;
    }

    @Override
    public IndexingOverride onUpdate(Product product) {
        if (product.getStatus() == Status.ENABLED) {
            return IndexingOverride.UPDATE;
        }
        return IndexingOverride.REMOVE;
    }

    @Override
    public IndexingOverride onDelete(Product product) {
        return IndexingOverride.APPLY_DEFAULT;
    }

    @Override
    public IndexingOverride onCollectionUpdate(Product product) {
        return onUpdate(product);
    }
}
