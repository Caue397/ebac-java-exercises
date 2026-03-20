package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private ProductDao dao;

    @Before
    public void setUp() {
        SingletonMap.getInstance().getMap().clear();
        dao = new ProductDao();
    }

    private Product buildProduct(String code, String name, BigDecimal value) {
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setDescription("Descrição de " + name);
        product.setValue(value);
        return product;
    }

    @Test
    public void saveShouldReturnTrueWhenProductIsNew() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        assertTrue(dao.save(product));
    }

    @Test
    public void saveShouldReturnFalseWhenProductAlreadyExists() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        dao.save(product);
        assertFalse(dao.save(product));
    }

    @Test
    public void getShouldReturnProductAfterSave() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        dao.save(product);

        Product found = dao.get("P001");

        assertNotNull(found);
        assertEquals("Notebook", found.getName());
        assertEquals("P001", found.getCode());
    }

    @Test
    public void getShouldReturnNullWhenProductDoesNotExist() {
        assertNull(dao.get("INEXISTENTE"));
    }

    @Test
    public void updateShouldChangeProductData() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        dao.save(product);

        Product updated = buildProduct("P001", "Notebook Gamer", new BigDecimal("7000.00"));
        updated.setDescription("Notebook para jogos");
        dao.update(updated);

        Product found = dao.get("P001");
        assertEquals("Notebook Gamer", found.getName());
        assertEquals(new BigDecimal("7000.00"), found.getValue());
        assertEquals("Notebook para jogos", found.getDescription());
    }

    @Test
    public void updateShouldDoNothingWhenProductDoesNotExist() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        dao.update(product);

        assertNull(dao.get("P001"));
    }

    @Test
    public void deleteShouldRemoveProduct() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        dao.save(product);

        dao.delete("P001");

        assertNull(dao.get("P001"));
    }

    @Test
    public void deleteShouldDoNothingWhenProductDoesNotExist() {
        dao.delete("INEXISTENTE");
        assertNull(dao.get("INEXISTENTE"));
    }

    @Test
    public void getAllShouldReturnAllSavedProducts() {
        dao.save(buildProduct("P001", "Notebook", new BigDecimal("3500.00")));
        dao.save(buildProduct("P002", "Mouse", new BigDecimal("150.00")));
        dao.save(buildProduct("P003", "Teclado", new BigDecimal("200.00")));

        Collection<Product> products = dao.getAll();

        assertEquals(3, products.size());
    }

    @Test
    public void getAllShouldReturnEmptyWhenNoProductsExist() {
        assertTrue(dao.getAll().isEmpty());
    }
}
