package com.fiap.burger.gateway.product.model;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "product")
@Entity
public class ProductJPA extends BaseDomainJPA {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Category category;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Double price;

    public ProductJPA() {
    }

    public ProductJPA(
        Long id,
        Category category,
        String name,
        String description,
        Double price,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt
    ) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductJPA productJPA)) return false;
        return Objects.equals(hashCode(), productJPA.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getCategory(),
            getName(),
            getDescription(),
            getPrice(),
            getCreatedAt(),
            getModifiedAt(),
            getDeletedAt()
        );
    }

    public static ProductJPA toJPA(Product product) {
        return new ProductJPA(
            product.getId(),
            product.getCategory(),
            product.getName(),
            product.getDescription(),
            product.getValue(),
            product.getCreatedAt(),
            product.getModifiedAt(),
            product.getDeletedAt()
        );
    }

    public Product toEntity() {
        return new Product(id, category, name, description, price, createdAt, modifiedAt, deletedAt);
    }
}