package dev.cauegallizzi.mock;

import dev.cauegallizzi.dao.IClientDao;
import dev.cauegallizzi.entity.Client;

public class ClientDaoMock implements IClientDao {

    @Override
    public String save() {
        return "Success";
    }

    @Override
    public Client getById(Integer id) {
        return new Client(1, "John Doe", "Rua Teste 123");
    }

    @Override
    public Client update(Integer id, String name, String address) {
        return new Client(1, "Marie", "Rua Teste 321");
    }

    @Override
    public String delete(Integer id) {
        return "Success";
    }
}
