package dev.cauegallizzi.dao.jpa;

import dev.cauegallizzi.domain.jpa.CustomerJpa;

public class CustomerDaoJpa extends GenericDaoJpa<CustomerJpa> implements ICustomerDaoJpa {
    public CustomerDaoJpa(String databaseName) {
        super(databaseName);
    }

    @Override
    protected Class<CustomerJpa> getClassType() {
        return CustomerJpa.class;
    }
}
