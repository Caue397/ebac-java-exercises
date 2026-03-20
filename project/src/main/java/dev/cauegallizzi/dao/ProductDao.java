package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;

public class ProductDao extends GenericDao<Product, String> implements IProductDao {

    public ProductDao() {
        super();
    }

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }

    @Override
    public void updateData(Product entity, Product entityRegistered) {
        entityRegistered.setName(entity.getName());
        entityRegistered.setDescription(entity.getDescription());
        entityRegistered.setValue(entity.getValue());
        entityRegistered.setCode(entity.getCode());
    }
}
