package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao extends GenericDao<Customer, Long> implements ICustomerDao {
    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }

    @Override
    protected String getInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_customer(name, cpf, email) ");
        sb.append("VALUES (?, ?, ?);");
        return sb.toString();
    }

    @Override
    protected String getUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_customer ");
        sb.append("SET name = ?, email = ?, cpf = ? ");
        sb.append("WHERE cpf = ?;");
        return sb.toString();
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM tb_customer WHERE cpf = ?;";
    }

    @Override
    protected void setQueryParamInsert(PreparedStatement stmInsert, Customer entity) throws SQLException {
        stmInsert.setString(1, entity.getName());
        stmInsert.setLong(2, entity.getCpf());
        stmInsert.setString(3, entity.getEmail());
    }

    @Override
    protected void setQueryParamDelete(PreparedStatement stmDelete, Long value) throws SQLException {
        stmDelete.setLong(1, value);
    }

    @Override
    protected void setQueryParamUpdate(PreparedStatement stmUpdate, Customer entity) throws SQLException {
        stmUpdate.setString(1, entity.getName());
        stmUpdate.setString(2, entity.getEmail());
        stmUpdate.setLong(3, entity.getCpf());
        stmUpdate.setLong(4, entity.getCpf());
    }

    @Override
    protected void setQueryParamSelect(PreparedStatement stmSelect, Long value) throws SQLException {
        stmSelect.setLong(1, value);
    }

    public CustomerDao() {
        super();
    }
}
