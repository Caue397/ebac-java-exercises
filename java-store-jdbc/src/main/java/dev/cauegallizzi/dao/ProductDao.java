package dev.cauegallizzi.dao;

import dev.cauegallizzi.dao.jdbc.ConnectionFactory;
import dev.cauegallizzi.domain.Customer;
import dev.cauegallizzi.domain.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public Product getById(Integer id) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Product product = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM product WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                product = new Product(
                  rs.getString("name"),
                  rs.getString("description"),
                  rs.getBigDecimal("price"),
                  rs.getInt("stock")
                );
                product.setId(rs.getInt("id"));
                product.setActive(rs.getBoolean("active"));
                product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return product;
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
    public List<Product> getAll() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM product;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                );
                product.setId(rs.getInt("id"));
                product.setActive(rs.getBoolean("active"));
                product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                products.add(product);
            }
            return products;
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
    public Integer create(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO product(name, description, price, stock, active) VALUES (?, ?, ?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setBigDecimal(3, product.getPrice());
            stm.setInt(4, product.getStock());
            stm.setBoolean(5, product.getActive());
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
    public Integer update(Integer id, Product product) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE product SET name = ?, description = ?, price = ?, stock = ?, active = ?, updated_at = NOW() WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setBigDecimal(3, product.getPrice());
            stm.setInt(4, product.getStock());
            stm.setBoolean(5, product.getActive());
            stm.setInt(6, id);
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
            String sql = "DELETE FROM product WHERE id = ?;";
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
