package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Car;

public class CarDao extends GenericDao<Car> implements ICarDao {

    @Override
    protected Class<Car> getClassType() {
        return Car.class;
    }
}
