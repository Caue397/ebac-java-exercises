package dev.cauegallizzi.PostgresTests;

import dev.cauegallizzi.dao.jpa.CustomerDaoJpa;
import dev.cauegallizzi.dao.jpa.ICustomerDaoJpa;
import dev.cauegallizzi.dao.jpa.ISaleDaoJpa;
import dev.cauegallizzi.dao.jpa.SaleDaoJpa;
import dev.cauegallizzi.domain.jpa.CustomerJpa;
import dev.cauegallizzi.domain.jpa.SaleJpa;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class SaleDaoJpaTest {

    private final ISaleDaoJpa saleDao = new SaleDaoJpa("postgres");
    private final ICustomerDaoJpa customerDao = new CustomerDaoJpa("postgres");

    private CustomerJpa createAndSaveCustomer(String name) {
        CustomerJpa customer = new CustomerJpa();
        customer.setName(name);
        customer.setEmail(name.toLowerCase().replace(" ", ".") + "@email.com");
        customer.setCpf(System.nanoTime());
        return customerDao.save(customer);
    }

    private SaleJpa buildSale(CustomerJpa customer) {
        SaleJpa sale = new SaleJpa();
        sale.setCode(UUID.randomUUID().toString().substring(0, 8));
        sale.setCustomer(customer);
        sale.setStatus(SaleJpa.SaleStatus.PENDING);
        return sale;
    }

    @Test
    public void should_save_sale() {
        CustomerJpa customer = createAndSaveCustomer("Fernando Costa");
        SaleJpa sale = buildSale(customer);

        SaleJpa saved = saleDao.save(sale);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_sale_by_id() {
        CustomerJpa customer = createAndSaveCustomer("Gabriela Nunes");
        SaleJpa sale = buildSale(customer);
        SaleJpa saved = saleDao.save(sale);

        SaleJpa found = saleDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals(SaleJpa.SaleStatus.PENDING, found.getStatus());
    }

    @Test
    public void should_get_all_sales() {
        CustomerJpa customer = createAndSaveCustomer("Hugo Alves");
        SaleJpa sale = buildSale(customer);
        saleDao.save(sale);

        List<SaleJpa> sales = saleDao.getAll();

        assertNotNull(sales);
        assertFalse(sales.isEmpty());
    }

    @Test
    public void should_update_sale_status() {
        CustomerJpa customer = createAndSaveCustomer("Isabela Freitas");
        SaleJpa sale = buildSale(customer);
        SaleJpa saved = saleDao.save(sale);

        saved.setStatus(SaleJpa.SaleStatus.COMPLETED);
        SaleJpa updated = saleDao.update(saved);

        assertEquals(SaleJpa.SaleStatus.COMPLETED, updated.getStatus());
    }

    @Test
    public void should_delete_sale() {
        CustomerJpa customer = createAndSaveCustomer("Jonas Pires");
        SaleJpa sale = buildSale(customer);
        SaleJpa saved = saleDao.save(sale);
        UUID id = saved.getId();

        saleDao.delete(saved);

        SaleJpa deleted = saleDao.getById(id);
        assertNull(deleted);
    }
}
