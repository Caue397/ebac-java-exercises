package dev.cauegallizzi.domain;

import dev.cauegallizzi.annotation.Column;
import dev.cauegallizzi.annotation.Key;
import dev.cauegallizzi.annotation.Table;

@Table("tb_customer")
public class Customer {

    @Column(dbName = "id", setJavaName = "setId")
    private Long id;

    @Column(dbName = "name", setJavaName = "setName")
    private String name;

    @Column(dbName = "email", setJavaName = "setEmail")
    private String email;

    @Key("getCpf")
    @Column(dbName = "cpf", setJavaName = "setCpf")
    private Long cpf;

    @Column(dbName = "tel", setJavaName = "setTel")
    private Long tel;

    @Column(dbName = "address", setJavaName = "setAddress")
    private String address;

    @Column(dbName = "number", setJavaName = "setNumber")
    private Integer number;

    @Column(dbName = "city", setJavaName = "setCity")
    private String city;

    @Column(dbName = "state", setJavaName = "setState")
    private String state;

    @Column(dbName = "created_at", setJavaName = "setCreatedAt")
    private String createdAt;

    @Column(dbName = "updated_at", setJavaName = "setUpdatedAt")
    private String updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }

    public Long getTel() { return tel; }
    public void setTel(Long tel) { this.tel = tel; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
