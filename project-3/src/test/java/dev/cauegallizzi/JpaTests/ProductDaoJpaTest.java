package dev.cauegallizzi.JpaTests;

import dev.cauegallizzi.dao.jpa.IProductDaoJpa;
import dev.cauegallizzi.dao.jpa.ProductDaoJpa;
import dev.cauegallizzi.domain.jpa.ProductJpa;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductDaoJpaTest {

    private final IProductDaoJpa productDao = new ProductDaoJpa();

    private ProductJpa buildProduct(String name, BigDecimal value) {
        ProductJpa product = new ProductJpa();
        product.setCode(UUID.randomUUID().toString().substring(0, 8));
        product.setName(name);
        product.setValue(value);
        return product;
    }

    @Test
    public void should_save_product() {
        ProductJpa product = buildProduct("Notebook", new BigDecimal("3500.00"));

        ProductJpa saved = productDao.save(product);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_product_by_id() {
        ProductJpa product = buildProduct("Monitor", new BigDecimal("1200.00"));
        ProductJpa saved = productDao.save(product);

        ProductJpa found = productDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Monitor", found.getName());
    }

    @Test
    public void should_get_all_products() {
        ProductJpa product = buildProduct("Teclado", new BigDecimal("250.00"));
        productDao.save(product);

        List<ProductJpa> products = productDao.getAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void should_update_product() {
        ProductJpa product = buildProduct("Mouse", new BigDecimal("150.00"));
        ProductJpa saved = productDao.save(product);

        saved.setName("Mouse Gamer");
        ProductJpa updated = productDao.update(saved);

        assertEquals("Mouse Gamer", updated.getName());
    }

    @Test
    public void should_delete_product() {
        ProductJpa product = buildProduct("Headset", new BigDecimal("400.00"));
        ProductJpa saved = productDao.save(product);
        UUID id = saved.getId();

        productDao.delete(saved);

        ProductJpa deleted = productDao.getById(id);
        assertNull(deleted);
    }
}
