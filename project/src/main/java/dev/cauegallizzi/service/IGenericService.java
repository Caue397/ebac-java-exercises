package dev.cauegallizzi.service;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService<T, E extends Serializable> {

    Boolean save(T entity);

    void update(T entity);

    void delete(E key);

    T get(E key);

    Collection<T> getAll();
}
