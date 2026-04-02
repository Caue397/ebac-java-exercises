package dev.cauegallizzi;

import dev.cauegallizzi.dao.CustomerDao;
import dev.cauegallizzi.dao.jdbc.ConnectionFactory;
import dev.cauegallizzi.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    private CustomerDao customerDao;
    private Integer createdCustomerId;

    @Before
    public void setUp() throws Exception {
        customerDao = new CustomerDao();
        createdCustomerId = insertTestCustomer("Test User", "00000000000", "test@email.com");
    }

    @After
    public void tearDown() throws Exception {
        deleteCustomerById(createdCustomerId);
    }

    @Test
    public void getById_shouldReturnCustomer_whenFound() throws Exception {
        Customer result = customerDao.getById(createdCustomerId);

        assertNotNull(result);
        assertEquals("Test User", result.getName());
        assertEquals("00000000000", result.getCpf());
        assertEquals("test@email.com", result.getEmail());
    }

    @Test
    public void getById_shouldReturnNull_whenNotFound() throws Exception {
        Customer result = customerDao.getById(-1);

        assertNull(result);
    }

    @Test
    public void getAll_shouldReturnCustomerList() throws Exception {
        List<Customer> result = customerDao.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void create_shouldReturn1_whenInsertSucceeds() throws Exception {
        Customer customer = new Customer("New User", "11111111111", "new@email.com");

        Integer result = customerDao.create(customer);

        assertEquals(Integer.valueOf(1), result);

        deleteCustomerByCpf("11111111111");
    }

    @Test
    public void update_shouldReturn1_whenUpdateSucceeds() throws Exception {
        Integer id = insertTestCustomer("To Update", "222222222", "toupdate@email.com");

        Customer updateCustomer = new Customer("Updated", "222222222", "updated@email.com");

        Integer result = customerDao.update(id, updateCustomer);

        assertEquals(Integer.valueOf(1), result);

        deleteCustomerByCpf("222222222");
    }

    @Test
    public void update_shouldReturn0_whenUpdateFailed() throws Exception {
        Customer updateCustomer = new Customer("Updated", "11111111111", "updated@email.com");

        Integer result = customerDao.update(-1, updateCustomer);

        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void delete_shouldReturn1_whenDeleteSucceeds() throws Exception {
        Integer extraId = insertTestCustomer("To Delete", "99999999999", "delete@email.com");

        Integer result = customerDao.delete(extraId);

        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void delete_shouldReturn0_whenIdDoesNotExist() throws Exception {
        Integer result = customerDao.delete(-1);

        assertEquals(Integer.valueOf(0), result);
    }

    private Integer insertTestCustomer(String name, String cpf, String email) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO customer(name, cpf, email) VALUES (?, ?, ?) RETURNING id"
        );
        stm.setString(1, name);
        stm.setString(2, cpf);
        stm.setString(3, email);
        var rs = stm.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        stm.close();
        return id;
    }

    private void deleteCustomerById(Integer id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
        stm.setInt(1, id);
        stm.executeUpdate();
        stm.close();
    }

    private void deleteCustomerByCpf(String cpf) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE cpf = ?");
        stm.setString(1, cpf);
        stm.executeUpdate();
        stm.close();
    }
}
