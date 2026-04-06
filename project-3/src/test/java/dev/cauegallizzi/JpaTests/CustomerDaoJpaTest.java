package dev.cauegallizzi.JpaTests;

import dev.cauegallizzi.dao.jpa.CustomerDaoJpa;
import dev.cauegallizzi.dao.jpa.ICustomerDaoJpa;
import dev.cauegallizzi.domain.jpa.CustomerJpa;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class CustomerDaoJpaTest {

    private final ICustomerDaoJpa customerDao = new CustomerDaoJpa();

    private CustomerJpa buildCustomer(String name, long cpf) {
        CustomerJpa customer = new CustomerJpa();
        customer.setName(name);
        customer.setEmail(name.toLowerCase().replace(" ", ".") + "@email.com");
        customer.setCpf(cpf);
        return customer;
    }

    @Test
    public void should_save_customer() {
        CustomerJpa customer = buildCustomer("Ana Lima", System.nanoTime());

        CustomerJpa saved = customerDao.save(customer);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_customer_by_id() {
        CustomerJpa customer = buildCustomer("Bruno Souza", System.nanoTime());
        CustomerJpa saved = customerDao.save(customer);

        CustomerJpa found = customerDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Bruno Souza", found.getName());
    }

    @Test
    public void should_get_all_customers() {
        CustomerJpa customer = buildCustomer("Carla Mendes", System.nanoTime());
        customerDao.save(customer);

        List<CustomerJpa> customers = customerDao.getAll();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
    }

    @Test
    public void should_update_customer() {
        CustomerJpa customer = buildCustomer("Diego Ramos", System.nanoTime());
        CustomerJpa saved = customerDao.save(customer);

        saved.setName("Diego Ramos Jr.");
        CustomerJpa updated = customerDao.update(saved);

        assertEquals("Diego Ramos Jr.", updated.getName());
    }

    @Test
    public void should_delete_customer() {
        CustomerJpa customer = buildCustomer("Eva Torres", System.nanoTime());
        CustomerJpa saved = customerDao.save(customer);
        UUID id = saved.getId();

        customerDao.delete(saved);

        CustomerJpa deleted = customerDao.getById(id);
        assertNull(deleted);
    }
}
