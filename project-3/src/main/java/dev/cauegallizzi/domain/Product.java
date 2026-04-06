package dev.cauegallizzi.domain;

import dev.cauegallizzi.annotation.Column;
import dev.cauegallizzi.annotation.Key;
import dev.cauegallizzi.annotation.Table;

import java.math.BigDecimal;

@Table("tb_product")
public class Product {

    @Column(dbName = "id", setJavaName = "setId")
    private Long id;

    @Key("getCode")
    @Column(dbName = "code", setJavaName = "setCode")
    private String code;

    @Column(dbName = "name", setJavaName = "setName")
    private String name;

    @Column(dbName = "description", setJavaName = "setDescription")
    private String description;

    @Column(dbName = "value", setJavaName = "setValue")
    private BigDecimal value;

    @Column(dbName = "stock_quantity", setJavaName = "setStockQuantity")
    private Integer stockQuantity;

    @Column(dbName = "created_at", setJavaName = "setCreatedAt")
    private String createdAt;

    @Column(dbName = "updated_at", setJavaName = "setUpdatedAt")
    private String updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
