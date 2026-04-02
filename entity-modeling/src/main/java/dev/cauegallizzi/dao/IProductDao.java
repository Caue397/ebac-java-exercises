package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;

import java.util.List;
import java.util.UUID;

public interface IProductDao {
    Product save(Product product);

    Product getById(UUID id);

    List<Product> getAll();

    Product update(Product product);

    void delete(Product product);
}
