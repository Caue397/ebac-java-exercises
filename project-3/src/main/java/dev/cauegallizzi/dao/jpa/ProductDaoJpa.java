package dev.cauegallizzi.dao.jpa;

import dev.cauegallizzi.domain.jpa.ProductJpa;

public class ProductDaoJpa extends GenericDaoJpa<ProductJpa> implements IProductDaoJpa {
    @Override
    protected Class<ProductJpa> getClassType() {
        return ProductJpa.class;
    }
}
