package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Brand;

public class BrandDao extends GenericDao<Brand> implements IBrandDao {

    @Override
    protected Class<Brand> getClassType() {
        return Brand.class;
    }
}
