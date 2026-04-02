package dev.cauegallizzi;

import dev.cauegallizzi.dao.ProductDao;
import dev.cauegallizzi.dao.jdbc.ConnectionFactory;
import dev.cauegallizzi.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private ProductDao productDao;
    private Integer createdProductId;

    @Before
    public void setUp() throws Exception {
        productDao = new ProductDao();
        createdProductId = insertTestProduct("Test Product", "Test Description", new BigDecimal("10.50"), 5, true);
    }

    @After
    public void tearDown() throws Exception {
        deleteProductById(createdProductId);
    }

    @Test
    public void getById_shouldReturnProduct_whenFound() throws Exception {
        Product result = productDao.getById(createdProductId);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals(new BigDecimal("10.50"), result.getPrice());
        assertEquals(Integer.valueOf(5), result.getStock());
    }

    @Test
    public void getById_shouldReturnNull_whenNotFound() throws Exception {
        Product result = productDao.getById(-1);

        assertNull(result);
    }

    @Test
    public void getAll_shouldReturnProductList() throws Exception {
        List<Product> result = productDao.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void create_shouldReturn1_whenInsertSucceeds() throws Exception {
        Product product = new Product("New Product", "New Description", new BigDecimal("19.99"), 3);

        Integer result = productDao.create(product);

        assertEquals(Integer.valueOf(1), result);

        deleteProductByName("New Product");
    }

    @Test
    public void update_shouldReturn1_whenUpdateSucceeds() throws Exception {
        Integer id = insertTestProduct("To Update", "Old Description", new BigDecimal("5.00"), 2, true);

        Product updateProduct = new Product("Updated", "New Description", new BigDecimal("15.00"), 7);

        Integer result = productDao.update(id, updateProduct);

        assertEquals(Integer.valueOf(1), result);

        deleteProductById(id);
    }

    @Test
    public void update_shouldReturn0_whenUpdateFailed() throws Exception {
        Product updateProduct = new Product("Updated", "New Description", new BigDecimal("15.00"), 7);

        Integer result = productDao.update(-1, updateProduct);

        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void delete_shouldReturn1_whenDeleteSucceeds() throws Exception {
        Integer extraId = insertTestProduct("To Delete", "Delete Description", new BigDecimal("1.00"), 1, true);

        Integer result = productDao.delete(extraId);

        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void delete_shouldReturn0_whenIdDoesNotExist() throws Exception {
        Integer result = productDao.delete(-1);

        assertEquals(Integer.valueOf(0), result);
    }

    private Integer insertTestProduct(String name, String description, BigDecimal price, Integer stock, Boolean active) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO product(name, description, price, stock, active) VALUES (?, ?, ?, ?, ?) RETURNING id"
        );
        stm.setString(1, name);
        stm.setString(2, description);
        stm.setBigDecimal(3, price);
        stm.setInt(4, stock);
        stm.setBoolean(5, active);
        var rs = stm.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        stm.close();
        return id;
    }

    private void deleteProductById(Integer id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM product WHERE id = ?");
        stm.setInt(1, id);
        stm.executeUpdate();
        stm.close();
    }

    private void deleteProductByName(String name) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM product WHERE name = ?");
        stm.setString(1, name);
        stm.executeUpdate();
        stm.close();
    }
}
