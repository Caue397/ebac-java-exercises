package dev.cauegallizzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

public abstract class GenericDao<T> implements IGenericDao<T> {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("application-tomcat");

    public abstract Class<T> getClassType();

    @Override
    public T save(T entity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public T getById(UUID id) {
        EntityManager em = factory.createEntityManager();
        T entity = em.find(getClassType(), id);
        em.close();
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        EntityManager em = factory.createEntityManager();
        List<T> result = em.createQuery("FROM " + getClassType().getSimpleName()).getResultList();
        em.close();
        return result;
    }

    @Override
    public T update(T entity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        T merged = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return merged;
    }

    @Override
    public void delete(T entity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        T managed = em.merge(entity);
        em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }
}
