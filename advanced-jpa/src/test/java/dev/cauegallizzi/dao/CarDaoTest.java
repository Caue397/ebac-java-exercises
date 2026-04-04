package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Accessory;
import dev.cauegallizzi.domain.Brand;
import dev.cauegallizzi.domain.Car;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class CarDaoTest {

    private final ICarDao carDao = new CarDao();
    private final IBrandDao brandDao = new BrandDao();
    private final IAccessoryDao accessoryDao = new AccessoryDao();

    private Brand createAndSaveBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brandDao.save(brand);
    }

    @Test
    public void should_save_car() {
        Brand brand = createAndSaveBrand("Toyota");
        Car car = new Car();
        car.setName("Corolla");
        car.setBrand(brand);

        Car saved = carDao.save(car);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_car_by_id() {
        Brand brand = createAndSaveBrand("Honda");
        Car car = new Car();
        car.setName("Civic");
        car.setBrand(brand);
        Car saved = carDao.save(car);

        Car found = carDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Civic", found.getName());
    }

    @Test
    public void should_get_all_cars() {
        Brand brand = createAndSaveBrand("Ford");
        Car car = new Car();
        car.setName("Focus");
        car.setBrand(brand);
        carDao.save(car);

        List<Car> cars = carDao.getAll();

        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    public void should_update_car() {
        Brand brand = createAndSaveBrand("Chevrolet");
        Car car = new Car();
        car.setName("Onix");
        car.setBrand(brand);
        Car saved = carDao.save(car);

        saved.setName("Onix Plus");
        Car updated = carDao.update(saved);

        assertEquals("Onix Plus", updated.getName());
    }

    @Test
    public void should_delete_car() {
        Brand brand = createAndSaveBrand("Volkswagen");
        Car car = new Car();
        car.setName("Gol");
        car.setBrand(brand);
        Car saved = carDao.save(car);
        UUID id = saved.getId();

        carDao.delete(saved);

        Car deleted = carDao.getById(id);
        assertNull(deleted);
    }

    @Test
    public void should_save_car_with_accessories() {
        Accessory accessory = new Accessory();
        accessory.setName("Air Conditioning");
        accessoryDao.save(accessory);

        Brand brand = createAndSaveBrand("Fiat");
        Car car = new Car();
        car.setName("Pulse");
        car.setBrand(brand);
        car.setAccessories(List.of(accessory));

        Car saved = carDao.save(car);

        assertNotNull(saved.getId());
        assertFalse(saved.getAccessories().isEmpty());
    }
}
