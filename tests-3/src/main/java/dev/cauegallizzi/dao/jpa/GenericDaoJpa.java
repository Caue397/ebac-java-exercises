package dev.cauegallizzi.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

public abstract class GenericDaoJpa<T> implements IGenericDaoJpa<T> {
    private EntityManagerFactory emf;
    protected abstract Class<T> getClassType();

    public GenericDaoJpa(String databaseName) {
        this.emf = Persistence.createEntityManagerFactory(databaseName);
    }

    @Override
    public T save(T entity) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
        return entity;
    }

    @Override
    public T getById(UUID id) {
        EntityManager manager = emf.createEntityManager();
        T entity = manager.find(getClassType(), id);
        manager.close();
        return entity;
    }

    @Override
    public List<T> getAll() {
        EntityManager manager = emf.createEntityManager();
        List<T> entities = manager
                .createQuery("FROM " + getClassType().getSimpleName(), getClassType())
                .getResultList();
        manager.close();
        return entities;
    }

    @Override
    public T update(T entity) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        T merged = manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
        return merged;
    }

    @Override
    public void delete(T entity) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.merge(entity));
        manager.getTransaction().commit();
        manager.close();
    }
}
