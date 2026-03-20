package dev.cauegallizzi.dao;

import dev.cauegallizzi.annotation.Key;
import dev.cauegallizzi.exception.KeyNotFound;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDao<T, E extends Serializable> implements IGenericDao<T, E> {

    private SingletonMap singletonMap;

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T entityRegistered);

    public GenericDao() {
        this.singletonMap = SingletonMap.getInstance();
    }

    public E getKey(T entity) throws KeyNotFound {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Key.class)) {
                Key key = field.getAnnotation(Key.class);
                String methodName = key.value();
                try {
                    Method method = entity.getClass().getMethod(methodName);
                    return (E) method.invoke(entity);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new KeyNotFound("Main key of " + entity.getClass() + " not founded");
                }
            }
        }
        if (returnValue == null) {
            throw new KeyNotFound("Main key of " + entity.getClass() + " not founded");
        }
        return null;
    }

    @Override
    public Boolean save(T entity) {
        Map<E, T> map = getMap();
        E key = getKey(entity);
        if (map.containsKey(key)) {
            return false;
        }

        map.put(key, entity);
        return true;
    }

    @Override
    public void delete(E key) {
        Map<E, T> internMap = getMap();
        T registeredObject = internMap.get(key);
        if (registeredObject != null) {
            internMap.remove(key, registeredObject);
        }
    }

    @Override
    public void update(T entity) {
        Map<E, T> internMap = getMap();
        E key = getKey(entity);
        T registeredObject = internMap.get(key);
        if (registeredObject != null) {
            updateData(entity, registeredObject);
        }
    }

    @Override
    public T get(E key) {
        Map<E, T> internMap = getMap();
        return internMap.get(key);
    }

    @Override
    public Collection<T> getAll() {
        Map<E, T> internMap = getMap();
        return internMap.values();
    }

    private Map<E, T> getMap() {
        Map<E, T> internMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        if (internMap == null) {
            internMap = new HashMap<>();
            this.singletonMap.getMap().put(getClassType(), internMap);
        }
        return internMap;
    }
}
