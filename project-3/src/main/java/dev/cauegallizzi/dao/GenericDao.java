package dev.cauegallizzi.dao;

import dev.cauegallizzi.annotation.Column;
import dev.cauegallizzi.annotation.Key;
import dev.cauegallizzi.annotation.Table;
import dev.cauegallizzi.exception.DAOException;
import dev.cauegallizzi.exception.KeyException;
import dev.cauegallizzi.exception.TableException;
import dev.cauegallizzi.exception.TypeNotFounded;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GenericDao<T, E extends Serializable> implements IGenericDao<T,E> {
    public abstract Class<T> getClassType();

    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    protected abstract void setQueryParamInsert(PreparedStatement stmInsert, T entity) throws SQLException;
    protected abstract void setQueryParamDelete(PreparedStatement stmDelete, E valor) throws SQLException;
    protected abstract void setQueryParamUpdate(PreparedStatement stmUpdate, T entity) throws SQLException;
    protected abstract void setQueryParamSelect(PreparedStatement stmSelect, E valor) throws SQLException;

    public GenericDao() {}

    @Override
    public T get(E value) {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            stm = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + " WHERE " + getKey(getClassType()) + " = ?;"
            );
            setQueryParamSelect(stm, value);
            rs = stm.executeQuery();
            if (rs.next()) {
                T entity = getClassType().getConstructor(null).newInstance(null);
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        String dbName = column.dbName();
                        String javaSetName = column.setJavaName();
                        Class<?> classField = field.getType();
                        try {
                            Method method = entity.getClass().getMethod(javaSetName, classField);
                            setValueByType(entity, method, classField, rs, dbName);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            throw new DAOException("Error reading object");
                        } catch (TypeNotFounded e) {
                            throw new TypeNotFounded("Error reading object");
                        }
                    }
                }
                return entity;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | KeyException e) {
            throw new DAOException("Error reading object");
        } finally {
            closeConnection(connection, stm, rs);
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<T> entities = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            stm = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + ";"
            );
            rs = stm.executeQuery();
            while (rs.next()) {
                T entity = getClassType().getConstructor(null).newInstance(null);
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        String dbName = column.dbName();
                        String javaSetName = column.setJavaName();
                        Class<?> classField = field.getType();
                        try {
                            Method method = entity.getClass().getMethod(javaSetName, classField);
                            setValueByType(entity, method, classField, rs, dbName);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            throw new DAOException("Error reading object");
                        } catch (TypeNotFounded e) {
                            throw new TypeNotFounded("Error reading object");
                        }
                    }
                }
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | KeyException e) {
            throw new DAOException("Error reading object");
        } finally {
            closeConnection(connection, stm, rs);
        }
        return entities;
    }

    @Override
    public Boolean create(T entity) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            stm = connection.prepareStatement(getInsertQuery());
            setQueryParamInsert(stm, entity);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error creating a entity");
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Boolean update(T entity) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            stm = connection.prepareStatement(getUpdateQuery());
            setQueryParamUpdate(stm, entity);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Boolean delete(E value) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            stm = connection.prepareStatement(getDeleteQuery());
            setQueryParamDelete(stm, value);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error to delete");
        } finally {
            closeConnection(connection, stm, null);
        }
    }


    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void setValueByType(T entity, Method method, Class<?> classField, ResultSet rs, String fieldName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, TypeNotFounded {
        if (classField.equals(Integer.class)) {
            Integer val = rs.getInt(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Long.class)) {
            Long val = rs.getLong(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Double.class)) {
            Double val =  rs.getDouble(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Short.class)) {
            Short val =  rs.getShort(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(BigDecimal.class)) {
            BigDecimal val =  rs.getBigDecimal(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(String.class)) {
            String val =  rs.getString(fieldName);
            method.invoke(entity, val);
        } else {
            throw new TypeNotFounded("Type not founded: " + classField);
        }
    }

    public String getKey(Class clazz) throws KeyException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Key.class)
                    && field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                return column.dbName();
            }
        }
        return null;
    }

    private String getTableName() throws TableException {
        if (getClassType().isAnnotationPresent(Table.class)) {
            Table table = getClassType().getAnnotation(Table.class);
            return table.value();
        } else {
            throw new TableException("Table " + getClassType().getName() + " not founded!!!");
        }
    }
}
