package dev.cauegallizzi;

import dev.cauegallizzi.dao.ConnectionFactory;
import dev.cauegallizzi.dao.ProductDao;
import dev.cauegallizzi.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private ProductDao productDao;
    private final String TEST_CODE = "TEST-001";

    @Before
    public void setUp() throws Exception {
        productDao = new ProductDao();
        insertTestProduct(TEST_CODE, "Test Product", "Test description", new BigDecimal("99.99"), 10);
    }

    @After
    public void tearDown() throws Exception {
        deleteProductByCode(TEST_CODE);
    }

    @Test
    public void get_shouldReturnProduct_whenFound() throws Exception {
        Product result = productDao.get(TEST_CODE);

        assertNotNull(result);
        assertEquals(TEST_CODE, result.getCode());
        assertEquals("Test Product", result.getName());
        assertEquals(new BigDecimal("99.99"), result.getValue());
    }

    @Test
    public void get_shouldReturnNull_whenNotFound() throws Exception {
        Product result = productDao.get("NON-EXISTENT");

        assertNull(result);
    }

    @Test
    public void getAll_shouldReturnProductCollection() throws Exception {
        Collection<Product> result = productDao.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void create_shouldReturnTrue_whenInsertSucceeds() throws Exception {
        Product product = new Product();
        product.setCode("NEW-001");
        product.setName("New Product");
        product.setDescription("New description");
        product.setValue(new BigDecimal("49.90"));
        product.setStockQuantity(5);

        Boolean result = productDao.create(product);

        assertTrue(result);

        deleteProductByCode("NEW-001");
    }

    @Test
    public void update_shouldReturnTrue_whenUpdateSucceeds() throws Exception {
        String code = "UPD-001";
        insertTestProduct(code, "To Update", "Old description", new BigDecimal("10.00"), 1);

        Product updateProduct = new Product();
        updateProduct.setCode(code);
        updateProduct.setName("Updated Product");
        updateProduct.setDescription("Updated description");
        updateProduct.setValue(new BigDecimal("20.00"));
        updateProduct.setStockQuantity(2);

        Boolean result = productDao.update(updateProduct);

        assertTrue(result);

        deleteProductByCode(code);
    }

    @Test
    public void delete_shouldReturnTrue_whenDeleteSucceeds() throws Exception {
        String code = "DEL-001";
        insertTestProduct(code, "To Delete", "Delete description", new BigDecimal("5.00"), 0);

        Boolean result = productDao.delete(code);

        assertTrue(result);
    }

    private void insertTestProduct(String code, String name, String description, BigDecimal value, int stockQuantity) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO tb_product(code, name, description, value, stock_quantity) VALUES (?, ?, ?, ?, ?)"
        );
        stm.setString(1, code);
        stm.setString(2, name);
        stm.setString(3, description);
        stm.setBigDecimal(4, value);
        stm.setInt(5, stockQuantity);
        stm.executeUpdate();
        stm.close();
    }

    private void deleteProductByCode(String code) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM tb_product WHERE code = ?");
        stm.setString(1, code);
        stm.executeUpdate();
        stm.close();
    }
}
