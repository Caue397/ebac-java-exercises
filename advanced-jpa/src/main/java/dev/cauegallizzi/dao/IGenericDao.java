package dev.cauegallizzi.dao;

import java.util.List;
import java.util.UUID;

public interface IGenericDao<T> {
    T save(T entity);
    T getById(UUID id);
    List<T> getAll();
    T update(T entity);
    void delete(T entity);
}
