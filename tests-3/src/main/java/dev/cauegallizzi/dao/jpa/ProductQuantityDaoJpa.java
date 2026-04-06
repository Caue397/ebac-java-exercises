package dev.cauegallizzi.dao.jpa;

import dev.cauegallizzi.domain.jpa.ProductQuantityJpa;

public class ProductQuantityDaoJpa extends GenericDaoJpa<ProductQuantityJpa> implements IProductQuantityDaoJpa {
    public ProductQuantityDaoJpa(String databaseName) {
        super(databaseName);
    }

    @Override
    protected Class<ProductQuantityJpa> getClassType() {
        return ProductQuantityJpa.class;
    }
}
