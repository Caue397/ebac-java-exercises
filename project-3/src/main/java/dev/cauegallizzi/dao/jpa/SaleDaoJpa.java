package dev.cauegallizzi.dao.jpa;

import dev.cauegallizzi.domain.jpa.SaleJpa;

public class SaleDaoJpa extends GenericDaoJpa<SaleJpa> implements ISaleDaoJpa {
    @Override
    protected Class<SaleJpa> getClassType() {
        return SaleJpa.class;
    }
}
