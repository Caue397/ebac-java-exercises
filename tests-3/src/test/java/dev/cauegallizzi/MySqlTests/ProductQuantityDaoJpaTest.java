package dev.cauegallizzi.MySqlTests;

import dev.cauegallizzi.dao.jpa.*;
import dev.cauegallizzi.domain.jpa.CustomerJpa;
import dev.cauegallizzi.domain.jpa.ProductJpa;
import dev.cauegallizzi.domain.jpa.ProductQuantityJpa;
import dev.cauegallizzi.domain.jpa.SaleJpa;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductQuantityDaoJpaTest {

    private final IProductQuantityDaoJpa productQuantityDao = new ProductQuantityDaoJpa("mysql");
    private final IProductDaoJpa productDao = new ProductDaoJpa("mysql");
    private final ISaleDaoJpa saleDao = new SaleDaoJpa("mysql");
    private final ICustomerDaoJpa customerDao = new CustomerDaoJpa("mysql");

    private CustomerJpa createAndSaveCustomer(String name) {
        CustomerJpa customer = new CustomerJpa();
        customer.setName(name);
        customer.setEmail(name.toLowerCase().replace(" ", ".") + "@email.com");
        customer.setCpf(System.nanoTime());
        return customerDao.save(customer);
    }

    private ProductJpa createAndSaveProduct(String name, BigDecimal value) {
        ProductJpa product = new ProductJpa();
        product.setCode(UUID.randomUUID().toString().substring(0, 8));
        product.setName(name);
        product.setValue(value);
        return productDao.save(product);
    }

    private SaleJpa createAndSaveSale(CustomerJpa customer) {
        SaleJpa sale = new SaleJpa();
        sale.setCode(UUID.randomUUID().toString().substring(0, 8));
        sale.setCustomer(customer);
        sale.setStatus(SaleJpa.SaleStatus.PENDING);
        return saleDao.save(sale);
    }

    private ProductQuantityJpa buildProductQuantity(ProductJpa product, SaleJpa sale, int quantity) {
        ProductQuantityJpa pq = new ProductQuantityJpa();
        pq.setProduct(product);
        pq.setSale(sale);
        pq.setQuantity(quantity);
        pq.setTotal(product.getValue().multiply(new BigDecimal(quantity)));
        return pq;
    }

    @Test
    public void should_save_product_quantity() {
        CustomerJpa customer = createAndSaveCustomer("Karen Dias");
        SaleJpa sale = createAndSaveSale(customer);
        ProductJpa product = createAndSaveProduct("Cadeira", new BigDecimal("800.00"));

        ProductQuantityJpa pq = buildProductQuantity(product, sale, 2);
        ProductQuantityJpa saved = productQuantityDao.save(pq);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_product_quantity_by_id() {
        CustomerJpa customer = createAndSaveCustomer("Lucas Melo");
        SaleJpa sale = createAndSaveSale(customer);
        ProductJpa product = createAndSaveProduct("Mesa", new BigDecimal("1500.00"));

        ProductQuantityJpa pq = buildProductQuantity(product, sale, 1);
        ProductQuantityJpa saved = productQuantityDao.save(pq);

        ProductQuantityJpa found = productQuantityDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals(Integer.valueOf(1), found.getQuantity());
    }

    @Test
    public void should_get_all_product_quantities() {
        CustomerJpa customer = createAndSaveCustomer("Marina Rocha");
        SaleJpa sale = createAndSaveSale(customer);
        ProductJpa product = createAndSaveProduct("Impressora", new BigDecimal("600.00"));

        ProductQuantityJpa pq = buildProductQuantity(product, sale, 3);
        productQuantityDao.save(pq);

        List<ProductQuantityJpa> list = productQuantityDao.getAll();

        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void should_update_product_quantity() {
        CustomerJpa customer = createAndSaveCustomer("Nelson Borges");
        SaleJpa sale = createAndSaveSale(customer);
        ProductJpa product = createAndSaveProduct("Webcam", new BigDecimal("300.00"));

        ProductQuantityJpa pq = buildProductQuantity(product, sale, 1);
        ProductQuantityJpa saved = productQuantityDao.save(pq);

        saved.setQuantity(5);
        saved.setTotal(product.getValue().multiply(new BigDecimal(5)));
        ProductQuantityJpa updated = productQuantityDao.update(saved);

        assertEquals(Integer.valueOf(5), updated.getQuantity());
    }

    @Test
    public void should_delete_product_quantity() {
        CustomerJpa customer = createAndSaveCustomer("Olivia Campos");
        SaleJpa sale = createAndSaveSale(customer);
        ProductJpa product = createAndSaveProduct("Microfone", new BigDecimal("450.00"));

        ProductQuantityJpa pq = buildProductQuantity(product, sale, 2);
        ProductQuantityJpa saved = productQuantityDao.save(pq);
        UUID id = saved.getId();

        productQuantityDao.delete(saved);

        ProductQuantityJpa deleted = productQuantityDao.getById(id);
        assertNull(deleted);
    }
}
