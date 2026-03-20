package dev.cauegallizzi.service;

import dev.cauegallizzi.dao.IGenericDao;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T, E extends Serializable> implements IGenericService<T,E> {

    protected IGenericDao<T,E> dao;

    public GenericService(IGenericDao<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean save(T entity) {
        return this.dao.save(entity);
    }

    @Override
    public void update(T entity) {
        this.dao.update(entity);
    }

    @Override
    public void delete(E key) {
        this.dao.delete(key);
    }

    @Override
    public T get(E key) {
        return this.dao.get(key);
    }

    @Override
    public Collection<T> getAll() {
        return this.dao.getAll();
    }
}
