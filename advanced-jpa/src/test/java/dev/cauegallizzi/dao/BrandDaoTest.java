package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Brand;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class BrandDaoTest {

    private final IBrandDao brandDao = new BrandDao();

    @Test
    public void should_save_brand() {
        Brand brand = new Brand();
        brand.setName("Toyota");

        Brand saved = brandDao.save(brand);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_brand_by_id() {
        Brand brand = new Brand();
        brand.setName("Honda");
        Brand saved = brandDao.save(brand);

        Brand found = brandDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Honda", found.getName());
    }

    @Test
    public void should_get_all_brands() {
        Brand brand = new Brand();
        brand.setName("Ford");
        brandDao.save(brand);

        List<Brand> brands = brandDao.getAll();

        assertNotNull(brands);
        assertFalse(brands.isEmpty());
    }

    @Test
    public void should_update_brand() {
        Brand brand = new Brand();
        brand.setName("Chevrolet");
        Brand saved = brandDao.save(brand);

        saved.setName("GM");
        Brand updated = brandDao.update(saved);

        assertEquals("GM", updated.getName());
    }

    @Test
    public void should_delete_brand() {
        Brand brand = new Brand();
        brand.setName("Fiat");
        Brand saved = brandDao.save(brand);
        UUID id = saved.getId();

        brandDao.delete(saved);

        Brand deleted = brandDao.getById(id);
        assertNull(deleted);
    }
}
