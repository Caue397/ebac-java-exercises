package dev.cauegallizzi.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDao<T, E extends Serializable> {
    Boolean save(T entity);

    void delete(E value);

    void update(T entity);

    T get(E value);

    Collection<T> getAll();
}
