package dev.cauegallizzi.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDao<T, E extends Serializable> {
    T get(E value);

    Collection<T> getAll();

    Boolean create(T entity);

    Boolean update(T entity);

    Boolean delete(E value);
}

