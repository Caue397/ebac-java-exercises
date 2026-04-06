package dev.cauegallizzi.dao.jpa;

import dev.cauegallizzi.domain.jpa.SaleJpa;

public class SaleDaoJpa extends GenericDaoJpa<SaleJpa> implements ISaleDaoJpa {
    public SaleDaoJpa(String databaseName) {
        super(databaseName);
    }

    @Override
    protected Class<SaleJpa> getClassType() {
        return SaleJpa.class;
    }
}
