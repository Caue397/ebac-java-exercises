package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao extends GenericDao<Product, String> implements IProductDao {

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO tb_product(code, name, description, value, stock_quantity) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE tb_product SET name = ?, description = ?, value = ?, stock_quantity = ? WHERE code = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM tb_product WHERE code = ?";
    }

    @Override
    protected void setQueryParamInsert(PreparedStatement stmInsert, Product entity) throws SQLException {
        stmInsert.setString(1, entity.getCode());
        stmInsert.setString(2, entity.getName());
        stmInsert.setString(3, entity.getDescription());
        stmInsert.setBigDecimal(4, entity.getValue());
        stmInsert.setInt(5, entity.getStockQuantity() != null ? entity.getStockQuantity() : 0);
    }

    @Override
    protected void setQueryParamDelete(PreparedStatement stmDelete, String value) throws SQLException {
        stmDelete.setString(1, value);
    }

    @Override
    protected void setQueryParamUpdate(PreparedStatement stmUpdate, Product entity) throws SQLException {
        stmUpdate.setString(1, entity.getName());
        stmUpdate.setString(2, entity.getDescription());
        stmUpdate.setBigDecimal(3, entity.getValue());
        stmUpdate.setInt(4, entity.getStockQuantity() != null ? entity.getStockQuantity() : 0);
        stmUpdate.setString(5, entity.getCode());
    }

    @Override
    protected void setQueryParamSelect(PreparedStatement stmSelect, String value) throws SQLException {
        stmSelect.setString(1, value);
    }

    public ProductDao() {
        super();
    }
}
