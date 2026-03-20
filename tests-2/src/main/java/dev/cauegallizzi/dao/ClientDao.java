package dev.cauegallizzi.dao;

import dev.cauegallizzi.domain.Client;

public class ClientDao implements IClientDao {

    @Override
    public String save() {
        return "Success";
    }

    @Override
    public Client getById(Integer id) {
        return new Client(3, "Joao", "Rua do Joao");
    }

    @Override
    public Client update(Integer id, String name, String address) {
        return new Client(3, "Pedro", "Rua do Pedro");
    }

    @Override
    public String delete(Integer id) {
        return "Success";
    }
}
