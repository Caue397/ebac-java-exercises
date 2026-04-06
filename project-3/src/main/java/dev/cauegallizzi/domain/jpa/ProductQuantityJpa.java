package dev.cauegallizzi.domain.jpa;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tb_product_quantity")
public class ProductQuantityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private ProductJpa product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_sale_fk",
            foreignKey = @ForeignKey(name = "fk_prod_qtd_sale"),
            referencedColumnName = "id", nullable = false
    )
    private SaleJpa sale;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public ProductJpa getProduct() { return product; }
    public void setProduct(ProductJpa product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public SaleJpa getSale() { return sale; }
    public void setSale(SaleJpa sale) { this.sale = sale; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
