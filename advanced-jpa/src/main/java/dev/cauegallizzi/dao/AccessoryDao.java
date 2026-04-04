package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Accessory;

public class AccessoryDao extends GenericDao<Accessory> implements IAccessoryDao {

    @Override
    protected Class<Accessory> getClassType() {
        return Accessory.class;
    }
}
