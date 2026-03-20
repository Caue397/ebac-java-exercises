package dev.cauegallizzi.service;

import dev.cauegallizzi.dao.IProductDao;
import dev.cauegallizzi.domain.Product;

public class ProductService extends GenericService<Product, String> implements IProductService {
    public ProductService(IProductDao dao) {
        super(dao);
    }
}
