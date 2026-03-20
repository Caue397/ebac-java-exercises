package dev.cauegallizzi.mock;

import dev.cauegallizzi.dao.IProductDao;
import dev.cauegallizzi.domain.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductDaoMock implements IProductDao {

    private final Map<String, Product> storage = new HashMap<>();

    @Override
    public Boolean save(Product entity) {
        if (storage.containsKey(entity.getCode())) {
            return false;
        }
        storage.put(entity.getCode(), entity);
        return true;
    }

    @Override
    public void update(Product entity) {
        Product registered = storage.get(entity.getCode());
        if (registered != null) {
            registered.setName(entity.getName());
            registered.setDescription(entity.getDescription());
            registered.setValue(entity.getValue());
            registered.setCode(entity.getCode());
        }
    }

    @Override
    public void delete(String key) {
        storage.remove(key);
    }

    @Override
    public Product get(String key) {
        return storage.get(key);
    }

    @Override
    public Collection<Product> getAll() {
        return storage.values();
    }
}
