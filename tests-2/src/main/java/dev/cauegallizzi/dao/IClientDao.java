package dev.cauegallizzi.dao;

import dev.cauegallizzi.entity.Client;

public interface IClientDao {
    String save();
    Client getById(Integer id);
    Client update(Integer id, String name, String address);
    String delete(Integer id);
}
