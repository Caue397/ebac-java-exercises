package dev.cauegallizzi.dao;

import dev.cauegallizzi.dao.jdbc.ConnectionFactory;
import dev.cauegallizzi.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDao {

    @Override
    public Customer getById(Integer id) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM customer WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
                customer.setId(rs.getInt("id"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return customer;
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Customer> getAll() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM customer;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
                customer.setId(rs.getInt("id"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                customers.add(customer);
            }
            return customers;
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer create(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO customer(name, cpf, email) VALUES (?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, customer.getName());
            stm.setString(2, customer.getCpf());
            stm.setString(3, customer.getEmail());
            return stm.executeUpdate();
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer update(Integer id, Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE customer SET name = ?, cpf = ?, email = ?, updated_at = NOW() WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, customer.getName());
            stm.setString(2, customer.getCpf());
            stm.setString(3, customer.getEmail());
            stm.setInt(4, id);
            return stm.executeUpdate();
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM customer WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            return stm.executeUpdate();
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
