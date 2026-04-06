package dev.cauegallizzi.domain.jpa;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_sale")
public class SaleJpa {

    public enum SaleStatus {
        PENDING, COMPLETED, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(
            name = "id_customer_fk",
            foreignKey = @ForeignKey(name = "fk_sale_customer"),
            referencedColumnName = "id", nullable = false
    )
    private CustomerJpa customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<ProductQuantityJpa> products;

    private SaleStatus status;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public CustomerJpa getCustomer() { return customer; }
    public void setCustomer(CustomerJpa customer) { this.customer = customer; }

    public Set<ProductQuantityJpa> getProducts() { return products; }
    public void setProducts(Set<ProductQuantityJpa> products) { this.products = products; }

    public SaleStatus getStatus() { return status; }
    public void setStatus(SaleStatus status) { this.status = status; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
