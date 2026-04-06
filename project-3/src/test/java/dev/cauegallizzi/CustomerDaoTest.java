package dev.cauegallizzi;

import dev.cauegallizzi.dao.ConnectionFactory;
import dev.cauegallizzi.dao.CustomerDao;
import dev.cauegallizzi.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    private CustomerDao customerDao;
    private final Long TEST_CPF = 00000000000L;

    @Before
    public void setUp() throws Exception {
        customerDao = new CustomerDao();
        insertTestCustomer("Test User", TEST_CPF, "test@email.com");
    }

    @After
    public void tearDown() throws Exception {
        deleteCustomerByCpf(TEST_CPF);
    }

    @Test
    public void get_shouldReturnCustomer_whenFound() throws Exception {
        Customer result = customerDao.get(TEST_CPF);

        assertNotNull(result);
        assertEquals("Test User", result.getName());
        assertEquals(TEST_CPF, result.getCpf());
        assertEquals("test@email.com", result.getEmail());
    }

    @Test
    public void get_shouldReturnNull_whenNotFound() throws Exception {
        Customer result = customerDao.get(-1L);

        assertNull(result);
    }

    @Test
    public void getAll_shouldReturnCustomerCollection() throws Exception {
        Collection<Customer> result = customerDao.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void create_shouldReturnTrue_whenInsertSucceeds() throws Exception {
        Customer customer = new Customer();
        customer.setName("New User");
        customer.setCpf(11111111111L);
        customer.setEmail("new@email.com");

        Boolean result = customerDao.create(customer);

        assertTrue(result);

        deleteCustomerByCpf(11111111111L);
    }

    @Test
    public void update_shouldReturnTrue_whenUpdateSucceeds() throws Exception {
        Long cpf = 22222222222L;
        insertTestCustomer("To Update", cpf, "toupdate@email.com");

        Customer updateCustomer = new Customer();
        updateCustomer.setName("Updated");
        updateCustomer.setCpf(cpf);
        updateCustomer.setEmail("updated@email.com");

        Boolean result = customerDao.update(updateCustomer);

        assertTrue(result);

        deleteCustomerByCpf(cpf);
    }

    @Test
    public void delete_shouldReturnTrue_whenDeleteSucceeds() throws Exception {
        Long cpf = 99999999999L;
        insertTestCustomer("To Delete", cpf, "delete@email.com");

        Boolean result = customerDao.delete(cpf);

        assertTrue(result);
    }

    private void insertTestCustomer(String name, Long cpf, String email) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO tb_customer(name, cpf, email) VALUES (?, ?, ?)"
        );
        stm.setString(1, name);
        stm.setLong(2, cpf);
        stm.setString(3, email);
        stm.executeUpdate();
        stm.close();
    }

    private void deleteCustomerByCpf(Long cpf) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM tb_customer WHERE cpf = ?");
        stm.setLong(1, cpf);
        stm.executeUpdate();
        stm.close();
    }
}
