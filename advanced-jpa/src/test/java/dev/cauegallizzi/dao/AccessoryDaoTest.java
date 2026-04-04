package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Accessory;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class AccessoryDaoTest {

    private final IAccessoryDao accessoryDao = new AccessoryDao();

    @Test
    public void should_save_accessory() {
        Accessory accessory = new Accessory();
        accessory.setName("Air Conditioning");

        Accessory saved = accessoryDao.save(accessory);

        assertNotNull(saved.getId());
    }

    @Test
    public void should_get_accessory_by_id() {
        Accessory accessory = new Accessory();
        accessory.setName("GPS");
        Accessory saved = accessoryDao.save(accessory);

        Accessory found = accessoryDao.getById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("GPS", found.getName());
    }

    @Test
    public void should_get_all_accessories() {
        Accessory accessory = new Accessory();
        accessory.setName("Sunroof");
        accessoryDao.save(accessory);

        List<Accessory> accessories = accessoryDao.getAll();

        assertNotNull(accessories);
        assertFalse(accessories.isEmpty());
    }

    @Test
    public void should_update_accessory() {
        Accessory accessory = new Accessory();
        accessory.setName("Bluetooth");
        Accessory saved = accessoryDao.save(accessory);

        saved.setName("Bluetooth 5.0");
        Accessory updated = accessoryDao.update(saved);

        assertEquals("Bluetooth 5.0", updated.getName());
    }

    @Test
    public void should_delete_accessory() {
        Accessory accessory = new Accessory();
        accessory.setName("Backup Camera");
        Accessory saved = accessoryDao.save(accessory);
        UUID id = saved.getId();

        accessoryDao.delete(saved);

        Accessory deleted = accessoryDao.getById(id);
        assertNull(deleted);
    }
}
