package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private final IProductDao productDao = new ProductDao();

    // ---------- helpers ----------

    private Product buildProduct() {
        Product product = new Product();
        product.setCode("PROD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        product.setName("Produto Teste");
        product.setDescription("Descrição do produto de teste");
        product.setValue(new BigDecimal("99.90"));
        return product;
    }

    // ---------- save ----------

    @Test
    public void save_shouldPersistProduct_andReturnWithId() {
        Product saved = productDao.save(buildProduct());

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getCreatedAt());
    }

    // ---------- getById ----------

    @Test
    public void getById_shouldReturnProduct_whenExists() {
        Product saved = productDao.save(buildProduct());

        Product found = productDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals(saved.getCode(), found.getCode());
        assertEquals(saved.getName(), found.getName());
    }

    @Test
    public void getById_shouldReturnNull_whenNotExists() {
        Product found = productDao.getById(UUID.randomUUID());

        assertNull(found);
    }

    // ---------- getAll ----------

    @Test
    public void getAll_shouldReturnNonEmptyList_afterSave() {
        productDao.save(buildProduct());

        List<Product> products = productDao.getAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    // ---------- update ----------

    @Test
    public void update_shouldChangeName_andPersist() {
        Product saved = productDao.save(buildProduct());

        saved.setName("Nome Atualizado");
        saved.setValue(new BigDecimal("149.99"));
        productDao.update(saved);

        Product updated = productDao.getById(saved.getId());

        assertEquals("Nome Atualizado", updated.getName());
        assertEquals(0, new BigDecimal("149.99").compareTo(updated.getValue()));
    }

    @Test
    public void update_shouldRefreshUpdatedAt() {
        Product saved = productDao.save(buildProduct());

        saved.setName("Outro Nome");
        productDao.update(saved);

        Product updated = productDao.getById(saved.getId());

        assertNotNull(updated.getUpdatedAt());
    }

    // ---------- delete ----------

    @Test
    public void delete_shouldRemoveProduct_fromDatabase() {
        Product saved = productDao.save(buildProduct());
        UUID id = saved.getId();

        productDao.delete(saved);

        Product found = productDao.getById(id);
        assertNull(found);
    }
}
