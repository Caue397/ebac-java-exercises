package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

public class ProductDao implements IProductDao {

    @Override
    public Product save(Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity-modeling");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(product);
        manager.getTransaction().commit();

        manager.close();
        emf.close();

        return product;
    }

    @Override
    public Product getById(UUID id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity-modeling");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        Product product = manager.find(Product.class, id);
        manager.getTransaction().commit();

        manager.close();
        emf.close();

        return product;
    }

    @Override
    public List<Product> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity-modeling");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        List<Product> products = manager.createQuery("FROM Product", Product.class).getResultList();
        manager.getTransaction().commit();

        manager.close();
        emf.close();

        return products;
    }

    @Override
    public Product update(Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity-modeling");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        Product productMerged = manager.merge(product);
        manager.getTransaction().commit();

        manager.close();
        emf.close();

        return productMerged;
    }

    @Override
    public void delete(Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity-modeling");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        manager.remove(manager.merge(product));
        manager.getTransaction().commit();

        manager.close();
        emf.close();
    }
}
