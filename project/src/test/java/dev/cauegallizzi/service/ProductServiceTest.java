package dev.cauegallizzi.service;

import dev.cauegallizzi.domain.Product;
import dev.cauegallizzi.mock.ProductDaoMock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService service;

    @Before
    public void setUp() {
        service = new ProductService(new ProductDaoMock());
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
        assertTrue(service.save(product));
    }

    @Test
    public void saveShouldReturnFalseWhenProductAlreadyExists() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        service.save(product);
        assertFalse(service.save(product));
    }

    @Test
    public void getShouldReturnProductAfterSave() {
        Product product = buildProduct("P001", "Notebook", new BigDecimal("3500.00"));
        service.save(product);

        Product found = service.get("P001");

        assertNotNull(found);
        assertEquals("Notebook", found.getName());
        assertEquals("P001", found.getCode());
    }

    @Test
    public void getShouldReturnNullWhenProductDoesNotExist() {
        assertNull(service.get("INEXISTENTE"));
    }

    @Test
    public void updateShouldChangeProductData() {
        service.save(buildProduct("P001", "Notebook", new BigDecimal("3500.00")));

        Product updated = buildProduct("P001", "Notebook Gamer", new BigDecimal("7000.00"));
        updated.setDescription("Notebook para jogos");
        service.update(updated);

        Product found = service.get("P001");
        assertEquals("Notebook Gamer", found.getName());
        assertEquals(new BigDecimal("7000.00"), found.getValue());
        assertEquals("Notebook para jogos", found.getDescription());
    }

    @Test
    public void updateShouldDoNothingWhenProductDoesNotExist() {
        service.update(buildProduct("P001", "Notebook", new BigDecimal("3500.00")));
        assertNull(service.get("P001"));
    }

    @Test
    public void deleteShouldRemoveProduct() {
        service.save(buildProduct("P001", "Notebook", new BigDecimal("3500.00")));
        service.delete("P001");
        assertNull(service.get("P001"));
    }

    @Test
    public void deleteShouldDoNothingWhenProductDoesNotExist() {
        service.delete("INEXISTENTE");
        assertNull(service.get("INEXISTENTE"));
    }

    @Test
    public void getAllShouldReturnAllSavedProducts() {
        service.save(buildProduct("P001", "Notebook", new BigDecimal("3500.00")));
        service.save(buildProduct("P002", "Mouse", new BigDecimal("150.00")));
        service.save(buildProduct("P003", "Teclado", new BigDecimal("200.00")));

        Collection<Product> products = service.getAll();
        assertEquals(3, products.size());
    }

    @Test
    public void getAllShouldReturnEmptyWhenNoProductsExist() {
        assertTrue(service.getAll().isEmpty());
    }
}
