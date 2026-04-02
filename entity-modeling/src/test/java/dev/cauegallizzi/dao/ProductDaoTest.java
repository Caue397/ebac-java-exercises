package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private final IProductDao productDao = new ProductDao();

    @Test
    public void should_save_product() {
        Product product = new Product("Notebook", 1001L, new BigDecimal("3500.00"));

        Product saved = productDao.save(product);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_product_by_id() {
        Product product = new Product("Mouse", 1002L, new BigDecimal("150.00"));
        Product saved = productDao.save(product);

        Product found = productDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Mouse", found.getName());
    }

    @Test
    public void should_get_all_products() {
        productDao.save(new Product("Teclado", 1003L, new BigDecimal("250.00")));

        List<Product> products = productDao.getAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void should_update_product() {
        Product product = new Product("Monitor", 1004L, new BigDecimal("1200.00"));
        Product saved = productDao.save(product);

        saved.setName("Monitor 4K");
        saved.setPrice(new BigDecimal("2000.00"));
        Product updated = productDao.update(saved);

        assertEquals("Monitor 4K", updated.getName());
        assertEquals(new BigDecimal("2000.00"), updated.getPrice());
    }

    @Test
    public void should_delete_product() {
        Product product = new Product("Headset", 1005L, new BigDecimal("300.00"));
        Product saved = productDao.save(product);
        UUID id = saved.getId();

        productDao.delete(saved);

        Product deleted = productDao.getById(id);
        assertNull(deleted);
    }
}
