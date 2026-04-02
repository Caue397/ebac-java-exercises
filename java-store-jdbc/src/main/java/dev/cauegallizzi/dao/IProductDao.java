package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Customer;
import dev.cauegallizzi.domain.Product;

import java.util.List;

public interface IProductDao {
    Product getById(Integer id) throws Exception;

    List<Product> getAll() throws Exception;

    Integer create(Product product) throws Exception;

    Integer update(Integer id, Product product) throws Exception;

    Integer delete(Integer id) throws Exception;
}
