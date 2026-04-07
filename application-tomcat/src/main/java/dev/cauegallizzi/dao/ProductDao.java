package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;

public class ProductDao extends GenericDao<Product> implements IProductDao {

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }
}
