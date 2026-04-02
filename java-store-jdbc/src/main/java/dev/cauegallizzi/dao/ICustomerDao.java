package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Customer;

import java.util.List;

public interface ICustomerDao {
    Customer getById(Integer id) throws Exception;

    List<Customer> getAll() throws Exception;

    Integer create(Customer customer) throws Exception;

    Integer update(Integer id, Customer customer) throws Exception;

    Integer delete(Integer id) throws Exception;
}
